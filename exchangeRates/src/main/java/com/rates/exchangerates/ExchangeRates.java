package com.rates.exchangerates;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ExchangeRates extends Application {
    WebParser webParser = new WebParser();
    @Override
    public void start(Stage primaryStage) {
        TableView<Bank> bankTable = new TableView<>();

        TableColumn<Bank, String> bankNameColumn = new TableColumn<>("Банк");
        bankNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        bankTable.getColumns().add(bankNameColumn);

        TableColumn<Bank, Double> usdBuyColumn = new TableColumn<>("Купить");
        usdBuyColumn.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getCurrencyRate("USD", true)).asObject());

        TableColumn<Bank, Double> usdSellColumn = new TableColumn<>("Продать");
        usdSellColumn.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getCurrencyRate("USD", false)).asObject());

        TableColumn<Bank, Void> usdColumn = new TableColumn<>("USD");
        usdColumn.getColumns().addAll(usdBuyColumn, usdSellColumn);

        TableColumn<Bank, Double> eurBuyColumn = new TableColumn<>("Купить");
        eurBuyColumn.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getCurrencyRate("EUR", true)).asObject());

        TableColumn<Bank, Double> eurSellColumn = new TableColumn<>("Продать");
        eurSellColumn.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getCurrencyRate("EUR", false)).asObject());

        TableColumn<Bank, Void> eurColumn = new TableColumn<>("EUR");
        eurColumn.getColumns().addAll(eurBuyColumn, eurSellColumn);

        bankTable.getColumns().addAll(usdColumn, eurColumn);
        Button refreshButton = new Button("получить актуальные данные");
        refreshButton.setOnAction(_ -> {
            ObservableList<Bank> banks = null;
            try {
                banks = FXCollections.observableArrayList(loadBanks());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bankTable.setItems(banks);

        });
        VBox root = new VBox(10, bankTable, refreshButton);
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Курсы валют");
        primaryStage.show();
    }

    private List<Bank> loadBanks() throws IOException {
        webParser.parse();
        return webParser.bankData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

