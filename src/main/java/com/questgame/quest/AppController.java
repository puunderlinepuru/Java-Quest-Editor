package com.questgame.quest;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    TextField stageNumber;
    @FXML
    TextArea optionsArea;
    @FXML
    TextArea itemsNeedArea;
    @FXML
    TextArea itemsGetArea;
    @FXML
    TextArea linksArea;
    @FXML
    TextArea writeStage;
    @FXML
    TextArea readStage;
    @FXML
    Label itemsNeedLabel;
    @FXML
    Label itemsGetLabel;
    @FXML
    Label forMessages;
    @FXML
    Label optionsLabel;
    @FXML
    Label linksLabel;
    @FXML
    Pane editTopPane;

    FileHandler fileHandler = new FileHandler();
    String tempStr;
    String curr_stage;
    String[] tempArray;
    String[] tempArray2;
    List<String> inventory;

    protected void display() {

//            Text
        if (edit_switch)
        {
//            Text
            writeStage.setText(fileHandler.text);

//            Options
            optionsArea.setText(fileHandler.options_text);


//            Links
            tempStr = "";
            for (int i = 0; i < fileHandler.links.size(); i+=2) {
                tempStr = tempStr.concat(fileHandler.links.get(i) + "-" + fileHandler.links.get(i+1) + "\n");
            }
            linksArea.setText(tempStr);

//            Reqs
            tempStr = "";
            for (int i = 0; i < fileHandler.reqs.size(); i+=2) {
                tempStr = tempStr.concat(fileHandler.reqs.get(i) + " for " + fileHandler.reqs.get(i+1) + "\n");
            }
            itemsNeedArea.setText(tempStr);

//            Unlocks
            tempStr = "";
            for (String a : fileHandler.unlocks) {tempStr = tempStr.concat(a + "\n");}
            itemsGetArea.setText(tempStr);

        }
        else
        {
            readStage.setText(fileHandler.text + "\n\n" + fileHandler.options_text);
        }

    }

    protected String checkStageNumber(String checkifnumber) {
        try {
            forMessages.setText("");
            return String.valueOf(Integer.parseInt(checkifnumber));
        } catch (NumberFormatException e) {
            forMessages.setText("<- wrong format");
            return null;
        }
    }

//    restoreButton;
    @FXML
    protected void onRestoreButton() {

    }

    @FXML
    protected void onRestartButton() {
        initialize();
    }

//    editButton;
    @FXML
    protected void onEditButton() {
        if(!edit_switch) {
            readStage.setVisible(false);
            readStage.setDisable(true);

            writeStage.setVisible(true);
            writeStage.setDisable(false);

            itemsNeedArea.setVisible(true);
            itemsNeedArea.setDisable(false);
            itemsGetArea.setVisible(true);
            itemsGetArea.setDisable(false);
            itemsNeedLabel.setVisible(true);
            itemsNeedLabel.setDisable(false);
            itemsGetLabel.setVisible(true);
            itemsGetLabel.setDisable(false);

            optionsArea.setVisible(true);
            optionsArea.setDisable(false);
            optionsLabel.setVisible(true);
            optionsLabel.setDisable(false);

            editTopPane.setVisible(true);
            editTopPane.setDisable(false);

            linksArea.setVisible(true);
            linksArea.setDisable(false);
            linksLabel.setVisible(true);
            linksLabel.setDisable(false);



            edit_switch = true;
            System.out.println("editing mode for stage: " + curr_stage);
        }
        else {
            readStage.setVisible(true);
            readStage.setDisable(false);

            writeStage.setVisible(false);
            writeStage.setDisable(true);

            itemsNeedArea.setVisible(false);
            itemsNeedArea.setDisable(true);
            itemsGetArea.setVisible(false);
            itemsGetArea.setDisable(true);
            itemsNeedLabel.setVisible(false);
            itemsNeedLabel.setDisable(true);
            itemsGetLabel.setVisible(false);
            itemsGetLabel.setDisable(true);

            optionsArea.setVisible(false);
            optionsArea.setDisable(true);
            optionsLabel.setVisible(false);
            optionsLabel.setDisable(true);

            editTopPane.setVisible(false);
            editTopPane.setDisable(true);

            linksArea.setVisible(false);
            linksArea.setDisable(true);
            linksLabel.setVisible(false);
            linksLabel.setDisable(true);

            edit_switch = false;
            System.out.println("reading mode for stage: " + curr_stage);
        }
        display();
        fileHandler.get_from_file(curr_stage, edit_switch);
    }

    @FXML
    public void initialize(){
        inventory = new ArrayList<>();
        curr_stage = "1";
        edit_switch = false;
        System.out.println("stage: " + curr_stage);
        fileHandler.get_from_file(curr_stage, false);
        display();
    }

