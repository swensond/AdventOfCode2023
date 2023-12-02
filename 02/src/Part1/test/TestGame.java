package Part1.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Part1.src.CubeSet;
import Part1.src.Game;

public class TestGame {

    final Game testGame = new Game() {
        {
            maximumCube = new CubeSet(12, 13, 14);
        }
    };

    @Test
    public void TestGame1() {
        assertEquals(true, testGame.validate(new ArrayList<>() {
            {
                add(new CubeSet(4, 0, 3));
                add(new CubeSet(1, 2, 6));
                add(new CubeSet(0, 2, 0));
            }
        }));
    }

    @Test
    public void TestGame2() {
        assertEquals(true, testGame.validate(new ArrayList<>() {
            {
                add(new CubeSet(0, 2, 1));
                add(new CubeSet(1, 3, 4));
                add(new CubeSet(1, 1, 1));
            }
        }));
    }

    @Test
    public void TestGame3() {
        assertEquals(false, testGame.validate(new ArrayList<>() {
            {
                add(new CubeSet(20, 8, 6));
                add(new CubeSet(4, 13, 5));
                add(new CubeSet(1, 5, 0));
            }
        }));
    }

    @Test
    public void TestGame4() {
        assertEquals(false, testGame.validate(new ArrayList<>() {
            {
                add(new CubeSet(3, 1, 6));
                add(new CubeSet(6, 3, 0));
                add(new CubeSet(14, 3, 15));
            }
        }));
    }

    @Test
    public void TestGame5() {
        assertEquals(true, testGame.validate(new ArrayList<>() {
            {
                add(new CubeSet(6, 3, 1));
                add(new CubeSet(1, 2, 2));
            }
        }));
    }

    @Test
    public void TestParseCubes() {
        ArrayList<CubeSet> exampleSets = new ArrayList<CubeSet>() {
            {
                add(new CubeSet(4, 0, 3));
                add(new CubeSet(1, 2, 6));
                add(new CubeSet(0, 2, 0));
            }
        };

        ArrayList<CubeSet> parsedSets = testGame.parseCubes("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        assertEquals(true, parsedSets.stream()
                .allMatch((CubeSet set) -> exampleSets.stream().anyMatch((CubeSet exSet) -> exSet.equals(set))));
    }

    @Test
    public void TestParseGameId() {
        assertEquals((Integer) 1, testGame.parseGameId("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"));
    }
}
