package test.tictactoe.service.ai;

import org.junit.jupiter.api.Test;
import tictactoe.model.Cell;
import tictactoe.model.Mark;
import tictactoe.service.ai.AIService;
import tictactoe.service.coordinates.CellCoordinate;
import tictactoe.service.coordinates.CoordinateService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lelental on 02.06.2017.
 */
class AIBotTest {

    private CoordinateService coordinateService = new CoordinateService();
    private AIService aiService = new AIService(coordinateService.getCellCoordinate().getCells());

    @Test
    void test_make_move() {

        CellCoordinate.setMarkBoard(1, 2, Mark.Nought);
        CellCoordinate.setMarkBoard(0, 0, Mark.Cross);
        CellCoordinate.setMarkBoard(1, 1, Mark.Nought);

        AIService.turn = 3;

        System.out.println("Board before AI move:\n");
        printBoard();
        aiService.getAIBot().makeMove();
        int chosenCell = aiService.getAIBot().getCellId();

        System.out.println("\nBest cell id chosen by AI: " + chosenCell + "\n");

        for (Cell cell : coordinateService.getCellCoordinate().getCells()) {
            if (cell.getId() == chosenCell) {
                CellCoordinate.setMarkBoard(cell.getRow(), cell.getColumn(), Mark.Cross);
            }
        }

        System.out.println("Board after AI move:\n");
        printBoard();

        assertEquals(3, chosenCell);
    }

    private void printBoard() {
        for (Mark[] board : CellCoordinate.getMarkBoard()) {
            System.out.println(Arrays.toString(board));
        }
    }

}