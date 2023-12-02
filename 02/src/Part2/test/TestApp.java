package Part2.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Part1.src.CubeSet;
import Part1.src.Game;
import Part2.src.App;

public class TestApp {
    @Test
    public void TestPowerOfSetsMininum() {
        Game game = new Game() {
            {
                maximumCube = new CubeSet(0, 0, 0);
            }
        };

        assertEquals((Integer) 2286, App.PowerOfSetsMininum("02/src/Part2/test/input", game));
    }
}
