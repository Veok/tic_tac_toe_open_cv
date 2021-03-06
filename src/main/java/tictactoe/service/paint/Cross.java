package tictactoe.service.paint;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import tictactoe.model.Cell;
import tictactoe.model.Mark;
import tictactoe.service.ai.AIService;
import tictactoe.service.coordinates.CoordinateService;

import java.util.List;

import static tictactoe.service.ai.AIService.turn;

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
        Scalar crossColor = new Scalar(0,10,255);

        if (turn > 0) {

            aiService.makeMove();
            Cell cell2 = cells.get(aiService.getAiBot().getCellId());

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
                    CoordinateService.setMarkBoard(cell.getRow(), cell.getColumn(), cell.getMark());
                }
            }
        }
    }


    void setAiService(AIService aiService) {
        this.aiService = aiService;
    }
}
