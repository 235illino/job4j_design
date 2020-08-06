package it;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test
    public void setTest() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("first");
        array.add("second");
        array.add("third");
        array.set(1, "newSecond");
        String rsl = "newSecond";
        assertThat(rsl, is(array.get(1)));
    }

    @Test
    public void removeTest() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("first");
        array.add("second");
        array.add("third");
        array.remove(1);
        String rsl = "third";
        assertThat(rsl, is(array.get(1)));
    }


}