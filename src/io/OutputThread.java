package io;

import java.io.OutputStream;
import java.util.Queue;

public class OutputThread extends Thread{
    public OutputThread(OutputStream outputStream, Queue<String> outputQueue) {
    }

    @Override
    public void run() {
        super.run();
    }
}
