package com.questgame.quest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    Label itemsNeed;
    @FXML
    Label itemsNeedLabel;
    @FXML
    Label itemsGet;
    @FXML
    Label itemsGetLabel;
    @FXML
    Label forMessages;
    @FXML
    Pane editBottomPane;
    @FXML
    Pane editTopPane;

    FileHandler fileHandler = new FileHandler();
    Text read_text = new Text();
    String itemsGetString;
    String itemsNeedString;
    String[] tempArray;

    protected Integer intStageNumber(String checkifnumber) {
        try {
            forMessages.setText("");
            return Integer.parseInt(checkifnumber);
        } catch (NumberFormatException e) {
            forMessages.setText("<- wrong format");
            return null;
        }
    }

    @FXML
    public void initialize(){
        read_text.setText(fileHandler.getIntro());
        readStage.getChildren().remove(read_text);
        readStage.getChildren().add(read_text);
        writeStage.setText(read_text.getText());
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

            itemsNeed.setVisible(true);
            itemsNeed.setDisable(false);
            itemsGet.setVisible(true);
            itemsGet.setDisable(false);
            itemsNeedLabel.setVisible(true);
            itemsNeedLabel.setDisable(false);
            itemsGetLabel.setVisible(true);
            itemsGetLabel.setDisable(false);

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

            itemsNeed.setVisible(false);
            itemsNeed.setDisable(true);
            itemsGet.setVisible(false);
            itemsGet.setDisable(true);
            itemsNeedLabel.setVisible(false);
            itemsNeedLabel.setDisable(true);
            itemsGetLabel.setVisible(false);
            itemsGetLabel.setDisable(true);

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
            fileHandler.get((intStageNumber(stageNumber.getText())));

//            Text
            read_text.setText(fileHandler.text);

//            Get Items
            itemsGetString = "";
            for (String a : fileHandler.unlocks) {itemsGetString = itemsGetString.concat(a + "\n");}
            itemsGet.setText(itemsGetString);

//            Need Items
            itemsNeedString = "";
            tempArray = new String[]{};
            for (int i = 0; i < fileHandler.reqs.size(); i++) {
                tempArray = fileHandler.reqs.get(i).split("for");
                itemsNeedString = itemsNeedString.concat(tempArray[0] + " for " + tempArray[1] + "\n");
            }

            itemsNeed.setText(itemsNeedString);
//            System.out.println(intStageNumber(stageNumber.getText()));
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