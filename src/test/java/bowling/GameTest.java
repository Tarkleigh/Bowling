package bowling;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GameTest {

    @Test
    public void testProcessRoll() {
        Frame testFrame = new Frame(25);

        Game classUnderTest = new Game();
        classUnderTest.processRoll(5, testFrame);

        Assert.assertEquals(testFrame.getGameScore(), 30);
    }

    @Test
    public void testProcessRollWithBonusFrame() {
        //Create a strike bonus frame
        Frame bonusFrame = new Frame(0);
        bonusFrame.addRoll(new Roll(10));

        Frame testFrame = new Frame(10);

        Game classUnderTest = new Game();
        classUnderTest.bonusFrames.add(bonusFrame);
        classUnderTest.processRoll(5, testFrame);

        Assert.assertEquals(bonusFrame.getGameScore(), 15);
        Assert.assertEquals(testFrame.getGameScore(), 20);
        Assert.assertEquals(classUnderTest.bonusFrames.size(), 1);
    }

    @Test
    public void testProcessRollWithMultipleBonusFrame() {
        //Create a spare bonus frame
        Frame spareBonusFrame = new Frame(0);
        spareBonusFrame.addRoll(new Roll(5));
        spareBonusFrame.addRoll(new Roll(5));

        //Create a strike bonus frame
        Frame strikeBonusFrame = new Frame(10);
        strikeBonusFrame.addRoll(new Roll(10));

        Frame testFrame = new Frame(20);

        Game classUnderTest = new Game();
        classUnderTest.bonusFrames.add(spareBonusFrame);
        classUnderTest.bonusFrames.add(strikeBonusFrame);

        classUnderTest.processRoll(5, testFrame);

        Assert.assertEquals(spareBonusFrame.getGameScore(), 15);
        Assert.assertEquals(strikeBonusFrame.getGameScore(), 25);
        Assert.assertEquals(testFrame.getGameScore(), 35);

        //The spare bonus frame should be removed as it only grants one bonus roll
        Assert.assertEquals(classUnderTest.bonusFrames.size(), 1);
        Assert.assertEquals(classUnderTest.bonusFrames.getFirst(), strikeBonusFrame);
    }

    @Test
    public void testRoll() {
        Game classUnderTest = new Game();
        classUnderTest.roll(5);

        Assert.assertEquals(classUnderTest.gameScore, 5);
        Assert.assertEquals(classUnderTest.playedFrames.size(), 1);
        Assert.assertEquals(classUnderTest.round, 1);
        Assert.assertTrue(classUnderTest.bonusFrames.isEmpty());

        Frame currentFrame = classUnderTest.playedFrames.getLast();
        Assert.assertFalse(currentFrame.complete);
    }

    @Test
    public void testRollWithCompleteFrame() {
        Game classUnderTest = new Game();
        classUnderTest.roll(5);
        classUnderTest.roll(4);

        Assert.assertEquals(classUnderTest.gameScore, 9);
        Assert.assertEquals(classUnderTest.playedFrames.size(), 1);
        Assert.assertEquals(classUnderTest.round, 2);
        Assert.assertTrue(classUnderTest.bonusFrames.isEmpty());

        Frame currentFrame = classUnderTest.playedFrames.getLast();
        Assert.assertTrue(currentFrame.complete);
    }

    @Test
    public void testRollWithTwoFrames() {
        Game classUnderTest = new Game();
        classUnderTest.roll(5);
        classUnderTest.roll(4);
        classUnderTest.roll(7);

        Assert.assertEquals(classUnderTest.gameScore, 16);
        Assert.assertEquals(classUnderTest.playedFrames.size(), 2);
        Assert.assertEquals(classUnderTest.round, 2);
        Assert.assertTrue(classUnderTest.bonusFrames.isEmpty());

        Frame currentFrame = classUnderTest.playedFrames.getLast();
        Assert.assertFalse(currentFrame.complete);
    }

    @Test
    public void testRollWithBonusRoundFrame() {
        Game classUnderTest = new Game();
        classUnderTest.roll(10);

        Assert.assertEquals(classUnderTest.gameScore, 10);
        Assert.assertEquals(classUnderTest.playedFrames.size(), 1);
        Assert.assertEquals(classUnderTest.round, 2);
        Assert.assertEquals(classUnderTest.bonusFrames.size(), 1);

        Frame currentFrame = classUnderTest.playedFrames.getLast();
        Assert.assertTrue(currentFrame.complete);
    }

}