package TicTacToe.service;

import TicTacToe.controller.VideoController;
import TicTacToe.service.ai.AIService;
import TicTacToe.service.coordinates.CoordinateService;
import TicTacToe.service.detection.DetectionService;
import TicTacToe.service.paint.PaintService;

/**
 * @author Lelental on 17.04.2017.
 */
public class GameService {

    private static boolean gameOver = false;
    private PaintService paintService;
    private AIService aiService;
    private DetectionService detectionService;
    private CoordinateService coordinateService;

    protected GameService(VideoController video) {
        this.coordinateService = new CoordinateService();
        this.aiService = new AIService(coordinateService.getCellCoordinate().getCells());
        this.detectionService = new DetectionService(video.getMat());
        this.paintService = new PaintService(video.getMat(), coordinateService.getCellCoordinate().getCells());

    }


    public void startGame() {
        paintService.setService(detectionService, aiService);
        if (!gameOver) {
            detectionService.detect();
        }
            paintService.paint();

    }

    public void restartGame() {

        detectionService.restart();
        paintService.restart();
        coordinateService.restart();
        aiService.restart();
        gameOver = false;

    }


    public static void setGameOver() {
        GameService.gameOver = true;
    }

    public static boolean isGameOver() {
        return gameOver;
    }


}
