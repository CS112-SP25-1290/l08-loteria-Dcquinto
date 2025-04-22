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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.util.Random;

public class HelloApplication extends Application {

    private Label titleLabel, messageLabel;
    private ImageView cardImageView;
    private Button drawCardButton;
    private ProgressBar gameProgressBar;
    private int cardIndex = 0;

    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    @Override
    public void start(Stage stage) {

        //Setting up VBox
        VBox root = new VBox();
        Scene scene = new Scene(root,600,700);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5.0);
        stage.setTitle("ECHaLE STEM Loteria");
        stage.setScene(scene);
        stage.show();

        titleLabel = new Label("Welcome to ECHaLE STEM Loteria!");
        titleLabel.setFont(new Font("Arial", 20));
        root.getChildren().add(titleLabel);

        Image image = new Image("file:./resources/0.png");
        cardImageView = new ImageView(image);
        cardImageView.setFitWidth(500);
        cardImageView.setFitHeight(600);
        root.getChildren().add(cardImageView);

        messageLabel = new Label("Draw a card to start the game.");
        messageLabel.setFont(new Font ("Arial", 20));
        root.getChildren().add(messageLabel);

        drawCardButton = new Button("Generate a new card");
        drawCardButton.setOnAction(event -> {
            drawCard(cardImageView, messageLabel, gameProgressBar);
        });
        root.getChildren().add(drawCardButton);

        gameProgressBar = new ProgressBar();
        root.getChildren().add(gameProgressBar);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) {
                    drawCard(cardImageView, messageLabel, gameProgressBar);
                }
            }
        });
    }


    private void drawCard(ImageView cardImageView, Label messageLabel, ProgressBar gameProgressBar) {
        if(cardIndex == LOTERIA_CARDS.length) {
            messageLabel.setText("GAME OVER. No more cards! Exit and run program again to reset ^_^");
            gameProgressBar.setStyle("fx-accent: red");
            drawCardButton.setDisable(true);
        }
        else {
            LoteriaCard card = LOTERIA_CARDS[cardIndex];
            cardImageView.setImage(card.getImage());
            cardIndex++;
            gameProgressBar.setProgress((double)cardIndex/(double)LOTERIA_CARDS.length);
        }
    }
    public static void main(String [] args) {
    launch();
    }
}