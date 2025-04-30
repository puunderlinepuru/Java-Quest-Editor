package com.questgame.quest;

import java.io.*;
import java.util.*;

public class FileHandler {

    protected String[] tempArray;
    protected List<String> contents;
    protected int options;
    protected String tempStr;
    protected String text;
    protected String options_text;
    protected List<String> unlocks;
    protected List<String> reqs;
    protected List<String> links;
    protected boolean req_section;
    protected boolean unl_section;
    protected boolean link_section;

    protected void get_from_file(String number, boolean edit_switch) {

        text = "";
        options = 0;
        options_text = "";
        links = new ArrayList<>();
        contents = new ArrayList<>();
        reqs = new ArrayList<>();
        unlocks = new ArrayList<>();

        String directoryPath = "resources";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(number +" .txt")) {
                    try {
                        File script = new File("resources/" + number + " .txt");
                        Scanner reader = new Scanner(script).useDelimiter("#");
                        while (reader.hasNext())
                        {
                            tempStr = reader.next();
                            tempStr = tempStr.replace("\r", "");
                            contents.add(tempStr);
                        }

                        for (int i = 0; i < contents.size();i++){

//                            Text
                            if (contents.get(i).contains("Text"))
                            {
                                tempStr = contents.get(i+1);
                                tempStr = tempStr.substring(0, tempStr.lastIndexOf("\n"));
                                tempStr = tempStr.substring(0, tempStr.lastIndexOf("\n"));
                                text = tempStr;
                            }

//                            Options
                            if (contents.get(i).contains("Options") ) {
                                options_text = contents.get(i+1);
                                if (options_text.charAt(options_text.length()-1) == '\n' & options_text.charAt(options_text.length()-2) == '\n')
                                {
                                    options_text = options_text.replace("\n\n", "");
                                } else if (options_text.charAt(options_text.length()-1) == '\n') {
                                    options_text = options_text.substring(0, options_text.lastIndexOf("\n"));
                                }
                                options = contents.get(i+1).length() - contents.get(i+1).replace("[", "").length();
                            }

//                            Links
                            if (link_section) {
                                if (contents.get(i).contains("-")) {
                                    tempArray = contents.get(i).replaceAll("\\s", "").split("-");
                                    links.add(tempArray[0]);
                                    links.add(tempArray[1]);
                                }
                            }
                            if (contents.get(i).contains("Links") ) {link_section=true;}

//                            Requirements
                            if (req_section) {
                                if (contents.get(i).contains("for")) {
                                    tempArray = contents.get(i).replaceAll("\\s", "").split("for");
                                    reqs.add(tempArray[0]);
                                    reqs.add(tempArray[1]);
                                }
                            }
                            if (contents.get(i).contains("Requirements") )
                            {
                                link_section=false;
                                req_section=true;
                            }

//                            Unlocks
                            if (unl_section) {unlocks.add(contents.get(i).replaceAll("\\s", ""));}
                            if (contents.get(i).contains("Unlocks") ) {
                                req_section=false;
                                unl_section=true;
                            }
                        }
                        unl_section = false;
/*                        System.out.println("Text: " + text);
                        System.out.println("options: " + options);
                        System.out.println("Links: " + links);
                        System.out.println("Reqs: " + reqs);
                        System.out.println("Unls: " + unlocks);*/

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
            if (edit_switch) {

                try {
                    File script = new File("resources/" + number + " .txt");
                    script.createNewFile();
                    System.out.println("Couldn't find file. File created.");
                } catch (Exception e) {
                    System.out.println("Error creating");
                }
            }

//            file
        }
    }


    protected void spellcheck(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.println(Character.getName(text.charAt(i)));
        }
    }

    protected void formatFile(String number){
        File script = new File("resources/" + number + " .txt");
        try {
            new FileOutputStream(script).close();
            FileWriter fileWriter = new FileWriter(script);

//            Text
            fileWriter.write("#Text\n#" + text);

//            Options
            fileWriter.write("\n\n#Options\n#" + options_text);

//            Links
            fileWriter.write("\n\n#Links");
            if (!links.isEmpty()) {
                for (int i = 0; i < links.size(); i += 2) {
                    fileWriter.write("\n#" + links.get(i) + "-" + links.get(i + 1));
                }
            }

//            Reqs
            fileWriter.write("\r\n\r\n#Requirements");
            if (!reqs.isEmpty()) {
                for (int i = 0; i < reqs.size(); i += 2) {
                    fileWriter.write("\n#" + reqs.get(i) + " for " + reqs.get(i + 1));
                }
            }

//            Unlocks
            fileWriter.write("\r\n\r\n#Unlocks");
            if (!unlocks.isEmpty()) {
                for (String a : unlocks) {
                    fileWriter.write("\n#" + a);
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
