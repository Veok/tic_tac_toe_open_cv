package TicTacToe.service;

import TicTacToe.model.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lelental on 15.04.2017.
 */
public class CellService {

    private HashMap<Map<Double,Double>, Cell> mapOfCells;
    private List listOfCells = new ArrayList<Cell>();
    public CellService() {
    }

    public HashMap<Map<Double, Double>, Cell> getMapOfCells() {
        return mapOfCells;
    }

    public void setMapOfCells(HashMap<Map<Double, Double>, Cell> mapOfCells) {
        this.mapOfCells = mapOfCells;
    }

    public List getListOfCells() {
        return listOfCells;
    }

    public void setListOfCells(List listOfCells) {
        this.listOfCells = listOfCells;
    }
}
