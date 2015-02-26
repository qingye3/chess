package chess.lib.controller;

import chess.lib.validator.PawnMoveValidator;

/**
 * Created by Qing on 2/10/2015.
 * The controller for King. Currently uses the standard move protocol inherited from Move Controller
 * TODO: Override the Move method to perform castling functions
 */
public class PawnMoveController extends MoveController {
    /**
     * Constructor
     */
    public PawnMoveController() {
        super();
        moveValidator = new PawnMoveValidator();
    }

}
