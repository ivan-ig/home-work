package com.sbrf.reboot.service.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutorService {

    private final int numberOfThreads;

    private final ExecutorService service;

    public TaskExecutorService(int numberOfThreads) {
        this.service = Executors.newFixedThreadPool(setNumberOfThreads(numberOfThreads));
        this.numberOfThreads = setNumberOfThreads(numberOfThreads);
    }

    public void execute(Task task) {
        for (int i = numberOfThreads; i > 0; i--) {
            service.execute(task);
        }
    }

    public void shutdown() {
        service.shutdown();
    }

    private int setNumberOfThreads(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            numberOfThreads = 4;
        }
        if (numberOfThreads > 8) {
            numberOfThreads = 8;
        }
        return numberOfThreads;
    }
}
