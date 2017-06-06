package tictactoe.service.coordinates;

import tictactoe.model.Cell;
import tictactoe.model.Mark;
import org.opencv.core.Point;

import java.util.ArrayList;

import static tictactoe.service.video.VideoService.CAMERA_HEIGHT;
import static tictactoe.service.video.VideoService.CAMERA_WIDTH;

/**
 * @author Lelental on 05.05.2017.
 */
public class CellCoordinate {

    private final int FIRST_COLUMN = CAMERA_WIDTH / 3;
    private final int SECOND_COLUMN = CAMERA_WIDTH / 3 * 2;
    private final int THIRD_COLUMN = CAMERA_WIDTH;
    private final int FIRST_LINE = CAMERA_HEIGHT / 3;
    private final int SECOND_LINE = CAMERA_HEIGHT / 3 * 2;
    private final int THIRD_LINE = CAMERA_HEIGHT;

    private ArrayList<Cell> cells;
    private static Mark[][] markBoard = new Mark[3][3];


    CellCoordinate() {
        cells = new ArrayList<>();
        setListOfCells();
    }

    ArrayList<Cell> getCells() {
        return cells;
    }


    static void setMarkBoard(int row, int column, Mark mark) {
        markBoard[row][column] = mark;
    }

    static Mark[][] getMarkBoard() {
        return markBoard;
    }

    void resetMarkBoard() {
        markBoard = new Mark[3][3];
    }

    private void setListOfCells() {

        cells.add(new Cell(new Point(160, 90), 0, FIRST_COLUMN, 0, FIRST_LINE, 0, 0, 0));
        cells.add(new Cell(new Point(480, 90), FIRST_COLUMN, SECOND_COLUMN, 0, FIRST_LINE, 0, 1, 1));
        cells.add(new Cell(new Point(800, 90), SECOND_COLUMN, THIRD_COLUMN, 0, FIRST_LINE, 0, 2, 2));
        cells.add(new Cell(new Point(160, 270), 0, FIRST_COLUMN, FIRST_LINE, SECOND_LINE, 1, 0, 3));
        cells.add(new Cell(new Point(480, 270), FIRST_COLUMN, SECOND_COLUMN, FIRST_LINE, SECOND_LINE, 1, 1, 4));
        cells.add(new Cell(new Point(800, 270), SECOND_COLUMN, THIRD_COLUMN, FIRST_LINE, SECOND_LINE, 1, 2, 5));
        cells.add(new Cell(new Point(160, 450), 0, FIRST_COLUMN, SECOND_LINE, THIRD_LINE, 2, 0, 6));
        cells.add(new Cell(new Point(480, 450), FIRST_COLUMN, SECOND_COLUMN, SECOND_LINE, THIRD_LINE, 2, 1, 7));
        cells.add(new Cell(new Point(800, 450), SECOND_COLUMN, THIRD_COLUMN, SECOND_LINE, THIRD_LINE, 2, 2, 8));

    }


}
