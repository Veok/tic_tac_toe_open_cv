package tictactoe.service.video;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * @author Lelental on 05.05.2017.
 */
public class VideoService {

    public final static int CAMERA_WIDTH = 960;
    public final static int CAMERA_HEIGHT = 540;

    private Mat mat;
    private VideoCapture videoCapture;

    public VideoService() {
        this.mat = new Mat();
        this.videoCapture = new VideoCapture();
    }

    public Mat getCapturedFrame() {
        videoCapture.read(mat);
        Core.flip(mat, mat, 1);
        return mat;
    }

    public Mat getMat() {
        return mat;
    }

    public VideoCapture capture() {
        return videoCapture;
    }

}
