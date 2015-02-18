package chess.controller;

import chess.validator.PrincessMoveValidator;

/**
 * Created by Qing on 2/18/2015.
 * The controller for Princess
 */
public class PrincessMoveController extends MoveController {
    public PrincessMoveController() {
        super();
        moveValidator = new PrincessMoveValidator();
    }
}
