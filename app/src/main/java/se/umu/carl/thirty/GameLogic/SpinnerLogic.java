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

    /**
     * metod som anropas när användaren klickar på ett specifikt val i spinnern
     * @param spinner - används för att disabla spinnern
     * @param btnThrow - används för att gömma kast knappen
     * @param btnTakePoints - används för att visa ta poäng knappen
     * @param diceLogic - används för att kalla på metoden deselectAllDice
     */

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
