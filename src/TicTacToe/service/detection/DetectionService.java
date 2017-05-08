package TicTacToe.service.detection;

import TicTacToe.service.IRestartService;
import org.opencv.core.Mat;

/**
 * @author Lelental on 05.05.2017.
 */
public class DetectionService implements IRestartService {

    private NoughtDetection noughtDetection;

    public DetectionService(Mat mat) {
        this.noughtDetection = new NoughtDetection(mat);
    }

    @Override
    public void restartParameters() {
        noughtDetection.resetDetection();
    }

    public NoughtDetection getNoughtDetection() {
        return noughtDetection;
    }


}
