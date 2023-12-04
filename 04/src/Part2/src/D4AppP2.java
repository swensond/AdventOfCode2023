package Part2.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class D4AppP2 {
    public static void main(String[] args) {
        System.out.println(calculateNumberOfWinningCards("04/input"));
    }

    public static int calculateNumberOfWinningCards(String inputFile) {
        String[] scratchCards = parseScratchCards(inputFile);

        Map<Integer, Integer> cards = new HashMap<Integer, Integer>();

        int index = 1;

        for (String scratchCard : scratchCards) {
            final int iIndex = index;

            int[] winningNumbers = parseCardWinners(scratchCard);

            int numberOfMatches = calculateWinnings(scratchCard, winningNumbers);

            cards.compute(index, (k, v) -> (v == null) ? 1 : v + 1);

            for (int i : IntStream.range(index + 1, index + numberOfMatches + 1).toArray()) {
                cards.compute(i, (k, v) -> ((v == null) ? 0 : v) + cards.get(iIndex));
            }

            index++;
        }

        return cards.values().stream().reduce(0, Integer::sum);
    }

    public static String[] parseScratchCards(String inputFile) {
        ArrayList<String> scratchCards = new ArrayList<String>();

        try (Scanner file = new Scanner(new File(inputFile))) {
            while (file.hasNext()) {
                scratchCards.add(file.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scratchCards.toArray(String[]::new);
    }

    public static int[] parseCardWinners(String scratchCard) {
        return Arrays.asList(scratchCard.split("\\:")[1].split("\\|")[0].split(" "))
                .stream()
                .filter(Predicate.not(String::isEmpty))
                .filter(Predicate.not((s) -> s == " "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int calculateWinnings(String scratchCard, int[] winningNumbers) {
        int[] myNumbers = Arrays.asList(scratchCard.split("\\:")[1].split("\\|")[1].split(" "))
                .stream()
                .filter(Predicate.not(String::isEmpty))
                .filter(Predicate.not((s) -> s == " "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numberOfMatches = 0;

        List<Integer> winningNumbersList = Arrays.stream(winningNumbers).boxed().toList();

        for (int myNumber : myNumbers) {
            numberOfMatches += (winningNumbersList.contains(myNumber)) ? 1 : 0;
        }

        return numberOfMatches;
    }
}