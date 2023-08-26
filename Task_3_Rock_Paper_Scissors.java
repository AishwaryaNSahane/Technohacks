import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Task_3_Rock_Paper_Scissors extends Application
 {
    private final String[] choices = {"rock", "paper", "scissors"};
    private final Random random = new Random();
    private Stage stage;
    private Label resultLabel;
    private Label playerChoiceLabel;
    private Label computerChoiceLabel;
    private int playerScore = 0;
    private int computerScore = 0;
    private Label scoreLabel;

    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
     {
        stage = primaryStage;
        stage.setTitle("Rock-Paper-Scissors Game");

        resultLabel = new Label("");
        playerChoiceLabel = new Label("");
        computerChoiceLabel = new Label("");
        scoreLabel = new Label("Score: Player " + playerScore + " Computer " + computerScore);

        
        scoreLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        scoreLabel.setAlignment(Pos.CENTER);

        Button rockButton = createButton("Rock");
        rockButton.setOnAction(e -> playGame("rock"));

        Button paperButton = createButton("Paper");
        paperButton.setOnAction(e -> playGame("paper"));

        Button scissorsButton = createButton("Scissors");
        scissorsButton.setOnAction(e -> playGame("scissors"));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
        layout.getChildren().addAll(
            resultLabel, 
            playerChoiceLabel, 
            computerChoiceLabel,
            scoreLabel,
            rockButton, 
            paperButton, 
            scissorsButton
        );

        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String text)
     {
        Button button = new Button(text);
        button.setMinWidth(90);
        return button;
    }

    private void playGame(String playerChoice)
     {
        int randomIndex = random.nextInt(5);
        String computerChoice = choices[randomIndex];

        String result = determineWinner(playerChoice, computerChoice);
        updateScore(result);
        displayResult(result, playerChoice, computerChoice);
        updateScoreLabel();
    }

    private String determineWinner(String playerChoice, String computerChoice) 
    {
        if (playerChoice.equals(computerChoice)) 
        {
            return "It's a tie!";
        } else if ((playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (playerChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (playerChoice.equals("scissors") && computerChoice.equals("paper"))) 
                   {
            return "You win!";
        } 
        else
         {
            return "Computer wins!";
        }
    }

    private void updateScore(String result)
     {
        if (result.equals("You win!"))
         {
            playerScore++;
        } else if (result.equals("Computer wins!"))
         {
            computerScore++;
        }
    }

    private void updateScoreLabel()
     {
        scoreLabel.setText("Score: Player " + playerScore + " Computer " + computerScore);
    }

    private void displayResult(String result, String playerChoice, String computerChoice) 
    {
        resultLabel.setText(result);
        playerChoiceLabel.setText("Your choice: " + playerChoice);
        computerChoiceLabel.setText("Computer's choice: " + computerChoice);
    }
    
}
