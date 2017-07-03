package operations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by Porwal Brother on 7/3/2017.
 */
public class SubstractionTest {

    public static final double DELTA = 0.000001;
    private Subtraction subtraction;
    private SavingHistory savingHistory;

    @Before
    public void setup() {
        subtraction = new Subtraction(savingHistory);
    }

    @Test
    public void SubtructionOneTest() {
        subtraction.setFirstElement(450);
        subtraction.setSecondElement(300);
        subtraction.doOperation();
        assertEquals(150, subtraction.getResult(), DELTA);
    }

    @Test
    public void SubtructionTwoTest() {
        subtraction.setFirstElement(300);
        subtraction.setSecondElement(450);
        subtraction.doOperation();
        assertEquals(-150, subtraction.getResult(), DELTA);
    }

    @Test
    public void SubtructionThreeTest() {
        subtraction.setFirstElement(-300);
        subtraction.setSecondElement(450);
        subtraction.doOperation();
        assertEquals(-750, subtraction.getResult(), DELTA);
    }

    @Test
    public void SubtructionFourTest() {
        subtraction.setFirstElement(0.321456);
        subtraction.setSecondElement(0.654321);
        subtraction.doOperation();
        assertEquals(-0.332865, subtraction.getResult(), DELTA);
    }

    @Test
    public void SubtructionFifthTest() {
        subtraction.setFirstElement(-0.321456);
        subtraction.setSecondElement(-0.654321);
        subtraction.doOperation();
        assertEquals(0.332865, subtraction.getResult(), DELTA);
    }

    @Test
    public void ComposeStringTest() {
        subtraction.setFirstElement(2);
        subtraction.setSecondElement(3);
        subtraction.doOperation();
        subtraction.resultToString = Helper.changeResult(subtraction.result);
        subtraction.firstElementToString = Helper
                .changeElement(subtraction.firstElement);
        subtraction.secondElementToString = Helper
                .changeElement(subtraction.secondElement);
        assertEquals("subtraction: 2 - 3 = -1\n", subtraction.toString());
    }

    @Test
    public void ComposeStringWithNegativeSecondElementTest() {
        subtraction.setFirstElement(8);
        subtraction.setSecondElement(-2);
        subtraction.doOperation();
        subtraction.resultToString = Helper.changeResult(subtraction.result);
        subtraction.firstElementToString = Helper.changeElement(subtraction.firstElement);
        subtraction.secondElementToString = Helper.changeElement(subtraction.secondElement);
        assertEquals("subtraction: 8 - (-2) = 10\n", subtraction.toString());
    }
}

