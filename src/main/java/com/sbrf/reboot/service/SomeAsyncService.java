package com.sbrf.reboot.service;

import java.time.Duration;
import java.util.concurrent.*;

public class SomeAsyncService {

    private final SomeCalculationService someCalculationService;

    public SomeAsyncService(SomeCalculationService someCalculationService) {
        this.someCalculationService = someCalculationService;
    }

    public String doCalculation() throws ExecutionException, InterruptedException, TimeoutException {

        final Duration timeout = Duration.ofMillis(3600);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> handler = executor.submit(() -> {

                CompletableFuture<Integer> generator =
                        CompletableFuture.supplyAsync(someCalculationService::generateSomeNumber, executor);

                CompletableFuture<Integer> multiplier =
                        CompletableFuture.supplyAsync(someCalculationService::multiplySomeNumber, executor);

                CompletableFuture<Integer> firstCombination =
                        generator.thenCombineAsync(multiplier, (generationResult, multiplicationResult) -> {
                            //Some generationResult & multiplicationResult calculation...
                            someCalculationService.sleepOneSecond();
                            return 155;
                        }, executor);

                CompletableFuture<Integer> secondCombination =
                        generator.thenCombineAsync(multiplier, (generationResult, multiplicationResult) -> {
                            //Some generationResult & multiplicationResult calculation...
                            someCalculationService.sleepOneSecond();
                            return 194;
                        }, executor);

                return "" + (firstCombination.get() + secondCombination.get());
        });

        String result = handler.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
        executor.shutdownNow();
        return result;
    }

}
