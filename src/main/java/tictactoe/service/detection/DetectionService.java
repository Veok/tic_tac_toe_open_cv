package tictactoe.service.detection;

import tictactoe.service.IRestartService;
import org.opencv.core.Mat;
import org.opencv.core.Point;

/**
 * @author Lelental on 05.05.2017.
 */
public class DetectionService implements IRestartService, ICanBeDetected {

    private ObjectDetection objectDetection;

    public DetectionService(Mat mat) {
        this.objectDetection = new ObjectDetection(mat);
    }

    @Override
    public void detect() {
        objectDetection.detect();
    }

    @Override
    public void restart() {
        objectDetection.resetDetection();
    }

    public Point getDetectedPoint() {
        return objectDetection.getDetectedPoint();
    }


}
