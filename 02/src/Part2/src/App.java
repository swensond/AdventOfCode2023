package Part2.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Part1.src.CubeSet;
import Part1.src.Game;

public class App {
    public static void main(String[] args) {
        Game game = new Game() {
            {
                maximumCube = new CubeSet(0, 0, 0);
            }
        };

        System.out.println(PowerOfSetsMininum("02/input", game));
    }

    public static Integer PowerOfSetsMininum(String inputFile, Game game) {
        Integer total = 0;

        try (Scanner file = new Scanner(new File(inputFile))) {
            while (file.hasNext()) {
                String gameResult = file.nextLine();
                ArrayList<CubeSet> cubes = game.parseCubes(gameResult);

                CubeSet mininumSet = cubes.stream().reduce(new CubeSet(0, 0, 0), (a, b) -> {
                    if (a.red < b.red) {
                        a.red = b.red;
                    }
                    if (a.blue < b.blue) {
                        a.blue = b.blue;
                    }
                    if (a.green < b.green) {
                        a.green = b.green;
                    }

                    return a;
                });

                total += mininumSet.red * mininumSet.blue * mininumSet.green;
            }
        } catch (Exception e) {
        }

        return total;
    }
}
