package com.example.refreshment_agency.util;

import java.util.Scanner;

/**
 * Utility class for reading user input from the console.
 * This class wraps a Scanner object to provide convenient methods for reading different types of input.
 */
public class InputReader {
    private final Scanner scanner;

    /**
     * Constructs an InputReader and initializes a Scanner to read from standard input.
     */
    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of text from the console.
     *
     * @return The line read from the console.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Reads a line of text from the console after displaying a prompt.
     *
     * @param prompt The message to display to the user before reading input.
     * @return The line read from the console.
     */
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Reads an integer from the console.
     *
     * @return The integer read from the console.
     * @throws NumberFormatException If the input is not a valid integer.
     */
    public int readInt() throws NumberFormatException {
        String input = readLine();
        return Integer.parseInt(input);
    }

    /**
     * Reads an integer from the console after displaying a prompt.
     *
     * @param prompt The message to display to the user before reading input.
     * @return The integer read from the console.
     * @throws NumberFormatException If the input is not a valid integer.
     */
    public int readInt(String prompt) throws NumberFormatException {
        System.out.print(prompt);
        String input = readLine();
        return Integer.parseInt(input);
    }

    /**
     * Closes the underlying Scanner object to release system resources.
     * It's important to call this method when the InputReader is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}