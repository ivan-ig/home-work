package com.sbrf.reboot.service;

import java.time.Duration;
import java.util.concurrent.*;

public class SomeService {

    private final ReportService reportService;

    public SomeService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void doSomething() throws ExecutionException, InterruptedException, TimeoutException {

        final Duration timeout = Duration.ofSeconds(5);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        final Future<String> handler = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                // Реализуйте отправку отчета используя CompletableFuture
                CompletableFuture<String> reportResult = CompletableFuture.
                        supplyAsync(() -> reportService.sendReport("Отправляю отчет"), executor);

                //какой то код..
                Thread.sleep(Duration.ofSeconds(3).toMillis());

                if (reportResult.get().equals("SUCCESS")) {
                    System.out.println("Отчет отправлен успешно");
                }

                return "some return";
            }
        });

        handler.get(timeout.toMillis(), TimeUnit.MILLISECONDS);

        executor.shutdownNow();
    }
}
