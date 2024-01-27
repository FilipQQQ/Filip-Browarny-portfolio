module app.pociagi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens app.pociagi to javafx.fxml;
    exports app.pociagi;
    exports app.pociagi.db.Objects;
    opens app.pociagi.db.Objects to javafx.fxml;
    exports app.pociagi.utils;
    opens app.pociagi.utils to javafx.fxml;
    exports app.pociagi.controllers;
    opens app.pociagi.controllers to javafx.fxml;
    exports app.pociagi.db.Utils;
    opens app.pociagi.db.Utils to javafx.fxml;
    exports app.pociagi.db.Finders.All;
    opens app.pociagi.db.Finders.All to javafx.fxml;
    exports app.pociagi.db.Finders.Single;
    opens app.pociagi.db.Finders.Single to javafx.fxml;
}