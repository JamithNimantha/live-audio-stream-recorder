package com.debuggerme.controller;

import com.debuggerme.model.StationDTO;
import com.debuggerme.util.Stations;
import com.debuggerme.util.StationsConstant;
import com.debuggerme.util.StreamDownloader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;

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
//        System.out.println(StationsConstant.getStationUrl(cmbStations.getSelectionModel().getSelectedItem()));
    }


    @FXML
    void btnRecordonAction(ActionEvent event) throws IOException {
        if (dirPath != null && cmbStations.getSelectionModel().getSelectedItem() != null && !txtFileName.getText().isEmpty()) {
            Timer timer = new Timer();
            String fileName = txtFileName.getText();
            String stationUrl = StationsConstant.getStationUrl(cmbStations.getSelectionModel().getSelectedItem());
            String chunkUrl = StationsConstant.getChunkUrl(cmbStations.getSelectionModel().getSelectedItem());
            timer.schedule(new StreamDownloader(chunkUrl, stationUrl, dirPath, fileName), 0, 10000);

            StationDTO dto = new StationDTO();
            dto.setTimer(timer);
            dto.setStation(cmbStations.getSelectionModel().getSelectedItem());
            dto.setFileName(fileName);
            dto.setStartedTime(LocalTime.now().withNano(0));
            dto.setStatus("Recording");
            dto.setLocation(dirPath);

            Button btnOpen = new Button();
            btnOpen.setStyle("-fx-background-color: #79a463;-fx-cursor: hand");
            btnOpen.setGraphic(new ImageView(new Image("/images/icons8-preview-pane-15.png")));


            Tooltip open = new Tooltip();
            open.setText("click to open folder");
            btnOpen.setTooltip(open);

            Button btnAction = new Button();
            btnAction.setStyle("-fx-background-color: #a43414;-fx-cursor: hand");
            btnAction.setGraphic(new ImageView(new Image("/images/icons8-stop-15.png")));

            Tooltip stop = new Tooltip();
            stop.setText("click to stop recording");
            btnAction.setTooltip(stop);

            dto.setOpen(btnOpen);
            dto.setAction(btnAction);

            Timeline time = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dto.setSize(new DecimalFormat("0.00").format(
                            new File(dirPath+"/"+fileName+".mp3").length()/ (1024.0f * 1024.0f)));
                    ObservableList<StationDTO> items = tblList.getItems();
                    List<StationDTO> ss = new ArrayList<>(items);
                    items.clear();
                    tblList.getItems().addAll(FXCollections.observableArrayList(ss));

                }
            }), new KeyFrame(Duration.seconds(10)));
            time.setCycleCount(Animation.INDEFINITE);
            time.play();

            dto.setTimeline(time);

            dto.getAction().setOnAction(event1 -> {
                dto.getTimer().cancel();
                dto.setTimer(null);
                dto.setStatus("Stopped");
                dto.setEndTime(LocalTime.now().withNano(0));
                dto.getAction().setDisable(true);
                dto.getTimeline().stop();

                ObservableList<StationDTO> items = tblList.getItems();
                List<StationDTO> ss = new ArrayList<>(items);
                items.clear();
                tblList.getItems().addAll(FXCollections.observableArrayList(ss));
            });

            dto.getOpen().setOnAction(event1 -> {

                    /*
                   * if you are using linux mint use this otherwise it will not work(It will crash the programe)
                   *
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
                      */
                try {
                    Desktop.getDesktop().open(new File(dto.getLocation()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            txtFileName.clear();
            tblList.getItems().add(dto);

        } else {
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
//            System.out.println(selectedDirectory.getAbsolutePath());
            txtPath.setText(dirPath);

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnRecordon.setGraphic(new ImageView(new Image("/images/icons8-video-record-24.png")));
        btnPath.setGraphic(new ImageView(new Image("/images/icons8-folder-24.png")));

        String[] stations = StationsConstant.getNames(Stations.class);
        cmbStations.getItems().addAll(stations);

        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("station"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("location"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fileName"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("startedTime"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("endTime"));
        tblList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("size"));
        tblList.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblList.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("action"));
        tblList.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("open"));

    }
}
