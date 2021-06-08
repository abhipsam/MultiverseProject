import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MarsRoverTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testPositionDirectionRotation(){
        String input1 = "(2, 3, W) LFRFFL";
        Position position1 = MarsRoverMain.getInitialPosition(input1);
        String left = position1.rotateLeft().getDirection().toString();
        Assert.assertEquals(left,"S");
        String right = position1.rotateRight().getDirection().toString();
        Assert.assertEquals(right,"N");

        String input2 = "(2, 3, E) LFRFFL";
        Position position2 = MarsRoverMain.getInitialPosition(input2);
        left = position2.rotateLeft().getDirection().toString();
        Assert.assertEquals(left,"N");
        right = position2.rotateRight().getDirection().toString();
        Assert.assertEquals(right,"S");
    }

    @Test(expected = IllegalStateException.class)
    public void testGridZeroCoordinate(){
        String instruction = "0 4";
        MarsRoverMain.getGridCoordinates(instruction);
    }

    @Test(expected = IllegalStateException.class)
    public void testNegativeGridCoordinates(){
        String instruction = "-2 4";
        MarsRoverMain.getGridCoordinates(instruction);
    }

    @Test
    public void testLostCase(){
       String coordinates = "4 8";
       Grid grid = MarsRoverMain.getGridCoordinates(coordinates);
       String instruction = "(0, 2, N) FFLFRFF";
       MarsRoverMain.parseInput(instruction,grid);
       Assert.assertEquals("(0,4,W) LOST", outContent.toString().trim());
    }

    @Test
    public void testPositiveCase(){
        String coordinates = "4 8";
        Grid grid = MarsRoverMain.getGridCoordinates(coordinates);
        String instruction = "(2, 3, E) LFRFF";
        MarsRoverMain.parseInput(instruction,grid);
        Assert.assertEquals("(4,4,E)", outContent.toString().trim());
    }

}
