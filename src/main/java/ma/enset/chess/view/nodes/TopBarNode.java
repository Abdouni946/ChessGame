package ma.enset.chess.view.nodes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import ma.enset.chess.view.scenes.HomeScene;

public class TopBarNode extends Pane {
    private HBox root;
    private Button homeButton;
    private Button InfoButton;
    private static final int TOP_BAR_HEIGHT = 40;
    private static final String TOP_BAR_STYLE = "-fx-background-color: rgb(128,156,84)\n;",
            HIGHLIGHTED_TOP_BAR_BUTTON_STYLE = "-fx-background-color: rgb(240,236,204);",
            HOME_ICON_FILE_PATH = "file:./src/main/java/ma/enset/chess/view/resources/home_icon.png",
            INFO_ICON_FILE_PATH = "file:./src/main/java/ma/enset/chess/view/resources/info_icon.png";

    private static final Insets TOP_BAR_PADDING = new Insets(5, 5, 5, 5);

    public TopBarNode() {
        setMinHeight(TOP_BAR_HEIGHT);
        setMaxHeight(TOP_BAR_HEIGHT);

        root = new HBox();

        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());
        root.setStyle(TOP_BAR_STYLE);
        root.setPadding(TOP_BAR_PADDING);
        root.setAlignment(Pos.CENTER_LEFT);
        buildHomeButton();
        buildInfoButton();
        getChildren().add(root);
        root.getChildren().add(homeButton);
        root.getChildren().add(InfoButton);
        Label title = new Label("Chess");
        title.setFont(new Font("Impact", 20));
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);
        title.minWidthProperty().bind(root.widthProperty().subtract(homeButton.widthProperty().multiply(2)));
        title.maxWidthProperty().bind(root.widthProperty().subtract(homeButton.widthProperty().multiply(2)));
        HBox.setHgrow(title, Priority.ALWAYS);
        root.getChildren().add(title);
    }

    private void buildInfoButton() {
        InfoButton = new Button();
        InfoButton.minHeightProperty().bind(root.heightProperty().subtract(root.getPadding().getBottom() + root.getPadding().getTop()));
        InfoButton.maxHeightProperty().bind(InfoButton.minHeightProperty());
        InfoButton.minWidthProperty().bind(InfoButton.minHeightProperty());
        InfoButton.maxWidthProperty().bind(InfoButton.minHeightProperty());
        InfoButton.setStyle(root.getStyle());

        InfoButton.setOnMouseEntered(mouseEvent -> InfoButton.setStyle(HIGHLIGHTED_TOP_BAR_BUTTON_STYLE));
        InfoButton.setOnMouseExited(mouseEvent -> InfoButton.setStyle(TOP_BAR_STYLE));
        // add image to the button
        ImageView imageView = new ImageView(new Image(INFO_ICON_FILE_PATH, 20, 20, false, false));
        imageView.minWidth(InfoButton.getWidth());
        imageView.maxWidth(InfoButton.getWidth());
        imageView.minHeight(InfoButton.getHeight());
        imageView.maxHeight(InfoButton.getHeight());
        InfoButton.setGraphic(imageView);

        InfoButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(new HomeScene(stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        InfoButton.setOnMouseClicked(mouseEvent -> {
            // Create a new stage or dialog to display the rules
            Stage rulesStage = new Stage();
            rulesStage.setTitle("Les régles d'échecs");

            // Create a ScrollPane for text overflow
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);

            // Create a TextFlow for formatted text
            TextFlow textFlow = new TextFlow();
            textFlow.setPadding(new Insets(10, 10, 10, 10));
            textFlow.getChildren().add(getFormattedChessRules());

            // Add the TextFlow to the ScrollPane
            scrollPane.setContent(textFlow);

            // Set the scene
            Scene rulesScene = new Scene(scrollPane, 600, 500); // Adjust size as necessary
            rulesStage.setScene(rulesScene);

            // Show the stage
            rulesStage.show();
        });

        InfoButton.setOnMouseEntered(mouseEvent -> InfoButton.setStyle(HIGHLIGHTED_TOP_BAR_BUTTON_STYLE));
        InfoButton.setOnMouseExited(mouseEvent -> InfoButton.setStyle(TOP_BAR_STYLE));

    }

    private TextFlow getFormattedChessRules() {
        // Create formatted Text nodes for each section of the rules
        Text step1Title = new Text("Étape 1. Comment les pièces d'échecs se déplacent\n");
        step1Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step1 = new Text("Chacun des 6 différents types de pièces se déplace différemment. Les pièces ne peuvent pas se déplacer à travers d'autres pièces (bien que le cavalier puisse sauter par-dessus), et ne peuvent jamais se déplacer sur une case occupée par l'une de leurs propres pièces. Cependant, elles peuvent se déplacer pour prendre la place d'une pièce adverse qui est alors capturée. Les pièces sont généralement déplacées dans des positions où elles peuvent capturer d'autres pièces (en atterrissant sur leur case et en les remplaçant), défendre leurs propres pièces en cas de capture, ou contrôler des cases importantes du jeu.\n");

        Text step2Title = new Text("Étape 2. La Tour\n");
        step2Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step2 = new Text("La tour peut se déplacer aussi loin qu'elle le souhaite, mais seulement en avant, en arrière et sur les côtés. Les tours sont particulièrement puissantes quand elles se protègent mutuellement et travaillent ensemble !\n");

        Text step3Title = new Text("Étape 3. Le Fou\n");
        step3Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step3 = new Text("Le fou peut se déplacer aussi loin qu'il le souhaite, mais seulement en diagonale. Chaque fou commence sur une couleur (claire ou foncée) et doit toujours rester sur cette couleur. Les fous travaillent bien ensemble car ils couvrent les faiblesses l'un de l'autre.\n");

        Text step4Title = new Text("Étape 4. La Reine\n");
        step4Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step4 = new Text("La reine peut se déplacer d'un nombre quelconque de cases libres en ligne droite verticalement, horizontalement, ou en diagonale, combinant ainsi les mouvements de la tour et du fou. La reine est la pièce la plus puissante. Elle peut être utilisée pour attaquer ou défendre à tout moment dans la partie.\n");

        Text step5Title = new Text("Étape 5. Le Roi\n");
        step5Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step5 = new Text("Le roi peut se déplacer d'une case dans n'importe quelle direction (horizontalement, verticalement, ou en diagonale). Le roi ne peut pas être déplacé sur une case où il serait en prise par une autre pièce. Le roi est également impliqué dans le mouvement spécial appelé le roque.\n");

        Text step6Title = new Text("Étape 6. Le Cavalier\n");
        step6Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step6 = new Text("Les cavaliers se déplacent d'une manière très différente des autres pièces – allant de deux cases dans une direction, et ensuite un mouvement supplémentaire à un angle de 90 degrés, à la manière de la forme d'un “L”. Les cavaliers sont aussi les seules pièces qui peuvent passer par-dessus d'autres pièces.\n");

        Text step7Title = new Text("Étape 7. Le Pion\n");
        step7Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step7 = new Text("Les pions sont inhabituels car ils se déplacent et capturent de différentes manières : ils avancent tout droit, mais capturent en diagonale. Les pions ne peuvent avancer que d'une case à la fois, sauf pour leur tout premier mouvement où ils peuvent avancer de deux cases. Les pions ne peuvent capturer qu'une case en diagonale devant eux. Ils ne peuvent ni se déplacer, ni capturer en arrière. Si une autre pièce se trouve directement devant un pion, il ne peut ni passer ni capturer cette pièce.\n");

        Text step8Title = new Text("Étape 8. Mouvements Spéciaux\n");
        step8Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step8 = new Text("Il y a quelques règles spéciales aux échecs qui peuvent ne pas sembler logiques au premier abord. Elles ont été créées pour rendre le jeu plus amusant et intéressant. La prise en passant est une règle spéciale pour les pions. Elle se produit lorsqu'un pion se déplace de deux cases depuis sa position initiale et se retrouve à côté d'un pion ennemi, comme s'il n'avait bougé que d'une case. Le pion ennemi peut capturer le pion “en passant”, c'est-à-dire qu'il peut être capturé même s'il n'a pas atterri sur cette case. Ce mouvement spécial doit être fait immédiatement après que le premier pion ait passé, sinon l'option de le capturer n'est plus disponible. Ce mouvement ne peut se produire qu'au tout prochain tour.\n");

        Text step9Title = new Text("Étape 9. Le Roque\n");
        step9Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step9 = new Text("Le roque est un mouvement spécial impliquant le roi et la tour. Il ne peut être effectué que si le roi n'a jamais bougé, la tour impliquée n'a jamais bougé, les cases entre le roi et la tour impliquée sont inoccupées, le roi n'est pas en échec, et le roi ne passera pas par-dessus ni ne finira sur une case attaquée par une pièce ennemie. Le roque se fait en déplaçant le roi de deux cases vers l'une des tours, puis en plaçant la tour de l'autre côté du roi, adjacente à lui.\n");

        Text step10Title = new Text("Étape 10. Échec et Mat\n");
        step10Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step10 = new Text("Lorsqu'un roi est sous attaque immédiate par une ou deux pièces de l'adversaire, on dit qu'il est en échec. Un coup en réponse à un échec est légal uniquement s'il résulte en une position où le roi n'est plus en échec. Cela peut impliquer de capturer la pièce qui donne l'échec ; d'interposer une pièce entre la pièce attaquante et le roi (ce qui est possible uniquement si la pièce attaquante est une reine, une tour, ou un fou et qu'il y a une case entre elle et le roi) ; ou de déplacer le roi sur une case où il n'est pas attaqué. Le roque n'est pas une réponse permise à un échec.\n" +
                "Un joueur incapable de déplacer ses pièces car il est en échec et mat perd la partie. Cela peut arriver de plusieurs manières, le plus souvent lorsqu'une pièce attaquée ne peut pas échapper à la capture.\n");

        Text step11Title = new Text("Étape 11. Pat\n");
        step11Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step11 = new Text("Le pat est une situation dans le jeu d'échecs où le joueur dont c'est le tour de jouer n'est pas en échec mais n'a aucun coup légal. Les règles des échecs stipulent que lorsque le pat se produit, la partie se termine par une nulle. Le pat est un facteur important dans le jeu final – dans la plupart des cas, le joueur avec un avantage matériel peut gagner la partie en forçant le pat. Dans des positions plus complexes, le pat est beaucoup plus rare, prenant généralement la forme d'une arnaque qui réussit uniquement si le côté supérieur est inattentif. Le pat est aussi un thème commun dans les études de fin de partie et autres problèmes d'échecs.\n");

        Text step12Title = new Text("Étape 12. Nulle\n");
        step12Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step12 = new Text("Il y a plusieurs façons qu'une partie d'échecs se termine par une nulle :\n" +
                "La nulle par accord mutuel : Les nulles sont le plus souvent atteintes par un accord mutuel entre les joueurs. Selon les règles des échecs, \"la partie est nulle sur accord entre les deux joueurs pendant la partie.\"[1] Les parties peuvent aussi se terminer par une nulle en cas de pat, de répétition triple, de la règle des cinquante coups, ou d'une nulle par impossibilité de mater (généralement à cause d'un matériel insuffisant pour mater).\n" +
                "Le pat : Le pat est la situation dans laquelle le joueur dont c'est le tour de jouer n'est pas en échec mais n'a aucun coup légal. Les règles des échecs stipulent que lorsque le pat se produit, la partie se termine par une nulle. Le pat est un facteur important dans le jeu final – dans la plupart des cas, le joueur avec un avantage matériel peut gagner la partie en forçant le pat. Dans des positions plus complexes, le pat est beaucoup plus rare, prenant généralement la forme d'une arnaque qui réussit uniquement si le côté supérieur est inattentif. Le pat est aussi un thème commun dans les études de fin de partie et autres problèmes d'échecs.\n" +
                "La répétition triple : La partie est nulle quand la même position se produit trois fois (bien que pas nécessairement trois fois par les mêmes joueurs). Les trois occurrences de la position ne doivent pas se produire sur des coups consécutifs pour qu'une réclamation soit valide. Les règles de la FIDE ne mentionnent pas l'échec perpétuel ; il s'agit simplement d'un type spécifique de nulle par répétition triple.\n" +
                "La règle des cinquante coups : La partie est nulle lorsque le joueur au trait effectue un coup de telle sorte que les cinquante derniers coups consécutifs effectués par chaque joueur ont été sans mouvement de pion et sans aucune capture. Le but de cette règle est d'empêcher un joueur n'ayant aucune chance de gagner de continuer obstinément à jouer indéfiniment et ainsi forcer son adversaire à démontrer à plusieurs reprises suffisamment de compétence pour gagner. La règle des cinquante coups n'est pas appliquée automatiquement par l'arbitre ; elle doit être réclamée par un joueur avant que la partie ne soit terminée. L'arbitre compte alors les coups à rebours à partir de la dernière capture ou du dernier mouvement de pion. Si le nombre est cinquante ou plus, la partie est nulle.\n" +
                "Matériel insuffisant : La partie est automatiquement nulle lorsqu'aucune séquence de coups légaux ne peut mener au mat de l'un ou l'autre côté. L'un ou l'autre des joueurs peut réclamer une nulle lorsqu'il est impossible pour l'un ou l'autre joueur de mater.\n");

        Text step13Title = new Text("Étape 13. Comment gagner une partie d'échecs ?\n");
        step13Title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        Text step13 = new Text("Pour gagner aux échecs, un joueur doit mater son adversaire.\n" +
                "\n" +
                "L'échec et mat se produit lorsque le roi est attaqué par une autre pièce et n'a aucun moyen de s'échapper. A ce moment-là, le jeu est terminé. Mais l’échec et mat ne se produit pas sans préparation.");

        TextFlow textFlow = new TextFlow(
                step1Title
                , step1
                , step2Title
                , step2
                , step3Title
                , step3
                , step4Title
                , step4
                , step5Title
                , step5
                , step6Title
                , step6
                , step7Title
                , step7
                , step8Title
                , step8
                , step9Title
                , step9
                , step10Title
                , step10
                , step11Title
                , step11
                , step12Title
                , step12
                , step13Title
                , step13
        );
        textFlow.setPrefWidth(540);

        return textFlow;
    }

    private void buildHomeButton() {
        homeButton = new Button();
        homeButton.minHeightProperty().bind(root.heightProperty().subtract(root.getPadding().getBottom() + root.getPadding().getTop()));
        homeButton.maxHeightProperty().bind(homeButton.minHeightProperty());
        homeButton.minWidthProperty().bind(homeButton.minHeightProperty());
        homeButton.maxWidthProperty().bind(homeButton.minHeightProperty());
        homeButton.setStyle(root.getStyle());

        // add image to the button
        ImageView imageView = new ImageView(new Image(HOME_ICON_FILE_PATH, 20, 20, false, false));
        imageView.minWidth(homeButton.getWidth());
        imageView.maxWidth(homeButton.getWidth());
        imageView.minHeight(homeButton.getHeight());
        imageView.maxHeight(homeButton.getHeight());
        homeButton.setGraphic(imageView);

        homeButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(new HomeScene(stage.getScene().getWidth(), stage.getScene().getHeight()));
        });
        homeButton.setOnMouseEntered(mouseEvent -> homeButton.setStyle(HIGHLIGHTED_TOP_BAR_BUTTON_STYLE));
        homeButton.setOnMouseExited(mouseEvent -> homeButton.setStyle(TOP_BAR_STYLE));
    }
}
