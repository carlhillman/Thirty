package se.umu.carl.thirty;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class ScoreLogic extends Activity { //håller koll på poängräkninslogiken och spelregler
    int currentScore;
    boolean pointTypeChoosen = false;
    static int totalSum;

    //bestämer vilken poäng som ska sättas.
    protected void increaseCurrentScore(Spinner spinner, ArrayAdapter<String> adapter) {
        String selectedItem = spinner.getSelectedItem().toString();
        if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
            try {
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
                    case "Välj poängtyp": //ta bort detta alternativ ifall när man öppnar listan rensas detta alternativ
                        System.out.println("Du har inte valt någon poängtyp, försök igen");
                }
                //lägga in någonstans det valet och den poängen man får för att visa slutresultatet.
                ChoicePointResult.choicePoints.put((String) selectedItem, ChoicePointResult.score);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //kollar vilka kombinationer och tärningar som finns på fältet
    protected void createCombinatedDiceNumbers(String selectedItem, int choicePoint, ArrayAdapter<String> adapter) {
        ArrayList<Dice> dices = new ArrayList<>();
        try {
            for (Map.Entry<Integer, ArrayList<Dice>> entry : GlobalDiceNumbers.triesAndDiceNumbers.entrySet()) {
                dices = entry.getValue();
                break;
            }
            //gör en array av tärningslistan
            Dice[] diceArray = dices.toArray(new Dice[0]);
            Vector<Dice> sortedDice = new Vector<>(Arrays.asList(diceArray));
            Combination(sortedDice, choicePoint);

            //ifall totalsum är större än 0 får man poäng
            if (totalSum > 0) {
                currentScore = totalSum;
                ChoicePointResult.score = currentScore;

            } else {
                //ifall tärningarna på spelplanen inte går att göra upp till valet får spelaren 0 poäng
                ChoicePointResult.score = 0;
            }
            pointTypeChoosen = true;
            adapter.remove((String) selectedItem);
            adapter.notifyDataSetChanged();
            RoundsLogic.isNewRound = true;
            GlobalDiceNumbers.triesAndDiceNumbers.clear();
            return;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    // Metod för att hitta alla unika kombinaioner av givna element så att summan är lika med poänvalet
    static void unique_combination(int l, int sum, int choicePoint, Vector<Dice> local, Vector<Dice> sortedDice) {
        // om en unik kombination hittas läggst totalsum på
        if (sum == choicePoint) {
            totalSum += sum;
        }
        // För alla andra kombinationer
        for (int i = l; i < sortedDice.size(); i++) {
            // Kolla om summan överstiger poängvalet
            if (sum + sortedDice.get(i).value > choicePoint)
                continue;
            // Kolla om det repeteras eller inte
            if (i == 1 && sortedDice.get(i) == sortedDice.get(i - 1) && i > l)
                continue;
            // Ta med elementet till kombinationen
            local.add(sortedDice.get(i));
            // Rekursivt metodanrop
            unique_combination(i + 1, sum + sortedDice.get(i).value, choicePoint, local, sortedDice);

            // Tar bort elementet från kombinationen
            local.remove(local.size() - 1);

        }
    }
    // Metod för att hitta alla kombinationer av givna element
    static void Combination(Vector<Dice> sortedDice, int choicePoint) {
        // Sortera elementen
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < sortedDice.size(); i++) {
            numbers.add(sortedDice.get(i).value);
        }
        Collections.sort(numbers);
        // För att lagra kombinationen
        Vector<Dice> local = new Vector<>();
        unique_combination(0, 0, choicePoint, local, sortedDice);
    }
}






