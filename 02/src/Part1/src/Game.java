package Part1.src;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    public CubeSet maximumCube;

    public boolean validate(ArrayList<CubeSet> cubeSets) {
        return cubeSets.stream().allMatch(maximumCube::greaterThanOrEqual);
    }

    public Integer parseGameId(String game) {
        Matcher testGameIdMatcher = Pattern.compile("Game (\\d+):").matcher(game);

        while (testGameIdMatcher.find()) {
            return Integer.parseInt(testGameIdMatcher.group(1));
        }

        return 0;
    }

    public ArrayList<CubeSet> parseCubes(String game) {
        Pattern testCubePattern = Pattern.compile("(?<blue>(\\d+) blue)|(?<red>(\\d+) red)|(?<green>(\\d+) green)");

        ArrayList<CubeSet> cubeSets = new ArrayList<CubeSet>();

        try (Scanner sets = new Scanner(game).useDelimiter(";")) {
            while (sets.hasNext()) {
                String set = sets.next();
                Matcher matches = testCubePattern.matcher(set);

                Map<String, Integer> groups = matches.namedGroups();

                CubeSet cubeSet = new CubeSet(0, 0, 0);

                while (matches.find()) {
                    for (Entry<String, Integer> group : groups.entrySet()) {
                        if (matches.group(group.getKey()) != null) {
                            cubeSet.setNumber(Integer.parseInt(matches.group(group.getValue() + 1)), group.getKey());
                        }
                    }
                }

                cubeSets.add(cubeSet);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return cubeSets;
    }
}
