package Part1.src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calibration {
    public static void main(String[] args) throws IOException {
        Calibration c = new Calibration();

        System.out.println(c.calculate("01/input"));
    }

    public Integer calculate(String filename) throws IOException {
        Scanner file = new Scanner(new File(filename));

        List<String> inputs = new ArrayList<String>();
        List<Integer> calibrationInputs = new ArrayList<Integer>();

        while (file.hasNext()) {
            inputs.add(file.next());
        }

        file.close();

        Pattern numberRegex = Pattern.compile("\\d");

        for (String s : inputs) {
            Matcher test = numberRegex.matcher(s);
            List<String> calLineinputs = new ArrayList<String>();
            Integer calibrationValue;

            while (test.find()) {
                calLineinputs.add(test.group(0));
            }

            calibrationValue = Integer.parseInt(calLineinputs.getFirst().concat(calLineinputs.getLast()));

            calibrationInputs.add(calibrationValue);
        }

        Integer calibratedValue = calibrationInputs.stream().reduce(0, (a, b) -> a + b);

        return calibratedValue;
    }
}