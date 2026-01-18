"""
 SMOSConnection simulates a connection to the SMOS server.
 According to the postcondition, the connection is interrupted after deleting a teaching.
"""
class SMOSConnection:
    def __init__(self):
        """
        Constructor initializes the connection as connected.
        """
        self.connected = True  # Simulate initial connection
        print("Connected to SMOS server.")
    def interruptConnection(self):
        """
        Interrupt the connection to the SMOS server.
        This is called after a teaching is deleted.
        """
        if self.connected:
            self.connected = False
            # In a real application, this would close network connections, release resources, etc.
            print("Connection to SMOS server interrupted.")
    def isConnected(self):
        """
        Check if the connection is currently active.
        @return true if connected, false otherwise
        """
        return self.connected