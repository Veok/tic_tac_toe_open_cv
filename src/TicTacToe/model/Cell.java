package TicTacToe.model;

import org.opencv.core.Point;

import java.util.HashMap;

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
    private HashMap<Double, Double> map = new HashMap<>();

    public Cell(Point centerPoint, double minX, double maxX, double minY, double maxY) {

        this.centerPoint = centerPoint;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public Cell() {
    }

    public HashMap<Double, Double> mapOfXY() {
        for (int i = (int) minX; i <= maxX; i++) {
            for (int j = (int) minY; j <= maxY; j++) {
                map.put((double) i, (double) j);
            }
        }
        return map;
    }
}
