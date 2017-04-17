package TicTacToe.model;

import org.opencv.core.Point;

/**
 * @author Lelental on 15.04.2017.
 */
public class Cell {

    private int id;
    private boolean isPainted;
    private Point centerPoint;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;


    public Cell(Point centerPoint, double minX, double maxX, double minY, double maxY, int id) {

        this.id = id;
        this.centerPoint = centerPoint;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public Cell() {
    }

    public boolean isPainted() {
        return isPainted;
    }

    public void setPainted() {
        isPainted = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }
}
