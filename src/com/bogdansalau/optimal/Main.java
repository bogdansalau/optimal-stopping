package com.bogdansalau.optimal;

import com.bogdansalau.optimal.game.GameController;
import com.bogdansalau.optimal.player.Player;
import com.bogdansalau.optimal.player.impl.*;
import com.bogdansalau.optimal.ui.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    private final List<Player> playerList = Arrays.asList(
            new Player1(),
            new Player2(),
            new Player3(),
            new Player4(),
            new Player5(),
            new Player6()
    );

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("scoreboard.fxml").openStream());
        TableController tableController = fxmlLoader.getController();

        primaryStage.setTitle("Hiring problem");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new Thread(() -> new GameController(playerList, tableController).start()).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
