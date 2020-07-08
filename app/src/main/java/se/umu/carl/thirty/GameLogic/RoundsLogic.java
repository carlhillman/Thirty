package se.umu.carl.thirty.GameLogic;

// Logiken för hantering av antal rundor och kast.
public class RoundsLogic {
    public static int totalNumberOfRounds = 1;
    public static int totalNumberOfThrowsDisplayed = 0;
    public static boolean isNewRound = false;
    public static boolean isGameOver = false;

    /**
     * metod som kollar om antal rundor är 10 och spelaren tar sina sista poäng, och därefter avslutar spelet
     * @return isGameOver
     */
    public static boolean getAndSetGameOver() {
        if (ScoreLogic.pointTypeChosen && totalNumberOfRounds == 5) {
            isGameOver = true;
        }
        return isGameOver;
    }
    /**
     * metod som kollar ifall en ny runda har påbörjats och inkrementerar i så fall antal kast
     * @return totalNumberOfThrowsDisplayed
     */
    public static int getAndSetThrows() {
        if (isNewRound) {
            totalNumberOfThrowsDisplayed = 0;
        }
        totalNumberOfThrowsDisplayed++;
        return totalNumberOfThrowsDisplayed;
    }
    /**
     * metod som kollar ifall en ny runda har påbörjats och inkrementerar i så fall antal rundor
     * @return totalNumberOfRounds
     */
    public static int getAndSetRounds() {
        if (isNewRound) {
            totalNumberOfRounds++;
        }
        return totalNumberOfRounds;
    }
}