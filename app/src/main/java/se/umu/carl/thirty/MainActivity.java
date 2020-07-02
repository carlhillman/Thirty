package se.umu.carl.thirty;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Build;
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
import java.util.Objects;

import se.umu.carl.thirty.GameLogic.DiceLogic;
import se.umu.carl.thirty.GameLogic.RestoreGUI;
import se.umu.carl.thirty.GameLogic.RoundsLogic;
import se.umu.carl.thirty.GameLogic.ScoreLogic;
import se.umu.carl.thirty.GameLogic.SpinnerLogic;
import se.umu.carl.thirty.Views.SpinnerItems;
import se.umu.carl.thirty.Views.FeedBackDialogMessageBox;

public class MainActivity extends AppCompatActivity {
    private Button btnThrow;
    private Button btnTakePoints;
    private TextView textViewRounds;
    private TextView textViewThrows;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    private Boolean spinnerTouched = false;

    ScoreLogic scoreLogic = new ScoreLogic(this);
    DiceLogic diceLogic = new DiceLogic(this);
    SpinnerLogic spinnerLogic = new SpinnerLogic(this);
    FeedBackDialogMessageBox messageBox = new FeedBackDialogMessageBox(MainActivity.this);


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            diceLogic.firstDieImageView = findViewById(R.id.image_view_dice1);
            diceLogic.secondDieImageView = findViewById(R.id.image_view_dice2);
            diceLogic.thirdDieImageView = findViewById(R.id.image_view_dice3);
            diceLogic.fourthDieImageView = findViewById(R.id.image_view_dice4);
            diceLogic.fifthDieImageView = findViewById(R.id.image_view_dice5);
            diceLogic.sixthDieImageView = findViewById(R.id.image_view_dice6);

