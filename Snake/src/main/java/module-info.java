module sequut.snake {
    requires javafx.controls;
    requires javafx.fxml;

    opens sequut.gui to javafx.fxml;
    exports sequut.gui;
    exports sequut.settings;
    exports sequut.logic;
}