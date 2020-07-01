package se.umu.carl.thirty.Views;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MessageBox {
    Activity activity;
    public MessageBox(Activity activity){
        this.activity = activity;
    }
    //alla dialog rutor för specifika användarfel
    private  FragmentManager getSupportFragmentManager(){
        FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
        return fragmentManager;
    }

    public void showRoundSucceededDialog(int score) {
        CustomDialog customDialog = new CustomDialog("Tagna poäng: " + score, "Kasta för att börja nästa runda");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    public void showNoDieSelected() {
        CustomDialog customDialog = new CustomDialog("Ingen tärning vald!", "Du kan inte ta någon poäng om du inte valt någon tärning!");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }
}
