package com.gerini;

import java.io.IOException;
import java.util.List;
import com.gerini.businesLogic.Game;
import com.gerini.businesLogic.Horse;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PrimaryController {
    private Game game = new Game();
    private int amount;
    @FXML
    VBox bars;
    @FXML
    Button start;
    @FXML
    Spinner<Integer> nHorse;
    @FXML
    Text log;
    public void initialize() {
        SpinnerValueFactory<Integer> spinner = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(2,game.getMaxhorses(), 2);// min max base
            
        nHorse.setValueFactory(spinner);
    }

    private void createBars() {
        for (int i = 0; i < amount; i++) {
            ProgressBar bar = new ProgressBar(0);
            bar.setPrefWidth(1000);
            bar.setId(i + "horse");
            bars.getChildren().add(bar);
        }
    }

    public void handleStart() throws IOException {
        amount = nHorse.getValue();
        try {
            game.addHorse(amount);
        } catch (Exception e) {
            throw new IOException(e);
        }
        createBars();
        startTimeline();
        new Thread(()->{
            game.startGame();
            log.setText("il cavallo numero "+game.getWinner()+" ha vinto");
        }).start();;

    }

    private void startTimeline() {
        List<Horse> horses = game.getHorses();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(50), event -> {
                    for (int i = 0; i < amount; i++) {
                        Node node = bars.getChildren().get(i);
                        if (node instanceof ProgressBar) {
                            ProgressBar bar = (ProgressBar) node;
                            Horse horse = horses.get(i);
                            double progress = horse.getPosition() / 1000.0;
                            bar.setProgress(progress);
                        }
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
