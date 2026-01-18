package com.example.convention;

/**
 * Simple implementation of ETOURServerAdapter.
 * For demonstration, we simulate connection status.
 */
public class ETOURServerAdapterImpl implements ETOURServerAdapter {
    private boolean connectionUp = true;

    public void setConnectionUp(boolean up) {
        this.connectionUp = up;
    }

    @Override
    public boolean checkConnection() {
        return connectionUp;
    }

    @Override
    public void notifyActivation(String conventionId) {
        if (!connectionUp) {
            System.out.println("ERROR: Cannot notify ETOUR server because connection is down.");
            throw new RuntimeException("ETOUR server connection interrupted");
        }
        System.out.println("Notified ETOUR server about activation of convention: " + conventionId);
    }
}