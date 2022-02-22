package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.ReportService;
import com.sbrf.reboot.service.SomeAsyncService;
import com.sbrf.reboot.service.SomeCalculationService;
import com.sbrf.reboot.service.SomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.*;

public class CompletableFeatureTest {

    @Test
    public void successCompletableFeature() throws ExecutionException, InterruptedException, TimeoutException {
        ReportService reportService = Mockito.mock(ReportService.class);

        when(reportService.sendReport("Отправляю отчет")).then(e->{

            Thread.sleep(Duration.ofSeconds(3).toMillis());
            return "SUCCESS";
        });

        SomeService someService = new SomeService(reportService);

        someService.doSomething();

        verify(reportService, times(1)).sendReport("Отправляю отчет");
    }

    @Test
    public void successAsyncCompletableFeature() throws ExecutionException, InterruptedException, TimeoutException {
        SomeCalculationService someCalculationService = Mockito.mock(SomeCalculationService.class);

        when(someCalculationService.generateSomeNumber()).then(e -> {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            return 2;
        });

        when(someCalculationService.multiplySomeNumber()).then(e -> {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            return 2;
        });

        doAnswer(e -> {
            Thread.sleep(Duration.ofSeconds(1).toMillis());
            return null;
        }).when(someCalculationService).sleepOneSecond();

        SomeAsyncService someAsyncService = new SomeAsyncService(someCalculationService);

        String actual  = someAsyncService.doCalculation();
        String expected = "349";

        verify(someCalculationService, times(2)).sleepOneSecond();
        Assertions.assertEquals(expected, actual);
    }
}
