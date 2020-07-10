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
    // public  boolean pointTypeChosen = false;
    private Context context;

    public Dice diceClass;
    ResultStorage resultStorage;
    RoundsLogic roundsLogic;

    public ScoreLogic(Context context, Dice diceClass, ResultStorage resultStorage, RoundsLogic roundsLogic) {
        this.context = context;
        this.diceClass = diceClass;
        this.resultStorage = resultStorage;
        this.roundsLogic = roundsLogic;
    }

    /**
     * bestämer vilken poäng som ska sättas
     *
     * @param spinner - spinner används för att få tag på vilket poängval användaren valt
     * @param adapter - adaptern används för att kunna ta bort valet från spinnern när poängen har gått igenom
     */
    public void setChoicePoint(Spinner spinner, ArrayAdapter<String> adapter) {
        String selectedItem = spinner.getSelectedItem().toString();
        if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
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
     * Lägger in valda tärningar i selectedDice listan som används som parameter när metoden anropar metoden getCurrentScoreFromCombination
     *
     * @param selectedItem - används för att veta vilket poängval som gjordes
     * @param adapter      - adaptern används för att kunna ta bort valet från spinnern när poängen har gått igenom
     * @param choicePoint  - används för att veta vilket värde/heltal poängvalet har
     */
    protected void calculateCurrentScore(String selectedItem, int choicePoint, ArrayAdapter<String> adapter) {
        ArrayList<Die> dice = new ArrayList<>();
        ArrayList<Die> selectedDice = new ArrayList<>();
        try {
            for (Map.Entry<Integer, ArrayList<Die>> entry : diceClass.triesAndDiceNumbers.entrySet()) {
                dice = entry.getValue();
                break;
            }
            for (Die die : dice) {
                if (die.selected) {
                    selectedDice.add(die);
                }
            }
            int currentScoreFromCombination = getCurrentScoreFromCombination(selectedDice, choicePoint);
            if (choicePoint == 3) {
                resultStorage.score = currentScoreFromCombination;
            } else {
                if (currentScoreFromCombination % choicePoint == 0 && currentScoreFromCombination >= choicePoint) {
                    resultStorage.score = currentScoreFromCombination;
                } else {
                    resultStorage.score = 0;
                }
            }
            roundsLogic.pointTypeChosen = true;
            adapter.remove(selectedItem);
            adapter.notifyDataSetChanged();
            roundsLogic.isNewRound = true;
            diceClass.triesAndDiceNumbers.clear();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Räknar och kombinerar på de tärningar som användaren valt att räkna på.
     * Skulle man ta med tärningar som inte ger den valda kombinationen ignorereas dem och spelaren får 0 poäng för just dem tärningarna
     *
     * @param selectedDice - används för att veta vilket poängval som gjordes
     * @param choicePoint  - används för att veta vilket värde/heltal poängvalet har
     * @return currentScore - retunerar poäng som kommit från tärningskombinationen
     */
    private int getCurrentScoreFromCombination(ArrayList<Die> selectedDice, int choicePoint) {
        for (int i = 0; i < selectedDice.size(); i++) {
            if (choicePoint == 3) {
                if (selectedDice.get(i).value <= 3) {
                    currentScore += selectedDice.get(i).value;
                }
            } else {
                if (selectedDice.get(i).value == choicePoint) {
                    selectedDice.get(i).value = 0;
                    currentScore += choicePoint;
                }
                for (int j = i + 1; j < selectedDice.size(); j++) {
                    if (selectedDice.get(i).value + selectedDice.get(j).value == choicePoint) {
                        selectedDice.get(i).value = 0;
                        selectedDice.get(j).value = 0;
                        currentScore += choicePoint;
                    }
                    for (int k = j + 1; k < selectedDice.size(); k++) {
                        if (selectedDice.get(i).value + selectedDice.get(j).value + selectedDice.get(k).value == choicePoint) {
                            selectedDice.get(i).value = 0;
                            selectedDice.get(j).value = 0;
                            selectedDice.get(k).value = 0;
                            currentScore += choicePoint;
                        }
                        for (int l = k + 1; l < selectedDice.size(); l++) {
                            if (selectedDice.get(i).value + selectedDice.get(j).value +
                                    selectedDice.get(k).value + selectedDice.get(l).value == choicePoint) {
                                selectedDice.get(i).value = 0;
                                selectedDice.get(j).value = 0;
                                selectedDice.get(k).value = 0;
                                selectedDice.get(l).value = 0;
                                currentScore += choicePoint;
                            }
                            for (int m = l + 1; m < selectedDice.size(); m++) {
                                if (selectedDice.get(i).value + selectedDice.get(j).value + selectedDice.get(k).value +
                                        selectedDice.get(l).value + selectedDice.get(m).value == choicePoint) {
                                    selectedDice.get(i).value = 0;
                                    selectedDice.get(j).value = 0;
                                    selectedDice.get(k).value = 0;
                                    selectedDice.get(l).value = 0;
                                    selectedDice.get(m).value = 0;
                                    currentScore += choicePoint;
                                }
                                for (int n = m + 1; n < selectedDice.size(); n++) {
                                    if (selectedDice.get(i).value + selectedDice.get(j).value + selectedDice.get(k).value +
                                            selectedDice.get(l).value + selectedDice.get(m).value + selectedDice.get(n).value == choicePoint) {
                                        selectedDice.get(i).value = 0;
                                        selectedDice.get(j).value = 0;
                                        selectedDice.get(k).value = 0;
                                        selectedDice.get(l).value = 0;
                                        selectedDice.get(m).value = 0;
                                        selectedDice.get(n).value = 0;
                                        currentScore += choicePoint;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return currentScore;
    }

    /**
     * anropar metoden setChoicePoint och sätter tillbaka standard värdet "välj poängtyp" i spinnern
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






