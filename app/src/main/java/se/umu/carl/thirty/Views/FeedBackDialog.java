package se.umu.carl.thirty.Views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import se.umu.carl.thirty.R;

//Dialog fragment som skapas.
public class FeedBackDialog extends AppCompatDialogFragment {
    protected static final String TAG = "FeedBackDialog";
    protected String title;
    protected String message;
    protected Integer score;
    protected boolean gameIsOver = false;

    /**
     * Återställer titel, meddelande när användaren tagit poäng
     * @param score - poäng tagna
     */
    public void restoreSucceededMessage(int score) {
        title = getResources().getString(R.string.succeededTitle) + score;
        message = getResources().getString(R.string.succeededMessage);

    }
    /**
     * Återställer meddelande för användaren att spelet är slut
     * @param score - poäng tagna
     */
    public void restoreGameOverMessage(int score){
        title = getResources().getString(R.string.succeededTitle) + score;
        message = getResources().getString(R.string.gameOverMessage);
    }
    /**
     * Återställer meddelande för användaren att ingen tärning har valts
     */
    public void restoreNoDieChosenMessage() {
        title = getResources().getString(R.string.failedTitle);
        message = getResources().getString(R.string.failedMessage);
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("currentScore");
            gameIsOver = savedInstanceState.getBoolean("gameIsOver");
            if (score == -1) {
                restoreNoDieChosenMessage();
            }
            else if(score >= 0 && !gameIsOver){
                restoreSucceededMessage(score);
            }
            else if(gameIsOver){
                restoreGameOverMessage(score);
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentScore", score);
        outState.putBoolean("gameIsOver", gameIsOver);
    }
}


