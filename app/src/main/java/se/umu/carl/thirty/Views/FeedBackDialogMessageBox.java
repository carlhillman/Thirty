package se.umu.carl.thirty.Views;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import se.umu.carl.thirty.R;

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
            feedBackDialog.title = activity.getResources().getString(R.string.succeededTitle) + score;
            feedBackDialog.message = activity.getResources().getString(R.string.succeededMessage);
            //     FeedBackDialog.newInstance("Tagna poäng: ", score, "Kasta för att börja nästa runda");
            feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void showNoDieSelected() {
        try {
            FeedBackDialog feedBackDialog = new FeedBackDialog();
            feedBackDialog.title = activity.getResources().getString(R.string.failedTitle);
            feedBackDialog.message = activity.getResources().getString(R.string.failedMessage);
            //sätter till en otillåten poäng
            feedBackDialog.score = -1;
            feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
