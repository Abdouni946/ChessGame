package ma.enset.chess.view.scenes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ma.enset.chess.presenter.GameMediator;
import ma.enset.chess.presenter.LocalBoardPresenter;
import ma.enset.chess.presenter.MediatorConstructionFlags;
import ma.enset.chess.view.nodes.BoardNode;
import ma.enset.chess.view.nodes.TimerNode;
import ma.enset.chess.view.nodes.TitleLabel;
import ma.enset.chess.view.nodes.TopBarNode;

public class HomeScene extends Scene {
    private VBox root;
    private HBox boardContainer;
    private TopBarNode topBar;
    private BoardNode board;
    private TitleLabel titleLabel;
    private VBox selectPlayerSideBar, selectTimeSideBar, selectBotSideBar;
    private boolean isLocalGame;
    private String bot;

    public HomeScene(double width, double height) {
        super(new VBox(), width, height);
        root = (VBox) getRoot();
        root.setStyle("-fx-background-color: #382c2c;");
        boardContainer = new HBox();
        topBar = new TopBarNode();
        board = new BoardNode(new LocalBoardPresenter());
        board.buildMessageNode("Bienvenue!");
        board.displayMessageNode();
        boardContainer.getChildren().add(board);
        root.getChildren().addAll(topBar, boardContainer);
        buildSelectPlayerSideBar();
        buildSelectTimeSideBar();
        buildSelectBotSideBar();
        selectBotSideBar.minWidthProperty().bind(boardContainer.widthProperty().multiply(0.4));
        selectBotSideBar.maxWidthProperty().bind(boardContainer.widthProperty().multiply(0.4));
        selectBotSideBar.minHeightProperty().bind(boardContainer.heightProperty());
        selectBotSideBar.maxHeightProperty().bind(boardContainer.heightProperty());
        selectTimeSideBar.minWidthProperty().bind(boardContainer.widthProperty().multiply(0.4));
        selectTimeSideBar.maxWidthProperty().bind(boardContainer.widthProperty().multiply(0.4));
        selectTimeSideBar.minHeightProperty().bind(boardContainer.heightProperty());
        selectTimeSideBar.maxHeightProperty().bind(boardContainer.heightProperty());
        selectPlayerSideBar.minWidthProperty().bind(boardContainer.widthProperty().multiply(0.4));
        selectPlayerSideBar.maxWidthProperty().bind(boardContainer.widthProperty().multiply(0.4));
        selectPlayerSideBar.minHeightProperty().bind(boardContainer.heightProperty());
        selectPlayerSideBar.maxHeightProperty().bind(boardContainer.heightProperty());
        boardContainer.getChildren().add(selectPlayerSideBar);
        boardContainer.minWidthProperty().bind(root.widthProperty().subtract(40));
        boardContainer.maxWidthProperty().bind(root.widthProperty().subtract(40));
        boardContainer.minHeightProperty().bind(root.heightProperty().subtract(40));
        boardContainer.maxHeightProperty().bind(root.heightProperty().subtract(40));
        board.minWidthProperty().bind(boardContainer.widthProperty().multiply(0.6));
        board.maxWidthProperty().bind(boardContainer.widthProperty().multiply(0.6));
        board.minHeightProperty().bind(boardContainer.heightProperty());
        board.maxHeightProperty().bind(boardContainer.heightProperty());
        VBox.setVgrow(boardContainer, Priority.ALWAYS);
        HBox.setHgrow(board, Priority.ALWAYS);
    }

