package TicTacToe.service;

import TicTacToe.model.Cell;
import TicTacToe.model.Mark;
import TicTacToe.model.Player;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Lelental on 17.04.2017.
 */
public class GameService {

    private Player human;
    private Player cpu;
    private VideoService videoService;


    public GameService() {
        this.human = new Player(Mark.Nought);
        this.cpu = new Player(Mark.Cross);
        this.videoService = new VideoService();
    }


    public void game() {
        getVideoService().paintGameBoard();
        getVideoService().detectCircle();
        getVideoService().paintNought();

        for (Cell s : videoService.getCellService().getListOfCells()) {
            if (s.isPainted()) {
                videoService.paintCross(videoService.getCellService().getListOfCells().get(4));
            }

        }
    }


    public VideoService getVideoService() {
        return videoService;
    }


}
