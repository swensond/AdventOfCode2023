package Part1.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class D6App {
    public static void main(String[] args) {
        ArrayList<Entry<Integer, Integer>> races = new ArrayList<>();

        try (Scanner file = new Scanner(new File("06/input"))) {
            int[] time = Stream.of(file.nextLine().split("\\:")[1].split(" ")).filter(Predicate.not(String::isEmpty))
                    .mapToInt(Integer::parseInt).toArray();
            int[] distances = Stream.of(file.nextLine().split("\\:")[1].split(" "))
                    .filter(Predicate.not(String::isEmpty)).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < time.length; i++) {
                races.add(Map.entry(time[i], distances[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Long possibilities = races
                .stream()
                .map((a) -> {
                    Integer time = a.getKey();
                    Integer distance = a.getValue();

                    return IntStream.range(0, time).filter((timeHeld) -> (timeHeld * (time - timeHeld)) > distance).count();
                }).reduce(Long.valueOf(1), Math::multiplyExact);

        System.out.println(possibilities);
    }
}
