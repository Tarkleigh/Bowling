package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Roll> rolls = new ArrayList<>(3);

    private int gameScore;

    private int remainingPins = 10;

    private int bonusPointRolls = 0;

    boolean complete = false;

    public Frame(int gameScore) {
        this.gameScore = gameScore;
    }

    public void addRoll(Roll rollToAdd) {
        if (rollToAdd == null || rolls.size() > 3) {
            return;
        }

        rolls.add(rollToAdd);
        gameScore = gameScore + rollToAdd.pinsHit();
        remainingPins = remainingPins - rollToAdd.pinsHit();

        if (remainingPins == 0) {
            if (rolls.size() == 2) {
                //Spare i.e. all ten pins hit with the second roll
                bonusPointRolls = 1;
                complete = true;
            } else if (rolls.size() == 1) {
                //Strike i.e. all ten pins hit with the first oll
                bonusPointRolls = 2;
                complete = true;
            }
        }

        if (rolls.size() >= 2) {
            complete = true;
        }
    }

    public void addBonusPoints(int pinsHit) {
        gameScore = gameScore + pinsHit;
        bonusPointRolls--;
    }

    public void addPointsToGameStore(int pointsToAdd) {
        gameScore = gameScore + pointsToAdd;
    }

    public int getGameScore() {
        return gameScore;
    }

    public int getBonusPointRolls() {
        return bonusPointRolls;
    }

    public boolean isComplete() {
        return complete;
    }

    public int getRemainingPins() {
        return remainingPins;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "gameScore=" + gameScore +
                '}';
    }
}
