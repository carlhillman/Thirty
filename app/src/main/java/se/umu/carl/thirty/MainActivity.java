package se.umu.carl.thirty;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import se.umu.carl.thirty.GameLogic.DiceLogic;
import se.umu.carl.thirty.GameLogic.RestoreGUIManager;
import se.umu.carl.thirty.GameLogic.RoundsLogic;
import se.umu.carl.thirty.GameLogic.ScoreLogic;
import se.umu.carl.thirty.GameLogic.SpinnerLogic;
import se.umu.carl.thirty.Models.Dice;
import se.umu.carl.thirty.Models.ResultStorage;
import se.umu.carl.thirty.Views.SpinnerItems;
import se.umu.carl.thirty.Views.FeedBackDialogMessageBox;

// MainActivity en klass som initierar alla GUI element och dess klick event
public class MainActivity extends AppCompatActivity {
    private Button btnThrow;
    private Button btnTakePoints;
    private TextView textViewRounds;
    private TextView textViewThrows;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private Boolean spinnerTouched = false;

    //Model klasser
    Dice dice = new Dice();
    ResultStorage resultStorage = new ResultStorage();

    // Logik klasser
    RoundsLogic roundsLogic = new RoundsLogic();
    RestoreGUIManager restoreGUIManager = new RestoreGUIManager(roundsLogic);
    ScoreLogic scoreLogic = new ScoreLogic(this, dice, resultStorage, roundsLogic, restoreGUIManager);

    DiceLogic diceLogic = new DiceLogic(this, dice, roundsLogic, restoreGUIManager);
    SpinnerLogic spinnerLogic = new SpinnerLogic(this, restoreGUIManager);

    //Views klasser och dialogruta
    SpinnerItems spinnerItems = new SpinnerItems();
    FeedBackDialogMessageBox messageBox = new FeedBackDialogMessageBox(MainActivity.this, roundsLogic);

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initUIElements(this);
            initClickListeners();

