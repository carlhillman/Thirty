package se.umu.carl.thirty.GameLogic;

// Logiken för hantering av antal rundor och kast.
public class RoundsLogic {
    public static int totalNumberOfRounds = 1;
    public static int totalNumberOfThrowsDisplayed = 0;
    public static boolean isNewRound = false;

    public static int getAndSetThrows() {
        if (isNewRound) {
            totalNumberOfThrowsDisplayed = 0;
        }
        totalNumberOfThrowsDisplayed++;
        return totalNumberOfThrowsDisplayed;
    }

    public static int getAndSetRounds() {
        if (isNewRound) {
            totalNumberOfRounds++;
        }
        return totalNumberOfRounds;
    }
}