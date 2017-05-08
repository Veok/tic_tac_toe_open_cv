package TicTacToe.service.draw;

import TicTacToe.model.Cell;
import TicTacToe.model.Mark;
import TicTacToe.service.ai.AIService;
import TicTacToe.service.position.CellPosition;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

/**
 * @author Lelental on 05.05.2017.
 */
public class NoughtDraw implements ICanBeDraw {

    private  Point pointOfCircle;
    private List<Cell> cells;
    private Mat mat;

    NoughtDraw(List<Cell> cells, Mat mat) {
        this.cells = cells;
        this.mat = mat;
    }

    @Override
    public void draw() {

        if (pointOfCircle != null) {

            int noughtThickness = 4;
            double x = pointOfCircle.x;
            double y = pointOfCircle.y;
            Scalar noughtColor = new Scalar(222, 1, 34);

            for (Cell cell : cells) {

                if (cell.isPainted() && cell.getMark() != Mark.Cross) {
                    Imgproc.circle(mat, cell.getCenterPoint(), 20, noughtColor, noughtThickness);
                }

                if (x > cell.getMinX() && x < cell.getMaxX() && y > cell.getMinY() && y < cell.getMaxY()
                        && cell.getMark() == null) {

                    cell.setPainted(true);
                    cell.setMark(Mark.Nought);
                    CellPosition.cellArray(cell.getRow(), cell.getColumn(), cell.getMark());
                    AIService.turn++;
                }
            }
        }
    }


    public void setPointOfCircle(Point pointOfCircle) {
        this.pointOfCircle = pointOfCircle;
    }
}
