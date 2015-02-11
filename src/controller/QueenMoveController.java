package controller;

import validator.QueenMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 */
public class QueenMoveController extends MoveController {
    public QueenMoveController() {
        super();
        moveValidator = new QueenMoveValidator();
    }
}
