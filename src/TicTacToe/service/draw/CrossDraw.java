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

import static TicTacToe.service.ai.AIService.turn;

/**
 * @author Lelental on 05.05.2017.
 */
public class CrossDraw implements ICanBeDraw {

    private List<Cell> cells;
    private Mat mat;
    private AIService aiService;

    CrossDraw(List<Cell> cells, Mat mat) {
        this.cells = cells;
        this.mat = mat;
    }

    @Override
    public void draw() {
        int crossThickness = 11;
        Scalar crossColor = new Scalar(231, 31, 3);

        if (turn > 0) {

            aiService.getEasyBot().makeMove();
            Cell cell2 = cells.get(aiService.getEasyBot().getCellId());

            for (Cell cell : cells) {

                if (cell.isPainted() && cell.getMark() != Mark.Nought) {

                    Imgproc.line(mat, new Point(cell.getCenterPoint().x - 40, cell.getCenterPoint().y + 45),
                            new Point(cell.getCenterPoint().x + 40, cell.getCenterPoint().y - 45),
                            crossColor, crossThickness);

                    Imgproc.line(mat, new Point(cell.getCenterPoint().x - 40, cell.getCenterPoint().y - 45),
                            new Point(cell.getCenterPoint().x + 40, cell.getCenterPoint().y + 45),
                            crossColor, crossThickness);
                }

                if (cell.getMark() != Mark.Nought) {
                    cell = cell2;
                    cell.setPainted(true);
                    cell.setMark(Mark.Cross);
                    CellPosition.cellArray(cell.getRow(), cell.getColumn(), cell.getMark());
                }
            }
        }
    }


    public void setAiService(AIService aiService) {
        this.aiService = aiService;
    }
}
