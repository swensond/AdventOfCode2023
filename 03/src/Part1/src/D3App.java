package Part1.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.util.Set;

public class D3App {
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
        System.out.println(SumOfEngineParts("03/input"));
    }

    public static Integer SumOfEngineParts(String inputFile) {
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

                if (Character.isDigit(character) || character == '.') {
                    continue;
                }

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

                        total += Integer.parseInt(partNumber.toString());
                    }
                }
            }
        }

        return total;
    }
}
