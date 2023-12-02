package Part1.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Part1.src.App;
import Part1.src.CubeSet;
import Part1.src.Game;

public class TestApp {

    @Test
    public void Test() {
        Game game = new Game() {
            {
                maximumCube = new CubeSet(12, 13, 14);
            }
        };

        assertEquals((Integer) 8, App.AccumulateGames("02/src/Part1/test/input", game));
    }
}
