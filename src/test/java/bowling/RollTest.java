package bowling;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RollTest {

    @Test
    public void testConstruction() {
        Roll classUnderTest = new Roll(7);
        Assert.assertEquals(classUnderTest.pinsHit(), 7);
    }

    @Test
    public void testConstructionWithTooLowNumber() {
        Roll classUnderTest = new Roll(-5);
        Assert.assertEquals(classUnderTest.pinsHit(), 0);
    }

    @Test
    public void testConstructionWithTooHighNumber() {
        Roll classUnderTest = new Roll(11);
        Assert.assertEquals(classUnderTest.pinsHit(), 10);
    }
}