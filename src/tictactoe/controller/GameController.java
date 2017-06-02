package tictactoe.controller;

import tictactoe.service.GameService;


/**
 * @author Lelental on 05.05.2017.
 */
class GameController extends GameService {

    GameController(VideoController video) {
        super(video);
    }
}
