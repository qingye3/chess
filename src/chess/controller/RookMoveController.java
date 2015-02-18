package chess.controller;

import chess.validator.RookMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 * The controller for Rook. Uses the standard move protocol inherited from Move Controller
 */
public class RookMoveController extends MoveController {
    /**
     * Constructor
     */
    public RookMoveController() {
        super();
        moveValidator = new RookMoveValidator();
    }
}
