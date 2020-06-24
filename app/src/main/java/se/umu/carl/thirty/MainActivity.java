package se.umu.carl.thirty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
    private TextView textViewRounds;
    private TextView textViewTries;
    private TextView textViewCurrentScore;
    //knapp för att ta sig till resultatvyn
    private Button btnResult;
    private Button btnThrow;
    private Spinner spinner;
    //boolean kollar vilken tärning som har valts
    private boolean dice1Selected = false;
    private boolean dice2Selected = false;
    private boolean dice3Selected = false;
    private boolean dice4Selected = false;
    private boolean dice5Selected = false;
    private boolean dice6Selected = false;

    private ArrayList<Dice> globalDices = new ArrayList<>();
    private Random random = new Random();
    private ArrayAdapter<String> adapter;
    private ScoreLogic scoreLogic = new ScoreLogic();
    private boolean userIsInteracting = false;

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
        ArrayList<String> choices = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.choices)));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choices);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textViewRounds = findViewById(R.id.txtRounds);
        textViewTries = findViewById(R.id.txtTries);
        //nuvarande poäng är den poängen man får för den nuvarande rundan ska inte ses som ett slut resultat
        textViewCurrentScore = findViewById(R.id.txtCurrentScore);

        btnResult = findViewById(R.id.btnResult);
        btnThrow = findViewById(R.id.btnThrow);
        //I början av spelet sätter antal rundor till 1
        textViewRounds.setText("Rounds:" + RoundsLogic.totalNumberOfRounds);


        imageViewDice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice1Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice1Selected = true;
                    imageViewDice1.setBackgroundColor(Color.BLUE);
                } else {
                    dice1Selected = false;
                    imageViewDice1.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        imageViewDice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice2Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice2Selected = true;
                    imageViewDice2.setBackgroundColor(Color.BLUE);
                } else {
                    dice2Selected = false;
                    imageViewDice2.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        imageViewDice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice3Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice3Selected = true;
                    imageViewDice3.setBackgroundColor(Color.BLUE);
                } else {
                    dice3Selected = false;
                    imageViewDice3.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        imageViewDice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice4Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice4Selected = true;
                    imageViewDice4.setBackgroundColor(Color.BLUE);
                } else {
                    dice4Selected = false;
                    imageViewDice4.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        imageViewDice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice5Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice5Selected = true;
                    imageViewDice5.setBackgroundColor(Color.BLUE);
                } else {
                    dice5Selected = false;
                    imageViewDice5.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        imageViewDice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dice6Selected && RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                    dice6Selected = true;
                    imageViewDice6.setBackgroundColor(Color.BLUE);
                } else {
                    dice6Selected = false;
                    imageViewDice6.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        //lägger till en, flera eller alla tärningarnas bilder i en ArrayList
        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }
                if (RoundsLogic.totalNumberOfThrowsDisplayed == 3 && !scoreLogic.pointTypeSucceeded) {
                    showNotAllowedToThrow();
                }
                //när man kastar till en ny runda.
                else if (scoreLogic.pointTypeSucceeded) {
                    rollDices(selectedDicesImages);
                    textViewTries.setText("Antal kast:" + RoundsLogic.getAndSetThrows());
                    textViewRounds.setText("Runda:" + RoundsLogic.getAndSetRounds());
                    scoreLogic.pointTypeSucceeded = false;
                }
                //när man kastar i en befintlig runda.
                else {
                    rollDices(selectedDicesImages);
                    RoundsLogic.isNewRound = false;
                    textViewTries.setText("Antal kast:" + RoundsLogic.getAndSetThrows());
                }
                //ifall det är en ny runda måste den blåa ram färgerna försvinna för att visa att det är en ny runda
                for (ImageView diceImage : selectedDicesImages) {
                    if (RoundsLogic.isNewRound) {
                        diceImage.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        //ska lägga till poäng som valideras när man klickat i vilken poängtyp man vill ha
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userIsInteracting) {
                    if (RoundsLogic.totalNumberOfThrowsDisplayed > 0) {
                        scoreLogic.increaseCurrentScore(spinner, adapter);
                        if (scoreLogic.pointTypeSucceeded == true) {
                            textViewCurrentScore.setText("Nuvarande poäng: " + scoreLogic.currentScore);
                            showRoundSucceededDialog();
                            deselectAllDices();
                            //när 10 rundor är avklarade skickas användaren till resultatvyn.
                            if(RoundsLogic.totalNumberOfRounds == 10){
                                openResultFragment();
                            }
                        } else {
                            //gör om valet
                            showChoicePointRequestFailedDialog();
                        }
                    } else {
                        showNotAllowedToChooseDialog();
                    }
                }
            }

            // för att stänga onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {
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
    }

    private void setDiceImages(ArrayList<ImageView> imageDiceList, ArrayList<Dice> dices) {
        for (ImageView imageViewDice : imageDiceList) {
            int randomNumber = random.nextInt(6) + 1;
            RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(100);
            rotate.setInterpolator(new LinearInterpolator());
            imageViewDice.startAnimation(rotate);
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
        }
        GlobalDiceNumbers.triesAndDiceNumbers.put(RoundsLogic.totalNumberOfThrowsDisplayed, dices);
    }

    //alla dialog rutor för specifika användarfel
    private void showRoundSucceededDialog() {
        CustomDialog customDialog = new CustomDialog("Runda klar", "Kasta igen för att börja nästa runda");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    private void showChoicePointRequestFailedDialog() {
        CustomDialog customDialog = new CustomDialog("Fel val", "Det angivna valet går inte att utföra");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    private void showNotAllowedToChooseDialog() {
        CustomDialog customDialog = new CustomDialog("Fel", "Du kan inte välja något om du inte gjort ett kast!");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    private void showNotAllowedToThrow() {
        CustomDialog customDialog = new CustomDialog("Fel", "Slut på kast, du måste välja något val!");
        customDialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    private void deselectAllDices() {
        dice1Selected = false;
        dice2Selected = false;
        dice3Selected = false;
        dice4Selected = false;
        dice5Selected = false;
        dice6Selected = false;
    }

    @Override //kollar om man användaren använder gränsnittet.
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }
}