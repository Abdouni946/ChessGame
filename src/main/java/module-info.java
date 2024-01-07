module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.chess to javafx.fxml;
    exports ma.enset.chess.view;
    exports ma.enset.chess.model.game.pieces;
    exports ma.enset.chess.model.util;
    exports ma.enset.chess.model.ai;
    exports ma.enset.chess.model.game;
    exports ma.enset.chess.view.nodes;
    exports ma.enset.chess.view.scenes;
}