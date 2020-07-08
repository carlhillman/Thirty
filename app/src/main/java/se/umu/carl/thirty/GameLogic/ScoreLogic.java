package se.umu.carl.thirty.GameLogic;

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
    public int currentScore;
    //måste vara static eftersom den används i DiceLogic
    public static boolean pointTypeChosen = false;
    private Context context;

    public Dice diceClass;
    ResultStorage resultStorage;

    public ScoreLogic(Context context, Dice diceClass, ResultStorage resultStorage) {
        this.context = context;
        this.diceClass = diceClass;
        this.resultStorage = resultStorage;
    }

    /**
     * bestämer vilken poäng som ska sättas
     *
     * @param spinner - spinner används för att få tag på vilket poängval användaren valt
     * @param adapter - adaptern används för att kunna ta bort valet från spinnern när poängen har gått igenom
     */
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
                resultStorage.choicePoints.put(selectedItem, resultStorage.score);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * kollar vilka kombinationer och tärningar som finns på fältet
     *
     * @param selectedItem - används för att veta vilket poängval som gjordes
     * @param adapter      - adaptern används för att kunna ta bort valet från spinnern när poängen har gått igenom
     * @param choicePoint  - används för att veta vilket värde/heltal poängtypen har
     */
    protected void calculateCurrentScore(String selectedItem, int choicePoint, ArrayAdapter<String> adapter) {
        ArrayList<Die> dice = new ArrayList<>();
        try {
            for (Map.Entry<Integer, ArrayList<Die>> entry : diceClass.triesAndDiceNumbers.entrySet()) {
                dice = entry.getValue();
                break;
            }
            ArrayList<Die> selectedDice = new ArrayList<>();
            for (Die die : dice) {
                if (die.selected) {
                    selectedDice.add(die);
                }
            }
            for (int i = 0; i < selectedDice.size(); i++) {
                if (selectedDice.get(i).value == choicePoint) { //tärningar som direkt matchar valet tilldelas värde 0
                    currentScore += choicePoint;
                    selectedDice.get(i).value = 0;
                }
            }

            //ifall summan av tärningarna är delbart med valet
            if (currentScore % choicePoint == 0 && currentScore >= choicePoint) {
                resultStorage.score = currentScore;
            } else {
                //ifall tärningarna på spelplanen inte går att göra upp till valet får spelaren 0 poäng
                currentScore = 0;
                resultStorage.score = 0;
            }
            pointTypeChosen = true;
            adapter.remove(selectedItem);
            adapter.notifyDataSetChanged();
            RoundsLogic.isNewRound = true;
            diceClass.triesAndDiceNumbers.clear();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * anropar metoden setChoicePoint och sätter tillbaka default värdet "välj poängtyp" i spinnern
     *
     * @param spinner       - används för att veta vilket poängval som gjordes
     * @param adapter       - adaptern används för att kunna ta bort valet från spinnern när poängen har gått igenom
     * @param messageBox    - används för att visa en dialogruta som ger feedback
     * @param btnThrow      - används för att gömma kast knappen
     * @param btnTakePoints - används för att gömma ta poäng knappen
     * @param diceLogic     - används för att kalla på metoden deselectAllDice och disableDiceImage
     */
    public void getPoints(Spinner
                                  spinner, ArrayAdapter<String> adapter, FeedBackDialogMessageBox messageBox
            , Button btnThrow, Button btnTakePoints, DiceLogic diceLogic) {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}






