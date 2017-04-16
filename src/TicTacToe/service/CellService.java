package TicTacToe.service;

import TicTacToe.model.Cell;
import org.opencv.core.Point;

import java.util.ArrayList;

import static TicTacToe.service.VideoService.CAMERA_HEIGHT;
import static TicTacToe.service.VideoService.CAMERA_WIDTH;

/**
 * @author Lelental on 15.04.2017.
 */
public class CellService {

    private final int FIRST_COLUMN = CAMERA_WIDTH / 3;
    private final int SECOND_COLUMN = CAMERA_WIDTH / 3 * 2;
    private final int THIRD_COLUMN = CAMERA_WIDTH;
    private final int FIRST_LINE = CAMERA_HEIGHT / 3;
    private final int SECOND_LINE = CAMERA_HEIGHT / 3 * 2;
    private final int THIRD_LINE = CAMERA_HEIGHT;

    private ArrayList<Cell> listOfCells;

    public CellService() {
        this.listOfCells = new ArrayList<>();
        setListOfCells();
    }

    public ArrayList<Cell> getListOfCells() {
        return listOfCells;
    }

    private void setListOfCells() {

        listOfCells.add(new Cell(new Point(160, 90), 0, FIRST_COLUMN, 0, FIRST_LINE));
        listOfCells.add(new Cell(new Point(480, 90), FIRST_COLUMN, SECOND_COLUMN, 0, FIRST_LINE));
        listOfCells.add(new Cell(new Point(800, 90), SECOND_COLUMN, THIRD_COLUMN, 0, FIRST_LINE));
        listOfCells.add(new Cell(new Point(160, 270), 0, FIRST_COLUMN, FIRST_LINE, SECOND_LINE));
        listOfCells.add(new Cell(new Point(480, 270), FIRST_COLUMN, SECOND_COLUMN, FIRST_LINE, SECOND_LINE));
        listOfCells.add(new Cell(new Point(800, 270), SECOND_COLUMN, THIRD_COLUMN, FIRST_LINE, SECOND_LINE));
        listOfCells.add(new Cell(new Point(160, 450), 0, FIRST_COLUMN, SECOND_LINE, THIRD_LINE));
        listOfCells.add(new Cell(new Point(480, 450), FIRST_COLUMN, SECOND_COLUMN, SECOND_LINE, THIRD_LINE));
        listOfCells.add(new Cell(new Point(800, 450), SECOND_COLUMN, THIRD_COLUMN, SECOND_LINE, THIRD_LINE));

    }


}
