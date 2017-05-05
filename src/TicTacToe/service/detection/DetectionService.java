package TicTacToe.service.detection;

import org.opencv.core.Mat;

/**
 * @author Lelental on 05.05.2017.
 */
public class DetectionService {

    private NoughtDetection noughtDetection;

    public DetectionService(Mat mat) {
        this.noughtDetection = new NoughtDetection(mat);
    }

    public NoughtDetection getNoughtDetection() {
        return noughtDetection;
    }
}
