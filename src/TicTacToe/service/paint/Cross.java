package TicTacToe.service.paint;

import TicTacToe.model.Cell;
import TicTacToe.model.Mark;
import TicTacToe.service.ai.AIService;
import TicTacToe.service.coordinates.CellCoordinate;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import static TicTacToe.service.ai.AIService.turn;

/**
 * @author Lelental on 05.05.2017.
 */
public class Cross implements ICanBePainted {

    private List<Cell> cells;
    private Mat mat;
    private AIService aiService;

    Cross(List<Cell> cells, Mat mat) {
        this.cells = cells;
        this.mat = mat;
    }

    @Override
    public void paint() {
        int crossThickness = 10;
        Scalar crossColor = new Scalar(112,41,99);

        if (turn > 0) {

            aiService.getAIBot().makeMove();
            Cell cell2 = cells.get(aiService.getAIBot().getCellId());

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
                    CellCoordinate.cellArray(cell.getRow(), cell.getColumn(), cell.getMark());
                }
            }
        }
    }


    void setAiService(AIService aiService) {
        this.aiService = aiService;
    }
}
