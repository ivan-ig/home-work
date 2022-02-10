package com.sbrf.reboot.streams;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {
    /*
     * Отсортируйте коллекцию integers по возрастанию. Используйте Stream.
     */
    @Test
    public void sortedListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(3, 6, 8, 9);

        List<Integer> actualIntegers = integers.stream().sorted().collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);
    }

    /*
     * Отфильтруйте коллекцию integers.
     * В коллекции должны остаться только те числа, которые делятся без остатка на 2.  Используйте Stream.
     */
    @Test
    public void filteredListStream() {
        List<Integer> integers = Arrays.asList(6, 9, 8, 3);

        List<Integer> expectedIntegers = Arrays.asList(6, 8);

        List<Integer> actualIntegers = integers.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);

    }

    /*
     * Отфильтруйте и отсортируйте коллекцию books.
     * Получите коллекцию, в которой будут только книги от автора "Maria", отсортированные по цене.
     * Используйте Stream.
     */
    @AllArgsConstructor
    @EqualsAndHashCode
    class Book {
        private String name;
        private String author;
        private BigDecimal price;
    }

    @Test
    public void sortedAndFilteredBooks() {
        List<Book> books = Arrays.asList(
                new Book("Trees", "Maria", new BigDecimal(900)),
                new Book("Animals", "Tom", new BigDecimal(500)),
                new Book("Cars", "John", new BigDecimal(200)),
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Flowers", "Tom", new BigDecimal(700))
        );

        List<Book> expectedBooks = Arrays.asList(
                new Book("Birds", "Maria", new BigDecimal(100)),
                new Book("Trees", "Maria", new BigDecimal(900))

        );

        List<Book> actualBooks = books.stream()
                .filter(book -> "Maria".equals(book.author))
                .sorted(Comparator.comparing(book -> book.price))
                .collect(Collectors.toList());

        assertEquals(expectedBooks, actualBooks);

    }

    /*
     * Получите измененную коллекцию contracts.
     * Получите коллекцию, в которой будет тот же набор строк, только у каждой строки появится префикс "M-".
     * Используйте Stream.
     */
    @Test
    public void modifiedList() {
        List<String> contracts = Arrays.asList("NCC-1-CH", "NCC-2-US", "NCC-3-NH");

        List<String> expectedContracts = Arrays.asList("M-NCC-1-CH", "M-NCC-2-US", "M-NCC-3-NH");

        List<String> actualContracts = contracts.stream().map("M-"::concat).collect(Collectors.toList());

        assertEquals(expectedContracts, actualContracts);

    }

    /*
     * Ограничить коллекцию integers четырьмя элементами и найти минимальный.
     * Должен быть возвращен элемент, имеющий минимальное значение. Используйте Stream.
     */
    @Test
    public void limitedAndMinimumFound() {
        List<Integer> integers = Arrays.asList(6, 0, 9, 8, -3, 14, -7, 1, 6);

        int expectedInt = 0;

        int actualIntegers = integers.stream().limit(4)
                .min(Integer::compare)
                .orElse(Integer.MIN_VALUE);

        assertEquals(expectedInt, actualIntegers);

    }

    /*
     * Найти среднее арифметическое заданного диапазона значений.
     * Используйте Stream.
     */
    @Test
    public void getAverageOfRange() {
        OptionalDouble result = IntStream.range(7, 21).average();

        double expectedDouble = 13.5;

        if (result.isPresent()) {
            assertEquals(expectedDouble, result.getAsDouble());
        }
    }

    /*
     * Получить из заданной коллекции новую, в которой каждый следующий элемент равен половине предыдущего.
     * Для коллекции вида   {Ni, N(i+1), N(i+2), ... N(i+n)}
     * должна быть возвращена коллекция вида:
     *                      {Ni, Ni/2, N(i+1), N(i+1)/2, N(i+2), N(i+2)/2, ... N(i+n), N(i+n)/2}
     * где N - элемент исходной коллекции, i - индекс элемента.
     * Используйте flatMap (Stream API)
     */
    @Test
    public void modifyListWithFlatMap() {
        List<Integer> integers = Arrays.asList(6, 8, 10, 12);

        List<Integer> expectedIntegers = Arrays.asList(6, 3, 8, 4, 10, 5, 12, 6);

        List<Integer> actualIntegers = integers.stream()
                .flatMap((e) -> Stream.of(e, e/2))
                .collect(Collectors.toList());

        assertEquals(expectedIntegers, actualIntegers);
    }
}