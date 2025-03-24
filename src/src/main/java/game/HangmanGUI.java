package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HangmanGUI extends Application {
    private HangmanLogic gameLogic;
    private Text hangmanDrawing;
    private Text wordDisplay;
    private Text wrongLettersText;
    private Text attemptsLeftText;
    private FlowPane lettersPane;

    @Override
    public void start(Stage primaryStage) {
        gameLogic = new HangmanLogic();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Gioco dell'Impiccato");
        titleLabel.setFont(Font.font(24));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        hangmanDrawing = new Text(gameLogic.getDisegnoOmino());
        hangmanDrawing.setFont(Font.font("Monospaced", 16));

        wordDisplay = new Text(formatWord(gameLogic.getLettereIndovinate()));
        wordDisplay.setFont(Font.font(20));

        HBox infoBox = new HBox(20);
        infoBox.setAlignment(Pos.CENTER);

        wrongLettersText = new Text("Lettere errate: " + gameLogic.getLettereErrate());
        wrongLettersText.setFont(Font.font(16));

        attemptsLeftText = new Text("Tentativi rimasti: " + gameLogic.getTentativiRimasti());
        attemptsLeftText.setFont(Font.font(16));

        infoBox.getChildren().addAll(wrongLettersText, attemptsLeftText);

        centerBox.getChildren().addAll(hangmanDrawing, wordDisplay, infoBox);
        root.setCenter(centerBox);

        lettersPane = new FlowPane(8, 8);
        lettersPane.setAlignment(Pos.CENTER);

        for (char c = 'A'; c <= 'Z'; c++) {
            Button letterButton = new Button(String.valueOf(c));
            letterButton.setMinWidth(40);
            letterButton.setOnAction(e -> {
                letterButton.setDisable(true);
                makeGuess(letterButton.getText().charAt(0));
            });
            lettersPane.getChildren().add(letterButton);
        }

        Button newGameButton = new Button("Nuova Partita");
        newGameButton.setOnAction(e -> resetGame());

        VBox bottomBox = new VBox(15);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(lettersPane, newGameButton);

        root.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new Insets(20, 0, 0, 0));

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Gioco dell'Impiccato");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String formatWord(char[] letters) {
        StringBuilder sb = new StringBuilder();
        for (char c : letters) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }

    private void makeGuess(char letter) {
        boolean correct = gameLogic.indovinaLettera(Character.toLowerCase(letter));

        hangmanDrawing.setText(gameLogic.getDisegnoOmino());
        wordDisplay.setText(formatWord(gameLogic.getLettereIndovinate()));
        wrongLettersText.setText("Lettere errate: " + gameLogic.getLettereErrate());
        attemptsLeftText.setText("Tentativi rimasti: " + gameLogic.getTentativiRimasti());

        if (gameLogic.getPartitaFinita()) {
            disableAllButtons();

            Alert alert = new Alert(
                    gameLogic.getTentativiRimasti() > 0 ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle("Fine della partita");

            if (gameLogic.getTentativiRimasti() > 0) {
                alert.setHeaderText("Congratulazioni!");
                alert.setContentText("Hai indovinato la parola: " + gameLogic.getParolaDaIndovinare());
            } else {
                alert.setHeaderText("Hai perso!");
                alert.setContentText("La parola era: " + gameLogic.getParolaDaIndovinare());
            }

            alert.showAndWait();
        }
    }

    private void disableAllButtons() {
        lettersPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        });
    }

    private void resetGame() {
        gameLogic = new HangmanLogic();

        hangmanDrawing.setText(gameLogic.getDisegnoOmino());
        wordDisplay.setText(formatWord(gameLogic.getLettereIndovinate()));
        wrongLettersText.setText("Lettere errate: " + gameLogic.getLettereErrate());
        attemptsLeftText.setText("Tentativi rimasti: " + gameLogic.getTentativiRimasti());

        lettersPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setDisable(false);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}