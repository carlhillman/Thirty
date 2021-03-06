package se.umu.carl.thirty.GameLogic;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import se.umu.carl.thirty.Models.Die;
import se.umu.carl.thirty.Models.Dice;
import se.umu.carl.thirty.R;

//den generella logiken som hanterar tärningskast
public class DiceLogic {
    public ImageView firstDieImageView, secondDieImageView, thirdDieImageView,
            fourthDieImageView, fifthDieImageView, sixthDieImageView;
    public boolean isFirstDieSelected = true;
    public boolean isSecondDieSelected = true;
    public boolean isThirdDieSelected = true;
    public boolean isFourthDieSelected = true;
    public boolean isFifthDieSelected = true;
    public boolean isSixthDieSelected = true;
    Dice dice;
    public ArrayList<Die> globalDice = new ArrayList<>();

    private Context context;
    RoundsLogic roundsLogic;
    RestoreGUIManager restoreGUIManager;

    public DiceLogic(Context context, Dice dice, RoundsLogic roundsLogic, RestoreGUIManager restoreGUIManager) {
        this.context = context;
        this.dice = dice;
        this.roundsLogic = roundsLogic;
        this.restoreGUIManager = restoreGUIManager;

    }

    public int numberOfBlueDice = 0;

    /**
     * Bestämmer vad som ska ske när man klickar på den första tärningen
     *
     * @param btnThrow
     */
    public void clickFirstDie(Button btnThrow) {
        if (globalDice.size() == 6) {
            if (isFirstDieSelected && roundsLogic.totalNumberOfThrowsDisplayed > 0 && !restoreGUIManager.inChoosingPointProgress) {
                isFirstDieSelected = false;
                firstDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(0).selected = false;
                numberOfBlueDice++;
                if (numberOfBlueDice == 6) {
                    btnThrow.setVisibility(View.GONE);
                }
            } else if (restoreGUIManager.inChoosingPointProgress) {
                if (!isFirstDieSelected) {
                    isFirstDieSelected = true;
                    firstDieImageView.setBackgroundColor(Color.BLUE);
                    globalDice.get(0).selected = true;
                    numberOfBlueDice++;
                    if (numberOfBlueDice == 6) {
                        btnThrow.setVisibility(View.GONE);
                    }
                } else {
                    isFirstDieSelected = false;
                    firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
                    globalDice.get(0).selected = false;
                    numberOfBlueDice--;
                }
            } else {
                isFirstDieSelected = true;
                firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(0).selected = true;
                numberOfBlueDice--;
                if (!restoreGUIManager.inChoosingPointProgress && roundsLogic.totalNumberOfThrowsDisplayed < 3) {
                    btnThrow.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Bestämmer vad som ska ske när man klickar på den andra tärningen
     *
     * @param btnThrow
     */
    public void clickSecondDie(Button btnThrow) {
        if (globalDice.size() == 6) {
            if (isSecondDieSelected && roundsLogic.totalNumberOfThrowsDisplayed > 0 && !restoreGUIManager.inChoosingPointProgress) {
                isSecondDieSelected = false;
                secondDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(1).selected = false;
                numberOfBlueDice++;
                if (numberOfBlueDice == 6) {
                    btnThrow.setVisibility(View.GONE);
                }
            } else if (restoreGUIManager.inChoosingPointProgress) {
                if (!isSecondDieSelected) {
                    isSecondDieSelected = true;
                    secondDieImageView.setBackgroundColor(Color.BLUE);
                    globalDice.get(1).selected = true;
                    numberOfBlueDice++;
                    if (numberOfBlueDice == 6) {
                        btnThrow.setVisibility(View.GONE);
                    }
                } else {
                    isSecondDieSelected = false;
                    secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
                    globalDice.get(1).selected = false;
                    numberOfBlueDice--;
                }
            } else {
                isSecondDieSelected = true;
                secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(1).selected = true;
                numberOfBlueDice--;
                if (!restoreGUIManager.inChoosingPointProgress && roundsLogic.totalNumberOfThrowsDisplayed < 3) {
                    btnThrow.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    /**
     * Bestämmer vad som ska ske när man klickar på den tredje tärningen
     *
     * @param btnThrow
     */
    public void clickThirdDie(Button btnThrow) {
        if (globalDice.size() == 6) {
            if (isThirdDieSelected && roundsLogic.totalNumberOfThrowsDisplayed > 0 && !restoreGUIManager.inChoosingPointProgress) {
                isThirdDieSelected = false;
                thirdDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(2).selected = false;
                numberOfBlueDice++;
                if (numberOfBlueDice == 6) {
                    btnThrow.setVisibility(View.GONE);
                }
            } else if (restoreGUIManager.inChoosingPointProgress) {
                if (!isThirdDieSelected) {
                    isThirdDieSelected = true;
                    thirdDieImageView.setBackgroundColor(Color.BLUE);
                    globalDice.get(2).selected = true;
                    numberOfBlueDice++;
                    if (numberOfBlueDice == 6) {
                        btnThrow.setVisibility(View.GONE);
                    }
                } else {
                    isThirdDieSelected = false;
                    thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
                    globalDice.get(2).selected = false;
                    numberOfBlueDice--;
                }
            } else {
                isThirdDieSelected = true;
                thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(2).selected = true;
                numberOfBlueDice--;
                if (!restoreGUIManager.inChoosingPointProgress && roundsLogic.totalNumberOfThrowsDisplayed < 3) {
                    btnThrow.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Bestämmer vad som ska ske när man klickar på den fjärde tärningen
     *
     * @param btnThrow
     */
    public void clickFourthDie(Button btnThrow) {
        if (globalDice.size() == 6) {
            if (isFourthDieSelected && roundsLogic.totalNumberOfThrowsDisplayed > 0 && !restoreGUIManager.inChoosingPointProgress) {
                isFourthDieSelected = false;
                fourthDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(3).selected = false;
                numberOfBlueDice++;
                if (numberOfBlueDice == 6) {
                    btnThrow.setVisibility(View.GONE);
                }
            } else if (restoreGUIManager.inChoosingPointProgress) {
                if (!isFourthDieSelected) {
                    isFourthDieSelected = true;
                    fourthDieImageView.setBackgroundColor(Color.BLUE);
                    globalDice.get(3).selected = true;
                    numberOfBlueDice++;
                    if (numberOfBlueDice == 6) {
                        btnThrow.setVisibility(View.GONE);
                    }
                } else {
                    isFourthDieSelected = false;
                    fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                    globalDice.get(3).selected = false;
                    numberOfBlueDice--;
                }
            } else {
                isFourthDieSelected = true;
                fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(3).selected = true;
                numberOfBlueDice--;
                if (!restoreGUIManager.inChoosingPointProgress && roundsLogic.totalNumberOfThrowsDisplayed < 3) {
                    btnThrow.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Bestämmer vad som ska ske när man klickar på den femte tärningen
     *
     * @param btnThrow
     */
    public void clickFifthDie(Button btnThrow) {
        if (globalDice.size() == 6) {
            if (isFifthDieSelected && roundsLogic.totalNumberOfThrowsDisplayed > 0 && !restoreGUIManager.inChoosingPointProgress) {
                isFifthDieSelected = false;
                fifthDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(4).selected = false;
                numberOfBlueDice++;
                if (numberOfBlueDice == 6) {
                    btnThrow.setVisibility(View.GONE);
                }
            } else if (restoreGUIManager.inChoosingPointProgress) {
                if (!isFifthDieSelected) {
                    isFifthDieSelected = true;
                    fifthDieImageView.setBackgroundColor(Color.BLUE);
                    globalDice.get(4).selected = true;
                    numberOfBlueDice++;
                    if (numberOfBlueDice == 6) {
                        btnThrow.setVisibility(View.GONE);
                    }
                } else {
                    isFifthDieSelected = false;
                    fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                    globalDice.get(4).selected = false;
                    numberOfBlueDice--;
                }
            } else {
                isFifthDieSelected = true;
                fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(4).selected = true;
                numberOfBlueDice--;
                if (!restoreGUIManager.inChoosingPointProgress && roundsLogic.totalNumberOfThrowsDisplayed < 3) {
                    btnThrow.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Bestämmer vad som ska ske när man klickar på den sjätte tärningen
     *
     * @param btnThrow
     */
    public void clickSixthDie(Button btnThrow) {
        if (globalDice.size() == 6) {
            if (isSixthDieSelected && roundsLogic.totalNumberOfThrowsDisplayed > 0 && !restoreGUIManager.inChoosingPointProgress) {
                isSixthDieSelected = false;
                sixthDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(5).selected = false;
                numberOfBlueDice++;
                if (numberOfBlueDice == 6) {
                    btnThrow.setVisibility(View.GONE);
                }
            } else if (restoreGUIManager.inChoosingPointProgress) {
                if (!isSixthDieSelected) {
                    isSixthDieSelected = true;
                    sixthDieImageView.setBackgroundColor(Color.BLUE);
                    globalDice.get(5).selected = true;
                    numberOfBlueDice++;
                    if (numberOfBlueDice == 6) {
                        btnThrow.setVisibility(View.GONE);
                    }
                } else {
                    isSixthDieSelected = false;
                    sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                    globalDice.get(5).selected = false;
                    numberOfBlueDice--;
                }
            } else {
                isSixthDieSelected = true;
                sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(5).selected = true;
                numberOfBlueDice--;
                if (!restoreGUIManager.inChoosingPointProgress && roundsLogic.totalNumberOfThrowsDisplayed < 3) {
                    btnThrow.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Klicket för kast av en, flera eller alla tärningar. Uppdaterar även Die objekten i Dice listan
     *
     * @param spinner
     * @param txtThrows
     * @param txtRounds
     * @param btnThrow
     */
    public void clickThrow(Spinner spinner, TextView txtThrows, TextView txtRounds, Button btnThrow) {
        enableDiceImage();
        spinner.setEnabled(true);
        spinner.setVisibility(View.VISIBLE);
        ArrayList<ImageView> selectedDicesImages = new ArrayList<>();
        if (isFirstDieSelected && globalDice.size() == 6) {
            selectedDicesImages.add(firstDieImageView);
            if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(0);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isSecondDieSelected && globalDice.size() == 6) {
            selectedDicesImages.add(secondDieImageView);
            if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(1);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isThirdDieSelected && globalDice.size() == 6) {
            selectedDicesImages.add(thirdDieImageView);
            if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(2);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isFourthDieSelected && globalDice.size() == 6) {
            selectedDicesImages.add(fourthDieImageView);
            if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(3);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isFifthDieSelected && globalDice.size() == 6) {
            selectedDicesImages.add(fifthDieImageView);
            if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(4);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isSixthDieSelected && globalDice.size() == 6) {
            selectedDicesImages.add(sixthDieImageView);
            if (roundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(5);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (selectedDicesImages.size() == 0) {  // Om ingen tärning valts, blir alla valda
            addAllDiceImages(selectedDicesImages);
            selectAllDice();
            for (Die die : globalDice) {
                die.selected = true;
            }
        }
        //kasta till ny runda
        if (roundsLogic.pointTypeChosen) {
            txtThrows.setText(context.getResources().getString(R.string.numberOfThrows) + roundsLogic.getAndSetThrows());
            txtRounds.setText(context.getResources().getString(R.string.numberOfRounds) + roundsLogic.getAndSetRounds());
            roundsLogic.pointTypeChosen = false;
            rollDices(selectedDicesImages, btnThrow);
            numberOfBlueDice = 0;
        }
        //Kast i en befintlig runda.
        else {
            roundsLogic.isNewRound = false;
            rollDices(selectedDicesImages, btnThrow);
            txtThrows.setText(context.getResources().getString(R.string.numberOfThrows) + roundsLogic.getAndSetThrows());
        }
    }

    /**
     * Anropar setDiceImages metoden och gömmer kast knappen på villkor
     *
     * @param selectedDicesImages
     * @param btnThrow
     */
    public void rollDices(ArrayList<ImageView> selectedDicesImages, Button btnThrow) {
        try {
            setDiceImages(selectedDicesImages);
            //Efter att man har kastat tre gånger göm kasta knappen
            if (roundsLogic.totalNumberOfThrowsDisplayed == 2 && !roundsLogic.pointTypeChosen) {
                btnThrow.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Ändrar bilderna för tärningarna utifrån en Random generator
     *
     * @param diceImages
     */
    public void setDiceImages(ArrayList<ImageView> diceImages) {
        Random random = new Random();
        for (ImageView imageViewDice : diceImages) {
            int randomNumber = random.nextInt(6) + 1;
            switch (randomNumber) {
                case 1:
                    addOrUpdateDie(randomNumber);
                    imageViewDice.setImageResource(R.drawable.white1);
                    break;
                case 2:
                    addOrUpdateDie(randomNumber);
                    imageViewDice.setImageResource(R.drawable.white2);
                    break;
                case 3:
                    addOrUpdateDie(randomNumber);
                    imageViewDice.setImageResource(R.drawable.white3);
                    break;
                case 4:
                    addOrUpdateDie(randomNumber);
                    imageViewDice.setImageResource(R.drawable.white4);
                    break;
                case 5:
                    addOrUpdateDie(randomNumber);
                    imageViewDice.setImageResource(R.drawable.white5);
                    break;
                case 6:
                    addOrUpdateDie(randomNumber);
                    imageViewDice.setImageResource(R.drawable.white6);
                    break;
            }
            Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
            imageViewDice.startAnimation(rotate);
        }
        dice.triesAndDiceNumbers.put(roundsLogic.totalNumberOfThrowsDisplayed, globalDice);
    }

    /**
     * Lägger till ett ny Tärningsobjekt/Die till listan eller uppdaterar befintlig tärning/Die
     *
     * @param randomValue
     */
    private void addOrUpdateDie(int randomValue) {
        if (roundsLogic.totalNumberOfThrowsDisplayed == 0) {
            Die die = new Die(false, randomValue);
            globalDice.add(die);
        } else {
            for (Die die : globalDice) {
                if (die.selected) {
                    die.selected = false;
                    die.value = randomValue;
                    break;
                }
            }
        }
    }

    /**
     * Sätter på klick funktionaliteten för alla tärningsbilder
     */
    public void enableDiceImage() {
        firstDieImageView.setEnabled(true);
        secondDieImageView.setEnabled(true);
        thirdDieImageView.setEnabled(true);
        fourthDieImageView.setEnabled(true);
        fifthDieImageView.setEnabled(true);
        sixthDieImageView.setEnabled(true);
        restoreGUIManager.isDiceImageViewEnabled = true;
    }

    /**
     * Stänger av klick funktionaliteten för alla tärningsbilder
     */
    public void disableDiceImage() {
        firstDieImageView.setEnabled(false);
        secondDieImageView.setEnabled(false);
        thirdDieImageView.setEnabled(false);
        fourthDieImageView.setEnabled(false);
        fifthDieImageView.setEnabled(false);
        sixthDieImageView.setEnabled(false);
        restoreGUIManager.isDiceImageViewEnabled = false;
    }

    /**
     * Väljer alla tärningar
     */
    private void selectAllDice() {
        isFirstDieSelected = true;
        isSecondDieSelected = true;
        isThirdDieSelected = true;
        isFourthDieSelected = true;
        isFifthDieSelected = true;
        isSixthDieSelected = true;
    }

    /**
     * Metod som anropas i metoden clickThrow som lägger till alla ImageView i en ArrayList
     */
    private void addAllDiceImages(ArrayList<ImageView> selectedDicesImages) {
        selectedDicesImages.add(firstDieImageView);
        selectedDicesImages.add(secondDieImageView);
        selectedDicesImages.add(thirdDieImageView);
        selectedDicesImages.add(fourthDieImageView);
        selectedDicesImages.add(fifthDieImageView);
        selectedDicesImages.add(sixthDieImageView);
    }

    /**
     * Metod som väljer bort alla tärningar
     */
    public void deselectAllDice() {
        isFirstDieSelected = false;
        isSecondDieSelected = false;
        isThirdDieSelected = false;
        isFourthDieSelected = false;
        isFifthDieSelected = false;
        isSixthDieSelected = false;

        firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
        secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
        thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
        fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);

        if (globalDice.size() == 6) {
            for (Die die : globalDice) {
                die.selected = false;
            }
        }
    }
}
