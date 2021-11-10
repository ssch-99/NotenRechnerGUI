module ch.saschaschumacher.notenrechnergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.saschaschumacher.notenrechnergui to javafx.fxml;
    exports ch.saschaschumacher.notenrechnergui;
}