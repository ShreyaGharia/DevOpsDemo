package operations;

/**
 * Created by Porwal Brother on 7/3/2017.
 */

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

        import java.io.IOException;

        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.InOrder;
        import org.mockito.Mockito;
        import org.mockito.MockitoAnnotations;

public class AdditionaTest {
    private static final double DELTA = 0.00000001;
    private Additiona addition;
    private SavingHistory mockHistory;


    @Before
    public void setup(){
        mockHistory = mock(SavingHistory.class);
        addition = new Additiona(mockHistory);
    }

    @Test
    public void AdditionOneTest(){
        addition.setFirstElement(3);
        addition.setSecondElement(7);
        addition.doOperation();
        assertEquals(10,addition.getResult(),DELTA);
    }

    @Test
    public void AdditionTwoTest(){
        addition.setFirstElement(-3);
        addition.setSecondElement(-7);
        addition.doOperation();
        assertEquals(-10,addition.getResult(),DELTA);
    }

    @Test
    public void AdditionThreeTest(){
        addition.setFirstElement(0.25);
        addition.setSecondElement(0.5);
        addition.doOperation();
        assertEquals(0.75,addition.getResult(),DELTA);
    }

    @Test
    public void AdditionFourTest(){
        addition.setFirstElement(-0.25);
        addition.setSecondElement(-0.5);
        addition.doOperation();
        assertEquals(-0.75,addition.getResult(),DELTA);
    }

    @Test
    public void AdditionFiveTest(){
        addition.setFirstElement(-0.25);
        addition.setSecondElement(0.5);
        addition.doOperation();
        assertEquals(0.25,addition.getResult(),DELTA);
    }

    @Test
    public void AdditionSixTest() throws IOException{
        addition.setFirstElement(-0.25);
        addition.setSecondElement(0.5);
        addition.doOperationAndShowInformation();
        assertEquals(0.25,addition.getResult(),DELTA);
    }

    @Test
    public void doOperationAndShowInformationOneTest() throws IOException{
        Additiona spyAddition = Mockito.spy(new Additiona(mockHistory));
        spyAddition.setFirstElement(2);
        spyAddition.setSecondElement(3);
        spyAddition.doOperationAndShowInformation();
        verify(spyAddition, Mockito.times(1)).doOperation();
        verify(spyAddition, Mockito.times(1)).showInformationToUser();
        verify(spyAddition, Mockito.times(1)).printToConsole();
    }

    @Test
    public void testThrowIOException() throws IOException {
        mockHistory.setFilePath("file_2.txt");
        mockHistory.setSaveHistory(true);
        addition = new Additiona(mockHistory);
        addition.setFirstElement(2);
        addition.setSecondElement(3);
        addition.doOperation();
        Mockito.doThrow(new IOException()).when(mockHistory).writeToFile(addition.toString());
    }

    @Test
    public void ComposeStringWithNegativeSecondElementTest() {
        addition.setFirstElement(8);
        addition.setSecondElement(-4);
        addition.doOperation();
        addition.resultToString = Helper.changeResult(addition.result);
        addition.firstElementToString = Helper.changeElement(addition.firstElement);
        addition.secondElementToString = Helper.changeElement(addition.secondElement);
        assertEquals("addition: 8 + (-4) = 4\n", addition.toString());
    }
}
