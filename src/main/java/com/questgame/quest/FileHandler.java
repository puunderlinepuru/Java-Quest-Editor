package com.questgame.quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileHandler {

    protected List<String> contents;
    protected int options;
    protected String tempStr;
    protected String text;
    protected List<String> unlocks;
    protected List<String> reqs;
    protected List<String> links;
    protected boolean opt_section;
    protected boolean req_section;
    protected boolean unl_section;
    protected boolean link_section;


    protected String getIntro() {

        String directoryPath = "bin/resources";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals("Intro.txt")) {
                    try {
                        File script = new File("bin/resources/Intro.txt");
                        Scanner reader = new Scanner(script).useDelimiter("\\A");
                        return reader.next();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                    }

                }
            }
        }
        return "";
    }

    protected void get(int number) {

        links = new ArrayList<>();
        contents = new ArrayList<>();
        reqs = new ArrayList<>();
        unlocks = new ArrayList<>();

        String directoryPath = "bin/resources";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(number +" .txt")) {
                    try {
                        File script = new File("bin/resources/" + number + " .txt");
                        Scanner reader = new Scanner(script).useDelimiter("#");
                        while (reader.hasNext())
                        {
                            tempStr = reader.next();
                            contents.add(tempStr);
                        }

                        for (int i = 0; i < contents.size();i++){

//                            Text
                            if (contents.get(i).contains("Text"))
                            {
                                tempStr = contents.get(i+1);
                                tempStr = tempStr.substring(0, tempStr.lastIndexOf("\r\n"));
                                tempStr = tempStr.substring(0, tempStr.lastIndexOf("\r\n"));
                                text = tempStr;
                            }

//                            Options
                            if (opt_section) {options++;}
                            if (contents.get(i).contains("Options") ) {opt_section=true;}

//                            Links
                            if (link_section) {links.add(contents.get(i).replaceAll("\\s", ""));}
                            if (contents.get(i).contains("Links") ) {link_section=true; options--; opt_section=false;}

//                            Requirements
                            if (req_section) {
                                reqs.add(contents.get(i).replaceAll("\\s", ""));
                            }
                            if (contents.get(i).contains("Requirements") ) {link_section=false; links.removeLast();req_section=true;}

//                            Unlocks
                            if (unl_section) {unlocks.add(contents.get(i).replaceAll("\\s", ""));}
                            if (contents.get(i).contains("Unlocks") ) {req_section=false; reqs.removeLast();unl_section=true;}
                        }
                        unl_section = false;
                        System.out.println("Text: " + text);
                        System.out.println("options: " + options);
                        System.out.println("Links: " + links);
                        System.out.println("Reqs: " + reqs);
                        System.out.println("Unls: " + unlocks);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
