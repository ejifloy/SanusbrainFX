module com.sanusbrain {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires java.sql;
    requires mysql.connector.j;

    opens com.sanusbrain to javafx.fxml,MaterialFX;
    opens com.sanusbrain.Controllers to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Login to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary.Home to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary.Patient to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary.Patient.Base to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary.Patient.History to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary.Patient.Overview to MaterialFX, javafx.fxml;
    opens com.sanusbrain.Controllers.Primary.Settings to MaterialFX, javafx.fxml;


    exports com.sanusbrain;
    exports com.sanusbrain.Controllers;
    exports com.sanusbrain.Controllers.Login;
    exports com.sanusbrain.Controllers.Primary;
    exports com.sanusbrain.Controllers.Primary.Home;
    exports com.sanusbrain.Controllers.Primary.Patient;
    exports com.sanusbrain.Controllers.Primary.Patient.Base;
    exports com.sanusbrain.Controllers.Primary.Patient.History;
    exports com.sanusbrain.Controllers.Primary.Patient.Overview;
    exports com.sanusbrain.Controllers.Primary.Settings;
    exports com.sanusbrain.Models;
    exports com.sanusbrain.Models.DAO;
    exports com.sanusbrain.Models.Entities.Attachment;
    exports com.sanusbrain.Models.Entities.History;
    exports com.sanusbrain.Models.Entities.Patient;
    exports com.sanusbrain.Models.Service;
    exports com.sanusbrain.Views;
    exports com.sanusbrain.Views.Enums.FXML;
    exports com.sanusbrain.Views.Enums.Database;
    exports com.sanusbrain.Utils;
}