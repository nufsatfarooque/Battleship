module com.example.lab06_1a_210041115 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.lab06_1a_210041115 to javafx.fxml;
    exports com.example.lab06_1a_210041115;
}