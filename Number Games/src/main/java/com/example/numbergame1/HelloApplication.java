package com.example.numbergame1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class HelloApplication extends Application {
    private static final int MAX_ATTEMPTS = 10;
    private int numberToGuess;
    private int attempts;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guess the Number Game");

        Label instructionLabel = new Label("Guess a number between 1 and 100:");
        TextField guessField = new TextField();
        Button submitButton = new Button("Submit Guess");
        Label feedbackLabel = new Label();
        Label attemptsLabel = new Label();

        submitButton.setOnAction(e -> {
            String guessText = guessField.getText();
            int guess = Integer.parseInt(guessText);
            attempts++;
            if (guess > numberToGuess) {
                feedbackLabel.setText("Too high!");
            } else if (guess < numberToGuess) {
                feedbackLabel.setText("Too low!");
            } else {
                feedbackLabel.setText("Congratulations! You've guessed the number in " + attempts + " attempts.");
                resetGame();
            }
            attemptsLabel.setText("Attempts: " + attempts);
        });

        VBox vbox = new VBox(instructionLabel, guessField, submitButton, feedbackLabel, attemptsLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        resetGame();

        primaryStage.show();
    }

    private void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
    }
}
