package com.example.controller;

/*
Put header here


 */
import java.io.IOException;

import com.example.MainApp;

import javafx.fxml.FXML;

public class DashBoardController {

    @FXML
    private void acessOrganizar() throws IOException {
        MainApp.setRoot("Organizar", "Organizar Pastas");
    }
}
