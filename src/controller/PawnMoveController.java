package controller;

import validator.PawnMoveValidator;

/**
 * Created by Qing on 2/10/2015.
 * An implementation for moving the pawn. one of the move strategies
 */
public class PawnMoveController extends MoveController {
    public PawnMoveController() {
        super();
        moveValidator = new PawnMoveValidator();
    }

    //TODO: Override move to provide promotion and en passant funcitons
}
