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

    public static boolean isBtnTakePointsDisplayed = false;
    public static boolean isBtnThrowDisplayed = false;
    public static boolean inChoosingPointProgress = false;
    public static boolean isDiceImageViewEnabled = false;

    /**
     * metod som gömmer eller visar Button btnThrow beroende på spelets nuvarande status
     *
     * @param btnThrow
     */

    public static void setBtnThrowVisibility(Button btnThrow) {
        if (RoundsLogic.totalNumberOfThrowsDisplayed == 3) {
            btnThrow.setVisibility(View.GONE);
        }
        if (inChoosingPointProgress || ScoreLogic.pointTypeChosen) {
            btnThrow.setVisibility(View.GONE);
        }
        if (ScoreLogic.pointTypeChosen) {
            btnThrow.setVisibility(View.VISIBLE);
        }
    }

    /**
     * metod som gömmer eller visar Button btnTakePoints beroende på spelets nuvarande status
     *
     * @param btnTakePoints
     */
    public static void setBtnTakePointsVisibility(Button btnTakePoints) {
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
     * @param spinner
     */
    public static void setSpinnerState(Spinner spinner) {
        if (RoundsLogic.totalNumberOfThrowsDisplayed >= 1) {
            spinner.setVisibility(View.VISIBLE);
            spinner.setEnabled(true);
        }
        if (inChoosingPointProgress) {
            spinner.setEnabled(false);
        }
        if (ScoreLogic.pointTypeChosen) {
            spinner.setVisibility(View.GONE);
        }
    }

    /**
     * metod som gömmer kast knappen när spelet är slut
     *
     * @param btnThrow
     */
    public static void hideButtonOnResultFragmentOrientationChange(Button btnThrow) {
        if (RoundsLogic.getAndSetGameOver()) {
            btnThrow.setVisibility(View.GONE);
        }
    }


    /**
     * metod som sätter tillbaka den blå färgen runt tärningsbilderna
     *
     * @param isFirstDieSelected
     * @param isSecondDieSelected
     * @param isThirdDieSelected
     * @param isFourthDieSelected
     * @param isFifthDieSelected
     * @param isSixthDieSelected
     * @param firstDieImageView
     * @param secondDieImageView
     * @param thirdDieImageView
     * @param fourthDieImageView
     * @param fifthDieImageView
     * @param sixthDieImageView
     */
    public static void setDieBackgroundColor(boolean isFirstDieSelected, boolean isSecondDieSelected, boolean isThirdDieSelected,
                                             boolean isFourthDieSelected, boolean isFifthDieSelected, boolean isSixthDieSelected,
                                             ImageView firstDieImageView,
                                             ImageView secondDieImageView,
                                             ImageView thirdDieImageView,
                                             ImageView fourthDieImageView,
                                             ImageView fifthDieImageView,
                                             ImageView sixthDieImageView

    ) {

        setFirstDieImageViewColor(isFirstDieSelected, firstDieImageView);

        setSecondDieImageViewColor(isSecondDieSelected, secondDieImageView);

        setThirdDieImageViewColor(isThirdDieSelected, thirdDieImageView);

        setFourthDieImageViewColor(isFourthDieSelected, fourthDieImageView);

        setFifthDieImageViewColor(isFifthDieSelected, fifthDieImageView);

        setSixthDieImageViewColor(isSixthDieSelected, sixthDieImageView);
    }

    private static void setFirstDieImageViewColor(boolean isFirstDieSelected, ImageView firstDieImageView) {
        if (isFirstDieSelected && inChoosingPointProgress) {
            firstDieImageView.setBackgroundColor(Color.BLUE);
        } else if (!isFirstDieSelected && inChoosingPointProgress) {
            firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isFirstDieSelected) {
            firstDieImageView.setBackgroundColor(Color.BLUE);
        } else {
            firstDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static void setSecondDieImageViewColor(boolean isSecondDieSelected, ImageView secondDieImageView) {
        if (isSecondDieSelected && inChoosingPointProgress) {
            secondDieImageView.setBackgroundColor(Color.BLUE);
        } else if (!isSecondDieSelected && inChoosingPointProgress) {
            secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isSecondDieSelected) {
            secondDieImageView.setBackgroundColor(Color.BLUE);
        } else {
            secondDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static void setThirdDieImageViewColor(boolean isThirdDieSelected, ImageView thirdDieImageView) {
        if (isThirdDieSelected && inChoosingPointProgress) {
            thirdDieImageView.setBackgroundColor(Color.BLUE);
        } else if (!isThirdDieSelected && inChoosingPointProgress) {
            thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isThirdDieSelected) {
            thirdDieImageView.setBackgroundColor(Color.BLUE);
        } else {
            thirdDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static void setFourthDieImageViewColor(boolean isFourthDieSelected, ImageView fourthDieImageView) {
        if (isFourthDieSelected && inChoosingPointProgress) {
            fourthDieImageView.setBackgroundColor(Color.BLUE);
        } else if (!isFourthDieSelected && inChoosingPointProgress) {
            fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isFourthDieSelected) {
            fourthDieImageView.setBackgroundColor(Color.BLUE);
        } else {
            fourthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static void setFifthDieImageViewColor(boolean isFifthDieSelected, ImageView fifthDieImageView) {
        if (isFifthDieSelected && inChoosingPointProgress) {
            fifthDieImageView.setBackgroundColor(Color.BLUE);
        } else if (!isFifthDieSelected && inChoosingPointProgress) {
            fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isFifthDieSelected) {
            fifthDieImageView.setBackgroundColor(Color.BLUE);
        } else {
            fifthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static void setSixthDieImageViewColor(boolean isSixthDieSelected, ImageView sixthDieImageView) {
        if (isSixthDieSelected && inChoosingPointProgress) {
            sixthDieImageView.setBackgroundColor(Color.BLUE);
        } else if (!isSixthDieSelected && inChoosingPointProgress) {
            sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        } else if (!isSixthDieSelected) {
            sixthDieImageView.setBackgroundColor(Color.BLUE);
        } else {
            sixthDieImageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    /**
     * Sätter tillbaka vilka tärnings bilder som ska visa för respektive ImageView
     *
     * @param globalDice
     * @param firstDieImageView
     * @param secondDieImageView
     * @param thirdDieImageView
     * @param fourthDieImageView
     * @param fifthDieImageView
     * @param sixthDieImageView
     */
    public static void restoreDiceImageResource(ArrayList<Die> globalDice, ImageView firstDieImageView,
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
     * @param globalDice
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
     * @param globalDice
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
     * @param globalDice
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
     * @param globalDice
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
     * @param globalDice
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
     * @param globalDice
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


