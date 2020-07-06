package se.umu.carl.thirty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.umu.carl.thirty.Models.ResultStorage;

public class ResultFragment extends Fragment {

    TextView choiceOne;
    TextView choiceTwo;
    TextView choiceThree;
    TextView choiceFour;
    TextView choiceFive;
    TextView choiceSix;
    TextView choiceSeven;
    TextView choiceEight;
    TextView choiceNine;
    TextView choiceTen;
    TextView summaryText;

    TextView scoreOne;
    TextView scoreTwo;
    TextView scoreThree;
    TextView scoreFour;
    TextView scoreFive;
    TextView scoreSix;
    TextView scoreSeven;
    TextView scoreEight;
    TextView scoreNine;
    TextView scoreTen;
    TextView summaryScore;
    Button btnRestart;
int totalSum = 0;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        initUIElements(view);
      /*  btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getActivity().recreate();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
       */
        setColumnChoiceText();
        setColumnChoiceValueResult();
        summaryText.setText(getResources().getString(R.string.totalScore));
        summaryScore.setText(String.valueOf(totalSum));
        return view;
    }

    private void setColumnChoiceText(){
        ArrayList<String> keys = new ArrayList<>(ResultStorage.choicePoints.keySet());
        for (int index = 0; index < keys.size(); index++) {
            switch (index) {
                case 0:
                    Object key1 = keys.get(index);
                    choiceOne.setText(key1.toString());
                    break;
                case 1:
                    Object key2 = keys.get(index);
                    choiceTwo.setText(key2.toString());
                    break;
                case 2:
                    Object key3 = keys.get(index);
                    choiceThree.setText(key3.toString());
                    break;
                case 3:
                    Object key4 = keys.get(index);
                    choiceFour.setText(key4.toString());
                    break;
                case 4:
                    Object key5 = keys.get(index);
                    choiceFive.setText(key5.toString());
                    break;
                case 5:
                    Object key6 = keys.get(index);
                    choiceSix.setText(key6.toString());
                    break;
                case 6:
                    Object key7 = keys.get(index);
                    choiceSeven.setText(key7.toString());
                    break;
                case 7:
                    Object key8 = keys.get(index);
                    choiceEight.setText(key8.toString());
                    break;
                case 8:
                    Object key9 = keys.get(index);
                    choiceNine.setText(key9.toString());
                    break;
                case 9:
                    Object key10 = keys.get(index);
                    choiceTen.setText(key10.toString());
                    break;
            }
        }
    }

    private void setColumnChoiceValueResult(){
        ArrayList<Integer> values = new ArrayList<>(ResultStorage.choicePoints.values());
        totalSum = sumList(values);
        for (int index = 0; index < values.size(); index++) {
            switch (index) {
                case 0:
                    Object value1 = values.get(index);
                    scoreOne.setText(value1.toString());
                    break;
                case 1:
                    Object value2 = values.get(index);
                    scoreTwo.setText(value2.toString());
                    break;
                case 2:
                    Object value3 = values.get(index);
                    scoreThree.setText(value3.toString());
                    break;
                case 3:
                    Object value4 = values.get(index);
                    scoreFour.setText(value4.toString());
                    break;
                case 4:
                    Object value5 = values.get(index);
                    scoreFive.setText(value5.toString());
                    break;
                case 5:
                    Object value6 = values.get(index);
                    scoreSix.setText(value6.toString());
                    break;
                case 6:
                    Object value7 = values.get(index);
                    scoreSeven.setText(value7.toString());
                    break;
                case 7:
                    Object value8 = values.get(index);
                    scoreEight.setText(value8.toString());
                    break;
                case 8:
                    Object value9 = values.get(index);
                    scoreNine.setText(value9.toString());
                    break;
                case 9:
                    Object value10 = values.get(index);
                    scoreTen.setText(value10.toString());
                    break;
            }
        }
    }

    private void initUIElements(View view) {
        choiceOne = view.findViewById(R.id.choiceRow1);
        scoreOne = view.findViewById(R.id.scoreRow1);

        choiceTwo = view.findViewById(R.id.choiceRow2);
        scoreTwo = view.findViewById(R.id.scoreRow2);

        choiceThree = view.findViewById(R.id.choiceRow3);
        scoreThree = view.findViewById(R.id.scoreRow3);

        choiceFour = view.findViewById(R.id.choiceRow4);
        scoreFour = view.findViewById(R.id.scoreRow4);

        choiceFive = view.findViewById(R.id.choiceRow5);
        scoreFive = view.findViewById(R.id.scoreRow5);

        choiceSix = view.findViewById(R.id.choiceRow6);
        scoreSix = view.findViewById(R.id.scoreRow6);

        choiceSeven = view.findViewById(R.id.choiceRow7);
        scoreSeven = view.findViewById(R.id.scoreRow7);

        choiceEight = view.findViewById(R.id.choiceRow8);
        scoreEight = view.findViewById(R.id.scoreRow8);

        choiceNine = view.findViewById(R.id.choiceRow9);
        scoreNine = view.findViewById(R.id.scoreRow9);

        choiceTen = view.findViewById(R.id.choiceRow10);
        scoreTen = view.findViewById(R.id.scoreRow10);

        summaryText = view.findViewById(R.id.txtTotalScore);
        summaryScore = view.findViewById(R.id.txtTotalScoreValue);

        btnRestart = view.findViewById(R.id.btnRestart);
    }

    /**
     * summera en lista av heltal
     *
     * @param list - används för att summera den totala poängen
     * @return sum
     */
    private static int sumList(List<Integer> list) {
        int sum = 0;
        for (int integer : list) {
            if (integer > 0) {
                sum += integer;
            }
        }
        return sum;
    }
}