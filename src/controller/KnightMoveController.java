package controller;

import validator.KnightMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 */
public class KnightMoveController extends MoveController {
    public KnightMoveController() {
        super();
        moveValidator = new KnightMoveValidator();
    }
}
