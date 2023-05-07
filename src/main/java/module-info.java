module com.example.techprom_vol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.techprom_vol to javafx.fxml;
    exports com.example.techprom_vol;
}