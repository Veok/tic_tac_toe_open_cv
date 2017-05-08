package TicTacToe.service.draw;

import TicTacToe.model.Cell;
import TicTacToe.service.GameService;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import static TicTacToe.service.position.WinPosition.winPositions;

/**
 * @author Lelental on 05.05.2017.
 */
public class WinLineDraw implements ICanBeDraw {

    private static Point startPoint;
    private static Point endPoint;
    private List<Cell> cells;
    private Mat mat;
    private static boolean drawMe = false;

    WinLineDraw(List<Cell> cells, Mat mat) {
        this.cells = cells;
        this.mat = mat;
    }

    @Override
    public void draw() {

        List<Cell> list = cells;

        if (!GameService.isGameOver() && !drawMe) {

            if (winPositions(0, 0, list.get(0).getMark())) {

                if (list.get(1).getMark() == list.get(0).getMark() && list.get(0).getMark() == list.get(2).getMark()) {

                    startPoint = list.get(0).getCenterPoint();
                    endPoint = list.get(2).getCenterPoint();
                    drawMe = true;
                }
                if (list.get(0).getMark() == list.get(3).getMark() && list.get(0).getMark() == list.get(6).getMark()) {

                    startPoint = list.get(0).getCenterPoint();
                    endPoint = list.get(6).getCenterPoint();
                    drawMe = true;

                }
            } else if (winPositions(0, 1, list.get(1).getMark())) {

                startPoint = list.get(1).getCenterPoint();
                endPoint = list.get(7).getCenterPoint();
                drawMe = true;


            } else if (winPositions(0, 2, list.get(2).getMark())) {

                startPoint = list.get(2).getCenterPoint();
                endPoint = list.get(8).getCenterPoint();
                drawMe = true;

            } else if (winPositions(1, 0, list.get(3).getMark())) {

                startPoint = list.get(3).getCenterPoint();
                endPoint = list.get(5).getCenterPoint();
                drawMe = true;

            } else if (winPositions(2, 0, list.get(6).getMark())) {

                startPoint = list.get(6).getCenterPoint();
                endPoint = list.get(8).getCenterPoint();
                drawMe = true;

            } else if (winPositions(1, 1, list.get(6).getMark())
                    && list.get(6).getMark() == list.get(2).getMark()) {

                startPoint = list.get(6).getCenterPoint();
                endPoint = list.get(2).getCenterPoint();
                drawMe = true;

            } else if (winPositions(1, 1, list.get(8).getMark())
                    && list.get(8).getMark() == list.get(0).getMark()) {

                startPoint = list.get(8).getCenterPoint();
                endPoint = list.get(0).getCenterPoint();
                drawMe = true;

            }
        } else {
            paintEndGameLine();
        }
    }

    private void paintEndGameLine() {
        Imgproc.line(mat, startPoint, endPoint,
                new Scalar(255, 255, 255), 11);
    }

    void erase() {
        startPoint = null;
        endPoint = null;
        drawMe = false;
    }

}
