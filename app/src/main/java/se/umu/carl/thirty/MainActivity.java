package se.umu.carl.thirty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import se.umu.carl.thirty.GameLogic.DiceLogic;
import se.umu.carl.thirty.GameLogic.RoundsLogic;
import se.umu.carl.thirty.GameLogic.ScoreLogic;
import se.umu.carl.thirty.Views.MessageBox;

public class MainActivity extends AppCompatActivity {
    private Button btnThrow;
    private Button btnTakePoints;
    private TextView textViewRounds;
    private TextView textViewThrows;

    private Button btnResult;
    private Spinner spinner;

    private ArrayAdapter<String> adapter;
    private ScoreLogic scoreLogic = new ScoreLogic();
    private Boolean spinnerTouched = false;

    DiceLogic diceLogic = new DiceLogic(this);
    MessageBox messageBox = new MessageBox(MainActivity.this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //diceLogic.setUIComponents();
        diceLogic.imageViewDice1 = findViewById(R.id.image_view_dice1);
        diceLogic.imageViewDice2 = findViewById(R.id.image_view_dice2);
        diceLogic.imageViewDice3 = findViewById(R.id.image_view_dice3);
        diceLogic.imageViewDice4 = findViewById(R.id.image_view_dice4);
        diceLogic.imageViewDice5 = findViewById(R.id.image_view_dice5);
        diceLogic.imageViewDice6 = findViewById(R.id.image_view_dice6);

        spinner = findViewById(R.id.spinnerChoice);
        ArrayList<String> choices = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.choices)));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choices);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
        textViewRounds = findViewById(R.id.txtRounds);
        textViewThrows = findViewById(R.id.txtThrows);

        btnResult = findViewById(R.id.btnResult);
        btnThrow = findViewById(R.id.btnThrow);
        btnTakePoints = findViewById(R.id.btnTakePoints);
        btnTakePoints.setVisibility(View.GONE);

        //I början av spelet sätter antal rundor till 1
        textViewRounds.setText(getResources().getString(R.string.numberOfRounds) + RoundsLogic.totalNumberOfRounds);

        diceLogic.imageViewDice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickFirstDie();
            }
        });
        diceLogic.imageViewDice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickSecondDie();
            }
        });
        diceLogic.imageViewDice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickThirdDie();
            }
        });
        diceLogic.imageViewDice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickFourthDie();
            }
        });
        diceLogic.imageViewDice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickFifthDie();
            }
        });
        diceLogic.imageViewDice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickSixthDie();
            }
        });

        //lägger till en, flera eller alla tärningarnas bilder i en ArrayList
        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickThrow(spinner, textViewThrows, textViewRounds, btnThrow);
            }
        });
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spinnerTouched = true;
                return false;
            }
        });
        //ska lägga till poäng som valideras när man klickat i vilken poängtyp man vill ha
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerTouched) {
                    diceLogic.deselectAllDices();
                    btnTakePoints.setVisibility(View.VISIBLE);
                    btnThrow.setVisibility(View.GONE);
                }
                spinnerTouched = false;
            }
            // för att stänga
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnTakePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!diceLogic.isFirstDieSelected && !diceLogic.isSecondDieSelected && !diceLogic.isThirdDieSelected
                        && !diceLogic.isFourthDieSelected && !diceLogic.isFifthDieSelected && !diceLogic.isSixthDieSelected) {
                    messageBox.showNoDieSelected();
                } else {
                    scoreLogic.setChoicePoint(spinner, adapter);
                    messageBox.showRoundSucceededDialog(scoreLogic.currentScore);
                    scoreLogic.currentScore = 0;

                    diceLogic.deselectAllDices();

                    btnThrow.setVisibility(View.VISIBLE);
                    btnTakePoints.setVisibility(View.GONE);

                    spinner.setSelection(adapter.getPosition("Välj poängtyp"));
                    spinner.setVisibility(View.GONE);
                    diceLogic.disableDiceImage();
                    //när 10 rundor är avklarade skickas användaren till resultatvyn.
                    if (RoundsLogic.totalNumberOfRounds == 10) {
                        openResultFragment();
                    }
                }
            }
        });

        //visar resultatsvyn manuellt
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openResultFragment();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        //restora/hämta tillbaka variabler vid rotation av skärmen
        if (savedInstanceState != null) {
            savedInstanceState.getBoolean("dice1Selected");
            savedInstanceState.getBoolean("dice2Selected");
            savedInstanceState.getBoolean("dice3Selected");
            savedInstanceState.getBoolean("dice4Selected");
            savedInstanceState.getBoolean("dice5Selected");
            savedInstanceState.getBoolean("dice6Selected");

            int numberOfRounds = savedInstanceState.getInt("Round");
            int numberOfThrows = savedInstanceState.getInt("Throw");

            //     textViewRounds.setText(R.string.numberOfRounds + String.valueOf(numberOfRounds));
            //     textViewThrows.setText(R.string.numberOfThrows + String.valueOf(numberOfThrows));

            savedInstanceState.getBoolean("PointTypeSucceeded");
            savedInstanceState.getBoolean("choiceHasBeenSelected");
        }
    }

    //avsnitt för hjälp metoder börjar här
    private void openResultFragment() {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_layout, new ResultFragment());
            ft.commit();
            //gömmer resultat och kast knappen
            btnThrow.setVisibility(View.GONE);
            btnResult.setVisibility(View.GONE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("dice1Selected", diceLogic.isFirstDieSelected);
        outState.putBoolean("dice2Selected", diceLogic.isSecondDieSelected);
        outState.putBoolean("dice3Selected", diceLogic.isThirdDieSelected);
        outState.putBoolean("dice4Selected", diceLogic.isFourthDieSelected);
        outState.putBoolean("dice5Selected", diceLogic.isFifthDieSelected);
        outState.putBoolean("dice6Selected", diceLogic.isSixthDieSelected);

        outState.putInt("Round", RoundsLogic.totalNumberOfRounds);
        outState.putInt("Throw", RoundsLogic.totalNumberOfThrowsDisplayed);

        outState.putBoolean("PointTypeChoosen", scoreLogic.pointTypeChoosen);

    }
}

