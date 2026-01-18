package com.etour.deletebanner.service;

import com.etour.deletebanner.model.Banner;
import com.etour.deletebanner.model.RefreshmentPoint;
import com.etour.deletebanner.util.NetworkException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Simulates an API client for interacting with the ETOUR server.
 * This class handles fetching refreshment points, banners, and deleting banners.
 */
public class EtourApiClient {

    private static final String BASE_URL = "http://localhost:8080/api"; // Placeholder URL
    private final Gson gson = new Gson();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Simulates fetching a list of refreshment points from the ETOUR server.
     * @return A list of RefreshmentPoint objects.
     * @throws NetworkException if a network error occurs.
     */
    public List<RefreshmentPoint> getRefreshmentPoints() throws NetworkException {
        // In a real application, this would make an actual HTTP GET request.
        // For this simulation, we return dummy data.
        try {
            // Simulate network delay
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NetworkException("Operation interrupted", e);
        }

        List<RefreshmentPoint> points = new ArrayList<>();
        points.add(new RefreshmentPoint("rp1", "Central Park Cafe"));
        points.add(new RefreshmentPoint("rp2", "Beachside Bistro"));
        points.add(new RefreshmentPoint("rp3", "Mountain View Lodge"));
        return points;
    }

    /**
     * Simulates fetching banners associated with a specific refreshment point.
     * @param refreshmentPointId The ID of the refreshment point.
     * @return A list of Banner objects.
     * @throws NetworkException if a network error occurs.
     */
    public List<Banner> getBannersForRefreshmentPoint(String refreshmentPointId) throws NetworkException {
        // In a real application, this would make an actual HTTP GET request.
        // For this simulation, we return dummy data based on the refreshmentPointId.
        try {
            // Simulate network delay
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NetworkException("Operation interrupted", e);
        }

        List<Banner> banners = new ArrayList<>();
        switch (refreshmentPointId) {
            case "rp1":
                banners.add(new Banner("b101", "Summer Sale Banner", "rp1"));
                banners.add(new Banner("b102", "New Menu Banner", "rp1"));
                break;
            case "rp2":
                banners.add(new Banner("b201", "Happy Hour Banner", "rp2"));
                break;
            case "rp3":
                banners.add(new Banner("b301", "Winter Special Banner", "rp3"));
                banners.add(new Banner("b302", "Ski Rental Ad", "rp3"));
                banners.add(new Banner("b303", "Hot Chocolate Promo", "rp3"));
                break;
        }
        return banners;
    }

    /**
     * Simulates deleting a banner from the ETOUR server.
     * @param bannerId The ID of the banner to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws NetworkException if a network error occurs.
     */
    public boolean deleteBanner(String bannerId) throws NetworkException {
        // In a real application, this would make an actual HTTP DELETE request.
        // For this simulation, we always return true for successful deletion.
        try {
            // Simulate network delay
            TimeUnit.MILLISECONDS.sleep(700);
            // Simulate a successful deletion
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NetworkException("Operation interrupted", e);
        }
    }

    // Example of a real HTTP request method (not used in this simulation but for illustration)
    private String executeHttpGet(String url) throws NetworkException {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new NetworkException("HTTP GET request failed with status code: " + statusCode);
            }
        } catch (IOException e) {
            throw new NetworkException("Error executing HTTP GET request to " + url, e);
        }
    }

    // Example of a real HTTP DELETE request method (not used in this simulation but for illustration)
    private boolean executeHttpDelete(String url) throws NetworkException {
        HttpDelete request = new HttpDelete(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            return statusCode >= 200 && statusCode < 300;
        } catch (IOException e) {
            throw new NetworkException("Error executing HTTP DELETE request to " + url, e);
        }
    }
}