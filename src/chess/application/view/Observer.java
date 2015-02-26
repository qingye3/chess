package chess.application.view;

import chess.application.model.Observable;

/**
 * Created by Qing on 2/25/2015.
 * interface for the observer in the observer pattern
 */
public interface Observer {

    /**
     * @param observable the observable to observe
     */
    public void update(Observable observable);
}
