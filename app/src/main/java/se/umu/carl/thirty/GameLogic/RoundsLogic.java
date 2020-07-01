package se.umu.carl.thirty.GameLogic;

//Hanterar antal rundor och kast och nya rundor.
public class RoundsLogic {
    public static int totalNumberOfRounds = 1;
    public static int totalNumberOfThrowsDisplayed = 0;
    public static int totalNumberOfThrowsNotDisplayed = 0; //räknar alla kast under hela spelets gång
    public static boolean isNewRound = false;

    public static int getAndSetThrows() {
        if (isNewRound) {
            totalNumberOfThrowsDisplayed = 0;
        }
        totalNumberOfThrowsDisplayed++;
        totalNumberOfThrowsNotDisplayed++;
        return totalNumberOfThrowsDisplayed;
    }

    //ska sätta rundor. när försöka som visas nollställs ska rundor inkrementeras
    public static int getAndSetRounds() {
        if (isNewRound) {
            totalNumberOfRounds++;
        }
        return totalNumberOfRounds;
    }
}