package TicTacToe.service.detection;

import TicTacToe.service.IRestartService;
import org.opencv.core.Mat;

/**
 * @author Lelental on 05.05.2017.
 */
public class DetectionService implements IRestartService {

    private ObjectDetection objectDetection;

    public DetectionService(Mat mat) {
        this.objectDetection = new ObjectDetection(mat);
    }

    @Override
    public void restart() {
        objectDetection.resetDetection();
    }

    public ObjectDetection getObjectDetection() {
        return objectDetection;
    }


}
