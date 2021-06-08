public class Grid {
    private int xCoordinate;
    private int yCoordinate;

    public Grid(int x, int y){
        this.xCoordinate=x;
        this.yCoordinate=y;
    }

    public boolean isValidMove(Position position) {
        return position.isValidPosition(xCoordinate,yCoordinate);
    }
}
