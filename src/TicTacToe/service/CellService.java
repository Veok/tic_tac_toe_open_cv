package TicTacToe.service;

import TicTacToe.model.Cell;
import TicTacToe.model.Mark;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

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
    private static Mark[][] cellArray = new Mark[3][3];
    private static boolean gameOver = false;

    public CellService() {
        listOfCells = new ArrayList<>();
        setListOfCells();
    }

    public ArrayList<Cell> getListOfCells() {
        return listOfCells;
    }


    public void cellArray(int row, int column, Mark mark) {
        cellArray[row][column] = mark;

    }

    public static boolean winPositions(int row, int column, Mark mark) {

        if (mark != null) {
            if (cellArray[row][0] == mark && cellArray[row][1] == mark && cellArray[row][2] == mark) {
                gameOver = true;
                return true;
            } else if (cellArray[0][column] == mark && cellArray[1][column] == mark && cellArray[2][column] == mark) {
                gameOver = true;
                return true;
            } else if (row == 1 && column == 1 && cellArray[0][0] == mark && cellArray[row][column] == mark && cellArray[2][2] == mark) {
                gameOver = true;
                return true;
            } else if (row == 1 && column == 1 && cellArray[2][0] == mark && cellArray[row][column] == mark && cellArray[0][2] == mark) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    private void setListOfCells() {

        listOfCells.add(new Cell(new Point(160, 90), 0, FIRST_COLUMN, 0, FIRST_LINE, 0, 0));
        listOfCells.add(new Cell(new Point(480, 90), FIRST_COLUMN, SECOND_COLUMN, 0, FIRST_LINE, 0, 1));
        listOfCells.add(new Cell(new Point(800, 90), SECOND_COLUMN, THIRD_COLUMN, 0, FIRST_LINE, 0, 2));
        listOfCells.add(new Cell(new Point(160, 270), 0, FIRST_COLUMN, FIRST_LINE, SECOND_LINE, 1, 0));
        listOfCells.add(new Cell(new Point(480, 270), FIRST_COLUMN, SECOND_COLUMN, FIRST_LINE, SECOND_LINE, 1, 1));
        listOfCells.add(new Cell(new Point(800, 270), SECOND_COLUMN, THIRD_COLUMN, FIRST_LINE, SECOND_LINE, 1, 2));
        listOfCells.add(new Cell(new Point(160, 450), 0, FIRST_COLUMN, SECOND_LINE, THIRD_LINE, 2, 0));
        listOfCells.add(new Cell(new Point(480, 450), FIRST_COLUMN, SECOND_COLUMN, SECOND_LINE, THIRD_LINE, 2, 1));
        listOfCells.add(new Cell(new Point(800, 450), SECOND_COLUMN, THIRD_COLUMN, SECOND_LINE, THIRD_LINE, 2, 2));

    }
}
