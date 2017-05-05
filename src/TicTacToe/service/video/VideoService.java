package TicTacToe.service.video;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * @author Lelental on 05.05.2017.
 */
public class VideoService {

    public final static int CAMERA_WIDTH_ID = 3;
    public static int CAMERA_HEIGHT_ID = 4;
    public final static int CAMERA_WIDTH = 960;
    public final static int CAMERA_HEIGHT = 540;

    private Mat mat;
    private VideoCapture videoCapture;

    public VideoService() {
        this.mat = new Mat();
        this.videoCapture = new VideoCapture();
    }

    public Mat getFrame() {
        videoCapture.read(mat);
        Core.flip(mat, mat, 1);
        return mat;
    }

    public Mat getMat() {
        return mat;
    }

    public VideoCapture getVideoCapture() {
        return videoCapture;
    }

}
