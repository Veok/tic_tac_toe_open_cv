package TicTacToe.service;

import TicTacToe.model.Cell;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static TicTacToe.service.VideoService.CAMERA_HEIGHT;
import static TicTacToe.service.VideoService.CAMERA_WIDTH;

/**
 * @author Lelental on 15.04.2017.
 */
public class CellService {

    private final int FIRST_COLUMN = CAMERA_WIDTH / 3;
    private final int SECOND_COLUMN = CAMERA_WIDTH / 3 * 2;
    private final int THIRD_COLUMN = CAMERA_WIDTH;
    private final int FIST_LINE = CAMERA_HEIGHT / 3;
    private final int SECOND_LINE = CAMERA_HEIGHT / 3 * 2;
    private final int THIRD_LINE = CAMERA_HEIGHT;

    private ArrayList<Cell> listOfCells;
    private HashMap<Map<Double, Double>, Cell> mapOfCells;

    public CellService() {
        this.listOfCells = new ArrayList<>();
        this.mapOfCells = new HashMap<>();
        setListOfCells();
    }

    public HashMap<Map<Double, Double>, Cell> mapOfCells() {
        for (Cell cells : this.listOfCells) {
            mapOfCells.put(cells.mapOfXY(), cells);
        }
        return mapOfCells;
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
