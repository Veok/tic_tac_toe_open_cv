package TicTacToe.service;

import TicTacToe.model.Mark;

import static TicTacToe.service.CellService.getCellArray;


/**
 * @author Lelental on 17.04.2017.
 */
class GameService implements IWantBeNew {


    private BoardService boardService;
    private static boolean gameOver = false;


    public GameService() {
        this.boardService = new BoardService();
    }


    public void startGame() {
        getBoardService().paintGameBoard();
        if (!gameOver) {
            getBoardService().detectCircle();
        }
        getBoardService().paintNought();
        getBoardService().paintCross();
        getBoardService().paintLine();

    }

    @Override
    public void resetService() {
        gameOver = false;
        getBoardService().resetService();
        AIService.turn = 0;
    }

    public static boolean winPositions(int row, int column, Mark mark) {

        if (mark != null) {
            if (getCellArray()[row][0] == mark && getCellArray()[row][1] == mark && getCellArray()[row][2] == mark) {
                gameOver = true;
                return true;
            } else if (getCellArray()[0][column] == mark && getCellArray()[1][column] == mark && getCellArray()[2][column] == mark) {
                gameOver = true;
                return true;
            } else if (row == 1 && column == 1 && getCellArray()[0][0] == mark && getCellArray()[row][column] == mark && getCellArray()[2][2] == mark) {
                gameOver = true;
                return true;
            } else if (row == 1 && column == 1 && getCellArray()[2][0] == mark && getCellArray()[row][column] == mark && getCellArray()[0][2] == mark) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public BoardService getBoardService() {
        return boardService;
    }


}
