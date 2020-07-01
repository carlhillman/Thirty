package se.umu.carl.thirty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDice1;
    private ImageView imageViewDice2;
    private ImageView imageViewDice3;
    private ImageView imageViewDice4;
    private ImageView imageViewDice5;
    private ImageView imageViewDice6;

    private Button btnThrow;
    private Button btnTakePoints;
    private TextView textViewRounds;
    private TextView textViewThrows;
    //knapp för att ta sig till resultatvyn
    private Button btnResult;
    private Spinner spinner;
    //boolean kollar vilken tärning som har valts
    private boolean dice1Selected = false;
    private boolean dice2Selected = false;
    private boolean dice3Selected = false;
    private boolean dice4Selected = false;
    private boolean dice5Selected = false;
    private boolean dice6Selected = false;
    //ser till att användaren inte kan klicka i selectlistan flera gånger under samma runda och få poäng för det

    private ArrayList<Dice> globalDices = new ArrayList<>();
    private Random random = new Random();
    private ArrayAdapter<String> adapter;
    private ScoreLogic scoreLogic = new ScoreLogic();
    private boolean userIsInteracting = false;
    private Boolean spinnerTouched = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewDice1 = findViewById(R.id.image_view_dice1);
        imageViewDice2 = findViewById(R.id.image_view_dice2);
        imageViewDice3 = findViewById(R.id.image_view_dice3);
        imageViewDice4 = findViewById(R.id.image_view_dice4);
        imageViewDice5 = findViewById(R.id.image_view_dice5);
        imageViewDice6 = findViewById(R.id.image_view_dice6);
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

        imageViewDice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice1Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice1Selected = true;
                    imageViewDice1.setBackgroundColor(Color.BLUE);
                    globalDices.get(0).selected = true;
                } else {
                    dice1Selected = false;
                    imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
                    globalDices.get(0).selected = false;
                }
            }
        });
        imageViewDice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice2Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice2Selected = true;
                    imageViewDice2.setBackgroundColor(Color.BLUE);
                    globalDices.get(1).selected = true;
                } else {
                    dice2Selected = false;
                    imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
                    globalDices.get(1).selected = false;
                }
            }
        });
        imageViewDice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice3Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice3Selected = true;
                    imageViewDice3.setBackgroundColor(Color.BLUE);
                    globalDices.get(2).selected = true;
                } else {
                    dice3Selected = false;
                    imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
                    globalDices.get(2).selected = false;
                }
            }
        });
        imageViewDice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice4Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice4Selected = true;
                    imageViewDice4.setBackgroundColor(Color.BLUE);
                    globalDices.get(3).selected = true;
                } else {
                    dice4Selected = false;
                    imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
                    globalDices.get(3).selected = false;
                }
            }
        });
        imageViewDice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice5Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice5Selected = true;
                    imageViewDice5.setBackgroundColor(Color.BLUE);
                    globalDices.get(4).selected = true;
                } else {
                    dice5Selected = false;
                    imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
                    globalDices.get(4).selected = false;
                }
            }
        });
        imageViewDice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice6Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice6Selected = true;
                    imageViewDice6.setBackgroundColor(Color.BLUE);
                    globalDices.get(5).selected = true;
                } else {
                    dice6Selected = false;
                    imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
                    globalDices.get(5).selected = false;
                }
            }
        });

        //lägger till en, flera eller alla tärningarnas bilder i en ArrayList
        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDiceImage();
                ArrayList<ImageView> selectedDicesImages = new ArrayList<>();
                if (dice1Selected) {
                    selectedDicesImages.add(imageViewDice1);
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        Dice dice = globalDices.get(0);
                        if (!dice.selected) {
                            dice.selected = true;
                        }
                    }
                }
                if (dice2Selected) {
                    selectedDicesImages.add(imageViewDice2);
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        Dice dice = globalDices.get(1);
                        if (!dice.selected) {
                            dice.selected = true;
                        }
                    }
                }
                if (dice3Selected) {
                    selectedDicesImages.add(imageViewDice3);
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        Dice dice = globalDices.get(2);
                        if (!dice.selected) {
                            dice.selected = true;
                        }
                    }
                }
                if (dice4Selected) {
                    selectedDicesImages.add(imageViewDice4);
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        Dice dice = globalDices.get(3);
                        if (!dice.selected) {
                            dice.selected = true;
                        }
                    }
                }
                if (dice5Selected) {
                    selectedDicesImages.add(imageViewDice5);
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        Dice dice = globalDices.get(4);
                        if (!dice.selected) {
                            dice.selected = true;
                        }
                    }
                }
                if (dice6Selected) {
                    selectedDicesImages.add(imageViewDice6);
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        Dice dice = globalDices.get(5);
                        if (!dice.selected) {
                            dice.selected = true;
                        }
                    }
                }
                //om ingen eller alla tärningar har valts, kastas alla tärningar om. Och alla blir selected
                if (selectedDicesImages.size() == 0 || selectedDicesImages.size() == 6) {
                    selectedDicesImages.add(imageViewDice1);
                    selectedDicesImages.add(imageViewDice2);
                    selectedDicesImages.add(imageViewDice3);
                    selectedDicesImages.add(imageViewDice4);
                    selectedDicesImages.add(imageViewDice5);
                    selectedDicesImages.add(imageViewDice6);
                    for (Dice dice : globalDices) {
                        dice.selected = true;
                    }
                    spinner.setVisibility(View.VISIBLE);
                }
                //kasta till ny runda
               if (scoreLogic.pointTypeChoosen) {
                    rollDices(selectedDicesImages);
                    textViewThrows.setText(getResources().getString(R.string.numberOfThrows) + RoundsLogic.getAndSetThrows());
                    textViewRounds.setText(getResources().getString(R.string.numberOfRounds) + RoundsLogic.getAndSetRounds());
                    scoreLogic.pointTypeChoosen = false;
                }
                //Kast i en befintlig runda.
                else {
                    rollDices(selectedDicesImages);
                    RoundsLogic.isNewRound = false;
                    textViewThrows.setText(getResources().getString(R.string.numberOfThrows) + RoundsLogic.getAndSetThrows());
                }
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
                    deselectAllDices();
                    btnTakePoints.setVisibility(View.VISIBLE);
                    btnThrow.setVisibility(View.GONE);

                }
                spinnerTouched = false;
            }

            // för att stänga onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnTakePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice1Selected && !dice2Selected && !dice3Selected
                        && !dice4Selected && !dice5Selected && !dice6Selected) {
                    showNoDieSelected();
                } else {
                    scoreLogic.increaseCurrentScore(spinner, adapter);
                    showRoundSucceededDialog(scoreLogic.currentScore);
                    scoreLogic.currentScore = 0;

                    deselectAllDices();

                    btnThrow.setVisibility(View.VISIBLE);
                    btnTakePoints.setVisibility(View.GONE);

                    spinner.setSelection(adapter.getPosition("Välj poängtyp"));
                    spinner.setVisibility(View.GONE);
                    disableDiceImage();
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

            textViewRounds.setText(R.string.numberOfRounds + String.valueOf(numberOfRounds));
            textViewThrows.setText(R.string.numberOfThrows + String.valueOf(numberOfThrows));

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
    private void rollDices(ArrayList<ImageView> diceList) {
        setDiceImages(diceList, globalDices);
        //Efter att man har kastat tre gånger göm kasta knappen
        if (RoundsLogic.totalNumberOfThrowsDisplayed ==  2 && !scoreLogic.pointTypeChoosen) {
            btnThrow.setVisibility(View.GONE);
        }
    }

    private void setDiceImages(ArrayList<ImageView> imageDiceList, ArrayList<Dice> dices) {
        for (ImageView imageViewDice : imageDiceList) {
            int randomNumber = random.nextInt(6) + 1;
            switch (randomNumber) {
                case 1:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Dice dice = new Dice(false, randomNumber);
                        dices.add(dice);
                    } else {
                        for (Dice dice : dices) {
                            if (dice.selected) {
                                dice.selected = false;
                                dice.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white1);
                    break;
                case 2:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Dice dice = new Dice(false, randomNumber);
                        dices.add(dice);
                    } else {
                        for (Dice dice : dices) {
                            if (dice.selected) {
                                dice.selected = false;
                                dice.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white2);
                    break;
                case 3:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Dice dice = new Dice(false, randomNumber);
                        dices.add(dice);
                    } else {
                        for (Dice dice : dices) {
                            if (dice.selected) {
                                dice.selected = false;
                                dice.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white3);
                    break;
                case 4:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Dice dice = new Dice(false, randomNumber);
                        dices.add(dice);
                    } else {
                        for (Dice dice : dices) {
                            if (dice.selected) {
                                dice.selected = false;
                                dice.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white4);
                    break;
                case 5:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Dice dice = new Dice(false, randomNumber);
                        dices.add(dice);
                    } else {
                        for (Dice dice : dices) {
                            if (dice.selected) {
                                dice.selected = false;
                                dice.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white5);
                    break;
                case 6:
                    if (RoundsLogic.totalNumberOfThrowsDisplayed == 0) {
                        Dice dice = new Dice(false, randomNumber);
                        dices.add(dice);
                    } else {
                        for (Dice dice : dices) {
                            if (dice.selected) {
                                dice.selected = false;
                                dice.value = randomNumber;
                                break;
                            }
                        }
                    }
                    imageViewDice.setImageResource(R.drawable.white6);
                    break;
            }
            Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            imageViewDice.startAnimation(rotate);
        }
        GlobalDiceNumbers.triesAndDiceNumbers.put(RoundsLogic.totalNumberOfThrowsDisplayed, dices);
    }

    //alla dialog rutor för specifika användarfel
    private void showRoundSucceededDialog(int score) {
        CustomDialog customDialog = new CustomDialog("Tagna poäng: " + score, "Kasta för att börja nästa runda");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }
    private void showNoDieSelected() {
        CustomDialog customDialog = new CustomDialog("Ingen tärning vald!", "Du kan inte ta någon poäng om du inte valt någon tärning!");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }
    private void enableDiceImage(){
        imageViewDice1.setEnabled(true);
        imageViewDice2.setEnabled(true);
        imageViewDice3.setEnabled(true);
        imageViewDice4.setEnabled(true);
        imageViewDice5.setEnabled(true);
        imageViewDice6.setEnabled(true);
    }
    private void disableDiceImage(){
        imageViewDice1.setEnabled(false);
        imageViewDice2.setEnabled(false);
        imageViewDice3.setEnabled(false);
        imageViewDice4.setEnabled(false);
        imageViewDice5.setEnabled(false);
        imageViewDice6.setEnabled(false);
    }
    private void deselectAllDices() {
        dice1Selected = false;
        dice2Selected = false;
        dice3Selected = false;
        dice4Selected = false;
        dice5Selected = false;
        dice6Selected = false;
        imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
        imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("dice1Selected", dice1Selected);
        outState.putBoolean("dice2Selected", dice2Selected);
        outState.putBoolean("dice3Selected", dice3Selected);
        outState.putBoolean("dice4Selected", dice4Selected);
        outState.putBoolean("dice5Selected", dice5Selected);
        outState.putBoolean("dice6Selected", dice6Selected);

        outState.putInt("Round", RoundsLogic.totalNumberOfRounds);
        outState.putInt("Throw", RoundsLogic.totalNumberOfThrowsDisplayed);

        outState.putBoolean("PointTypeChoosen", scoreLogic.pointTypeChoosen);

    }
}

