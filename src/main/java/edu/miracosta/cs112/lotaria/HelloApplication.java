package edu.miracosta.cs112.lotaria;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class HelloApplication extends Application {
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    int cardIndex = 0;
    double cardProgress = 0;
    Label cardLabel = new Label();
    Button drawCardButton = new Button();
    ImageView cardImageView = new ImageView();
    ProgressBar gameProgressBar = new ProgressBar();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Loteria!");
        stage.setScene(scene);
        stage.show();

        Label titleLabel = new Label("Welcome to EchALE STEM Loteria!");
        titleLabel.setFont(new Font("Arial", 35));
        root.getChildren().add(titleLabel);

        Image cardImage = new Image("file:./resources/0.png");
        cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(500);
        cardImageView.setFitHeight(600);
        root.getChildren().add(cardImageView);

        cardLabel = new Label("Draw a card to begin the game!");
        cardLabel.setFont(new Font("Arial", 20));
        root.getChildren().add(cardLabel);

        gameProgressBar = new ProgressBar();
        gameProgressBar.setMaxWidth(500);
        root.getChildren().add(gameProgressBar);

        drawCardButton = new Button("Draw one card");
        drawCardButton.setFont(new Font("Arial", 20));
        drawCardButton.setOnAction(event -> {
            drawCard();
        });
        root.getChildren().add(drawCardButton);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                cardProgress += 0.001;
                gameProgressBar.setProgress(cardProgress);
                if(cardProgress >= 1.0) {
                    drawCard();
                    cardProgress = 0;
                }
            }
        };
        timer.start();

        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                drawCard();
            }
        });
    }

    private void drawCard() {
        if(cardIndex < LOTERIA_CARDS.length) {
            LoteriaCard card = LOTERIA_CARDS[cardIndex];
            cardImageView.setImage(card.getImage());
            cardLabel.setText(card.getCardName());
            cardIndex++;
            gameProgressBar.setProgress(cardProgress);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}