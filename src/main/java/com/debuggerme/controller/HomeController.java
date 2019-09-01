package com.debuggerme.controller;

import com.debuggerme.model.StationDTO;
import com.debuggerme.util.Stations;
import com.debuggerme.util.StationsConstant;
import com.debuggerme.util.StreamDownloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeController implements Initializable {

    @FXML
    private Button btnPath;

    @FXML
    private TextField txtPath;

    @FXML
    private Button btnRecordon;

    @FXML
    private ComboBox<String> cmbStations;

    @FXML
    private TextField txtFileName;

    @FXML
    private TableView<StationDTO> tblList;


    private String dirPath;

    private ExecutorService executorService;


    @FXML
    void cmbStationsOnAction(ActionEvent event) {
        System.out.println(StationsConstant.getStationUrl(cmbStations.getSelectionModel().getSelectedItem()));
    }



    @FXML
    void btnRecordonAction(ActionEvent event) throws IOException {
        if (dirPath!=null && cmbStations.getSelectionModel().getSelectedItem()!=null && !txtFileName.getText().isEmpty())  {
                Timer timer = new Timer();
                String fileName = txtFileName.getText();
                String stationUrl = StationsConstant.getStationUrl(cmbStations.getSelectionModel().getSelectedItem());
                String chunkUrl = StationsConstant.getChunkUrl(cmbStations.getSelectionModel().getSelectedItem());
                timer.schedule(new StreamDownloader(chunkUrl,stationUrl,dirPath,fileName), 0, 10000);

                StationDTO dto = new StationDTO();
                dto.setTimer(timer);
                dto.setStation(cmbStations.getSelectionModel().getSelectedItem());
                dto.setFileName(fileName);
                dto.setStartedTime(LocalDate.now());
                dto.setStatus("Recording");
                dto.setLocation(dirPath);

                Button btnOpen = new Button();
                btnOpen.setStyle("-fx-background-color: #79a463");
                btnOpen.setText("View");

                Button btnAction = new Button();
                btnAction.setStyle("-fx-background-color: #a43414");
                btnAction.setText("Stop");

                dto.setOpen(btnOpen);
                dto.setAction(btnAction);

                dto.getAction().setOnAction(event1 -> {
                    dto.getTimer().cancel();
                    dto.getAction().setText("Play");
                    dto.setStatus("Stopped");
                    dto.getAction().setDisable(true);

                    ObservableList<StationDTO> items = tblList.getItems();
                    List<StationDTO> ss = new ArrayList<>(items);
                    items.clear();
                    tblList.getItems().addAll(FXCollections.observableArrayList(ss));
                });

                dto.getOpen().setOnAction(event1 -> {
                    BasicThreadFactory factory = new BasicThreadFactory.Builder()
                            .namingPattern("YourPatternIndeficator")
                            .build();
                    executorService = Executors.newSingleThreadExecutor(factory);
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File(dto.getLocation());
                        executorService.execute(() -> {
                            try {
                                Desktop.getDesktop().open(myFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                    }
                });

                tblList.getItems().add(dto);

        }else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error Occurred !!", ButtonType.OK);
            a.setHeaderText(null);
            a.setContentText("Please Use Valid Station, Save Location or File Name !!!");
            a.show();
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] stations = StationsConstant.getNames(Stations.class);
        cmbStations.getItems().addAll(stations);

        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("station"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fileName"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("startedTime"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("location"));
        tblList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("action"));
        tblList.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("open"));

    }
}
