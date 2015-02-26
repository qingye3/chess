package chess.application.model;

import chess.application.view.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qing on 2/25/2015.
 * I'm creating the Observable class because swing does not work with java.util.observable as java.util.observable
 * has threading code
 *
 * This is a concrete class because how  observer pattern works
 */
public class Observable {
    List<Observer> observers;

    /**
     * naive constructor
     */
    public Observable() {
        observers = new ArrayList<Observer>();
    }


    /**
     * attach observer to the list
     * @param o the observer to attach
     */
    public void addObserver(Observer o){
        observers.add(o);
    }

    /**
     * notify all observers when the state of the observable changed
     */
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(this);
        }
    }
}
