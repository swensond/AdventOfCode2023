package Part2.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalLong;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import Part1.src.D5Map;

public class D5AppP2 {
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

        for (int i = 0; i < seeds.length; i += 2) {
            System.out.println("Processing seed group");

            OptionalLong location = LongStream
                    .range(seeds[i], seeds[i] + seeds[i + 1])
                    .parallel()
                    .map((seed) -> {
                        for (ArrayList<D5Map> subMaps : maps) {
                            long destination = seed;
                            for (D5Map map : subMaps) {
                                if (map.inRange(destination)) {
                                    destination = map.calculateDestination(destination);
                                }

                                if (destination != seed) {
                                    break;
                                }
                            }
                            seed = destination;
                        }

                        return seed;
                    })
                    .min();

            locations.add(location.getAsLong());
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
