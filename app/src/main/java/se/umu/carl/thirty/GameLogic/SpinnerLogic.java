package se.umu.carl.thirty.GameLogic;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

// Hjälp logik när man klickar på ett spinner val
public class SpinnerLogic {
    private Context context;
    public SpinnerLogic(Context context){
        this.context = context;
    }
    public void clickSelectedSpinnerItem(DiceLogic diceLogic, Spinner spinner, Button btnTakePoints,
                                         Button btnThrow){
        diceLogic.deselectAllDice();
        spinner.setEnabled(false);
        btnTakePoints.setVisibility(View.VISIBLE);
        RestoreGUIManager.isBtnTakePointsDisplayed = true;
        btnThrow.setVisibility(View.GONE);
        RestoreGUIManager.inChoosingPointProgress = true;
    }
}
