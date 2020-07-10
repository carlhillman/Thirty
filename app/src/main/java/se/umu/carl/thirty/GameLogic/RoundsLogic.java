package se.umu.carl.thirty.GameLogic;

// Logiken för hantering av antal rundor och kast.
public class RoundsLogic {
    public int totalNumberOfRounds = 1;
    public int totalNumberOfThrowsDisplayed = 0;
    public boolean isNewRound = false;
    public boolean isGameOver = false;
    public boolean pointTypeChosen = false;
    /**
     * Metod som kollar om antal rundor är 10 och spelaren tar sina sista poäng, och därefter avslutar spelet
     * @return isGameOver
     */
    public boolean getAndSetGameOver() {
        if (pointTypeChosen && totalNumberOfRounds == 1) {
            isGameOver = true;
        }
        return isGameOver;
    }
    /**
     * Metod som kollar ifall en ny runda har påbörjats och i så fall lägger till antal kast
     * @return totalNumberOfThrowsDisplayed
     */
    public int getAndSetThrows() {
        if (isNewRound) {
            totalNumberOfThrowsDisplayed = 0;
        }
        totalNumberOfThrowsDisplayed++;
        return totalNumberOfThrowsDisplayed;
    }
    /**
     * Metod som kollar ifall en ny runda har påbörjats och i så fall lägger till antal rundor
     * @return totalNumberOfRounds
     */
    public int getAndSetRounds() {
        if (isNewRound) {
            totalNumberOfRounds++;
        }
        return totalNumberOfRounds;
    }
}