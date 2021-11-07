package com.bogdansalau.optimal.ui;

import com.bogdansalau.optimal.model.PlayerScore;
import com.bogdansalau.optimal.player.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import static com.bogdansalau.optimal.game.Constants.STARTING_DELAY;

public class TableController {

    public static double DELAY_BETWEEN_GAMES = 100;

    @FXML
    private TableView<PlayerScore> tableView;

    @FXML
    private Slider slider;

    @FXML
    public void initialize() {

        TableColumn<PlayerScore, String> nameColumn = new TableColumn<>("Team name");
        nameColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getPlayerName());
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });

        TableColumn<PlayerScore, String> rateColumn = new TableColumn<>("Hire success rate");
        rateColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                double rate = p.getValue().getSuccessRate();

                return new SimpleStringProperty(formatter.format(rate * 100 ) + " %");
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
        rateColumn.setPrefWidth(200);

        TableColumn<PlayerScore, String> success = new TableColumn<>("Correct decisions");
        success.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                int guessed = p.getValue().getGuessed();

                return new SimpleStringProperty(guessed + "");
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
        rateColumn.setPrefWidth(200);

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(rateColumn);
        tableView.getColumns().add(success);

        slider.setMin(10);
        slider.setMax(100);
        slider.setValue(STARTING_DELAY);
        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> DELAY_BETWEEN_GAMES = slider.getValue());
    }

    public void update(Map<Player, PlayerScore> scoreMap) {
        tableView.getItems().clear();
        tableView.getItems().addAll(scoreMap.values());
    }
}
