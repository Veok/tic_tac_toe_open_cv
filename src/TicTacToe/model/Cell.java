package TicTacToe.model;

import org.opencv.core.Point;

/**
 * @author Lelental on 15.04.2017.
 */
public class Cell {

    private int row;
    private int column;
    private double minX, maxX;
    private double minY, maxY;
    private boolean isPainted;
    private Mark mark;
    private Point centerPoint;


    public Cell(Point centerPoint, double minX, double maxX, double minY, double maxY, int row, int column) {
        this.row = row;
        this.column = column;
        this.centerPoint = centerPoint;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

    }

    public boolean isPainted() {
        return isPainted;
    }

    public void setPainted(boolean painted) {
        isPainted = painted;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }
}
