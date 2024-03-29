module com.sanusbrain {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires java.sql;
    requires mysql.connector.j;

    opens com.sanusbrain to javafx.fxml,MaterialFX;
    opens com.sanusbrain.Controllers to MaterialFX, javafx.fxml;

    exports com.sanusbrain;
    exports com.sanusbrain.Models;
    exports com.sanusbrain.Views;
    exports com.sanusbrain.Controllers;
}