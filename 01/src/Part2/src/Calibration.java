package Part2.src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calibration {
    private static final Map<String, Integer> WordIntMap = new HashMap<String, Integer>() {
        {
            put("one", 1);
            put("two", 2);
            put("three", 3);
            put("four", 4);
            put("five", 5);
            put("six", 6);
            put("seven", 7);
            put("eight", 8);
            put("nine", 9);
        }
    };

    public static void main(String[] args) throws IOException {
        Calibration c = new Calibration();

        System.out.println(c.calculate("01/input"));
    }

    public Integer calculate(String filename) throws IOException {
        Scanner file = new Scanner(new File(filename));

        Pattern validDigitWords = Pattern.compile(String.join("|", WordIntMap.keySet()) + "|\\d");

        ArrayList<Integer> calibrationInputs = new ArrayList<Integer>();

        while (file.hasNext()) {
            String s = file.next();

            Matcher matches = validDigitWords.matcher(s);

            ArrayList<Integer> calibrationValues = new ArrayList<Integer>();

            if (matches.find()) {
                do {
                    String match = matches.group(0);

                    if (WordIntMap.containsKey(match)) {
                        calibrationValues.add(WordIntMap.get(match));
                    } else {
                        calibrationValues.add(Integer.parseInt(match));
                    }

                } while (matches.find(matches.start(0) + 1));
            }

            Integer calibrationValue = Integer.parseInt("" + calibrationValues.getFirst() + calibrationValues.getLast());

            calibrationInputs.add(calibrationValue);
        }

        file.close();

        Integer calibratedValue = calibrationInputs.stream().reduce(0, (a, b) -> a + b);

        return calibratedValue;
    }
}
