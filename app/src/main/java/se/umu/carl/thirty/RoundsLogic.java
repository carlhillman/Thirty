package se.umu.carl.thirty;

//Hanterar antal rundor och kast och nya rundor.
public class RoundsLogic {
    static int totalNumberOfRounds = 1;
    static int totalNumberOfThrowsDisplayed = 0;
    static int totalNumberOfThrowsNotDisplayed = 0; //räknar alla kast under hela spelets gång
    static boolean isNewRound = false;
    protected static int getAndSetThrows() {
        if (isNewRound) {
            totalNumberOfThrowsDisplayed = 0;
        }
        totalNumberOfThrowsDisplayed++;
        totalNumberOfThrowsNotDisplayed++;
        return totalNumberOfThrowsDisplayed;
    }
    //ska sätta rundor. när försöka som visas nollställs ska rundor inkrementeras
    protected static int getAndSetRounds() {
        if(isNewRound) {
        totalNumberOfRounds++;
        }
        return totalNumberOfRounds;
    }
}