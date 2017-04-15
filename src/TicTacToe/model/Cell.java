package TicTacToe.model;

import org.opencv.core.Point;

/**
 * @author Lelental on 15.04.2017.
 */
public class Cell {

    private boolean isSet;
    private Point centerPoint;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public Cell(Point centerPoint, double minX, double maxX, double minY, double maxY) {
        this.centerPoint = centerPoint;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public Cell() {
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }
}
