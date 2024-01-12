package ma.enset.chess.presenter;

import ma.enset.chess.view.Observer;

public interface Observable {
    void attach(Observer observer);

    void notifyObservers();
}
