package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class EqualsHashCodeTest {

     class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {

        /*
        Транзитивность: если с1 равен с2, а с2 равен с3, то с1 должен быть равен с3.
        Обеспечивается реализацией других контрактов:
          - рефлексивность: с1==с1==с1 - выполняется
          - определенность и симметричность -> устанавливаются случаи невыполнения:
              может выполняться только, если у всех объектов есть ссылки и эти объекты одного класса
          - согласованность: устанавливаются случаи выполнения
        */

            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
                return true;

            //Определенность: сравниваемые объекты должны быть определены
                // (транзитивность: не выполняется, если у одного из объектов нет ссылки)
            if (o == null ||
                    //Симметричность: объекты должны быть равны вне зависимости от порядка сравнивания.
                    //Для этого они должны быть одного класса
                        // (транзитивность: не выполняется, если объекты разных классов)
                    o.getClass() != this.getClass()) {
                return false;
            }
            Car other = (Car) o;
            //Согласованность: если поля не менялись, объекты должны быть по-прежнему равны
            return other.maxSpeed == maxSpeed &&
                    (Objects.equals(other.model, model)) &&
                    Objects.equals(other.color, color) &&
                    Objects.equals(other.releaseDate,releaseDate);
        }


        /*
        1.  Если поля объекта не изменялись, метод возвращает одинаковое
            значение вне зависимости от количества его вызовов.
        2.  Если объекты равны, метод возвращает одно и то же значение
            для каждого из них.
        3.  Если объекты не равны, то значения, возвращаемые методом,
            с некоторой вероятностью тоже не равны.
        */
        @Override
        public int hashCode() {

            final int multiplier = 27;
            int resultHashCode = 1;
            resultHashCode = resultHashCode * multiplier + model.hashCode();
            resultHashCode = resultHashCode * multiplier + color.hashCode();
            resultHashCode = resultHashCode * multiplier + releaseDate.hashCode();
            resultHashCode = resultHashCode * multiplier + maxSpeed;

            return resultHashCode;
        }
     }


    @Test
    public void checkReflexivity() {
        Car car1 = new Car();
        car1.model = "BMW";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 15;

        Assertions.assertTrue(car1.equals(car1));
    }

    @Test
    public void checkCertainty() {
         Car car1 = new Car();

         Assertions.assertFalse(car1.equals(null));
    }

    @Test
    public void checkConsistent() throws InterruptedException {
        Car car1 = new Car();
        car1.model = "BMW";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 15;

        Car car2 = new Car();
        car2.model = "BMW";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 15;

        boolean equalityBeforeChanges = car1.equals(car2);
        Thread.sleep(1000);
        boolean equalityAfterChanges = car1.equals(car2);

        Assertions.assertTrue(equalityBeforeChanges && equalityAfterChanges);
    }

    @Test
    public void failCheckConsistent() {
        Car car1 = new Car();
        car1.model = "BMW";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 15;

        Car car2 = new Car();
        car2.model = "BMW";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 15;

        boolean equalityBeforeChanges = car1.equals(car2);
        car2.color = "red";
        boolean equalityAfterChanges = car1.equals(car2);

        Assertions.assertFalse(equalityBeforeChanges && equalityAfterChanges);
    }

    @Test
    public void  checkSymmetry() {
        Car car1 = new Car();
        car1.model = "BMW";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 15;

        Car car2 = new Car();
        car2.model = "BMW";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 15;

        Assertions.assertTrue(car1.equals(car2) && car2.equals(car1));
    }

    @Test
    public void checkTransitivity() {
        Car car1 = new Car();
        car1.model = "BMW";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 15;

        Car car2 = new Car();
        car2.model = "BMW";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 15;

        Car car3 = new Car();
        car3.model = "BMW";
        car3.color = "black";
        car3.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car3.maxSpeed = 15;

        Assertions.assertTrue(car1.equals(car2) && car2.equals(car3) && car1.equals(car3));
    }

    @Test
    public  void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(),car2.hashCode());

    }

    @Test
    public void failEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(),car2.hashCode());

    }


}
