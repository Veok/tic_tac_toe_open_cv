package tictactoe.service;

import tictactoe.controller.VideoController;
import tictactoe.service.ai.AIService;
import tictactoe.service.coordinates.CoordinateService;
import tictactoe.service.detection.DetectionService;
import tictactoe.service.paint.PaintService;

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
        this.aiService = new AIService(coordinateService.getCells());
        this.detectionService = new DetectionService(video.getMat());
        this.paintService = new PaintService(video.getMat(), coordinateService.getCells());
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
