package se.umu.carl.thirty.GameLogic;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

import se.umu.carl.thirty.Models.Die;
import se.umu.carl.thirty.R;

// En hjälp klass som hämtar tillbaka GUI komponenter vid skärmrotation
public class RestoreGUIManager {

    public boolean isBtnTakePointsDisplayed = false;
    public boolean isBtnThrowDisplayed = false;
    public boolean inChoosingPointProgress = false;
    public boolean isDiceImageViewEnabled = false;
    RoundsLogic roundsLogic;

    public RestoreGUIManager(RoundsLogic roundsLogic) {
        this.roundsLogic = roundsLogic;
    }

    /**
     * metod som gömmer eller visar Button btnThrow beroende på spelets nuvarande status
     *
     * @param btnThrow - kast knappen
     */

    public void setBtnThrowVisibility(Button btnThrow, int numberOfBlueDice) {
        if (roundsLogic.totalNumberOfThrowsDisplayed == 3) {
            btnThrow.setVisibility(View.GONE);
        }
        if (inChoosingPointProgress || roundsLogic.pointTypeChosen || numberOfBlueDice == 6) {
            btnThrow.setVisibility(View.GONE);
        }
        if (roundsLogic.pointTypeChosen) {
            btnThrow.setVisibility(View.VISIBLE);
        }
    }

    /**
     * metod som gömmer eller visar Button btnTakePoints beroende på spelets nuvarande status
     *
     * @param btnTakePoints - ta poäng knappen
     */
    public void setBtnTakePointsVisibility(Button btnTakePoints) {
        if (inChoosingPointProgress) {
            btnTakePoints.setVisibility(View.VISIBLE);
        } else {
            btnTakePoints.setVisibility(View.GONE);
        }
    }

    /**
     * metod som gömmer eller visar samt enablar eller disablar Spinner spinner
     * beroende på spelets nuvarande status
     *
     * @param spinner - spinner med poängval
     */
    public void setSpinnerState(Spinner spinner) {
        if (roundsLogic.totalNumberOfThrowsDisplayed >= 1) {
            spinner.setVisibility(View.VISIBLE);
            spinner.setEnabled(true);
        }
        if (inChoosingPointProgress) {
            spinner.setEnabled(false);
        }
        if (roundsLogic.pointTypeChosen) {
            spinner.setVisibility(View.GONE);
        }
    }

    /**
     * metod som gömmer kast knappen när spelet är slut
     *
     * @param btnThrow - kast knappen
     */
    public void hideButtonOnResultFragmentOrientationChange(Button btnThrow) {
        if (roundsLogic.getAndSetGameOver()) {
            btnThrow.setVisibility(View.GONE);
        }
    }


    /**
     * metod som anropar metoderna setFirstDieImageViewColor, setSecondDieImageViewColor, setThirdDieImageViewColor
     * setFourthDieImageViewColor, setFifthDieImageViewColor och setSixthDieImageViewColor
     *
     * @param isFirstDieSelected  - boolean för första tärningsbilden
     * @param isSecondDieSelected - boolean för andra tärningsbilden
     * @param isThirdDieSelected  - boolean för tredje tärningsbilden
     * @param isFourthDieSelected - boolean för fjärde tärningsbilden
     * @param isFifthDieSelected  - boolean för femte tärningsbilden
     * @param isSixthDieSelected  - boolean för första tärningsbilden
     * @param firstDieImageView   - imageview för första tärningsbilden
     * @param secondDieImageView  - imageview för andra tärningsbilden
     * @param thirdDieImageView   - imageview för tredje tärningsbilden
     * @param fourthDieImageView  - imageview för fjärde tärningsbilden
     * @param fifthDieImageView   - imageview för femte tärningsbilden
     * @param sixthDieImageView   - imageview för sjätte tärningsbilden
     */
    public void setDieBackgroundColor(boolean isFirstDieSelected, boolean isSecondDieSelected, boolean isThirdDieSelected,
                                      boolean isFourthDieSelected, boolean isFifthDieSelected, boolean isSixthDieSelected,
                                      ImageView firstDieImageView,
                                      ImageView secondDieImageView,
                                      ImageView thirdDieImageView,
                                      ImageView fourthDieImageView,
                                      ImageView fifthDieImageView,
                                      ImageView sixthDieImageView, ArrayList<Die> dice

    ) {

        setFirstDieImageViewColor(isFirstDieSelected, firstDieImageView, dice);

        setSecondDieImageViewColor(isSecondDieSelected, secondDieImageView, dice);

        setThirdDieImageViewColor(isThirdDieSelected, thirdDieImageView, dice);

        setFourthDieImageViewColor(isFourthDieSelected, fourthDieImageView, dice);

        setFifthDieImageViewColor(isFifthDieSelected, fifthDieImageView, dice);

        setSixthDieImageViewColor(isSixthDieSelected, sixthDieImageView, dice);
    }

