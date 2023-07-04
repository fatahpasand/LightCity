package org.example.controllers;

import org.example.Game;
import org.example.Main;
import org.example.models.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class JobStatController {
    @FXML
    private Label label;
    @FXML
    private Label positionLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label workCountLabel;
    @FXML
    private Button backButton;

    public void backButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event, "company");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/company-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Image backgroundImage = new Image("file:src/main/resources/org/example/background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backgroundSize);
        root.setBackground(new Background(background));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() throws SQLException {
        Job job = new Job();

        positionLabel.setText("Job position: " + job.jobPosition());
        salaryLabel.setText("Salary: " + String.valueOf(job.salary()));
        workCountLabel.setText("Work count: " + String.valueOf(job.workCount()));

    }
}
