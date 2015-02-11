package controller;

import validator.RookMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 */
public class RookMoveController extends MoveController {
    public RookMoveController() {
        super();
        moveValidator = new RookMoveValidator();
    }
}
