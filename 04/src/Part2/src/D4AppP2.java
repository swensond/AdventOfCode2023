package Part2.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class D4AppP2 {
    public static void main(String[] args) {
        ArrayList<String> scratchCards = parseCards("04/input");
        int index = 1;
        Map<Integer, Integer> cards = new HashMap<Integer, Integer>();

        for (String scratchCard : scratchCards) {
            Set<Integer> winningNumbers = parseCardWinners(scratchCard);
            Integer numberOfMatches = calculateWinnings(scratchCard, winningNumbers);
            
            cards.put(index, cards.getOrDefault(index, 0) + 1);

            for (int i : IntStream.range(index + 1, index + numberOfMatches + 1).toArray()) {
                int total = cards.getOrDefault(i, 0) + cards.get(index);
                cards.put(i, total);
            }

            index++;
        }

        System.out.println(cards.values().stream().reduce(0, Integer::sum));
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

        return numberOfMatches;
    }
}