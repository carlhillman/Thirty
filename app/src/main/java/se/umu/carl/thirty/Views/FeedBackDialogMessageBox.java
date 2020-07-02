package se.umu.carl.thirty.Views;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class FeedBackDialogMessageBox {
    Activity activity;
    public FeedBackDialogMessageBox(Activity activity){
        this.activity = activity;
    }
    //alla dialog rutor för specifika användarfel
    private  FragmentManager getSupportFragmentManager(){
        return ((FragmentActivity)activity).getSupportFragmentManager();
    }

    public void showRoundSucceededDialog(int score) {
        FeedBackDialog feedBackDialog = new FeedBackDialog("Tagna poäng: " + score, "Kasta för att börja nästa runda");
        feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
    }

    public void showNoDieSelected() {
        FeedBackDialog feedBackDialog = new FeedBackDialog("Ingen tärning vald!", "Välj en tärning/tärningar");
        feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
    }
}
