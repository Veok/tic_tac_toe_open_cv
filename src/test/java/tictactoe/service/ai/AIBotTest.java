package tictactoe.service.ai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.model.Cell;
import tictactoe.model.Mark;
import tictactoe.service.coordinates.CoordinateService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lelental on 02.06.2017.
 */
class AIBotTest {

    private CoordinateService coordinateService = new CoordinateService();
    private AIService aiService = new AIService(coordinateService.getCells());
    private int chosenCell;

    private void printBoard() {
        for (Mark[] board : CoordinateService.getMarkBoard()) {
            System.out.println(Arrays.toString(board));
        }
    }

    private void doMove() {

        System.out.println("Board before AI move:\n");
        printBoard();
        aiService.makeMove();
        chosenCell = aiService.getAiBot().getCellId();

        System.out.println("\nBest cell id chosen by AI: " + chosenCell + "\n");

        for (Cell cell : coordinateService.getCells()) {
            if (cell.getId() == chosenCell) {
                CoordinateService.setMarkBoard(cell.getRow(), cell.getColumn(), Mark.Cross);
            }
        }

        System.out.println("Board after AI move:\n");
        printBoard();
    }

    @BeforeEach
    void clear_mark_board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CoordinateService.setMarkBoard(i, j, null);
            }
        }
    }

    @BeforeEach
    void reset_turn_counter(){
        AIService.turn = 0;
    }

    @Test
    void test_make_move() {

        CoordinateService.setMarkBoard(1, 2, Mark.Nought);
        CoordinateService.setMarkBoard(0, 0, Mark.Cross);
        CoordinateService.setMarkBoard(1, 1, Mark.Nought);

        AIService.turn = 3;

        doMove();

        assertEquals(3, chosenCell);
    }

    @Test
    void test_draw() {
        CoordinateService.setMarkBoard(0,0,Mark.Nought);
        CoordinateService.setMarkBoard(0,1,Mark.Cross);
        CoordinateService.setMarkBoard(1,0,Mark.Cross);
        CoordinateService.setMarkBoard(1,1,Mark.Cross);
        CoordinateService.setMarkBoard(1,2,Mark.Nought);
        CoordinateService.setMarkBoard(2,0,Mark.Nought);
        CoordinateService.setMarkBoard(2,1,Mark.Nought);

        AIService.turn = 7;

        doMove();

        assertEquals(8,chosenCell);


    }

    @Test
    void test_ai_win() {
        CoordinateService.setMarkBoard(0,0,Mark.Nought);
        CoordinateService.setMarkBoard(0,1,Mark.Nought);
        CoordinateService.setMarkBoard(0,2,Mark.Cross);
        CoordinateService.setMarkBoard(1,1,Mark.Cross);
        CoordinateService.setMarkBoard(1,2,Mark.Nought);

        AIService.turn = 5;

        doMove();

        assertEquals(6,chosenCell);
    }

}