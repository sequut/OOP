package com.loan;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unchecked")
public class LoanApp extends Application {
    private final LoanCalculator calculator = new LoanCalculator();
    private Stage primaryStage;
    private File file = null;
    private final TableView<PaymentScheduleRow> tableView = new TableView<>();
    private boolean scheduleReady = false;
    private List<PaymentSchedule> currentSchedule = null;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("ипотечный калькулятор");

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("файл");
        Menu menuWindow = new Menu("окно");

        MenuItem exitItem = new MenuItem("выход");
        MenuItem downloadTable = new MenuItem("скачать");
        MenuItem showGraph = new MenuItem("график");
        MenuItem loadTable = new MenuItem("загрузить");

        FileChooser fileChooser = new FileChooser();
        ComboBox<String> paymentTypeCombo = new ComboBox<>();
        Button calculateButton = new Button("посчитать");
        paymentTypeCombo.getItems().addAll("дифференцированные платежи", "аннуитетные платежи");

        TableColumn<PaymentScheduleRow, String> dateColumn = new TableColumn<>("дата платежа");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty());

        TableColumn<PaymentScheduleRow, BigDecimal> totalPaymentColumn = new TableColumn<>("общая сумма платежа");

        totalPaymentColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().totalPaymentProperty()));

        TableColumn<PaymentScheduleRow, BigDecimal> interestColumn = new TableColumn<>("проценты");
        interestColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().interestPaymentProperty()));

        TableColumn<PaymentScheduleRow, BigDecimal> principalColumn = new TableColumn<>("основной долг");
        principalColumn.setCellValueFactory(cellData ->  new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().principalPaymentProperty()));

        TableColumn<PaymentScheduleRow, BigDecimal> remainingColumn = new TableColumn<>("остаток задолженности");
        remainingColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().remainingBalanceProperty()));

        tableView.getColumns().addAll(dateColumn, totalPaymentColumn, interestColumn, principalColumn, remainingColumn);
        tableView.setPrefSize(750, 400);

        downloadTable.setOnAction(_ -> {
            if (scheduleReady) {
                File saveFile = fileChooser.showSaveDialog(primaryStage);
                if (saveFile != null) {
                    try {
                        new ExcelWriter().writeExcelTable(saveFile, currentSchedule);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        showGraph.setOnAction(_ -> {
            if (scheduleReady && currentSchedule != null)
                showPaymentGraph(currentSchedule);
            else
                showAlert("нет данных для отображения графика.");
        });

        loadTable.setOnAction(_ -> {
            file = fileChooser.showOpenDialog(primaryStage);
            scheduleReady = false;
        });

        calculateButton.setOnAction(_ -> {
            if (file != null) {
                try {
                    ExcelReader reader = new ExcelReader();
                    LoanParameters params = reader.readLoanParameters(file);

                    String paymentType = paymentTypeCombo.getValue();
                    if (paymentType != null) {
                        List<PaymentSchedule> schedule;
                        scheduleReady = true;

                        if (paymentType.equals("дифференцированные платежи"))
                            schedule = calculator.calculateDifferentiated(params);
                        else
                            schedule = calculator.calculateAnnuity(params);

                        currentSchedule = schedule;
                        populateTable(schedule);
                    } else
                        showAlert("пожалуйста, выберите тип платежей");

                } catch (Exception ex) {
                    showAlert("ошибка при чтении файла: " + ex.getMessage());
                }
            } else
                showAlert("пожалуйста, загрузите файл");
        });

        exitItem.setOnAction(_ -> primaryStage.close());

        MenuItem size800x600 = new MenuItem("800x600");
        size800x600.setOnAction(_ -> changeWindowSize(800, 600));

        MenuItem size1024x768 = new MenuItem("1024x768");
        size1024x768.setOnAction(_ -> changeWindowSize(1024, 768));

        MenuItem size1280x720 = new MenuItem("1280x720");
        size1280x720.setOnAction(_ -> changeWindowSize(1280, 720));

        menuFile.getItems().addAll(loadTable, showGraph, downloadTable, exitItem);
        menuWindow.getItems().addAll(size800x600, size1024x768, size1280x720);
        menuBar.getMenus().addAll(menuFile, menuWindow);

        VBox layout = new VBox(10, menuBar, calculateButton, paymentTypeCombo, tableView);
        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPaymentGraph(List<PaymentSchedule> schedule) {
        Stage graphStage = new Stage();
        graphStage.setTitle("график платежей");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("месяцы");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("сумма платежа");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("график платежей");

        XYChart.Series<Number, Number> totalPaymentSeries = new XYChart.Series<>();
        totalPaymentSeries.setName("общий платеж");

        XYChart.Series<Number, Number> principalSeries = new XYChart.Series<>();
        principalSeries.setName("основной долг");

        XYChart.Series<Number, Number> interestSeries = new XYChart.Series<>();
        interestSeries.setName("проценты");

        int month = 1;
        for (PaymentSchedule ps : schedule) {
            totalPaymentSeries.getData().add(new XYChart.Data<>(month, ps.totalPayment()));
            principalSeries.getData().add(new XYChart.Data<>(month, ps.principalPayment()));
            interestSeries.getData().add(new XYChart.Data<>(month, ps.interestPayment()));
            month++;
        }

        lineChart.getData().addAll(totalPaymentSeries, principalSeries, interestSeries);

        VBox vbox = new VBox(lineChart);
        Scene scene = new Scene(vbox, 800, 600);

        graphStage.setScene(scene);
        graphStage.show();
    }

    private void populateTable(List<PaymentSchedule> schedule) {
        ObservableList<PaymentScheduleRow> rows = FXCollections.observableArrayList();
        for (PaymentSchedule ps : schedule) {
            rows.add(new PaymentScheduleRow(
                    ps.paymentDate().toString(),
                    ps.totalPayment(),
                    ps.interestPayment(),
                    ps.principalPayment(),
                    ps.remainingBalance()
            ));
        }
        tableView.setItems(rows);
    }

    private void changeWindowSize(int width, int height) {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        tableView.setPrefSize(width * 0.8, height * 0.8);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
