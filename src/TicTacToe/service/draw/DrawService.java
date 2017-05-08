package TicTacToe.service.draw;

import TicTacToe.model.Cell;
import TicTacToe.service.IRestartService;
import org.opencv.core.Mat;

import java.util.List;


/**
 * @author Lelental on 05.05.2017.
 */
public class DrawService implements IRestartService{

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

    @Override
    public void restartParameters(){
        winLineDraw.erase();
        noughtDraw.setPointOfCircle(null);
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


}
