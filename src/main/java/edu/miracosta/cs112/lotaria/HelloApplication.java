package edu.miracosta.cs112.lotaria;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloApplication extends Application {
    //Constants
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };
    //Class label variables
    int cardIndex = 0;
    float currentProgress = 0;
    Label cardLabel = new Label();
    Button drawCardButton = new Button();
    ImageView cardImageView = new ImageView();
    ProgressBar gameProgressBar = new ProgressBar();
    LoteriaCard[] shuffledCards;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Loteria!");
        stage.setScene(scene);
        stage.show();
        //shuffledCards here
        shuffledCards = LOTERIA_CARDS.clone();
        List<LoteriaCard> list = Arrays.asList(shuffledCards);
        Collections.shuffle(list);
        list.toArray(shuffledCards);
        System.out.println(Arrays.toString(shuffledCards));


        Label titleLabel = new Label("Welcome to EchALE STEM Loteria!");
        titleLabel.setFont(new Font("Arial", 32));
        root.getChildren().add(titleLabel);

        Image cardImage = new Image("file:./resources/0.png");
        cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(500);
        cardImageView.setFitHeight(600);
        root.getChildren().add(cardImageView);

        cardLabel = new Label("Draw a card to begin the game. The progress bar will indicate how far are you in the game.");
        cardLabel.setFont(new Font("Arial", 18));
        cardLabel.setWrapText(true);
        cardLabel.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(cardLabel);

        gameProgressBar = new ProgressBar();
        gameProgressBar.setMaxWidth(500);
        root.getChildren().add(gameProgressBar);

        drawCardButton = new Button("Draw one card");
        drawCardButton.setFont(new Font("Arial", 18));
        drawCardButton.setOnAction(event -> {
            drawCard();
        });
        root.getChildren().add(drawCardButton);
        /*
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                currentProgress += 0.001f;
                gameProgressBar.setProgress(currentProgress);
                if(currentProgress >= 1.0) {
                    drawCard();
                    currentProgress = 0;
                }
            }
        };
        timer.start();
        */
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
            currentProgress = cardIndex / (float)shuffledCards.length;
            gameProgressBar.setProgress(currentProgress);
        }
        else {
            gameProgressBar.setStyle("-fx-accent: red");
            cardLabel.setText("GAME OVER! No more cards left! Please exit the program and rerun the program to reset the whole game. ^_^");
            drawCardButton.setDisable(true);
            cardImageView.setImage(new LoteriaCard().getImage());
        }
    }
    public static void main(String[] args) {
        launch();
    }
}