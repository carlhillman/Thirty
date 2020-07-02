package se.umu.carl.thirty.Views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FeedBackDialog extends AppCompatDialogFragment {
    protected static final String TAG = "FeedBackDialog";
    private String title;
    private String message;

    public FeedBackDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}


