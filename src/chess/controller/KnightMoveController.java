package chess.controller;

import chess.validator.KnightMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 * The controller for Knight. Uses the standard move protocol inherited from Move Controller
 */
public class KnightMoveController extends MoveController {
    /**
     * Constructor
     */
    public KnightMoveController() {
        super();
        moveValidator = new KnightMoveValidator();
    }
}
