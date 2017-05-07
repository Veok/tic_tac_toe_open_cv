package TicTacToe.service.draw;

import TicTacToe.model.Cell;
import org.opencv.core.Mat;

import java.util.List;


/**
 * @author Lelental on 05.05.2017.
 */
public class DrawService {

    private NoughtDraw noughtDraw;
    private BoardDraw boardDraw;
    private CrossDraw crossDraw;
    private WinLineDraw winLineDraw;

    public DrawService(Mat mat, List<Cell> cellList) {
        this.boardDraw = new BoardDraw(mat);
        this.noughtDraw = new NoughtDraw(cellList, mat);
        this.crossDraw = new CrossDraw(cellList, mat);
        this.winLineDraw = new WinLineDraw(cellList, mat);
    }


    public NoughtDraw getNoughtDraw() {
        return noughtDraw;
    }

    public BoardDraw getBoardDraw() {
        return boardDraw;
    }

    public CrossDraw getCrossDraw() {
        return crossDraw;
    }

    public WinLineDraw getWinLineDraw() {
        return winLineDraw;
    }

    public void erase(){
        winLineDraw.erase();
    }
}
