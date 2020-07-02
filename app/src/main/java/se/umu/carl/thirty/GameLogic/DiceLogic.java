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

//den generella Tärningslogiken som hanterar tärningskast
public class DiceLogic {
    public ImageView firstDieImageView, secondDieImageView, thirdDieImageView,
            fourthDieImageView, fifthDieImageView, sixthDieImageView;
    public boolean isFirstDieSelected = false;
    public boolean isSecondDieSelected = false;
    public boolean isThirdDieSelected = false;
    public boolean isFourthDieSelected = false;
    public boolean isFifthDieSelected = false;
    public boolean isSixthDieSelected = false;

    public ArrayList<Die> globalDice = new ArrayList<>();

    private Context context;

    public DiceLogic(Context context) {
        this.context = context;
    }

    public void clickFirstDie() {
        if (globalDice.size() == 6) {
            if (!isFirstDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isFirstDieSelected = true;
                firstDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(0).selected = true;
            } else {
                isFirstDieSelected = false;
                firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(0).selected = false;
            }
        }
    }

    public void clickSecondDie() {
        if (globalDice.size() == 6) {
            if (!isSecondDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isSecondDieSelected = true;
                secondDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(1).selected = true;
            } else {
                isSecondDieSelected = false;
                secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(1).selected = false;
            }
        }
    }

    public void clickThirdDie() {
        if (globalDice.size() == 6) {
            if (!isThirdDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isThirdDieSelected = true;
                thirdDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(2).selected = true;
            } else {
                isThirdDieSelected = false;
                thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(2).selected = false;
            }
        }
    }

    public void clickFourthDie() {
        if (globalDice.size() == 6) {
            if (!isFourthDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isFourthDieSelected = true;
                fourthDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(3).selected = true;
            } else {
                isFourthDieSelected = false;
                fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(3).selected = false;
            }
        }
    }

    public void clickFifthDie() {
        if (globalDice.size() == 6) {
            if (!isFifthDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isFifthDieSelected = true;
                fifthDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(4).selected = true;
            } else {
                isFifthDieSelected = false;
                fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(4).selected = false;
            }
        }
    }

    public void clickSixthDie() {
        if (globalDice.size() == 6) {
            if (!isSixthDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isSixthDieSelected = true;
                sixthDieImageView.setBackgroundColor(Color.BLUE);
                globalDice.get(5).selected = true;
            } else {
                isSixthDieSelected = false;
                sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(5).selected = false;
            }
        }
    }

    public void clickThrow(Spinner spinner, TextView txtThrows, TextView txtRounds, Button btnThrow) {
        enableDiceImage();
        spinner.setEnabled(true);
        spinner.setVisibility(View.VISIBLE);
        //spinner.setSelection(adapter.getPosition("Välj poängtyp"));
        ArrayList<ImageView> selectedDicesImages = new ArrayList<>();
        if (isFirstDieSelected) {
            selectedDicesImages.add(firstDieImageView);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(0);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isSecondDieSelected) {
            selectedDicesImages.add(secondDieImageView);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(1);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isThirdDieSelected) {
            selectedDicesImages.add(thirdDieImageView);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(2);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isFourthDieSelected) {
            selectedDicesImages.add(fourthDieImageView);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(3);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isFifthDieSelected) {
            selectedDicesImages.add(fifthDieImageView);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(4);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isSixthDieSelected) {
            selectedDicesImages.add(sixthDieImageView);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(5);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        //om ingen eller alla tärningar har valts, kastas alla tärningar om. Och alla blir selected
        if (selectedDicesImages.size() == 0) {
            selectedDicesImages.add(firstDieImageView);
            selectedDicesImages.add(secondDieImageView);
            selectedDicesImages.add(thirdDieImageView);
            selectedDicesImages.add(fourthDieImageView);
            selectedDicesImages.add(fifthDieImageView);
            selectedDicesImages.add(sixthDieImageView);
            for (Die die : globalDice) {
                die.selected = true;
            }
        }
        //kasta till ny runda
        if (ScoreLogic.pointTypeChoosen) {
            txtThrows.setText(context.getResources().getString(R.string.numberOfThrows) + RoundsLogic.getAndSetThrows());
            txtRounds.setText(context.getResources().getString(R.string.numberOfRounds) + RoundsLogic.getAndSetRounds());
            ScoreLogic.pointTypeChoosen = false;
            rollDices(selectedDicesImages, btnThrow);
        }
        //Kast i en befintlig runda.
        else {
            RoundsLogic.isNewRound = false;
            rollDices(selectedDicesImages, btnThrow);
            txtThrows.setText(context.getResources().getString(R.string.numberOfThrows) + RoundsLogic.getAndSetThrows());
        }
    }

    public void rollDices(ArrayList<ImageView> selectedDicesImages, Button btnThrow) {
        try {
            setDiceImages(selectedDicesImages);
            //Efter att man har kastat tre gånger göm kasta knappen
            if (RoundsLogic.totalNumberOfThrowsDisplayed == 2 && !ScoreLogic.pointTypeChoosen) {
                btnThrow.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

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
        Dice.triesAndDiceNumbers.put(RoundsLogic.totalNumberOfThrowsDisplayed, globalDice);
    }

    private void addOrUpdateDie(int randomValue) {
        if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
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

    public void enableDiceImage() {
        firstDieImageView.setEnabled(true);
        secondDieImageView.setEnabled(true);
        thirdDieImageView.setEnabled(true);
        fourthDieImageView.setEnabled(true);
        fifthDieImageView.setEnabled(true);
        sixthDieImageView.setEnabled(true);
        RestoreGUI.isDiceImageViewEnabled = true;
    }

    public void disableDiceImage() {
        firstDieImageView.setEnabled(false);
        secondDieImageView.setEnabled(false);
        thirdDieImageView.setEnabled(false);
        fourthDieImageView.setEnabled(false);
        fifthDieImageView.setEnabled(false);
        sixthDieImageView.setEnabled(false);
        RestoreGUI.isDiceImageViewEnabled = false;
    }

    public void deselectAllDices() {
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
