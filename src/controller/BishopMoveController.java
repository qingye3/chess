package controller;

import validator.BishopMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 */
public class BishopMoveController extends MoveController {

    public BishopMoveController() {
        super();
        moveValidator = new BishopMoveValidator();
    }
}
