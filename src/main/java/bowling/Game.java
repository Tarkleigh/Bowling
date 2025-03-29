package bowling;

import java.util.LinkedList;
import java.util.List;

public class Game {
    List<Frame> playedFrames = new LinkedList<>();

    List<Frame> bonusFrames = new LinkedList<>();

    int round;

    int gameScore;

    public Game() {
        round = 1;
        gameScore = 0;
        playedFrames.add(new Frame(0));
    }

    public void roll(int pinsHit) {
        Frame currentFrame = playedFrames.getLast();
        if (currentFrame.complete) {
            currentFrame = new Frame(gameScore);
            playedFrames.add(currentFrame);
        }

        processRoll(pinsHit, currentFrame);
        gameScore = currentFrame.getGameScore();

        if (currentFrame.complete) {
            round++;
            if (currentFrame.getBonusPointRolls() != 0) {
                bonusFrames.add(currentFrame);
            }
        }
    }

    public void processRoll(int pinsHit, Frame currentFrame) {
        Roll newRoll = new Roll(pinsHit);
        currentFrame.addRoll(newRoll);

        if (!bonusFrames.isEmpty()) {
            for (var bonusFrame : bonusFrames) {
                bonusFrame.addBonusPoints(pinsHit);
                //The bonus points change previous frames, so we have to add them to the current frame as well
                //to ensure everything stays consistent
                currentFrame.addPointsToGameStore(pinsHit);
            }
            bonusFrames.removeIf((frame -> frame.getBonusPointRolls() == 0));
        }

    }

    public List<Frame> getPlayedFrames() {
        return playedFrames;
    }
}
