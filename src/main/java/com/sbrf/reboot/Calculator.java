package com.sbrf.reboot;

public class Calculator {

    public int getAddition(int firstOperand, int secondOperand) {
        return Math.addExact(firstOperand, secondOperand);
    }

    public int getSubtraction(int firstOperand, int secondOperand) {
        return Math.subtractExact(firstOperand, secondOperand);
    }

    public int getMultiplication(int firstOperand, int secondOperand) {
        return Math.multiplyExact(firstOperand, secondOperand);
    }

    public int getDivision(int firstOperand, int secondOperand) {
        int result;
        if (secondOperand != 0) {
            result = Math.floorDiv(firstOperand, secondOperand);
            if (firstOperand == Integer.MIN_VALUE && secondOperand == -1) {
                result *= -1;
            }
        } else {
            result = Integer.MAX_VALUE;
        }
        return result;
    }

    public double getAverageOfFour(int firstOperand, int secondOperand,
                                   int thirdOperand, int fourthOperand) {
        return (firstOperand + secondOperand + thirdOperand + fourthOperand) / 4.0;
    }

    public double getExponentiation(double base, double degree) {
        return Math.pow(base, degree);
    }

    public int getFactorialOfPositiveNumberOrZero(int operand) {
        if (operand < 0) {
            return 0;
        }

        int result = 1;
        if (operand == 0) {
            return result;
        }

        for (int i = 1; i <= operand; i++) {
            result *= i;
        }
        return result;
    }
}
