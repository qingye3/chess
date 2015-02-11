package controller;

import validator.KingMoveValidator;

/**
 * Created by Qing on 2/11/2015.
 */
public class KingMoveController extends MoveController {
    public KingMoveController() {
        super();
        moveValidator = new KingMoveValidator();
    }
}
