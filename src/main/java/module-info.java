module com.questgame.quest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires java.desktop;

    opens com.questgame.quest to javafx.fxml;
    exports com.questgame.quest;
}