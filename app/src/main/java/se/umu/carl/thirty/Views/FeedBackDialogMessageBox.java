package se.umu.carl.thirty.Views;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class FeedBackDialogMessageBox {
    Activity activity;

    public FeedBackDialogMessageBox(Activity activity) {
        this.activity = activity;
    }

    //alla dialog rutor för specifika användarfel
    private FragmentManager getSupportFragmentManager() {
        return ((FragmentActivity) activity).getSupportFragmentManager();
    }

    public void showRoundSucceededDialog(int score) {
        try {
            FeedBackDialog feedBackDialog = new FeedBackDialog();
            feedBackDialog.score = score;
            feedBackDialog.title = "Tagna poäng: " + score;
            feedBackDialog.message = "Kasta för att börja nästa runda";
            //     FeedBackDialog.newInstance("Tagna poäng: ", score, "Kasta för att börja nästa runda");
            feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void setSucceededMessageOnRestore(int score){
        FeedBackDialog feedBackDialog = new FeedBackDialog();
        feedBackDialog.score = score;
        feedBackDialog.title = "Tagna poäng: " + feedBackDialog.score;
        feedBackDialog.message = "Kasta för att börja nästa runda";
    }

    public void showNoDieSelected() {

        FeedBackDialog feedBackDialog = new FeedBackDialog();
        feedBackDialog.title = "Ingen tärning vald!";
        feedBackDialog.message = "Välj en tärning/tärningar";
        feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);

    }
}
