package ru.nsu.g.akononov.factory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main{
    public static void main(String[] args) {

        File file = new File("src/resources/data.properties");
        Properties initValues = new Properties();
        try {
            initValues.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputValues i = new InputValues(initValues);
        Factory factory = new Factory(i);
    }
}