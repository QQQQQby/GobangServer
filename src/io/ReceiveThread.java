package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Queue;

public class ReceiveThread extends Thread {

    private DataInputStream inputStream;
    private final Queue<String> queue;
    private StringBuilder stringBuilder;

    public ReceiveThread(DataInputStream inputStream, Queue<String> queue) {
        this.inputStream = inputStream;
        this.queue = queue;
        stringBuilder = new StringBuilder();
    }

    @Override
    public void run() {
        String receivedString;
        int i;
        boolean hasHash = false;
        while (true) {
            try {
                receivedString = inputStream.readUTF();
                for (i = 0; i < receivedString.length(); i++) {
                    if (receivedString.charAt(i) == '#') {
                        stringBuilder.append(receivedString, 0, i);
                        synchronized (queue) {
                            queue.add(stringBuilder.toString());
                        }
                        stringBuilder = new StringBuilder(receivedString.substring(i + 1));
                        hasHash = true;
                        break;
                    }
                }
                if (!hasHash) stringBuilder.append(receivedString);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
