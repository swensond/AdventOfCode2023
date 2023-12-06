package Part2.src;

import java.io.File;
import java.util.Scanner;
import java.util.stream.LongStream;

public class D6AppP2 {
    public static void main(String[] args) {
        Long time;
        Long distance;

        try (Scanner file = new Scanner(new File("06/input"))) {
            time = Long.parseLong(file.nextLine().split("\\:")[1].replaceAll(" ", ""));
            distance = Long.parseLong(file.nextLine().split("\\:")[1].replaceAll(" ", ""));
            Long possibilities = LongStream
                    .range(0, time)
                    .filter((timeHeld) -> (timeHeld * (time - timeHeld)) > distance)
                    .count();
            System.out.println(possibilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
