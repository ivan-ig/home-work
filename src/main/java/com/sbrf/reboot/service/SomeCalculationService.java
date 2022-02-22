package com.sbrf.reboot.service;

import lombok.SneakyThrows;

import java.time.Duration;
import java.util.Random;

public class SomeCalculationService {

    public int generateSomeNumber() {

        return Math.floorDiv(new Random().nextInt(), 1316);
    }

    public int multiplySomeNumber() {

        return Math.multiplyExact(13, 16);
    }

    @SneakyThrows
    public void sleepOneSecond() {
        Thread.sleep(Duration.ofSeconds(1).toMillis());
    }
}
