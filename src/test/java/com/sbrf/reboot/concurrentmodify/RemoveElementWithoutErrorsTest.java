package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    private List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
    }

    @AfterEach
    public void reset() {
        list.clear();
    }

    @Test
    public void successConcurrentModificationException() {

        assertThrows(ConcurrentModificationException.class, () -> {
            
            for (Integer integer : list) {
                list.remove(1);
            }
            
        });

    }

    @Test
    public void successRemoveElementWithoutErrors() {

        list.removeIf(e -> list.indexOf(e) == 1);

        assertEquals(2, list.size());
    }

    @Test
    public void successRemoveElementWithoutErrorsUsingLegacyMethod() {

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (list.indexOf(element) == 1) {
                iterator.remove();
                break;
            }
        }

        assertEquals(2, list.size());
    }

    @Test
    public void  successRemoveElementWithoutErrorsUsingCopy() {
        ArrayList<Integer> listCopy = new ArrayList<>(list);

        for (Integer i : listCopy) {
            if (Integer.valueOf(2).equals(i)) {
                list.remove(i);
            }
        }

        assertEquals(2, list.size());
    }

}
