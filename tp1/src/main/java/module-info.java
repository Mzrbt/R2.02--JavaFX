module tp1 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens tp1 to javafx.fxml;
    exports tp1;
}
