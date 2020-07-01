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
import se.umu.carl.thirty.Models.GlobalDiceNumbers;
import se.umu.carl.thirty.R;

public class DiceLogic {

    public ImageView imageViewDice1, imageViewDice2, imageViewDice3, imageViewDice4, imageViewDice5, imageViewDice6;
    public boolean isFirstDieSelected = false;
    public boolean isSecondDieSelected = false;
    public boolean isThirdDieSelected = false;
    public boolean isFourthDieSelected = false;
    public boolean isFifthDieSelected = false;
    public boolean isSixthDieSelected = false;

    public ArrayList<Die> globalDice = new ArrayList<>();
    public ArrayList<ImageView> imageViewsDice = new ArrayList<>();
    public ArrayList<Boolean> diceSelected = new ArrayList<>();
    public Context context;

    public DiceLogic(Context context) {
        this.context = context;
    }
  /*  public void setUIComponents() {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        txtThrows = view.findViewById(R.id.txtThrows);
        txtRounds = view.findViewById(R.id.txtRounds);
        btnThrow = view.findViewById(R.id.btnThrow);
    }
   */

    public void clickFirstDie() {
        if (globalDice.size() == 6) {
            if (!isFirstDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isFirstDieSelected = true;
                imageViewDice1.setBackgroundColor(Color.BLUE);
                globalDice.get(0).selected = true;
            } else {
                isFirstDieSelected = false;
                imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(0).selected = false;
            }
        }
    }

    public void clickSecondDie() {
        if (globalDice.size() == 6) {
            if (!isSecondDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isSecondDieSelected = true;
                imageViewDice2.setBackgroundColor(Color.BLUE);
                globalDice.get(1).selected = true;
            } else {
                isSecondDieSelected = false;
                imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(1).selected = false;
            }
        }
    }

    public void clickThirdDie() {
        if (globalDice.size() == 6) {
            if (!isThirdDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isThirdDieSelected = true;
                imageViewDice3.setBackgroundColor(Color.BLUE);
                globalDice.get(2).selected = true;
            } else {
                isThirdDieSelected = false;
                imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(2).selected = false;
            }
        }
    }

    public void clickFourthDie() {
        if (globalDice.size() == 6) {
            if (!isFourthDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isFourthDieSelected = true;
                imageViewDice4.setBackgroundColor(Color.BLUE);
                globalDice.get(3).selected = true;
            } else {
                isFourthDieSelected = false;
                imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(3).selected = false;
            }
        }
    }

    public void clickFifthDie() {
        if (globalDice.size() == 6) {
            if (!isFifthDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isFifthDieSelected = true;
                imageViewDice5.setBackgroundColor(Color.BLUE);
                globalDice.get(4).selected = true;
            } else {
                isFifthDieSelected = false;
                imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(4).selected = false;
            }
        }
    }

    public void clickSixthDie() {
        if (globalDice.size() == 6) {
            if (!isSixthDieSelected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                isSixthDieSelected = true;
                imageViewDice6.setBackgroundColor(Color.BLUE);
                globalDice.get(5).selected = true;
            } else {
                isSixthDieSelected = false;
                imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(5).selected = false;
            }
        }
    }

    public void clickThrow(Spinner spinner, TextView txtThrows, TextView txtRounds, Button btnThrow) {
        enableDiceImage();
        ArrayList<ImageView> selectedDicesImages = new ArrayList<>();
        if (isFirstDieSelected) {
            selectedDicesImages.add(imageViewDice1);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(0);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isSecondDieSelected) {
            selectedDicesImages.add(imageViewDice2);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(1);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isThirdDieSelected) {
            selectedDicesImages.add(imageViewDice3);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(2);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isFourthDieSelected) {
            selectedDicesImages.add(imageViewDice4);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(3);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isFifthDieSelected) {
            selectedDicesImages.add(imageViewDice5);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(4);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (isSixthDieSelected) {
            selectedDicesImages.add(imageViewDice6);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(5);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        //om ingen eller alla tärningar har valts, kastas alla tärningar om. Och alla blir selected
        if (selectedDicesImages.size() == 0 || selectedDicesImages.size() == 6) {
            selectedDicesImages.add(imageViewDice1);
            selectedDicesImages.add(imageViewDice2);
            selectedDicesImages.add(imageViewDice3);
            selectedDicesImages.add(imageViewDice4);
            selectedDicesImages.add(imageViewDice5);
            selectedDicesImages.add(imageViewDice6);
            for (Die die : globalDice) {
                die.selected = true;
            }
            spinner.setVisibility(View.VISIBLE);
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
        GlobalDiceNumbers.triesAndDiceNumbers.put(RoundsLogic.totalNumberOfThrowsDisplayed, globalDice);
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


    /*private ArrayList<ImageView> getPopulatedImageViewDiceList() {
        if (imageViewsDice.size() != 6) {
            imageViewsDice.add(imageViewDice1);
            imageViewsDice.add(imageViewDice2);
            imageViewsDice.add(imageViewDice3);
            imageViewsDice.add(imageViewDice4);
            imageViewsDice.add(imageViewDice5);
            imageViewsDice.add(imageViewDice6);
        }
        return imageViewsDice;
    }

     */

    public void enableDiceImage() {
        imageViewDice1.setEnabled(true);
        imageViewDice2.setEnabled(true);
        imageViewDice3.setEnabled(true);
        imageViewDice4.setEnabled(true);
        imageViewDice5.setEnabled(true);
        imageViewDice6.setEnabled(true);
    }

    public void disableDiceImage() {
        imageViewDice1.setEnabled(false);
        imageViewDice2.setEnabled(false);
        imageViewDice3.setEnabled(false);
        imageViewDice4.setEnabled(false);
        imageViewDice5.setEnabled(false);
        imageViewDice6.setEnabled(false);
    }

    public void deselectAllDices() {
        isFirstDieSelected = false;
        isSecondDieSelected = false;
        isThirdDieSelected = false;
        isFourthDieSelected = false;
        isFifthDieSelected = false;
        isSixthDieSelected = false;

        imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
    }

}
