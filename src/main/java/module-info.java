module me.reidj.takiwadai {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.zaxxer.hikari;
    requires com.google.protobuf;
    requires java.sql;

    opens me.reidj.takiwadai to javafx.fxml;
    exports me.reidj.takiwadai;
    exports me.reidj.takiwadai.scene;
    opens me.reidj.takiwadai.scene to javafx.fxml;
    exports me.reidj.takiwadai.scene.main;
    exports me.reidj.takiwadai.scene.registration;
    opens me.reidj.takiwadai.scene.main to javafx.fxml;
    opens me.reidj.takiwadai.scene.registration to javafx.fxml;
}