    /**
     * metod som beroende på första tärningens tillstånd sätter en blåmarkerad färg runt bilderna och bestämmer om tärningen utifrån
     * en ArrayList anses vara vald eller inte
     *
     * @param isFirstDieSelected
     * @param firstDieImageView
     * @param dice               - lista med tärningar/Die objekt
     */
    private void setFirstDieImageViewColor(boolean isFirstDieSelected, ImageView firstDieImageView, ArrayList<Die> dice) {
        if (isFirstDieSelected && inChoosingPointProgress) {
            firstDieImageView.setBackgroundColor(Color.BLUE);
            dice.get(0).selected = true;
        } else if (!isFirstDieSelected && inChoosingPointProgress) {
            firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isFirstDieSelected) {
            if (roundsLogic.pointTypeChosen) {
                firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                firstDieImageView.setBackgroundColor(Color.BLUE);
            }
        } else {
            firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * metod som beroende på andra tärningens tillstånd sätter en blåmarkerad färg runt bilderna och bestämmer om tärningen utifrån
     * en ArrayList anses vara vald eller inte
     *
     * @param isSecondDieSelected
     * @param secondDieImageView
     * @param dice                - lista med tärningar/Die objekt
     */
    private void setSecondDieImageViewColor(boolean isSecondDieSelected, ImageView secondDieImageView, ArrayList<Die> dice) {
        if (isSecondDieSelected && inChoosingPointProgress) {
            secondDieImageView.setBackgroundColor(Color.BLUE);
            dice.get(1).selected = true;
        } else if (!isSecondDieSelected && inChoosingPointProgress) {
            secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isSecondDieSelected) {
            if (roundsLogic.pointTypeChosen) {
                secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                secondDieImageView.setBackgroundColor(Color.BLUE);
            }
        } else {
            secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * metod som beroende på tredje tärningens tillstånd sätter en blåmarkerad färg runt bilderna och bestämmer om tärningen utifrån
     * en ArrayList anses vara vald eller inte
     *
     * @param isThirdDieSelected
     * @param thirdDieImageView
     * @param dice               - lista med tärningar/Die objekt
     */
    private void setThirdDieImageViewColor(boolean isThirdDieSelected, ImageView thirdDieImageView, ArrayList<Die> dice) {
        if (isThirdDieSelected && inChoosingPointProgress) {
            thirdDieImageView.setBackgroundColor(Color.BLUE);
            dice.get(2).selected = true;
        } else if (!isThirdDieSelected && inChoosingPointProgress) {
            thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isThirdDieSelected) {
            if (roundsLogic.pointTypeChosen) {
                thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                thirdDieImageView.setBackgroundColor(Color.BLUE);
            }
        } else {
            thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * metod som beroende på fjärde tärningens tillstånd sätter en blåmarkerad färg runt bilderna och bestämmer om tärningen utifrån
     * en ArrayList anses vara vald eller inte
     *
     * @param isFourthDieSelected
     * @param fourthDieImageView
     * @param dice                - lista med tärningar/Die objekt
     */
    private void setFourthDieImageViewColor(boolean isFourthDieSelected, ImageView fourthDieImageView, ArrayList<Die> dice) {
        if (isFourthDieSelected && inChoosingPointProgress) {
            fourthDieImageView.setBackgroundColor(Color.BLUE);
            dice.get(3).selected = true;
        } else if (!isFourthDieSelected && inChoosingPointProgress) {
            fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isFourthDieSelected) {
            if (roundsLogic.pointTypeChosen) {
                fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                fourthDieImageView.setBackgroundColor(Color.BLUE);
            }
        } else {
            fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * metod som beroende på femte tärningens tillstånd sätter en blåmarkerad färg runt bilderna och bestämmer om tärningen utifrån
     * en ArrayList anses vara vald eller inte
     *
     * @param isFifthDieSelected
     * @param fifthDieImageView
     * @param dice               - lista med tärningar/Die objekt
     */
    private void setFifthDieImageViewColor(boolean isFifthDieSelected, ImageView fifthDieImageView, ArrayList<Die> dice) {
        if (isFifthDieSelected && inChoosingPointProgress) {
            fifthDieImageView.setBackgroundColor(Color.BLUE);
            dice.get(4).selected = true;
        } else if (!isFifthDieSelected && inChoosingPointProgress) {
            fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isFifthDieSelected) {
            if (roundsLogic.pointTypeChosen) {
                fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                fifthDieImageView.setBackgroundColor(Color.BLUE);
            }
        } else {
            fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * metod som beroende på sjätte tärningens tillstånd sätter en blåmarkerad färg runt bilderna och bestämmer om tärningen utifrån
     * en ArrayList anses vara vald eller inte
     *
     * @param isSixthDieSelected
     * @param sixthDieImageView
     * @param dice               - lista med tärningar/Die objekt
     */
    private void setSixthDieImageViewColor(boolean isSixthDieSelected, ImageView sixthDieImageView, ArrayList<Die> dice) {
        if (isSixthDieSelected && inChoosingPointProgress) {
            sixthDieImageView.setBackgroundColor(Color.BLUE);
            dice.get(5).selected = true;
        } else if (!isSixthDieSelected && inChoosingPointProgress) {
            sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isSixthDieSelected) {
            if (roundsLogic.pointTypeChosen) {
                sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                sixthDieImageView.setBackgroundColor(Color.BLUE);
            }
        } else {
            sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    /**
     * Sätter tillbaka vilka tärnings bilder/bild resurs som ska visa för respektive ImageView
     *
     * @param globalDice         - lista med tärningar/Die objekt
     * @param firstDieImageView
     * @param secondDieImageView
     * @param thirdDieImageView
     * @param fourthDieImageView
     * @param fifthDieImageView
     * @param sixthDieImageView
     */
    public void restoreDiceImageResource(ArrayList<Die> globalDice, ImageView firstDieImageView,
                                                ImageView secondDieImageView,
                                                ImageView thirdDieImageView,
                                                ImageView fourthDieImageView,
                                                ImageView fifthDieImageView,
                                                ImageView sixthDieImageView) {
        if (globalDice.size() == 6) {
            restoreFirstDieImage(globalDice, firstDieImageView);
            restoreSecondDieImage(globalDice, secondDieImageView);
            restoreThirdDieImage(globalDice, thirdDieImageView);
            restoreFourthDieImage(globalDice, fourthDieImageView);
            restoreFifthDieImage(globalDice, fifthDieImageView);
            restoreSixthDieImage(globalDice, sixthDieImageView);
        }
    }

    /**
     * Bestämer vilken bild som ska visas för den första tärningen
     *
     * @param globalDice   - lista med tärningar/Die objekt
     * @param dieImageView
     */
    private static void restoreFirstDieImage(ArrayList<Die> globalDice, ImageView dieImageView) {
        if (globalDice.get(0).value == 1) {
            dieImageView.setImageResource(R.drawable.white1);
        } else if (globalDice.get(0).value == 2) {
            dieImageView.setImageResource(R.drawable.white2);
        } else if (globalDice.get(0).value == 3) {
            dieImageView.setImageResource(R.drawable.white3);
        } else if (globalDice.get(0).value == 4) {
            dieImageView.setImageResource(R.drawable.white4);
        } else if (globalDice.get(0).value == 5) {
            dieImageView.setImageResource(R.drawable.white5);
        } else if (globalDice.get(0).value == 6) {
            dieImageView.setImageResource(R.drawable.white6);
        }
    }

    /**
     * Bestämer vilken bild som ska visas för den andra tärningen
     *
     * @param globalDice   - lista med tärningar/Die objekt
     * @param dieImageView
     */
    private static void restoreSecondDieImage(ArrayList<Die> globalDice, ImageView dieImageView) {
        if (globalDice.get(1).value == 1) {
            dieImageView.setImageResource(R.drawable.white1);
        } else if (globalDice.get(1).value == 2) {
            dieImageView.setImageResource(R.drawable.white2);
        } else if (globalDice.get(1).value == 3) {
            dieImageView.setImageResource(R.drawable.white3);
        } else if (globalDice.get(1).value == 4) {
            dieImageView.setImageResource(R.drawable.white4);
        } else if (globalDice.get(1).value == 5) {
            dieImageView.setImageResource(R.drawable.white5);
        } else if (globalDice.get(1).value == 6) {
            dieImageView.setImageResource(R.drawable.white6);
        }
    }

    /**
     * Bestämer vilken bild som ska visas för den tredje tärningen
     *
     * @param globalDice   - lista med tärningar/Die objekt
     * @param dieImageView
     */
    private static void restoreThirdDieImage(ArrayList<Die> globalDice, ImageView dieImageView) {
        if (globalDice.get(2).value == 1) {
            dieImageView.setImageResource(R.drawable.white1);
        } else if (globalDice.get(2).value == 2) {
            dieImageView.setImageResource(R.drawable.white2);
        } else if (globalDice.get(2).value == 3) {
            dieImageView.setImageResource(R.drawable.white3);
        } else if (globalDice.get(2).value == 4) {
            dieImageView.setImageResource(R.drawable.white4);
        } else if (globalDice.get(2).value == 5) {
            dieImageView.setImageResource(R.drawable.white5);
        } else if (globalDice.get(2).value == 6) {
            dieImageView.setImageResource(R.drawable.white6);
        }
    }

    /**
     * Bestämer vilken bild som ska visas för den fjärde tärningen
     *
     * @param globalDice   - lista med tärningar/Die objekt
     * @param dieImageView
     */
    private static void restoreFourthDieImage(ArrayList<Die> globalDice, ImageView dieImageView) {
        if (globalDice.get(3).value == 1) {
            dieImageView.setImageResource(R.drawable.white1);
        } else if (globalDice.get(3).value == 2) {
            dieImageView.setImageResource(R.drawable.white2);
        } else if (globalDice.get(3).value == 3) {
            dieImageView.setImageResource(R.drawable.white3);
        } else if (globalDice.get(3).value == 4) {
            dieImageView.setImageResource(R.drawable.white4);
        } else if (globalDice.get(3).value == 5) {
            dieImageView.setImageResource(R.drawable.white5);
        } else if (globalDice.get(3).value == 6) {
            dieImageView.setImageResource(R.drawable.white6);
        }
    }

    /**
     * Bestämer vilken bild som ska visas för den femte tärningen
     *
     * @param globalDice   - lista med tärningar/Die objekt
     * @param dieImageView
     */
    private static void restoreFifthDieImage(ArrayList<Die> globalDice, ImageView dieImageView) {
        if (globalDice.get(4).value == 1) {
            dieImageView.setImageResource(R.drawable.white1);
        } else if (globalDice.get(4).value == 2) {
            dieImageView.setImageResource(R.drawable.white2);
        } else if (globalDice.get(4).value == 3) {
            dieImageView.setImageResource(R.drawable.white3);
        } else if (globalDice.get(4).value == 4) {
            dieImageView.setImageResource(R.drawable.white4);
        } else if (globalDice.get(4).value == 5) {
            dieImageView.setImageResource(R.drawable.white5);
        } else if (globalDice.get(4).value == 6) {
            dieImageView.setImageResource(R.drawable.white6);
        }
    }

    /**
     * Bestämer vilken bild som ska visas för den sjätte tärningen
     *
     * @param globalDice   - lista med tärningar/Die objekt
     * @param dieImageView
     */
    private static void restoreSixthDieImage(ArrayList<Die> globalDice, ImageView dieImageView) {
        if (globalDice.get(5).value == 1) {
            dieImageView.setImageResource(R.drawable.white1);
        } else if (globalDice.get(5).value == 2) {
            dieImageView.setImageResource(R.drawable.white2);
        } else if (globalDice.get(5).value == 3) {
            dieImageView.setImageResource(R.drawable.white3);
        } else if (globalDice.get(5).value == 4) {
            dieImageView.setImageResource(R.drawable.white4);
        } else if (globalDice.get(5).value == 5) {
            dieImageView.setImageResource(R.drawable.white5);
        } else if (globalDice.get(5).value == 6) {
            dieImageView.setImageResource(R.drawable.white6);
        }
    }
}


