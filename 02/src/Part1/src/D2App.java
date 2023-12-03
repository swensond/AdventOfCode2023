package Part1.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class D2App {
    public static void main(String[] args) {
        Game game = new Game() {
            {
                maximumCube = new CubeSet(12, 13, 14);
            }
        };

        System.out.println(AccumulateGames("02/input", game));
    }

    public static Integer AccumulateGames(String inputFile, Game game) {
        Integer total = 0;

        try (Scanner file = new Scanner(new File(inputFile))) {
            while (file.hasNext()) {
                String gameResult = file.nextLine();
                Integer gameId = game.parseGameId(gameResult);
                ArrayList<CubeSet> cubes = game.parseCubes(gameResult);

                if (game.validate(cubes)) {
                    total += gameId;
                }
            }
        } catch (Exception e) {
        }

        return total;
    }
}
