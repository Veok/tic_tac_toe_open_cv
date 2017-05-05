package TicTacToe.controller;

import TicTacToe.service.GameService;


/**
 * @author Lelental on 05.05.2017.
 */
class GameController {

    private GameService gameService = new GameService();

    GameController() {
    }

    GameService getGameService() {
        return gameService;
    }
}
