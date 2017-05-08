package TicTacToe.service;


import TicTacToe.model.Cell;
import TicTacToe.service.ai.AIService;
import TicTacToe.service.detection.DetectionService;
import TicTacToe.service.draw.DrawService;
import TicTacToe.service.position.PositionService;
import TicTacToe.service.video.VideoService;

/**
 * @author Lelental on 17.04.2017.
 */
public class GameService implements IRestartService{

    private static boolean gameOver = false;
    private DrawService drawService;
    private AIService aiService;
    private DetectionService detectionService;
    private PositionService positionService;
    private VideoService videoService;

    protected GameService() {
        this.videoService = new VideoService();
        this.positionService = new PositionService();
        this.aiService = new AIService(positionService.getCellPosition().getListOfCells());
        this.detectionService = new DetectionService(videoService.getMat());
        this.drawService = new DrawService(videoService.getMat(), positionService.getCellPosition().getListOfCells());

    }


    public void startGame() {

        drawService.getBoardDraw().draw();
        drawService.getNoughtDraw().setPointOfCircle(detectionService.getNoughtDetection().getDetectedPoint());
        drawService.getCrossDraw().setAiService(aiService);

        if (!gameOver) {
            detectionService.getNoughtDetection().detect();
        }

        drawService.getNoughtDraw().draw();
        drawService.getCrossDraw().draw();
        drawService.getWinLineDraw().draw();
        System.out.println(AIService.turn);


    }

    public void restartParameters() {

        detectionService.restartParameters();
        drawService.restartParameters();
        positionService.restartParameters();
        aiService.restartParameters();
        gameOver = false;

    }

    public VideoService getVideoService() {
        return videoService;
    }

    public static void setGameOver() {
        GameService.gameOver = true;
    }

    public static boolean isGameOver() {
        return gameOver;
    }


}
