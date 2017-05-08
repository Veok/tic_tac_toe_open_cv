package TicTacToe.service;

import TicTacToe.service.ai.AIService;
import TicTacToe.service.coordinates.CoordinateService;
import TicTacToe.service.detection.DetectionService;
import TicTacToe.service.paint.PaintService;
import TicTacToe.service.video.VideoService;

/**
 * @author Lelental on 17.04.2017.
 */
public class GameService {

    private static boolean gameOver = false;
    private PaintService paintService;
    private AIService aiService;
    private DetectionService detectionService;
    private CoordinateService coordinateService;
    private VideoService videoService;

    protected GameService() {

        this.videoService = new VideoService();
        this.coordinateService = new CoordinateService();
        this.aiService = new AIService(coordinateService.getCellCoordinate().getCells());
        this.detectionService = new DetectionService(videoService.getMat());
        this.paintService = new PaintService(videoService.getMat(),
                coordinateService.getCellCoordinate().getCells());

    }


    public void startGame() {

        paintService.getBoard().draw();
        paintService.getNought().setPointOfCircle(detectionService.getObjectDetection().getDetectedPoint());
        paintService.getCross().setAiService(aiService);

        if (!gameOver) {
            detectionService.getObjectDetection().detect();
        }

        paintService.getNought().draw();
        paintService.getCross().draw();
        paintService.getWinLine().draw();

    }

    public void restartGame() {

        detectionService.restartParameters();
        paintService.restartParameters();
        coordinateService.restartParameters();
        aiService.restartParameters();
        gameOver = false;

    }

    public VideoService video() {
        return videoService;
    }

    public static void setGameOver() {
        GameService.gameOver = true;
    }

    public static boolean isGameOver() {
        return gameOver;
    }


}
