package com.questgame.quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileHandler {

    protected String temp;
    protected List<String> contents = new ArrayList<>();



    protected void get(int number) {
        String directoryPath = "bin/resources";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(number +" .txt")) {
                    try {
                        File script = new File("bin/resources/" + number + " .txt");
                        Scanner reader = new Scanner(script).useDelimiter("\\A");
                        while (reader.hasNext()) {
                            temp = reader.nextLine().replace("#", "");
                            temp = temp.replace(" ", "");
                            contents.add(temp);
                            contents.removeAll(Arrays.asList("", " "));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(contents);
                }
            }
        }
    }

    protected String getContents(int number) {
        contents.clear();
        get(number);
        return contents.get(1);
    }

    protected void make(){

    }
}
