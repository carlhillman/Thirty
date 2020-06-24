package se.umu.carl.thirty;

import android.widget.ImageView;
import java.util.ArrayList;

public class DiceLogic{
    static ArrayList<ImageView>selectedDicesImages = new ArrayList<>();
    //håller koll på vilka tärningar som ska kastas för den nya rundan
    protected static ArrayList<ImageView>getSelectedDicesImages(){
        return selectedDicesImages;
    }
}
