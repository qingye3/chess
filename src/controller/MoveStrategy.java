package controller;

import data.GameState;
import data.Position;

/**
 * Created by Qing on 2/10/2015.
 * This is the "strategy" for the move controller. Implements the "strategy" part of the strategy pattern
 */
public abstract class MoveStrategy {
    public abstract GameState move(GameState gameState, Position origin, Position destination);
}
