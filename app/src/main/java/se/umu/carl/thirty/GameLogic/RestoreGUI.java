package se.umu.carl.thirty.GameLogic;

import android.widget.ImageView;

import java.util.ArrayList;

import se.umu.carl.thirty.Models.Die;
import se.umu.carl.thirty.R;

public class RestoreGUI {

    public static void restoreDiceImageResource(ArrayList<Die> globalDice, ImageView firstDieImageView,
                                                ImageView secondImageView,
                                                ImageView thirdImageView,
                                                ImageView fourthImageView,
                                                ImageView fifthImageView,
                                                ImageView sixthImageView
    ) {
        if (globalDice.size() == 6) {
            for (Die die : globalDice) {
                if (globalDice.get(0).value == 1) {
                    firstDieImageView.setImageResource(R.drawable.white1);
                    break;
                } else if (globalDice.get(0).value == 2) {
                    firstDieImageView.setImageResource(R.drawable.white2);
                    break;
                } else if (globalDice.get(0).value == 3) {
                    firstDieImageView.setImageResource(R.drawable.white3);
                    break;

                } else if (globalDice.get(0).value == 4) {
                    firstDieImageView.setImageResource(R.drawable.white4);
                    break;

                } else if (globalDice.get(0).value == 5) {
                    firstDieImageView.setImageResource(R.drawable.white5);
                    break;

                } else if (globalDice.get(0).value == 6) {
                    firstDieImageView.setImageResource(R.drawable.white6);
                    break;
                }
            }
        }
    }
}
