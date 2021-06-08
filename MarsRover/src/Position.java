public class Position {
    int x;
    int y;
    private Direction direction;

    public Position(int x, int y, Direction direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
    }

    public Position rotateRight(){
        return new Position(x,y,direction.right());
    }

    public Position rotateLeft(){
        return new Position(x,y,direction.left());
    }

    public Position moveForward(){
        switch (direction.toString()) {
            case "E":
                return new Position(x+1, y, direction);
            case "W":
                return new Position(x-1, y, direction);
            case "N":
                return new Position(x, y+1, direction);
            case "S":
                return new Position(x, y-1, direction);
            default:
                throw new IllegalStateException("This is an invalid direction");
        }
    }

    public boolean isValidPosition(int gridXCoordinate, int gridYCoordinate) {
        return (this.x >= 0 && this.x<= gridXCoordinate) && (this.y>=0 && this.y <= gridYCoordinate);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "("+x + "," + y + "," + direction+")";
    }

}
