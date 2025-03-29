package bowling;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameTest {

    @Test
    public void testAddRoll() {
        Frame classUnderTest = new Frame(0);

        Roll testRoll = new Roll(6);
        classUnderTest.addRoll(testRoll);

        Assert.assertEquals(classUnderTest.getGameScore(), 6);
        Assert.assertEquals(classUnderTest.getRemainingPins(), 4);
        Assert.assertEquals(classUnderTest.getBonusPointRolls(), 0);

        Assert.assertFalse(classUnderTest.isComplete());
    }

    @Test
    public void testAddRollOpenFrame() {
        Frame classUnderTest = new Frame(0);

        Roll firstRoll = new Roll(6);
        classUnderTest.addRoll(firstRoll);

        Roll secondRoll = new Roll(3);
        classUnderTest.addRoll(secondRoll);

        //Open Frame - the frame is completed but there is no bonus since not all 10 pins were hit
        Assert.assertEquals(classUnderTest.getGameScore(), 9);
        Assert.assertEquals(classUnderTest.getRemainingPins(), 1);
        Assert.assertEquals(classUnderTest.getBonusPointRolls(), 0);

        Assert.assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testAddRollSpare() {
        Frame classUnderTest = new Frame(0);

        Roll firstRoll = new Roll(6);
        classUnderTest.addRoll(firstRoll);

        Roll secondRoll = new Roll(4);
        classUnderTest.addRoll(secondRoll);

        //Spare - the frame was completed with the second roll, the next roll will give bonus points
        Assert.assertEquals(classUnderTest.getGameScore(), 10);
        Assert.assertEquals(classUnderTest.getRemainingPins(), 0);
        Assert.assertEquals(classUnderTest.getBonusPointRolls(), 1);

        Assert.assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testAddRollStrike() {
        Frame classUnderTest = new Frame(0);

        Roll testRoll = new Roll(10);
        classUnderTest.addRoll(testRoll);

        //Strike - the frame was completed with the first roll, the next two rolls will give bonus points
        Assert.assertEquals(classUnderTest.getGameScore(), 10);
        Assert.assertEquals(classUnderTest.getRemainingPins(), 0);
        Assert.assertEquals(classUnderTest.getBonusPointRolls(), 2);

        Assert.assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testAddRollNullHandling() {
        Frame classUnderTest = new Frame(0);
        classUnderTest.addRoll(null);

        Assert.assertEquals(classUnderTest.getGameScore(), 0);
        Assert.assertEquals(classUnderTest.getRemainingPins(), 10);
        Assert.assertEquals(classUnderTest.getBonusPointRolls(), 0);

        Assert.assertFalse(classUnderTest.isComplete());
    }

    @Test
    public void testAddBonusPoints() {
        Frame classUnderTest = new Frame(0);

        //Add a strike roll so we have bonus point rolls
        Roll testRoll = new Roll(10);
        classUnderTest.addRoll(testRoll);

        classUnderTest.addBonusPoints(7);

        Assert.assertEquals(classUnderTest.getGameScore(), 17);
        Assert.assertEquals(classUnderTest.getBonusPointRolls(), 1);
    }

    @Test
    public void testAddPointsToGameStore() {
        Frame classUnderTest = new Frame(0);
        classUnderTest.addPointsToGameStore(6);

        Assert.assertEquals(classUnderTest.getGameScore(), 6);
    }

}