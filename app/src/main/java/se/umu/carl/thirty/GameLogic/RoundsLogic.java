package se.umu.carl.thirty.GameLogic;

// Logiken för hantering av antal rundor och kast.
public class RoundsLogic {
    public static int totalNumberOfRounds = 1;
    public static int totalNumberOfThrowsDisplayed = 0;
    public static boolean isNewRound = false;
    public static boolean isGameOver = false;

    public static boolean getAndSetGameOver() {
        if (ScoreLogic.pointTypeChoosen && totalNumberOfRounds == 10) {
            isGameOver = true;
        }
        return isGameOver;
    }

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