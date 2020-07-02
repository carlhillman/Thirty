package se.umu.carl.thirty.GameLogic;

import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

import se.umu.carl.thirty.Models.Die;
import se.umu.carl.thirty.R;
import se.umu.carl.thirty.Views.ChoicePointsSpinner;

public class RestoreGUI {

   /* public static void restoreSpinner(Spinner spinner) {
        ChoicePointsSpinner.retrieveAllItems(spinner);
        spinner.setVisibility(View.VISIBLE);
    }

    */

    public static void restoreDiceImageResource(ArrayList<Die> globalDice, ImageView firstDieImageView,
                                                ImageView secondImageView,
                                                ImageView thirdImageView,
                                                ImageView fourthImageView,
                                                ImageView fifthImageView,
                                                ImageView sixthImageView) {
        if (globalDice.size() == 6) {
            restoreFirstDieImage(globalDice, firstDieImageView);
            restoreSecondDieImage(globalDice, secondImageView);
            restoreThirdDieImage(globalDice, thirdImageView);
            restoreFourthDieImage(globalDice, fourthImageView);
            restoreFifthDieImage(globalDice, fifthImageView);
            restoreSixthDieImage(globalDice, sixthImageView);
        }
    }

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


