package Part1.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D4App {
    public static void main(String[] args) {
        ArrayList<String> scratchCards = parseCards("04/input");
        Integer total = 0;

        for (String scratchCard : scratchCards) {
            Set<Integer> winningNumbers = parseCardWinners(scratchCard);
            total += calculateWinnings(scratchCard, winningNumbers);
        }

        System.out.println(total);
    }

    public static ArrayList<String> parseCards(String inputFile) {
        ArrayList<String> scratchCards = new ArrayList<String>();
        try (Scanner file = new Scanner(new File(inputFile))) {
            while (file.hasNext()) {
                scratchCards.add(file.nextLine());
            }
        } catch (Exception e) {
        }

        return scratchCards;
    }

    public static Set<Integer> parseCardWinners(String scratchCards) {

        HashSet<Integer> winningNumbers = new HashSet<Integer>();

        String winningNumbersString = scratchCards.split("\\:")[1].split("\\|")[0];

        Matcher matches = Pattern.compile("\\d+").matcher(winningNumbersString);

        while (matches.find()) {
            winningNumbers.add(Integer.parseInt(matches.group(0)));
        }

        return winningNumbers;
    }

    public static Integer calculateWinnings(String scratchCard, Set<Integer> winningNumbers) {
        String myNumbers = scratchCard.split("\\:")[1].split("\\|")[1];
        Matcher matches = Pattern.compile("\\d+").matcher(myNumbers);

        Integer numberOfMatches = 0;

        while (matches.find()) {
            numberOfMatches += (winningNumbers.contains(Integer.parseInt(matches.group(0)))) ? 1 : 0;
        }

        if (numberOfMatches == 0) {
            return 0;
        }

        return ((int) Math.pow(2, numberOfMatches - 1));
    }
}
