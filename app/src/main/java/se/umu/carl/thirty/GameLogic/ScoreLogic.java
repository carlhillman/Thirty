package se.umu.carl.thirty.GameLogic;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Map;

import se.umu.carl.thirty.Models.ChoicePointResult;
import se.umu.carl.thirty.Models.Die;
import se.umu.carl.thirty.Models.GlobalDiceNumbers;

public class ScoreLogic extends Activity { //håller koll på poängräkninslogiken och spelregler
    public int currentScore;
    //måste vara static eftersom den används i DiceLogic
    public static boolean pointTypeChoosen = false;

    //bestämer vilken poäng som ska sättas.
    public void setChoicePoint(Spinner spinner, ArrayAdapter<String> adapter) {
        String selectedItem = spinner.getSelectedItem().toString();
        if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
            try {
                switch (selectedItem) {
                    case "Low":
                        calculateCurrentScore("Low", 3, adapter);
                        break;
                    case "4":
                        calculateCurrentScore("4", 4, adapter);
                        break;
                    case "5":
                        calculateCurrentScore("5", 5, adapter);
                        break;
                    case "6":
                        calculateCurrentScore("6", 6, adapter);
                        break;
                    case "7":
                        calculateCurrentScore("7", 7, adapter);
                        break;
                    case "8":
                        calculateCurrentScore("8", 8, adapter);
                        break;
                    case "9":
                        calculateCurrentScore("9", 9, adapter);
                        break;
                    case "10":
                        calculateCurrentScore("10", 10, adapter);
                        break;
                    case "11":
                        calculateCurrentScore("11", 11, adapter);
                        break;
                    case "12":
                        calculateCurrentScore("12", 12, adapter);
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
    protected void calculateCurrentScore(String selectedItem, int choicePoint, ArrayAdapter<String> adapter) {
        ArrayList<Die> dice = new ArrayList<>();
        try {
            for (Map.Entry<Integer, ArrayList<Die>> entry : GlobalDiceNumbers.triesAndDiceNumbers.entrySet()) {
                dice = entry.getValue();
                break;
            }
            for(Die die :dice){
                if(die.selected){
                    currentScore += die.value;
                }
            }
            //ifall summan av tärningarna är delbart med valet

            if(currentScore % choicePoint == 0 &&  currentScore >= choicePoint){
                ChoicePointResult.score = currentScore;
            } else {
                //ifall tärningarna på spelplanen inte går att göra upp till valet får spelaren 0 poäng
                currentScore = 0;
                ChoicePointResult.score = 0;
            }
            pointTypeChoosen = true;
            adapter.remove(selectedItem);
            adapter.notifyDataSetChanged();
            RoundsLogic.isNewRound = true;
            GlobalDiceNumbers.triesAndDiceNumbers.clear();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}






