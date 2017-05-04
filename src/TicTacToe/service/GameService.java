package TicTacToe.service;

import TicTacToe.model.Mark;
import TicTacToe.model.Player;


/**
 * @author Lelental on 17.04.2017.
 */
class GameService {

    private Player human;
    private Player cpu;
    private VideoService videoService;


    public GameService() {
        this.human = new Player(Mark.Nought);
        this.cpu = new Player(Mark.Cross);
        this.videoService = new VideoService();
    }


    public void startGame() {
        getVideoService().paintGameBoard();
        if (!CellService.isGameOver()) {
            getVideoService().detectCircle();
        }
        getVideoService().paintNought();
        getVideoService().paintCross();
        getVideoService().paintLine();

    }


    public VideoService getVideoService() {
        return videoService;
    }


}
