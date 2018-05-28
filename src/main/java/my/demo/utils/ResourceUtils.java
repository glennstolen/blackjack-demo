package my.demo.utils;


import my.demo.app.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceUtils {

    public static List<Card> getCardsFromJsonFile(String fileName) {

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonData =  (JSONObject) parser.parse(
                    new FileReader(fileName));

            List<Card> cards = new ArrayList<>();
            JSONArray cardList = (JSONArray) jsonData.get("cards");
            Iterator<JSONObject> iterator = cardList.iterator();
            while (iterator.hasNext()) {
                JSONObject card = iterator.next();
                String name = (String) card.get("suite");
                String rank = (String) card.get("rank");
                Long value = (Long)card.get("value");
                cards.add(new Card(name, rank, value.intValue()));
            }

            return cards;

        } catch (IOException | ParseException e) {
           throw new RuntimeException("Error reading file " + fileName+ " occurred", e);
        }
    }


    public static List<Card> getCardsFromTokenizedFile(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            List<String> lines = stream
                    .collect(Collectors.toList());

            List<String> items = new ArrayList<>();
            for (String line : lines) {
                items.addAll(tokenizer(line));
            }

            List<Card> cards = new ArrayList<>();
            for (String item : items) {
                int length = item.length();
                String rank = getRank(item, length);
                cards.add(new Card(item.substring(0,1), rank, getValueOfCard(rank)));
            }

           return cards;

        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName+ " occurred", e);
        }
    }

    private static int getValueOfCard(String rank) {
        switch (rank) {
            case "J":
            case "Q":
            case "K": return 10;
            case "A": return 11;
            default: return Integer.parseInt(rank);
        }
    }

    private static String getRank(String item, int length) {
        return item.substring(1, length);
    }

    /*
    public static String getValueOfCard(int value) {
        switch (value) {
            case 10:
            case 11:
        }

    }
*/
    private static Collection<String> tokenizer(String line) {
        return Collections.list(new StringTokenizer(line, ","))
                .stream()
                .map(token -> ((String) token).trim())
                .collect(Collectors.toList());
    }
}