            spinner = findViewById(R.id.spinnerChoice);
            ArrayList<String> choices = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.choices)));

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choices);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setVisibility(View.GONE);
            textViewRounds = findViewById(R.id.txtRounds);
            textViewThrows = findViewById(R.id.txtThrows);

            btnThrow = findViewById(R.id.btnThrow);
            btnTakePoints = findViewById(R.id.btnTakePoints);
            btnTakePoints.setVisibility(View.GONE);
            //I början av spelet sätter antal rundor till 1
            textViewRounds.setText(getResources().getString(R.string.numberOfRounds) + RoundsLogic.totalNumberOfRounds);

            diceLogic.firstDieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diceLogic.clickFirstDie();
                }
            });
            diceLogic.secondDieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diceLogic.clickSecondDie();
                }
            });
            diceLogic.thirdDieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diceLogic.clickThirdDie();
                }
            });
            diceLogic.fourthDieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diceLogic.clickFourthDie();
                }
            });
            diceLogic.fifthDieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diceLogic.clickFifthDie();
                }
            });
            diceLogic.sixthDieImageView.setOnClickListener(new View.OnClickListener() {
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
                        spinnerLogic.clickSelectedSpinnerItem(diceLogic, spinner, btnTakePoints, btnThrow);
                    }
                    spinnerTouched = false;
                }

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
                        scoreLogic.getPoints(spinner, adapter, messageBox, btnThrow, btnTakePoints, diceLogic);
                        // När 10 rundor är avklarade skickas användaren till resultatvyn.
                        if (RoundsLogic.getAndSetGameOver()) {
                            openResultFragment();
                        }
                    }
                }
            });

            //restora/hämta tillbaka variabler och utseende vid rotation av skärmen
            if (savedInstanceState != null) {
                diceLogic.isFirstDieSelected = savedInstanceState.getBoolean("isFirstDieSelected");
                diceLogic.isSecondDieSelected = savedInstanceState.getBoolean("isSecondDieSelected");
                diceLogic.isThirdDieSelected = savedInstanceState.getBoolean("isThirdDieSelected");
                diceLogic.isFourthDieSelected = savedInstanceState.getBoolean("isFourthDieSelected");
                diceLogic.isFifthDieSelected = savedInstanceState.getBoolean("isFifthDieSelected");
                diceLogic.isSixthDieSelected = savedInstanceState.getBoolean("isSixthDieSelected");
                RestoreGUI.inChoosingPointProgress = savedInstanceState.getBoolean("inChoosingPointProgress");
                RestoreGUI.isBtnThrowDisplayed = savedInstanceState.getBoolean("isBtnThrowDisplayed");
                RestoreGUI.isDiceImageViewEnabled = savedInstanceState.getBoolean("isDiceImageViewEnabled");

                if (!RestoreGUI.isDiceImageViewEnabled) {
                    diceLogic.disableDiceImage();
                } else {
                    diceLogic.enableDiceImage();
                }
                RestoreGUI.setBtnThrowVisibility(btnThrow);
                RestoreGUI.setBtnTakePointsVisibility(btnTakePoints);
                RestoreGUI.hideButtonOnResultFragmentOrientationChange(btnThrow);
                RestoreGUI.setDieBackgroundColor(diceLogic.isFirstDieSelected, diceLogic.isSecondDieSelected, diceLogic.isThirdDieSelected,
                        diceLogic.isFourthDieSelected, diceLogic.isFifthDieSelected, diceLogic.isSixthDieSelected,
                        diceLogic.firstDieImageView, diceLogic.secondDieImageView, diceLogic.thirdDieImageView, diceLogic.fourthDieImageView,
                        diceLogic.fifthDieImageView, diceLogic.sixthDieImageView);


                textViewRounds.setText(getResources().getString(R.string.numberOfRounds) + savedInstanceState.getInt("numberOfRounds"));
                textViewThrows.setText(getResources().getString(R.string.numberOfThrows) + savedInstanceState.getInt("numberOfThrows"));

                spinner.setSelection(adapter.getPosition(getResources().getStringArray(R.array.choices)[0]));

                if (savedInstanceState.getStringArrayList("choicePointsSpinner") != null) {
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Objects.requireNonNull(savedInstanceState.getStringArrayList("choicePointsSpinner")));
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    RestoreGUI.setSpinnerState(spinner);
                }
                diceLogic.globalDice = savedInstanceState.getParcelableArrayList("globalDice");
                if (diceLogic.globalDice != null) {
                    RestoreGUI.restoreDiceImageResource(diceLogic.globalDice, diceLogic.firstDieImageView,
                            diceLogic.secondDieImageView, diceLogic.thirdDieImageView, diceLogic.fourthDieImageView,
                            diceLogic.fifthDieImageView, diceLogic.sixthDieImageView);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //öppnar resultatvyn
    private void openResultFragment() {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_layout, new ResultFragment());
            ft.commit();
            //gömmer resultat och kast knappen
            btnThrow.setVisibility(View.GONE);
            btnTakePoints.setVisibility(View.GONE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putBoolean("isFirstDieSelected", diceLogic.isFirstDieSelected);
            outState.putBoolean("isSecondDieSelected", diceLogic.isSecondDieSelected);
            outState.putBoolean("isThirdDieSelected", diceLogic.isThirdDieSelected);
            outState.putBoolean("isFourthDieSelected", diceLogic.isFourthDieSelected);
            outState.putBoolean("isFifthDieSelected", diceLogic.isFifthDieSelected);
            outState.putBoolean("isSixthDieSelected", diceLogic.isSixthDieSelected);

            outState.putBoolean("isBtnThrowDisplayed", RestoreGUI.isBtnThrowDisplayed);
            outState.putBoolean("isBtnTakePointsDisplayed", RestoreGUI.isBtnTakePointsDisplayed);
            outState.putBoolean("inChoosingPointProgress", RestoreGUI.inChoosingPointProgress);
            outState.putBoolean("isDiceImageViewEnabled", RestoreGUI.isDiceImageViewEnabled);

            outState.putInt("numberOfRounds", RoundsLogic.totalNumberOfRounds);
            outState.putInt("numberOfThrows", RoundsLogic.totalNumberOfThrowsDisplayed);

            outState.putBoolean("PointTypeChosen", ScoreLogic.pointTypeChoosen);

            outState.putParcelableArrayList("globalDice", diceLogic.globalDice);

            outState.putStringArrayList("choicePointsSpinner", SpinnerItems.retrieveAllItems(spinner));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

