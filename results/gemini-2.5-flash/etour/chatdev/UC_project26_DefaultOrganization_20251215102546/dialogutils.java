'''
Utility class for displaying various types of JOptionPane dialogs.
Centralizes dialog creation for consistency and ease of use.
'''
package com.chatdev.gui.utils;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
public class DialogUtils {
    /**
     * Displays an error message dialog.
     * @param parentComponent The parent component for the dialog, or null if none.
     * @param message The error message to display.
     * @param title The title of the error dialog.
     */
    public static void showError(Component parentComponent, String message, String title) {
        JOptionPane.showMessageDialog(parentComponent,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays an informational message dialog.
     * @param parentComponent The parent component for the dialog, or null if none.
     * @param message The message to display.
     * @param title The title of the informational dialog.
     */
    public static void showMessage(Component parentComponent, String message, String title) {
        JOptionPane.showMessageDialog(parentComponent,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Displays a confirmation dialog and returns the user's choice.
     * @param parentComponent The parent component for the dialog, or null if none.
     * @param message The confirmation message to display.
     * @param title The title of the confirmation dialog.
     * @return JOptionPane.YES_OPTION if the user confirms, JOptionPane.NO_OPTION if not,
     *         or JOptionPane.CLOSED_OPTION if the dialog is closed.
     */
    public static int showConfirmation(Component parentComponent, String message, String title) {
        return JOptionPane.showConfirmDialog(parentComponent,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }
}