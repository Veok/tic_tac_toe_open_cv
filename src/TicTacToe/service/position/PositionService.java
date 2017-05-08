package TicTacToe.service.position;

import TicTacToe.model.Cell;
import TicTacToe.service.IRestartService;

/**
 * @author Lelental on 05.05.2017.
 */
public class PositionService implements IRestartService {

    private CellPosition cellPosition = new CellPosition();

    public PositionService() {
    }

    @Override
    public void restartParameters() {
        cellPosition.resetCellArray();

        for (Cell cell : cellPosition.getListOfCells()) {
            cell.setPainted(false);
            cell.setMark(null);
        }

    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }


}
