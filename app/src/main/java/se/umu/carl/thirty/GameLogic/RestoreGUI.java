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
public class RestoreGUI {

    public static boolean isBtnTakePointsDisplayed = false;
    public static boolean isBtnThrowDisplayed = false;
    public static boolean inChoosingPointProgress = false;
    public static boolean isDiceImageViewEnabled = false;

    public static void setBtnThrowVisibility(Button btnThrow) {
        if (RoundsLogic.totalNumberOfThrowsDisplayed == 3) {
            btnThrow.setVisibility(View.GONE);
        }
        if (inChoosingPointProgress || ScoreLogic.pointTypeChoosen) {
            btnThrow.setVisibility(View.GONE);
        }
        if (ScoreLogic.pointTypeChoosen) {
            btnThrow.setVisibility(View.VISIBLE);
        }
    }

    public static void setBtnTakePointsVisibility(Button btnTakePoints) {
        if (inChoosingPointProgress) {
            btnTakePoints.setVisibility(View.VISIBLE);
        } else {
            btnTakePoints.setVisibility(View.GONE);
        }
    }

    public static void setSpinnerState(Spinner spinner) {
        if (RoundsLogic.totalNumberOfThrowsDisplayed >= 1) {
            spinner.setVisibility(View.VISIBLE);
            spinner.setEnabled(true);
        }
        if (inChoosingPointProgress) {
            spinner.setEnabled(false);
        }
        if (ScoreLogic.pointTypeChoosen) {
            spinner.setVisibility(View.GONE);
        }
    }

    public static void setDieBackgroundColor(boolean isFirstDieSelected, boolean isSecondDieSelected, boolean isThirdDieSelected,
                                             boolean isFourthDieSelected, boolean isFifthDieSelected, boolean isSixthDieSelected,
                                             ImageView firstDieImageView,
                                             ImageView secondDieImageView,
                                             ImageView thirdDieImageView,
                                             ImageView fourthDieImageView,
                                             ImageView fifthDieImageView,
                                             ImageView sixthDieImageView

    ) {
        if (isFirstDieSelected) {
            firstDieImageView.setBackgroundColor(Color.BLUE);
        }
        if (isSecondDieSelected) {
            secondDieImageView.setBackgroundColor(Color.BLUE);
        }
        if (isThirdDieSelected) {
            thirdDieImageView.setBackgroundColor(Color.BLUE);
        }
        if (isFourthDieSelected) {
            fourthDieImageView.setBackgroundColor(Color.BLUE);
        }
        if (isFifthDieSelected) {
            fifthDieImageView.setBackgroundColor(Color.BLUE);
        }
        if (isSixthDieSelected) {
            sixthDieImageView.setBackgroundColor(Color.BLUE);
        }
    }

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


