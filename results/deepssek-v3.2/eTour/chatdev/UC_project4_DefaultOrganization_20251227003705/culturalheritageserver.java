'''
Mock server for Cultural Heritage Search System
Simulates server-side processing with network handling
'''
import java.io.*;
import java.net.*;
import java.util.List;
public class CulturalHeritageServer {
    private ServerSocket serverSocket;
    private SearchService searchService;
    private boolean running;
    public CulturalHeritageServer() {
        this.searchService = new SearchService();
        this.running = false;
    }
    public void start() {
        try {
            serverSocket = new ServerSocket(8080);
            running = true;
            System.out.println("Cultural Heritage Server started on port 8080");
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> handleClient(clientSocket)).start();
                } catch (IOException e) {
                    if (running) {
                        System.err.println("Error accepting client connection: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port 8080: " + e.getMessage());
        }
    }
    private void handleClient(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                String[] params = parseRequest(request);
                if (params != null && params.length == 4) {
                    List<CulturalObject> results = searchService.search(params[0], params[1], params[2], params[3]);
                    StringBuilder response = new StringBuilder();
                    for (CulturalObject obj : results) {
                        response.append(String.format("%s|%s|%s|%s|%d|%s;",
                            obj.getId(), obj.getName(), obj.getType(), 
                            obj.getLocation(), obj.getYear(), obj.getDescription()));
                    }
                    out.println(response.toString());
                } else {
                    out.println("ERROR: Invalid request format");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
    private String[] parseRequest(String request) {
        if (request == null || request.trim().isEmpty()) {
            return null;
        }
        String[] params = request.split("\\|", 4);
        if (params.length != 4) {
            return null;
        }
        return params;
    }
    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error stopping server: " + e.getMessage());
        }
        System.out.println("Cultural Heritage Server stopped");
    }
    public boolean isRunning() {
        return running;
    }
}