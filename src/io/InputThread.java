package io;

import java.io.InputStream;
import java.util.Queue;

public class InputThread extends Thread{

    Queue<String> queue;

    public InputThread(InputStream inputStream, Queue<String> queue) {
    }

    @Override
    public void run() {
        super.run();
    }
}
