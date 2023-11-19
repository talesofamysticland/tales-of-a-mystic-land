module org.talesof.talesofamysticland {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires jbcrypt;

    opens org.talesof.talesofamysticland to javafx.fxml;
    opens org.talesof.talesofamysticland.controller to javafx.fxml;

    exports org.talesof.talesofamysticland;
}
