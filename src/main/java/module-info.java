module com.mycompany.ej3_calculadora2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ej3_calculadora2 to javafx.fxml;
    exports com.mycompany.ej3_calculadora2;
}