//    choiceField;
    @FXML
    protected void onChoiceFieldEnter() {
        if (choiceField.getText().isBlank()) {return;}
        fileHandler.get_from_file(curr_stage, edit_switch);
        if (fileHandler.options >= Integer.parseInt(choiceField.getText()))
        {
            if (!fileHandler.reqs.isEmpty()) {
                if (fileHandler.reqs.contains(choiceField.getText())) {
                    if (inventory.contains(fileHandler.reqs.get(fileHandler.reqs.indexOf(choiceField.getText()) - 1))) {
                        if (!fileHandler.links.isEmpty()) {
                            curr_stage = fileHandler.links.get(fileHandler.links.indexOf(choiceField.getText()) + 1);
                        } else {
                            curr_stage += checkStageNumber(choiceField.getText());
                        }
                        inventory.addAll(fileHandler.unlocks);
                        fileHandler.get_from_file(curr_stage, edit_switch);
                        display();
                        choiceField.setText("");
                    }
                }
                choiceField.setText("");
            } else {
                if (!fileHandler.links.isEmpty()) {

                    if (fileHandler.links.contains(choiceField.getText()) & fileHandler.links.indexOf(choiceField.getText())%2 != 1) {
                        curr_stage = fileHandler.links.get(fileHandler.links.indexOf(choiceField.getText()) + 1);
                    }
                    else {curr_stage += checkStageNumber(choiceField.getText());}

                } else {
                    curr_stage += checkStageNumber(choiceField.getText());
                }
                inventory.addAll(fileHandler.unlocks);
                fileHandler.get_from_file(curr_stage, edit_switch);
                display();
                choiceField.setText("");
            }
        } else {
            choiceField.setText("");
        }
        System.out.println("stage: " + curr_stage);
    }

//    stageNumber;
    @FXML
    protected void onStageNumber() {

        if (checkStageNumber(stageNumber.getText()) != null)
        {
            curr_stage = checkStageNumber(stageNumber.getText());
            fileHandler.get_from_file(curr_stage, edit_switch);
            display();
        }
        else
        {
            System.out.println("something wrong");
        }
    }

//    writeStage;
    @FXML
    protected void onWriteStage() {

        if (!curr_stage.isEmpty())
        {
            get_from_app();
            fileHandler.formatFile(curr_stage);
            display();
        }

    }

    protected void get_from_app() {

        fileHandler.text = "";
        fileHandler.options = 0;
        fileHandler.options_text = "";
        fileHandler.links = new ArrayList<>();
        fileHandler.contents = new ArrayList<>();
        fileHandler.reqs = new ArrayList<>();
        fileHandler.unlocks = new ArrayList<>();

//        Text
        if (!writeStage.getText().isBlank()){fileHandler.text = writeStage.getText();}


//        Options
        if (!optionsArea.getText().isBlank()) {
            fileHandler.options_text = optionsArea.getText();
            fileHandler.options = optionsArea.getText().length() - optionsArea.getText().replace("[", "").length();
        }

//        Links
        if (!linksArea.getText().isBlank()) {
            tempArray = linksArea.getText().split("\n");
            for (String a : tempArray) {
                tempArray2 = a.split("-");
                fileHandler.links.addAll(Arrays.asList(tempArray2));
            }
        }

//        Requirements
        if (!itemsNeedArea.getText().isBlank()) {
            tempArray = itemsNeedArea.getText().split("\n");
            for (String a : tempArray) {
                tempArray2 = a.split(" for ");
                fileHandler.reqs.addAll(Arrays.asList(tempArray2));
            }
        }

//        Unlocks
        if (!itemsGetArea.getText().isBlank()) {
            fileHandler.unlocks.addAll(List.of(itemsGetArea.getText().split("\n")));
        }

    }
}