package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Queue;

public class SendThread extends Thread {

    private DataOutputStream outputStream;
    private final Queue<String> queue;

    public SendThread(DataOutputStream outputStream, Queue<String> queue) {
        this.outputStream = outputStream;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    if (queue.isEmpty()) continue;
                    outputStream.writeUTF(queue.remove() + "#");
                    Thread.sleep(50);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
