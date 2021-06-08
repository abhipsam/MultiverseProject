import java.util.ArrayList;
import java.util.Scanner;

public class MarsRoverMain {
    public static void main(String[] args){
        System.out.println("Please enter the grid co-ordinates:");
        Scanner scanner = new Scanner(System.in);
        Grid grid = getGridCoordinates(scanner.nextLine());
        System.out.println("Enter Instruction:");
        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            if(input.equals("quit")){
               break;
            }else{
                parseInput(input,grid);
                System.out.println("Enter Instruction:");
            }
        }
        scanner.close();
    }

    public static void parseInput(String input,Grid grid){
        String instructions = parseRoverInstruction(input);
        Position initialPosition = getInitialPosition(input);
        Rover rover = new Rover(grid, initialPosition);
        for(Character chr:instructions.toCharArray()){
            rover.process(chr);
            if(rover.isLost){
                break;
            }
        }
        System.out.println(rover.isLost?rover.position.toString()+" LOST":rover.position.toString());
    }

    public static Grid getGridCoordinates(String instruction) {
        String[] coordinates = instruction.split(" ");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if(x<=0 || y<=0){
            throw new IllegalStateException("Not a valid grid, the indexes must be greater than 0");
        }
        return new Grid(x,y);
    }

    public static String parseRoverInstruction(String str){
        int index2 = str.indexOf(')');
        String instructionArray = str.substring(index2+2);
        return instructionArray;
    }

    public static Position getInitialPosition(String str) {
        int index1=str.indexOf('(');
        int index2 = str.indexOf(')');

        String initialPos = str.substring(index1+1,index2);
        String[] pos=initialPos.split(", ");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);
        Direction direction = Direction.get(pos[2]);
        return new Position(x, y, direction);
    }
}
