package se.umu.carl.thirty;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreLogic extends Activity { //håller koll på poängräkninslogiken och spelregler
    int currentScore;
    boolean pointTypeSucceeded = false;
    //bestämer vilken poäng som ska sättas.
    protected void increaseCurrentScore(Spinner spinner, ArrayAdapter<String> adapter) {
        String selectedItem = spinner.getSelectedItem().toString();
        if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
            try {
                switch (selectedItem) {
                    case "Low":
                        addPoint(selectedItem, adapter);
                        break;
                    case "4":
                        addPoint(selectedItem, adapter);
                        break;
                    case "5":
                        addPoint(selectedItem, adapter);
                        break;
                    case "6":
                        addPoint(selectedItem, adapter);
                        break;
                    case "7":
                        addPoint(selectedItem, adapter);
                        break;
                    case "8":
                        addPoint(selectedItem, adapter);
                        break;
                    case "9":
                        addPoint(selectedItem, adapter);
                        break;
                    case "10":
                        addPoint(selectedItem, adapter);
                        break;
                    case "11":
                        addPoint(selectedItem, adapter);
                        break;
                    case "12":
                        addPoint(selectedItem, adapter);
                        break;
                    case "Välj poängtyp": //ta bort detta alternativ ifall när man öppnar listan rensas detta alternativ
                        System.out.println("Du har inte valt någon poängtyp, försök igen");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //lägger till poäng utifrån vad man har valt i listan, bör göras en validering här
    //som kollar att tärningen/tärningar faktiskt finns på spelplanen och kan kombineras för att summera till poängtypen
    protected void addPoint(String selectedItem, ArrayAdapter<String> adapter) {
        switch (selectedItem) {
            case "Low":
                createCombinatedDiceNumbers("Low", 3, adapter);
                break;
            case "4":
                createCombinatedDiceNumbers("4", 4, adapter);
                break;
            case "5":
                createCombinatedDiceNumbers("5", 5, adapter);
                break;
            case "6":
                createCombinatedDiceNumbers("6", 6, adapter);
                break;
            case "7":
                createCombinatedDiceNumbers("7", 7, adapter);
                break;
            case "8":
                createCombinatedDiceNumbers("8", 8, adapter);
                break;
            case "9":
                createCombinatedDiceNumbers("9", 9, adapter);
                break;
            case "10":
                createCombinatedDiceNumbers("10", 10, adapter);
                break;
            case "11":
                createCombinatedDiceNumbers("11", 11, adapter);
                break;
            case "12":
                createCombinatedDiceNumbers("12", 12, adapter);
                break;
        }
        //lägga in någonstans det valet och den poängen man får för att visa slutresultatet.
        ChoicePointResult.choicePoints.put((String) selectedItem, ChoicePointResult.score);
    }

    //kollar vilka kombinationer och tärningar som finns på fältet
    protected void createCombinatedDiceNumbers(String selectedItem, int choicePoint, ArrayAdapter<String> adapter) {
        boolean canbeCombined = false;
        ArrayList<Integer> combinatedDiceNumbers = new ArrayList<>();
        ArrayList<Dice> dices = new ArrayList<>();
        try {
            for (Map.Entry<Integer, ArrayList<Dice>> entry : GlobalDiceNumbers.triesAndDiceNumbers.entrySet()) {
                dices = entry.getValue();
                break;
            }
            //om första tärningen matchar
            if (dices.get(0).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(0).value);
                dices.get(0).value = 0;
            }
            //om andra tärningen matchar
            if (dices.get(1).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(1).value);
                dices.get(1).value = 0;
            }
            //om tredje tärningen matchar
            if (dices.get(2).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(2).value);
                dices.get(2).value = 0;
            }
            //om fjärde tärningen matchar
            if (dices.get(3).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(3).value);
                dices.get(3).value = 0;
            }
            //om femte tärningen matchar
            if (dices.get(4).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(4).value);
                dices.get(4).value = 0;
            }
            //om sista tärningen matchar
            if (dices.get(5).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(5).value);
                dices.get(5).value = 0;
            }
            //alla kombinationer av första tärningen
            if (dices.get(0).value + dices.get(1).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(0).value);
                combinatedDiceNumbers.add(dices.get(1).value);
                dices.get(0).value = 0;
                dices.get(1).value = 0;
            }
            if (dices.get(0).value + dices.get(1).value + dices.get(2).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(0).value);
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                dices.get(0).value = 0;
                dices.get(1).value = 0;
                dices.get(2).value = 0;
            }
            if (dices.get(0).value + dices.get(1).value + dices.get(2).value + dices.get(3).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(0).value);
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                dices.get(0).value = 0;
                dices.get(1).value = 0;
                dices.get(2).value = 0;
                dices.get(3).value = 0;
            }

            if (dices.get(0).value + dices.get(1).value + dices.get(2).value + dices.get(3).value + dices.get(4).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(0).value);
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                dices.get(0).value = 0;
                dices.get(1).value = 0;
                dices.get(2).value = 0;
                dices.get(3).value = 0;
                dices.get(4).value = 0;
            }
            if (dices.get(0).value + dices.get(1).value + dices.get(2).value + dices.get(3).value + dices.get(4).value + dices.get(5).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(0).value);
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                combinatedDiceNumbers.add(dices.get(5).value);
                dices.get(0).value = 0;
                dices.get(1).value = 0;
                dices.get(2).value = 0;
                dices.get(3).value = 0;
                dices.get(4).value = 0;
                dices.get(5).value = 0;
            }
            //alla kombinationer av andra tärningen
            if (dices.get(1).value + dices.get(2).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                dices.get(1).value = 0;
                dices.get(2).value = 0;
            }
            if (dices.get(1).value + dices.get(2).value + dices.get(3).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                dices.get(1).value = 0;
                dices.get(2).value = 0;
                dices.get(3).value = 0;
            }
            if (dices.get(1).value + dices.get(2).value + dices.get(3).value + dices.get(4).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                dices.get(1).value = 0;
                dices.get(2).value = 0;
                dices.get(3).value = 0;
                dices.get(4).value = 0;
            }
            if (dices.get(1).value + dices.get(2).value + dices.get(3).value + dices.get(4).value + dices.get(5).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(1).value);
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                combinatedDiceNumbers.add(dices.get(5).value);
                dices.get(1).value = 0;
                dices.get(2).value = 0;
                dices.get(3).value = 0;
                dices.get(4).value = 0;
                dices.get(5).value = 0;
            }
            //alla kombinationer av tredje tärningen
            if (dices.get(2).value + dices.get(3).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                dices.get(2).value = 0;
                dices.get(3).value = 0;
            }

            if (dices.get(2).value + dices.get(3).value + dices.get(4).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                dices.get(2).value = 0;
                dices.get(3).value = 0;
                dices.get(4).value = 0;
            }
            if (dices.get(2).value + dices.get(3).value + dices.get(4).value + dices.get(5).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(2).value);
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                combinatedDiceNumbers.add(dices.get(5).value);
                dices.get(2).value = 0;
                dices.get(3).value = 0;
                dices.get(4).value = 0;
                dices.get(5).value = 0;
            }
            //alla kombinationer av fjärde tärningen
            if (dices.get(3).value + dices.get(4).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                dices.get(3).value = 0;
                dices.get(4).value = 0;
            }
            if (dices.get(3).value + dices.get(4).value + dices.get(5).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(3).value);
                combinatedDiceNumbers.add(dices.get(4).value);
                combinatedDiceNumbers.add(dices.get(5).value);
                dices.get(3).value = 0;
                dices.get(4).value = 0;
                dices.get(5).value = 0;
            }
            //alla kombintationer av femte tärningen
            if (dices.get(4).value + dices.get(5).value == choicePoint) {
                combinatedDiceNumbers.add(dices.get(4).value);
                combinatedDiceNumbers.add(dices.get(5).value);
                dices.get(4).value = 0;
                dices.get(5).value = 0;
            }
            //då vet vi att valet kan göras och stämmer av med tärningarna
            if (combinatedDiceNumbers.size() > 0) {
                currentScore = sum(combinatedDiceNumbers);
                //lägger in loggningen av poängen
                ChoicePointResult.score = currentScore;
                GlobalDiceNumbers.triesAndDiceNumbers.clear();
                RoundsLogic.isNewRound = true;
                pointTypeSucceeded = true;
                adapter.remove((String) selectedItem);
                adapter.notifyDataSetChanged();
                //ifall tärningarna på spelplanen inte går att göra upp till valet
            } else {
                pointTypeSucceeded = false;
                System.out.println("Du måste välja en annan poängtyp!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //summera en lista av heltal
    private static int sum(List<Integer> list) {
        int sum = 0;
        for (int integer : list) {
            if(integer>0) {
                sum += integer;
            }
        }
        return sum;
    }
}





