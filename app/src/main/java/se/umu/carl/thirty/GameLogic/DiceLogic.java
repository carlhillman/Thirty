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
    ScoreLogic scoreLogic = new ScoreLogic();
    public ImageView imageViewDice1, imageViewDice2, imageViewDice3, imageViewDice4, imageViewDice5, imageViewDice6;
    public boolean dice1Selected = false;
    public boolean dice2Selected = false;
    public boolean dice3Selected = false;
    public boolean dice4Selected = false;
    public boolean dice5Selected = false;
    public boolean dice6Selected = false;

    public ArrayList<Die> globalDice = new ArrayList<>();

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
        if(globalDice.size() == 6) {
            if (!dice1Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                dice1Selected = true;
                imageViewDice1.setBackgroundColor(Color.BLUE);
                globalDice.get(0).selected = true;
            } else {
                dice1Selected = false;
                imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(0).selected = false;
            }
        }
    }
    public void clickSecondDie() {
        if(globalDice.size()==6) {
            if (!dice2Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                dice2Selected = true;
                imageViewDice2.setBackgroundColor(Color.BLUE);
                globalDice.get(1).selected = true;
            } else {
                dice2Selected = false;
                imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(1).selected = false;
            }
        }
    }

    public void clickThirdDie() {
        if(globalDice.size()==6) {
            if (!dice3Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                dice3Selected = true;
                imageViewDice3.setBackgroundColor(Color.BLUE);
                globalDice.get(2).selected = true;
            } else {
                dice3Selected = false;
                imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(2).selected = false;
            }
        }
    }

    public void clickFourthDie() {
        if(globalDice.size()==6) {
            if (!dice4Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                dice4Selected = true;
                imageViewDice4.setBackgroundColor(Color.BLUE);
                globalDice.get(3).selected = true;
            } else {
                dice4Selected = false;
                imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(3).selected = false;
            }
        }
    }

    public void clickFifthDie() {
        if(globalDice.size()==6) {
            if (!dice5Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                dice5Selected = true;
                imageViewDice5.setBackgroundColor(Color.BLUE);
                globalDice.get(4).selected = true;
            } else {
                dice5Selected = false;
                imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(4).selected = false;
            }
        }
    }

    public void clickSixthDie() {
        if(globalDice.size()==6) {
            if (!dice6Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                dice6Selected = true;
                imageViewDice6.setBackgroundColor(Color.BLUE);
                globalDice.get(5).selected = true;
            } else {
                dice6Selected = false;
                imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
                globalDice.get(5).selected = false;
            }
        }
    }

    public void clickThrow(Spinner spinner, TextView txtThrows, TextView txtRounds, Button btnThrow) {
        enableDiceImage();
        ArrayList<ImageView> selectedDicesImages = new ArrayList<>();
        if (dice1Selected) {
            selectedDicesImages.add(imageViewDice1);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(0);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (dice2Selected) {
            selectedDicesImages.add(imageViewDice2);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(1);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (dice3Selected) {
            selectedDicesImages.add(imageViewDice3);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(2);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (dice4Selected) {
            selectedDicesImages.add(imageViewDice4);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(3);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (dice5Selected) {
            selectedDicesImages.add(imageViewDice5);
            if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                Die die = globalDice.get(4);
                if (!die.selected) {
                    die.selected = true;
                }
            }
        }
        if (dice6Selected) {
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
        dice1Selected = false;
        dice2Selected = false;
        dice3Selected = false;
        dice4Selected = false;
        dice5Selected = false;
        dice6Selected = false;

        imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
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
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Die die = new Die(false, randomNumber);
                        globalDice.add(die);
                    } else {
                        for (Die die : globalDice) {
                            if (die.selected) {
                                die.selected = false;
                                die.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white1);
                    break;
                case 2:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Die die = new Die(false, randomNumber);
                        globalDice.add(die);
                    } else {
                        for (Die die : globalDice) {
                            if (die.selected) {
                                die.selected = false;
                                die.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white2);
                    break;
                case 3:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Die die = new Die(false, randomNumber);
                        globalDice.add(die);
                    } else {
                        for (Die die : globalDice) {
                            if (die.selected) {
                                die.selected = false;
                                die.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white3);
                    break;
                case 4:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Die die = new Die(false, randomNumber);
                        globalDice.add(die);
                    } else {
                        for (Die die : globalDice) {
                            if (die.selected) {
                                die.selected = false;
                                die.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white4);
                    break;
                case 5:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Die die = new Die(false, randomNumber);
                        globalDice.add(die);
                    } else {
                        for (Die die : globalDice) {
                            if (die.selected) {
                                die.selected = false;
                                die.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white5);
                    break;
                case 6:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Die die = new Die(false, randomNumber);
                        globalDice.add(die);
                    } else {
                        for (Die die : globalDice) {
                            if (die.selected) {
                                die.selected = false;
                                die.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white6);
                    break;
            }
            Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
            imageViewDice.startAnimation(rotate);
        }
        GlobalDiceNumbers.triesAndDiceNumbers.put(RoundsLogic.totalNumberOfThrowsDisplayed, globalDice);
    }
}
