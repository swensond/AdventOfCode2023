package Part2.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class D3AppP2 {
    public static ArrayList<Entry<Integer, Integer>> lookupDirections = new ArrayList<Entry<Integer, Integer>>() {
        {
            add(Map.entry(-1, -1));
            add(Map.entry(-1, 0));
            add(Map.entry(-1, 1));
            add(Map.entry(0, 1));
            add(Map.entry(1, 1));
            add(Map.entry(1, 0));
            add(Map.entry(1, -1));
            add(Map.entry(0, -1));
        }
    };

    public static void main(String[] args) {
        System.out.println(SumOfGearRatios("03/input"));
    }

    public static Integer SumOfGearRatios(String inputFile) {
        Integer total = 0;

        Map<Entry<Integer, Integer>, Character> grid = new HashMap<Entry<Integer, Integer>, Character>();
        Set<Entry<Integer, Integer>> seen = new HashSet<Entry<Integer, Integer>>();

        int row = 0;
        int width = 0;

        try (Scanner file = new Scanner(new File(inputFile))) {
            while (file.hasNext()) {
                char[] chars = file.nextLine().toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    grid.put(Map.entry(row, i), chars[i]);
                    width = (i > width) ? i : width;
                }

                row++;
            }
        } catch (Exception e) {
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c <= width; c++) {
                char character = grid.get(Map.entry(r, c));

                if (Character.isDigit(character) || character == '.' || character != '*') {
                    continue;
                }

                ArrayList<Integer> parts = new ArrayList<Integer>();

                for (Entry<Integer, Integer> direction : lookupDirections) {
                    Integer lR = r + direction.getKey();
                    Integer lC = c + direction.getValue();

                    if (lR > row || lR < 0 || lC > width || lC < 0) {
                        continue;
                    }

                    Entry<Integer, Integer> coord = Map.entry(lR, lC);

                    if (Character.isDigit(grid.get(coord)) && !seen.contains(coord)) {
                        Integer lBound = lC;
                        Integer rBound = lC;

                        do {
                            lBound--;
                        } while (lBound >= 0 && Character.isDigit(grid.get(Map.entry(lR, lBound))));

                        lBound++;

                        do {
                            rBound++;
                        } while (rBound <= width && Character.isDigit(grid.get(Map.entry(lR, rBound))));

                        StringBuilder partNumber = new StringBuilder();

                        IntStream.range(lBound, rBound).forEach(x -> {
                            partNumber.append(grid.get(Map.entry(lR, x)));
                            seen.add(Map.entry(lR, x));
                        });

                        parts.add(Integer.parseInt(partNumber.toString()));
                    }
                }

                if (parts.size() == 2) {
                    total += parts.stream().reduce(1, (a, b) -> a * b);
                }
            }
        }

        return total;
    }
}
