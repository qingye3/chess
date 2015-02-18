package chess.controller;

import chess.validator.QueenMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 * The controller for Queen. Uses the standard move protocol inherited from Move Controller
 */
public class QueenMoveController extends MoveController {
    /**
     * Constructor
     */
    public QueenMoveController() {
        super();
        moveValidator = new QueenMoveValidator();
    }
}
