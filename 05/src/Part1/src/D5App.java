package Part1.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Predicate;

public class D5App {
    public static void main(String[] args) {
        long[] seeds;
        ArrayList<ArrayList<D5Map>> maps = new ArrayList<>();

        try (Scanner file = new Scanner(new File("05/input"))) {
            seeds = Arrays
                    .asList(file.nextLine().split(":")[1].split(" "))
                    .stream()
                    .filter(Predicate.not(String::isEmpty))
                    .mapToLong(Long::parseLong)
                    .toArray();

            while (file.hasNextLine()) {
                String row = file.nextLine();

                if (row.contains(":")) {
                    ArrayList<D5Map> subMap = new ArrayList<>();

                    while (file.hasNextLine()) {
                        row = file.nextLine();
                        if (row.isEmpty() || row.isBlank()) {
                            break;
                        }
                        subMap.add(new D5Map(row));
                    }

                    maps.add(subMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        ArrayList<Long> locations = new ArrayList<>();

        for (long seed : seeds) {
            for (ArrayList<D5Map> subMaps : maps) {
                long destination = seed;
                for (D5Map map : subMaps) {
                    if (map.inRange(seed)) {
                        destination = map.calculateDestination(seed);
                    }

                    if (destination != seed) {
                        break;
                    }
                }
                seed = destination;
            }

            locations.add(seed);
        }

        System.out.println(Collections.min(locations));
    }
}

// seeds:
// seed-to-soil map:
// soil-to-fertilizer map:
// fertilizer-to-water map:
// water-to-light map:
// light-to-temperature map:
// temperature-to-humidity map:
// humidity-to-location map:
