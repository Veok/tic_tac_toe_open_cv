package TicTacToe.service.paint;

import TicTacToe.model.Cell;
import TicTacToe.service.IRestartService;
import org.opencv.core.Mat;

import java.util.List;


/**
 * @author Lelental on 05.05.2017.
 */
public class PaintService implements IRestartService {

    private Nought nought;
    private Board board;
    private Cross cross;
    private WinLine winLine;

    public PaintService(Mat mat, List<Cell> cellList) {
        this.board = new Board(mat);
        this.nought = new Nought(cellList, mat);
        this.cross = new Cross(cellList, mat);
        this.winLine = new WinLine(cellList, mat);
    }

    @Override
    public void restart() {
        winLine.erase();
        nought.setPointOfCircle(null);
    }

    public Nought getNought() {
        return nought;
    }

    public Board getBoard() {
        return board;
    }

    public Cross getCross() {
        return cross;
    }

    public WinLine getWinLine() {
        return winLine;
    }


}
