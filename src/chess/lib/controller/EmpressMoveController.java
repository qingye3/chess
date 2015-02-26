package chess.lib.controller;

import chess.lib.validator.EmpressMoveValidator;

/**
 * Created by Qing on 2/18/2015.
 */
public class EmpressMoveController extends MoveController {
    /**
     * Constructor
     */
    public EmpressMoveController() {
        super();
        moveValidator = new EmpressMoveValidator();
    }
}
