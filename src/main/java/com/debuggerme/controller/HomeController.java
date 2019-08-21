package com.debuggerme.controller;

import com.debuggerme.util.StreamRecorder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Timer;

public class HomeController {

    @FXML
    private Button btnPath;

    @FXML
    private TextField txtPath;

    @FXML
    private Button btnRecordon;

    public static String dirPath;

    private boolean isRecording = false;


    @FXML
    void btnRecordonAction(ActionEvent event) {
        Timer timer = new Timer();
        if (!isRecording) {
            timer.schedule(new StreamRecorder(), 0, 10000);
            btnRecordon.setText("STOP");
            isRecording = true;
        } else {
            timer.cancel();
            btnRecordon.setText("START");
        }

    }

    @FXML
    void onBtnPathOnAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        dirPath = selectedDirectory.getAbsolutePath();


        if (selectedDirectory == null) {
            //No Directory selected
        } else {
            System.out.println(selectedDirectory.getAbsolutePath());
            txtPath.setText(dirPath);

        }
    }



}