            getSavedInstanceState(savedInstanceState);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * initierar UI elementen som knappar, bilder och spinnerns klick lyssnare
     */
    private void initClickListeners() {
        diceLogic.firstDieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickFirstDie(btnThrow);
            }
        });
        diceLogic.secondDieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickSecondDie(btnThrow);
            }
        });
        diceLogic.thirdDieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickThirdDie(btnThrow);
            }
        });
        diceLogic.fourthDieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickFourthDie(btnThrow);
            }
        });
        diceLogic.fifthDieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickFifthDie(btnThrow);
            }
        });
        diceLogic.sixthDieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickSixthDie(btnThrow);
            }
        });
        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceLogic.clickThrow(spinner, textViewThrows, textViewRounds, btnThrow);
            }
        });
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spinnerTouched = true;
                return false;
            }
        });
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
                    if (roundsLogic.getAndSetGameOver()) {
                        openResultFragment();
                    }
                }
            }
        });
    }

    /**
     * initierar UI element som knappar, texter, spinner osv
     */
    private void initUIElements(Activity activity) {
        diceLogic.firstDieImageView = activity.findViewById(R.id.image_view_dice1);
        diceLogic.secondDieImageView = activity.findViewById(R.id.image_view_dice2);
        diceLogic.thirdDieImageView = activity.findViewById(R.id.image_view_dice3);
        diceLogic.fourthDieImageView = activity.findViewById(R.id.image_view_dice4);
        diceLogic.fifthDieImageView = activity.findViewById(R.id.image_view_dice5);
        diceLogic.sixthDieImageView = activity.findViewById(R.id.image_view_dice6);
        spinner = activity.findViewById(R.id.spinnerChoice);
        ArrayList<String> choices = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.choices)));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choices);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
        textViewRounds = activity.findViewById(R.id.txtRounds);
        textViewThrows = activity.findViewById(R.id.txtThrows);
        btnThrow = activity.findViewById(R.id.btnThrow);
        btnTakePoints = activity.findViewById(R.id.btnTakePoints);
        btnTakePoints.setVisibility(View.GONE);
        textViewRounds.setText(getResources().getString(R.string.numberOfRounds) + roundsLogic.totalNumberOfRounds);
    }

    /**
     * öppnar resultatvyn
     */
    private void openResultFragment() {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ResultFragment resultFragment = new ResultFragment();
            resultFragment.resultStorage = resultStorage;
            ft.replace(R.id.main_layout, resultFragment);
            ft.commit();
            // gömmer ta poäng och kast knappen
            btnThrow.setVisibility(View.GONE);
            btnTakePoints.setVisibility(View.GONE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Hämtar tillbaka variabler och utseende av GUI element vid rotation av skärmen
     *
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getSavedInstanceState(Bundle savedInstanceState) {
        try {
            if (savedInstanceState != null) {
                diceLogic.isFirstDieSelected = savedInstanceState.getBoolean("isFirstDieSelected");
                diceLogic.isSecondDieSelected = savedInstanceState.getBoolean("isSecondDieSelected");
                diceLogic.isThirdDieSelected = savedInstanceState.getBoolean("isThirdDieSelected");
                diceLogic.isFourthDieSelected = savedInstanceState.getBoolean("isFourthDieSelected");
                diceLogic.isFifthDieSelected = savedInstanceState.getBoolean("isFifthDieSelected");
                diceLogic.isSixthDieSelected = savedInstanceState.getBoolean("isSixthDieSelected");
                restoreGUIManager.inChoosingPointProgress = savedInstanceState.getBoolean("inChoosingPointProgress");
                restoreGUIManager.isBtnThrowDisplayed = savedInstanceState.getBoolean("isBtnThrowDisplayed");
                restoreGUIManager.isDiceImageViewEnabled = savedInstanceState.getBoolean("isDiceImageViewEnabled");
                int totalNumberOfRounds = savedInstanceState.getInt("numberOfRounds");
                textViewRounds.setText(getResources().getString(R.string.numberOfRounds) + totalNumberOfRounds);
                roundsLogic.totalNumberOfRounds = totalNumberOfRounds;
                int totalNumberOfThrows = savedInstanceState.getInt("numberOfThrows");
                textViewThrows.setText(getResources().getString(R.string.numberOfThrows) + totalNumberOfThrows);
                roundsLogic.totalNumberOfThrowsDisplayed = totalNumberOfThrows;
                roundsLogic.isNewRound = savedInstanceState.getBoolean("isNewRound");
                roundsLogic.pointTypeChosen = savedInstanceState.getBoolean("pointTypeChosen");
                resultStorage.choicePoints = (HashMap<String, Integer>) savedInstanceState.getSerializable("resultStorage.choicePoints");
                diceLogic.globalDice = savedInstanceState.getParcelableArrayList("globalDice");
                dice.triesAndDiceNumbers.put(roundsLogic.totalNumberOfThrowsDisplayed, diceLogic.globalDice);

                if (!restoreGUIManager.isDiceImageViewEnabled) {
                    diceLogic.disableDiceImage();
                } else {
                    diceLogic.enableDiceImage();
                }
                int numberOfBlueDice = savedInstanceState.getInt("numberOfBlueDice");
                restoreGUIManager.setBtnThrowVisibility(btnThrow, numberOfBlueDice);
                diceLogic.numberOfBlueDice = numberOfBlueDice;
                restoreGUIManager.setBtnTakePointsVisibility(btnTakePoints);
                restoreGUIManager.hideButtonOnResultFragmentOrientationChange(btnThrow);
                restoreGUIManager.setDieBackgroundColor(diceLogic.isFirstDieSelected, diceLogic.isSecondDieSelected, diceLogic.isThirdDieSelected,
                        diceLogic.isFourthDieSelected, diceLogic.isFifthDieSelected, diceLogic.isSixthDieSelected,
                        diceLogic.firstDieImageView, diceLogic.secondDieImageView, diceLogic.thirdDieImageView, diceLogic.fourthDieImageView,
                        diceLogic.fifthDieImageView, diceLogic.sixthDieImageView, diceLogic.globalDice);

              //  spinner.setSelection(adapter.getPosition(getResources().getStringArray(R.array.choices)[0]));
                spinnerTouched = savedInstanceState.getBoolean("spinnerTouched");

                if (savedInstanceState.getStringArrayList("choicePointsSpinner") != null) {
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Objects.requireNonNull(savedInstanceState.getStringArrayList("choicePointsSpinner")));
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    restoreGUIManager.setSpinnerState(spinner);
                }

                if (diceLogic.globalDice != null) {
                    restoreGUIManager.restoreDiceImageResource(diceLogic.globalDice, diceLogic.firstDieImageView,
                            diceLogic.secondDieImageView, diceLogic.thirdDieImageView, diceLogic.fourthDieImageView,
                            diceLogic.fifthDieImageView, diceLogic.sixthDieImageView);
                }
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putBoolean("isFirstDieSelected", diceLogic.isFirstDieSelected);
            outState.putBoolean("isSecondDieSelected", diceLogic.isSecondDieSelected);
            outState.putBoolean("isThirdDieSelected", diceLogic.isThirdDieSelected);
            outState.putBoolean("isFourthDieSelected", diceLogic.isFourthDieSelected);
            outState.putBoolean("isFifthDieSelected", diceLogic.isFifthDieSelected);
            outState.putBoolean("isSixthDieSelected", diceLogic.isSixthDieSelected);
            outState.putBoolean("isBtnThrowDisplayed", restoreGUIManager.isBtnThrowDisplayed);
            outState.putBoolean("isBtnTakePointsDisplayed", restoreGUIManager.isBtnTakePointsDisplayed);
            outState.putBoolean("inChoosingPointProgress", restoreGUIManager.inChoosingPointProgress);
            outState.putBoolean("isDiceImageViewEnabled", restoreGUIManager.isDiceImageViewEnabled);
            outState.putInt("numberOfRounds", roundsLogic.totalNumberOfRounds);
            outState.putInt("numberOfThrows", roundsLogic.totalNumberOfThrowsDisplayed);
            outState.putBoolean("isNewRound", roundsLogic.isNewRound);
            outState.putBoolean("pointTypeChosen", roundsLogic.pointTypeChosen);
            outState.putParcelableArrayList("globalDice", diceLogic.globalDice);
            outState.putStringArrayList("choicePointsSpinner", spinnerItems.retrieveAllItems(spinner));
            outState.putInt("numberOfBlueDice", diceLogic.numberOfBlueDice);
            outState.putSerializable("resultStorage.choicePoints", resultStorage.choicePoints);
            outState.putBoolean("spinnerTouched", spinnerTouched);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metod som stänger applikationen ifall man klickar på tillbaka knappen
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}

