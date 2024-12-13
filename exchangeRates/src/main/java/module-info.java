module com.rates.exchangerates {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.jsoup;
    requires com.fasterxml.jackson.databind;


    opens com.rates.exchangerates to javafx.fxml;
    exports com.rates.exchangerates;
}