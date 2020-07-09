package se.umu.carl.thirty.Models;

import java.util.HashMap;

// En klass som lägger till alla val och tillhörande poäng
public class ResultStorage {
       public int score;
       public HashMap<String, Integer> choicePoints = new HashMap<>();

       /**
       * Retunerar HashMap choicePoints
       * @return choicePoints
        */
       public HashMap<String,Integer>getChoicePoints(){
              return choicePoints;
       }
}
