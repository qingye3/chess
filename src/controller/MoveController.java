package controller;

import data.GameState;
import data.Position;

/**
 * Created by Qing on 2/10/2015.
 * This is the controller for the Pawn
 * It is the "Strategy" part of the Strategy pattern
 */
public abstract class MoveController {
    public abstract GameState move(GameState gameState, Position origin, Position destination);
}
