module ma.jakardev.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.jakardev.javafx to javafx.fxml;
    exports ma.jakardev.javafx;
    opens ma.jakardev.javafx.presentation.controllers;
    opens ma.jakardev.javafx.dao.entities;
    exports ma.jakardev.javafx.presentation.controllers;
}