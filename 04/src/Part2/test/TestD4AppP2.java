package Part2.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Part2.src.D4AppP2;

public class TestD4AppP2 {
    @Test
    public void TestCalculateNumberOfWinningCards() {
        assertEquals((int) 30, D4AppP2.calculateNumberOfWinningCards("04/src/Part2/test/input"));
    }

    @Test
    public void TestParseScratchCards() {
        String[] expectedStrings = new String[] {
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
        };

        assertArrayEquals(expectedStrings, D4AppP2.parseScratchCards("04/src/Part2/test/input"));
    }

    @Test
    public void TestParseCardWinners() {
        int[] expectedWinners = new int[] { 41, 48, 83, 86, 17 };
        String exampleScratchCard = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        assertArrayEquals(D4AppP2.parseCardWinners(exampleScratchCard), expectedWinners);
    }

    @Test
    public void TestCalculateWinnings() {
        int[] exampleWinners = new int[] { 41, 48, 83, 86, 17 };
        String exampleScratchCard = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        int expectedWinning = 4;

        assertEquals(expectedWinning, D4AppP2.calculateWinnings(exampleScratchCard, exampleWinners));
    }
}
