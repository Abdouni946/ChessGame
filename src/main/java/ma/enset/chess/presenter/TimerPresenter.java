package ma.enset.chess.presenter;

import javafx.application.Platform;
import ma.enset.chess.model.util.Colors;

import java.util.Timer;
import java.util.TimerTask;

public class TimerPresenter {
    private GameMediator gameMediator;
    private boolean gameOver;
    private long initialDurationMillis, passedDurationMillis, startTimeMillis;
    private Colors color;
    private Timer timer;

    public TimerPresenter(long durationMillis, Colors color, GameMediator gameMediator) {
        this.initialDurationMillis = durationMillis;
        passedDurationMillis = 0;
        this.color = color;
        timer = null;
        gameOver = false;
        this.gameMediator = gameMediator;
    }

    public Colors getColor() {
        return color;
    }

    public void start() {
        if (gameOver)
            return;
        startTimeMillis = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameOver = true;
                Platform.runLater(() -> gameMediator.notify(TimerPresenter.this));
                stop();
            }
        }, initialDurationMillis - passedDurationMillis);
    }

    public void stop() {
        if (timer == null)
            return;
        timer.cancel();
        passedDurationMillis += System.currentTimeMillis() - startTimeMillis;
        startTimeMillis = 0;
        timer = null;
    }

    public void gameIsOver() {
        gameOver = true;
        stop();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public long getTimeRemaining() {
        if (timer == null) {
            return initialDurationMillis - passedDurationMillis;
        } else {
            return initialDurationMillis - (passedDurationMillis + System.currentTimeMillis() - startTimeMillis);
        }
    }
}
