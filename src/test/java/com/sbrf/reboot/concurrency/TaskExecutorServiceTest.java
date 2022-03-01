package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.concurrency.Task;
import com.sbrf.reboot.service.concurrency.TaskExecutorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TaskExecutorServiceTest {

    @Test
    public void successRunMultithreading() throws InterruptedException {

        Task task = Mockito.mock(Task.class);
        CountDownLatch latch = new CountDownLatch(2);

        TaskExecutorService taskExecutorService = new TaskExecutorService(2);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());
        verify(task, times(2)).run();

        taskExecutorService.shutdown();
    }

    @Test
    public void successRunZeroThreads() {
        Task task = Mockito.mock(Task.class);

        TaskExecutorService taskExecutorService = new TaskExecutorService(0);

        taskExecutorService.execute(task);

        verify(task, atMost(4)).run();

        taskExecutorService.shutdown();
    }

    @Test
    public void successRunOneMlnThreads() {
        Task task = Mockito.mock(Task.class);
        int oneMln = 1_000_000;

        TaskExecutorService taskExecutorService = new TaskExecutorService(oneMln);

        taskExecutorService.execute(task);

        verify(task, atMost(8)).run();

        taskExecutorService.shutdown();
    }
}