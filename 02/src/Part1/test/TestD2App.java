package Part1.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Part1.src.D2App;
import Part1.src.CubeSet;
import Part1.src.Game;

public class TestD2App {

    @Test
    public void Test() {
        Game game = new Game() {
            {
                maximumCube = new CubeSet(12, 13, 14);
            }
        };

        assertEquals((Integer) 8, D2App.AccumulateGames("02/src/Part1/test/input", game));
    }
}
