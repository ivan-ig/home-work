package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, чтобы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов чтобы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = new ArrayList<String>(4) {{
            add("Иванов");
            add("Петров");
            add("Сидоров");
        }};

//        if (isPointsEnough)
            students.add(0,"Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет чтобы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {

        Set<Integer> moneyBox = new HashSet<>();
        int i = 0;
        while (i < 10) {
            moneyBox.add(i++);
        }

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги чтобы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        List<Book> bookshelf = new LinkedList<Book>() {{
            add(new Book());
            add(new Book());
            add(new Book());
        }};

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача (свой пример).
     * Школьный реестр хранит списки учеников девятых классов с разделением по литере класса.
     * Ученикм в одном классе уникальны. Каждый класс уникален.
     *
     * Вопрос.
     * Какие коллекции из Collection Framework вы бы использовали для решения задачи?
     *
     * Проинициализируйте schoolRegister, распределите шестерых учеников по трём классам(группам) так,
     * чтобы тест завершился успешно.
     */

    @Test
    public void fillSchoolRegister() {
        class Pupil{
        }

        Set<Pupil> groupA = new HashSet<Pupil>() {{
            add(new Pupil());
        }};
        Set<Pupil> groupB = new HashSet<Pupil>() {{
            add(new Pupil());
            add(new Pupil());
            add(new Pupil());
        }};
        Set<Pupil> groupC = new HashSet<Pupil>() {{
            add(new Pupil());
            add(new Pupil());
        }};

        Map<String, Set<Pupil>> schoolRegister = new HashMap<>();
        schoolRegister.put("Класс 9А", groupA);
        schoolRegister.put("Класс 9В", groupB);
        schoolRegister.put("Класс 9С", groupC);

        int numberOfGroups = schoolRegister.size();
        int totalNumberOfPupils = schoolRegister.values().stream().
                reduce(0, (a, b) -> a + b.size(), Integer::sum);

        assertTrue(numberOfGroups == 3 && totalNumberOfPupils == 6);
    }

}
