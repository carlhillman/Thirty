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

    public void setRestoreSucceededMessage(int score) {
        title = getResources().getString(R.string.succeededTitle) + score;
        message = getResources().getString(R.string.succeededMessage);

    }

    public void setRestoreNoDieChosenMessage() {
        title = getResources().getString(R.string.failedTitle);
        message = getResources().getString(R.string.failedMessage);
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("currentScore");
            if (score == -1) {
                setRestoreNoDieChosenMessage();
            } else {
                setRestoreSucceededMessage(score);
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
    }
}


