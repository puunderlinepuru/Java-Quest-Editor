package com.questgame.quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.Object;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AppController {
    @FXML
    Button restoreButton;
    @FXML
    Button editButton;
    protected boolean edit_switch;
    @FXML
    Button restartButton;
    @FXML
    TextField choiceField;
    @FXML
    TextField getItemField;
    @FXML
    TextField needItemNameField;
    @FXML
    TextField needItemOptionField;
    @FXML
    TextField stageNumber;
    @FXML
    TextArea writeStage;
    @FXML
    TextFlow readStage;
    @FXML
    Label keyItems;
    @FXML
    Label keyItemsLabel;
    @FXML
    Label forMessages;
    @FXML
    Pane editBottomPane;
    @FXML
    Pane editTopPane;

    FileHandler fileHandler = new FileHandler();
    Text read_text = new Text();

    protected Integer intStageNumber(String checkifnumber) {
        try {
            forMessages.setText("");
            return Integer.parseInt(checkifnumber);
        } catch (NumberFormatException e) {
            forMessages.setText("<- wrong format");
            return null;
        }
    }

//    restoreButton;
    @FXML
    protected void onRestoreButton() {

    }

//    editButton;
    @FXML
    protected void onEditButton() {
        if(!edit_switch) {
            readStage.setVisible(false);
            readStage.setDisable(true);

            writeStage.setVisible(true);
            writeStage.setDisable(false);

            keyItems.setVisible(true);
            keyItems.setDisable(false);
            keyItemsLabel.setVisible(true);
            keyItemsLabel.setDisable(false);

            editBottomPane.setVisible(true);
            editBottomPane.setDisable(false);
            editTopPane.setVisible(true);
            editTopPane.setDisable(false);

            edit_switch = true;
        }
        else {
            readStage.setVisible(true);
            readStage.setDisable(false);

            writeStage.setVisible(false);
            writeStage.setDisable(true);

            keyItems.setVisible(false);
            keyItems.setDisable(true);
            keyItemsLabel.setVisible(false);
            keyItemsLabel.setDisable(true);

            editBottomPane.setVisible(false);
            editBottomPane.setDisable(true);
            editTopPane.setVisible(false);
            editTopPane.setDisable(true);

            edit_switch = false;
        }
    }

//    choiceField;
    @FXML
    protected void onChoiceFieldEnter() {

    }

//    getItemField;
    @FXML
    protected void onItemFieldEnter() {

    }

//    needItemNameField;
    @FXML
    protected void onNeedItemNameField() {

    }

//    needItemOptionField;
    @FXML
    protected void onNeedItemOptionField() {

    }

//    stageNumber;
    @FXML
    protected void onStageNumber() {

        if (intStageNumber(stageNumber.getText()) != null)
        {
            read_text.setText(fileHandler.getContents(intStageNumber(stageNumber.getText())));
            System.out.println(intStageNumber(stageNumber.getText()));
        }
        else
        {
            System.out.println("something wrong");
        }

        writeStage.setText(read_text.getText());

        readStage.getChildren().remove(read_text);
        readStage.getChildren().add(read_text);
    }

//    writeStage;
    @FXML
    protected void onWriteStage() {

    }

//    readStage;

//    keyItems;
}