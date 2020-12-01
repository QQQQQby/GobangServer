package server;

import io.ReceiveThread;
import io.SendThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ClientHandler extends Thread {

    Socket connection;
    final Queue<String> inputQueue, outputQueue;

    public ClientHandler(Socket connection) throws IOException {
        this.connection = connection;
        inputQueue = new LinkedList<>();
        outputQueue = new LinkedList<>();
        DataInputStream inputStream = new DataInputStream(connection.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        new ReceiveThread(inputStream, inputQueue).start();
        new SendThread(outputStream, outputQueue).start();
    }

    public void send(String message) {
        synchronized (outputQueue) {
            outputQueue.add(message);
        }
    }

    @Override
    public void run() {
    }
}
