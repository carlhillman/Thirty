package se.umu.carl.thirty.Views;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import se.umu.carl.thirty.GameLogic.RoundsLogic;
import se.umu.carl.thirty.R;

public class FeedBackDialogMessageBox {
    Activity activity;
    RoundsLogic roundsLogic;

    public FeedBackDialogMessageBox(Activity activity, RoundsLogic roundsLogic) {
        this.activity = activity;
        this.roundsLogic = roundsLogic;
    }

    private FragmentManager getSupportFragmentManager() {
        return ((FragmentActivity) activity).getSupportFragmentManager();
    }

    /**
     * Sätter titel, meddelande och poäng för FeedBackDialog när användaren tagit poäng
     *
     * @param score - poäng tagna
     */
    public void showRoundSucceededDialog(int score) {
        try {
            FeedBackDialog feedBackDialog = new FeedBackDialog();
            feedBackDialog.score = score;
            feedBackDialog.title = activity.getResources().getString(R.string.succeededTitle) + score;
            if (roundsLogic.getAndSetGameOver()) {
                feedBackDialog.message = activity.getResources().getString(R.string.gameOverMessage);
                feedBackDialog.gameIsOver = true;
            } else {
                feedBackDialog.message = activity.getResources().getString(R.string.succeededMessage);
            }
            feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Varnar ifall ingen tärning är vald för användaren när denne ska ta sina poäng
     */
    public void showNoDieSelected() {
        try {
            FeedBackDialog feedBackDialog = new FeedBackDialog();
            feedBackDialog.title = activity.getResources().getString(R.string.failedTitle);
            feedBackDialog.message = activity.getResources().getString(R.string.failedMessage);
            //sätter till en otillåten poäng
            feedBackDialog.score = -1;
            feedBackDialog.show(getSupportFragmentManager(), FeedBackDialog.TAG);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
