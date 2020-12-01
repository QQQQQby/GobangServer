package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String hostname;
    private int port;

    private static Server server;
    private static final String DEFAULT_ADDRESS = "0.0.0.0";
    private static final int DEFAULT_PORT = 34279;

    public synchronized static Server getInstance() {
        if (server == null)
            server = new Server();
        return server;
    }

    private Server() {
        hostname = DEFAULT_ADDRESS;
        port = DEFAULT_PORT;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public synchronized void start() {
        InetSocketAddress address = new InetSocketAddress(hostname, port);
        ServerSocket serverSocket = null;
        // Create socket to wait for connection.
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert serverSocket != null;

        while (!serverSocket.isClosed()) {
            try {
                Socket connection = serverSocket.accept();
                //System.out.println(connection.getInetAddress());
                ClientHandler handler = new ClientHandler(connection);
                handler.setDaemon(true);
                handler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
