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
        FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
        return fragmentManager;
    }

    public void showRoundSucceededDialog(int score) {
        FeedBackDialog feedBackDialog = new FeedBackDialog("Tagna poäng: " + score, "Kasta för att börja nästa runda");
        feedBackDialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    public void showNoDieSelected() {
        FeedBackDialog feedBackDialog = new FeedBackDialog("Ingen tärning vald!", "Du kan inte ta någon poäng om du inte valt någon tärning!");
        feedBackDialog.show(getSupportFragmentManager(), "CustomDialog");
    }
}
