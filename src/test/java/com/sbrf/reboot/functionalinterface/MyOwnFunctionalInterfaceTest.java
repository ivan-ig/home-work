package com.sbrf.reboot.functionalinterface;

import lombok.Data;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MyOwnFunctionalInterfaceTest {

    static abstract @Data class Employee {
        private @NonNull BigDecimal salary;
        private final BigDecimal bonusRate;
    }

    @FunctionalInterface
    interface BonusCalculationFunction<E extends Employee> {
        BigDecimal calculateBonus(E e);
    }

    static class PaymentCalculator<E extends Employee> {
        public BigDecimal getTotalPayment(E employee, BonusCalculationFunction<E> bonusCalculationFunction) {
            return bonusCalculationFunction.calculateBonus(employee);
        }
    }

    @Test
    public void successCallFunctionInterface() {
        PaymentCalculator<Employee> paymentCalculator = new PaymentCalculator<>();

        BonusCalculationFunction<Employee> bonusCalculationFunction = employee ->
                employee.getSalary().add(employee.getSalary().multiply(employee.getBonusRate()));

        Employee lowRank = new Employee(new BigDecimal(50), new BigDecimal("0.50")) {};
        Employee highRank = new Employee(new BigDecimal(100), new BigDecimal("0.80")) {};

        BigDecimal totalPaymentForLowRankEmployee =
                paymentCalculator.getTotalPayment(lowRank, bonusCalculationFunction);
        BigDecimal totalPaymentForHighRankEmployee =
                paymentCalculator.getTotalPayment(highRank, bonusCalculationFunction);

        Assertions.assertEquals(new BigDecimal("75.00"), totalPaymentForLowRankEmployee);
        Assertions.assertEquals(new BigDecimal("180.00"), totalPaymentForHighRankEmployee);
    }
}