    public void buildSelectPlayerSideBar() {
        selectPlayerSideBar = new VBox();
        titleLabel = new TitleLabel("Choisissez le mode de jeu:");
        titleLabel.minWidthProperty().bind(selectPlayerSideBar.widthProperty().subtract(20));
        titleLabel.maxWidthProperty().bind(selectPlayerSideBar.widthProperty().subtract(20));
        titleLabel.minHeightProperty().bind(selectPlayerSideBar.heightProperty().multiply(0.3));
        titleLabel.maxHeightProperty().bind(selectPlayerSideBar.heightProperty().multiply(0.3));
        selectPlayerSideBar.getChildren().add(titleLabel);
        selectPlayerSideBar.setPadding(new Insets(20, 10, 20, 10));
        selectPlayerSideBar.setSpacing(20);
        selectPlayerSideBar.setAlignment(Pos.CENTER);

        Button localButton = new Button("Localement");
        localButton.setFont(new Font("Impact", 25));
        localButton.minWidthProperty().bind(titleLabel.widthProperty());
        localButton.maxWidthProperty().bind(titleLabel.widthProperty());
        localButton.setMinHeight(60);
        localButton.setMaxHeight(60);
        localButton.setStyle("-fx-background-color: rgb(40,36,36); -fx-text-fill: white;");

        localButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boardContainer.getChildren().remove(selectPlayerSideBar);
                boardContainer.getChildren().add(selectTimeSideBar);
                isLocalGame = true;
            }
        });

        localButton.setOnMouseEntered(mouseEvent -> localButton.setStyle("-fx-background-color: rgb(64,60,52); -fx-text-fill: white;"));
        localButton.setOnMouseExited(mouseEvent -> localButton.setStyle("-fx-background-color: rgb(40,36,36); -fx-text-fill: white;"));
        selectPlayerSideBar.getChildren().add(localButton);

        Button computerButton = new Button("Contre l'ordinateur");
        computerButton.setFont(new Font("Impact", 25));
        computerButton.minWidthProperty().bind(titleLabel.widthProperty());
        computerButton.maxWidthProperty().bind(titleLabel.widthProperty());
        computerButton.setMinHeight(60);
        computerButton.setMaxHeight(60);
        computerButton.setStyle("-fx-background-color: rgb(40,36,36);-fx-text-fill: white;");

        computerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boardContainer.getChildren().remove(selectPlayerSideBar);
                boardContainer.getChildren().add(selectBotSideBar);
                isLocalGame = false;
            }
        });
        computerButton.setOnMouseEntered(mouseEvent -> computerButton.setStyle("-fx-background-color: rgb(64,60,52);-fx-text-fill: white;"));
        computerButton.setOnMouseExited(mouseEvent -> computerButton.setStyle("-fx-background-color: rgb(40,36,36);-fx-text-fill: white;    "));
        selectPlayerSideBar.getChildren().add(computerButton);
    }

    public void buildSelectTimeSideBar() {
        selectTimeSideBar = new VBox();
        TitleLabel timeTitle = new TitleLabel("Limite de temps:");
        timeTitle.minWidthProperty().bind(selectTimeSideBar.widthProperty().subtract(20));
        timeTitle.maxWidthProperty().bind(selectTimeSideBar.widthProperty().subtract(20));
        timeTitle.minHeightProperty().bind(selectTimeSideBar.heightProperty().multiply(0.3));
        timeTitle.maxHeightProperty().bind(selectTimeSideBar.heightProperty().multiply(0.3));
        selectTimeSideBar.getChildren().add(timeTitle);
        selectTimeSideBar.setPadding(new Insets(20, 10, 20, 10));
        selectTimeSideBar.setSpacing(20);
        selectTimeSideBar.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-background-color: rgb(73, 204, 132);");

        TilePane buttonContainer = new TilePane();
        buttonContainer.setHgap(5);
        buttonContainer.setVgap(5);
        buttonContainer.minWidthProperty().bind(selectTimeSideBar.widthProperty());
        buttonContainer.maxWidthProperty().bind(selectTimeSideBar.widthProperty());
        buttonContainer.setAlignment(Pos.CENTER);

        int[] times = {1 * 60 * 1000, 2 * 60 * 1000, 5 * 60 * 1000, 10 * 60 * 1000, 20 * 60 * 1000, 30 * 60 * 1000};

        for (int time : times) {
            Button localButton = new Button(TimerNode.getTimerText(time));
            localButton.minWidthProperty().bind(selectTimeSideBar.widthProperty().multiply(0.4));
            localButton.maxWidthProperty().bind(selectTimeSideBar.widthProperty().multiply(0.4));
            localButton.minHeightProperty().bind(localButton.minWidthProperty().multiply(0.5));
            localButton.maxHeightProperty().bind(localButton.maxWidthProperty().multiply(0.5));
            localButton.setFont(new Font("Impact", 25));
            localButton.setStyle("-fx-background-color: rgb(40,36,36);-fx-text-fill: white;");

            localButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    if (isLocalGame) {
                        stage.setScene(new ChessBoardScene(getWidth(), getHeight(), new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, time)));
                    } else {
                        stage.setScene(new ChessBoardScene(getWidth(), getHeight(), new GameMediator(MediatorConstructionFlags.TIMED_AI, time, bot)));
                    }
                }
            });
            localButton.setOnMouseEntered(mouseEvent -> localButton.setStyle("-fx-background-color: rgb(64,60,52);-fx-text-fill: white;"));
            localButton.setOnMouseExited(mouseEvent -> localButton.setStyle("-fx-background-color: rgb(40,36,36);-fx-text-fill: white;"));
            buttonContainer.getChildren().add(localButton);
        }

        selectTimeSideBar.getChildren().add(buttonContainer);
    }

    public void buildSelectBotSideBar() {
        selectBotSideBar = new VBox();
        TitleLabel botTitle = new TitleLabel("Sélectionnez un adversaire:");
        botTitle.minWidthProperty().bind(selectBotSideBar.widthProperty().subtract(20));
        botTitle.maxWidthProperty().bind(selectBotSideBar.widthProperty().subtract(20));
        botTitle.minHeightProperty().bind(selectBotSideBar.heightProperty().multiply(0.3));
        botTitle.maxHeightProperty().bind(selectBotSideBar.heightProperty().multiply(0.3));
        selectBotSideBar.getChildren().add(botTitle);
        selectBotSideBar.setPadding(new Insets(20, 10, 20, 10));
        selectBotSideBar.setSpacing(20);
        selectBotSideBar.setAlignment(Pos.CENTER);

        TilePane buttonContainer = new TilePane();
        buttonContainer.setHgap(5);
        buttonContainer.setVgap(5);
        buttonContainer.minWidthProperty().bind(selectBotSideBar.widthProperty());
        buttonContainer.maxWidthProperty().bind(selectBotSideBar.widthProperty());
        buttonContainer.setAlignment(Pos.CENTER);

        String[] botNames = {"Débutant", "Avancé", "Master"};

        for (String botName : botNames) {
            Button localButton = new Button(botName);
            localButton.minWidthProperty().bind(selectBotSideBar.widthProperty().multiply(0.7));
            localButton.maxWidthProperty().bind(selectBotSideBar.widthProperty().multiply(0.7));
            localButton.minHeightProperty().bind(localButton.minWidthProperty().multiply(0.3));
            localButton.maxHeightProperty().bind(localButton.maxWidthProperty().multiply(0.3));
            localButton.setFont(new Font("Impact", 25));
            localButton.setStyle("-fx-background-color: rgb(40,36,36);-fx-text-fill: white;");

            localButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    boardContainer.getChildren().remove(selectBotSideBar);
                    boardContainer.getChildren().add(selectTimeSideBar);
                    bot = botName;
                }
            });
            localButton.setOnMouseEntered(mouseEvent -> localButton.setStyle("-fx-background-color: rgb(64,60,52);-fx-text-fill: white;"));
            localButton.setOnMouseExited(mouseEvent -> localButton.setStyle("-fx-background-color: rgb(40,36,36);-fx-text-fill: white;"));
            buttonContainer.getChildren().add(localButton);
        }

        selectBotSideBar.getChildren().add(buttonContainer);
    }
}
