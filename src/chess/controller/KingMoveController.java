package chess.controller;

import chess.validator.KingMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 * The controller for King. Currently uses the standard move protocol inherited from Move Controller
 * TODO: Override the Move method to perform castling functions
 */
public class KingMoveController extends MoveController {
    /**
     * Constructor
     */
    public KingMoveController() {
        super();
        moveValidator = new KingMoveValidator();
    }

}
