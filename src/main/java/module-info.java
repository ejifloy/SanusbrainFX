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
    exports com.sanusbrain.Utils;
    exports com.sanusbrain.Controllers.Patient;
    opens com.sanusbrain.Controllers.Patient to MaterialFX, javafx.fxml;
}