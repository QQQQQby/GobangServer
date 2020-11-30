package server;

import io.InputThread;
import io.OutputThread;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ClientHandler extends Thread {

    Socket connection;
    Queue<String> inputQueue, outputQueue;

    public ClientHandler(Socket connection) throws IOException {
        this.connection = connection;
        inputQueue = new LinkedList<>();
        outputQueue = new LinkedList<>();
        new InputThread(connection.getInputStream(), inputQueue).start();
        new OutputThread(connection.getOutputStream(), outputQueue).start();
    }

    @Override
    public void run() {
        super.run();
    }
}
