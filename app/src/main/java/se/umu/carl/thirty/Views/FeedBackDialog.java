package se.umu.carl.thirty.Views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import se.umu.carl.thirty.GameLogic.ScoreLogic;

public class FeedBackDialog extends AppCompatDialogFragment {
    protected static final String TAG = "FeedBackDialog";
    protected String title;
    protected String message;
    protected Integer score;


    public void setRestoreMessage(int score){
        if(ScoreLogic.pointTypeChoosen){
            title = "Tagna poäng: " + score;
            message = "Kasta för att börja nästa runda";
        }
        else{
            title = "Ingen tärning vald!";
            message = "Välj en tärning/tärningar";
        }
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setRestoreMessage(   savedInstanceState.getInt("currentScore"));
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
    }
}


