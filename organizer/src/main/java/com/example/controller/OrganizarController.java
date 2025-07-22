package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.MainApp;
import com.example.func.RegrasArquivos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class OrganizarController implements Initializable {

    //mensagem de concluido
    @FXML
    private Label labelConcluidoFX;

    //Botao Organizar
    @FXML
    private Button buttonOrganizarFX;

    //radio button
    private ToggleGroup regrasOrganizacaogGroup;

    @FXML
    private RadioButton radioButtonDataFX;
    @FXML
    private RadioButton radioButtonTipoFX;
    @FXML
    private RadioButton radioButtonExtensaoFX;

    //Botão de selecionar pasta
    @FXML
    private TextField TextFieldPastaSelectFX;

    //metodo que carrega assim que o FXML responsavel pelo controlador carrega
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Regras de Organizaçao em um grupo 
        regrasOrganizacaogGroup = new ToggleGroup();
        radioButtonDataFX.setToggleGroup(regrasOrganizacaogGroup);
        radioButtonTipoFX.setToggleGroup(regrasOrganizacaogGroup);
        radioButtonExtensaoFX.setToggleGroup(regrasOrganizacaogGroup);

        regrasOrganizacaogGroup.selectedToggleProperty().addListener((obs, antigo, novo) -> {
            if (regrasOrganizacaogGroup.getSelectedToggle() != null) {
                buttonOrganizarFX.setDisable(false);
            } else {
                buttonOrganizarFX.setDisable(true);
            }
        });
    }

    //Botão de voltar ao menu
    @FXML
    private void voltarMenu() throws IOException {
        MainApp.setRoot("dashboard", "Odos");
    }

    //Evento de selecionar pasta e exibir pasta selecionada
    @FXML
    private void selecionarPasta(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecione uma Pasta");

        Stage stage = (Stage) ((javafx.scene.control.Control) event.getSource()).getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            TextFieldPastaSelectFX.setText(selectedDirectory.getAbsolutePath()); // Define o texto do TextField
        } else {
            TextFieldPastaSelectFX.setText("Nenhuma pasta selecionada."); // Mensagem caso nada seja selecionado
        }
    }

    @FXML
    private void organizar() {
        if (radioButtonTipoFX.isSelected()) {
            String caminho = TextFieldPastaSelectFX.getText();
            RegrasArquivos.tipoDeArquivo(caminho);
            labelConcluidoFX.setVisible(true);
        }
    }
}
