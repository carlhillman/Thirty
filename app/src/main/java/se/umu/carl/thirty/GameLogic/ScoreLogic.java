package se.umu.carl.thirty.GameLogic;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Map;

import se.umu.carl.thirty.Models.Dice;
import se.umu.carl.thirty.Models.ResultStorage;
import se.umu.carl.thirty.Models.Die;
import se.umu.carl.thirty.R;
import se.umu.carl.thirty.Views.FeedBackDialogMessageBox;

// Logik som håller koll på nuvarande poäng, vilket val som ska räknas och validerar poängsättningen
public class ScoreLogic {
    public  int currentScore;
    //måste vara static eftersom den används i DiceLogic
    public static boolean pointTypeChoosen = false;
    private Context context;
    public ScoreLogic(Context context){
        this.context = context;
    }

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
                }
                //lägga in någonstans det valet och den poängen man får för att visa slutresultatet.
                ResultStorage.choicePoints.put(selectedItem, ResultStorage.score);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //kollar vilka kombinationer och tärningar som finns på fältet
    protected void calculateCurrentScore(String selectedItem, int choicePoint, ArrayAdapter<String> adapter) {
        ArrayList<Die> dice = new ArrayList<>();
        try {
            for (Map.Entry<Integer, ArrayList<Die>> entry : Dice.triesAndDiceNumbers.entrySet()) {
                dice = entry.getValue();
                break;
            }
            for (Die die : dice) {
                if (die.selected) {
                    currentScore += die.value;
                }
            }
            //ifall summan av tärningarna är delbart med valet
            if (currentScore % choicePoint == 0 && currentScore >= choicePoint) {
                ResultStorage.score = currentScore;
            } else {
                //ifall tärningarna på spelplanen inte går att göra upp till valet får spelaren 0 poäng
                currentScore = 0;
                ResultStorage.score = 0;
            }
            pointTypeChoosen = true;
            adapter.remove(selectedItem);
            adapter.notifyDataSetChanged();
            RoundsLogic.isNewRound = true;
            Dice.triesAndDiceNumbers.clear();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getPoints(Spinner spinner, ArrayAdapter<String>adapter, FeedBackDialogMessageBox messageBox
    , Button btnThrow, Button btnTakePoints, DiceLogic diceLogic){
        try {
            setChoicePoint(spinner, adapter);
            messageBox.showRoundSucceededDialog(currentScore);
            currentScore = 0;
            RestoreGUIManager.inChoosingPointProgress = false;
            btnThrow.setVisibility(View.VISIBLE);
            RestoreGUIManager.isBtnThrowDisplayed = true;
            btnTakePoints.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            spinner.setSelection(adapter.getPosition(context.getResources().getStringArray(R.array.choices)[0]));
            diceLogic.deselectAllDice();
            diceLogic.disableDiceImage();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}






