package se.umu.carl.thirty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = (View) inflater.inflate(R.layout.fragment_result, container, false);
        TextView choice1 = v.findViewById(R.id.choiceRow1);
        TextView score1 = v.findViewById(R.id.scoreRow1);

        TextView choice2 = v.findViewById(R.id.choiceRow2);
        TextView score2 = v.findViewById(R.id.scoreRow2);

        TextView choice3 = v.findViewById(R.id.choiceRow3);
        TextView score3 = v.findViewById(R.id.scoreRow3);

        TextView choice4 = v.findViewById(R.id.choiceRow4);
        TextView score4 = v.findViewById(R.id.scoreRow4);

        TextView choice5 = v.findViewById(R.id.choiceRow5);
        TextView score5 = v.findViewById(R.id.scoreRow5);

        TextView choice6 = v.findViewById(R.id.choiceRow6);
        TextView score6 = v.findViewById(R.id.scoreRow6);

        TextView choice7 = v.findViewById(R.id.choiceRow7);
        TextView score7 = v.findViewById(R.id.scoreRow7);

        TextView choice8 = v.findViewById(R.id.choiceRow8);
        TextView score8 = v.findViewById(R.id.scoreRow8);

        TextView choice9 = v.findViewById(R.id.choiceRow9);
        TextView score9 = v.findViewById(R.id.scoreRow9);

        TextView choice10 = v.findViewById(R.id.choiceRow10);
        TextView score10 = v.findViewById(R.id.scoreRow10);

        List keys = new ArrayList(ChoicePointResult.choicePoints.keySet());
        for (int index = 0; index < keys.size(); index++) {
            switch (index) {
                case 0:
                    Object key1 = keys.get(index);
                    choice1.setText(key1.toString());
                    break;
                case 1:
                    Object key2 = keys.get(index);
                    choice2.setText(key2.toString());
                    break;
                case 2:
                    Object key3 = keys.get(index);
                    choice3.setText(key3.toString());
                    break;
                case 3:
                    Object key4 = keys.get(index);
                    choice4.setText(key4.toString());
                    break;
                case 4:
                    Object key5 = keys.get(index);
                    choice5.setText(key5.toString());
                    break;
                case 5:
                    Object key6 = keys.get(index);
                    choice6.setText(key6.toString());
                    break;
                case 6:
                    Object key7 = keys.get(index);
                    choice7.setText(key7.toString());
                    break;
                case 7:
                    Object key8 = keys.get(index);
                    choice8.setText(key8.toString());
                    break;
                case 8:
                    Object key9 = keys.get(index);
                    choice9.setText(key9.toString());
                    break;
                case 9:
                    Object key10 = keys.get(index);
                    choice10.setText(key10.toString());
                    break;
            }
        }
        List values = new ArrayList(ChoicePointResult.choicePoints.values());
        for (int index = 0; index < values.size(); index++) {
            switch (index) {
                case 0:
                    Object value1 = values.get(index);
                    score1.setText(value1.toString());
                    break;
                case 1:
                    Object value2 = values.get(index);
                    score2.setText(value2.toString());
                    break;
                case 2:
                    Object value3 = values.get(index);
                    score3.setText(value3.toString());
                    break;
                case 3:
                    Object value4 = values.get(index);
                    score4.setText(value4.toString());
                    break;
                case 4:
                    Object value5 = values.get(index);
                    score5.setText(value5.toString());
                    break;
                case 5:
                    Object value6 = values.get(index);
                    score6.setText(value6.toString());
                    break;
                case 6:
                    Object value7 = values.get(index);
                    score7.setText(value7.toString());
                    break;
                case 7:
                    Object value8 = values.get(index);
                    score8.setText(value8.toString());
                    break;
                case 8:
                    Object value9 = values.get(index);
                    score9.setText(value9.toString());
                    break;
                case 9:
                    Object value10 = values.get(index);
                    score10.setText(value10.toString());
                    break;
            }
        }
        return v;
    }
}