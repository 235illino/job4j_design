package it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] numbers;
    private int point = 0;


    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        while (point < numbers.length - 1) {
            if (numbers[point] % 2 == 0) {
                break;
            } else {
                point++;
            }
        }
        return numbers[point] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}
