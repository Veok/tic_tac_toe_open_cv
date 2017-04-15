package TicTacToe.service;

import TicTacToe.model.Cell;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lelental on 15.04.2017.
 */
public class CellService {

    private final int FIRST_COLUMN = 320;
    private final int SECOND_COLUMN = 640;
    private final int THIRD_COLUMN = 960;
    private final int FIST_LINE = 180;
    private final int SECOND_LINE = 360;
    private final int THIRD_LINE = 540;

    private ArrayList<Cell> listOfCells;
    private HashMap<Map<Double, Double>, Cell> mapOfCells;

    public CellService() {
        this.listOfCells = new ArrayList<>();
        this.mapOfCells = new HashMap<Map<Double, Double>, Cell>();
        setListOfCells();
    }



    private void setListOfCells() {
        listOfCells.add(new Cell(new Point(160, 90), 0, FIRST_COLUMN, 0, FIST_LINE));
        listOfCells.add(new Cell(new Point(480, 90), FIRST_COLUMN, SECOND_COLUMN, 0, FIST_LINE));
        listOfCells.add(new Cell(new Point(700, 90), SECOND_COLUMN, THIRD_COLUMN, 0, FIST_LINE));
        listOfCells.add(new Cell(new Point(160, 180), 0, FIRST_COLUMN, FIST_LINE, SECOND_LINE));
        listOfCells.add(new Cell(new Point(480, 180), FIRST_COLUMN, SECOND_COLUMN, FIST_LINE, SECOND_LINE));
        listOfCells.add(new Cell(new Point(700, 180), SECOND_COLUMN, THIRD_COLUMN, FIST_LINE, SECOND_LINE));
        listOfCells.add(new Cell(new Point(160, 270), 0, FIRST_COLUMN, SECOND_LINE, THIRD_LINE));
        listOfCells.add(new Cell(new Point(480, 270), FIRST_COLUMN, SECOND_COLUMN, SECOND_LINE, THIRD_LINE));
        listOfCells.add(new Cell(new Point(700, 270), SECOND_COLUMN, THIRD_COLUMN, SECOND_LINE, THIRD_LINE));
    }
}
