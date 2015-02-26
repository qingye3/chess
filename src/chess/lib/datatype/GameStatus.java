package chess.lib.datatype;

/**
 * Representing the status of the game
 * Currently, IMPOSSIBLE refers to a state where the current side can immediate capture the King in the next move,
 * as it implies the opponent made an incorrect move previously.
 * Created by Qing on 2/11/2015.
 */
public enum GameStatus {
    IMPOSSIBLE, BLACKWINS, WHITEWINS, STALEMATE, NORMAL
}
