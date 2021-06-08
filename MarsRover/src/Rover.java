
public class Rover {
    Position position;
    Grid grid;
    boolean isLost;

    public Rover(Grid g,Position p){
        this.position=p;
        this.grid=g;
    }

    public Position getPosition() {
        return position;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    private void move() {
        if(grid.isValidMove(position.moveForward())) {
            position = position.moveForward();
        }else{
            this.setLost(true);
        }
    }

    private void turnLeft() {
        position = position.rotateLeft();
    }

    private void turnRight() {
        position = position.rotateRight();
    }

    public void process(Character instruction) {
        getNewDirection(instruction);
    }

    private void getNewDirection(Character instruction) {
        switch (instruction) {
            case 'R':
                turnRight();
                break;
            case 'L':
                turnLeft();
                break;
            case 'F':
                move();
                break;
        }
    }
}
