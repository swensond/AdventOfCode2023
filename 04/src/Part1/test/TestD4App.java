package Part1.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import Part1.src.D4App;

public class TestD4App {
    @Test
    public void TestCalculateWinnings() {
        HashSet<Integer> winningNumberExamples = new HashSet<Integer>(){{
            add(41);
            add(48);
            add(83);
            add(86);
            add(17);
        }};
        assertEquals((Integer) 8, D4App.calculateWinnings("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",winningNumberExamples));
    }
}
