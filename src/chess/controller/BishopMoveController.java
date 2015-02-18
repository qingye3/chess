package chess.controller;

import chess.validator.BishopMoveValidator;

/**
 *
 * Created by Qing on 2/11/2015.
 * The controller for Bishop. Uses the standard move protocol inherited from Move Controller
 */
public class BishopMoveController extends MoveController {

    /**
     * Constructor
     */
    public BishopMoveController() {
        super();
        moveValidator = new BishopMoveValidator();
    }
}
