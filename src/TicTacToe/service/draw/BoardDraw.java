package TicTacToe.service.draw;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static TicTacToe.service.video.VideoService.CAMERA_HEIGHT;
import static TicTacToe.service.video.VideoService.CAMERA_WIDTH;

/**
 * @author Lelental on 05.05.2017.
 */
public class BoardDraw implements ICanBeDraw {

    private Mat mat;

    BoardDraw(Mat mat) {
        this.mat = mat;
    }

    @Override
    public void draw() {

        int lineThickness = 11;
        Scalar boardColor = new Scalar(54, 69, 79);

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3, 0),
                new Point(CAMERA_WIDTH / 3, CAMERA_HEIGHT), boardColor, lineThickness);

        Imgproc.line(mat, new Point(CAMERA_WIDTH / 3 * 2, 0),
                new Point(CAMERA_WIDTH / 3 * 2, CAMERA_HEIGHT), boardColor, lineThickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3), boardColor, lineThickness);

        Imgproc.line(mat, new Point(0, CAMERA_HEIGHT / 3 * 2),
                new Point(CAMERA_WIDTH, CAMERA_HEIGHT / 3 * 2), boardColor, lineThickness);
    }
}
