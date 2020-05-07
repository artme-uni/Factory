package ru.nsu.g.akononov.factory.view;

import ru.nsu.g.akononov.factory.factory.Storage;
import ru.nsu.g.akononov.factory.factory.Worker;
import ru.nsu.g.akononov.factory.factory.observable.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UI extends JFrame implements Observer {
    private final int width;
    private final int height;

    ArrayList<String> names;
    ArrayList<Integer> capacity;

    private final int shiftX;
    private final int shiftY;

    private int currentShiftX;
    private int currentShiftY;


    public UI(Storage storage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        width = screenSize.width * 2 / 3;
        height = screenSize.height / 3;

        setSize(width, height + 25);
        initFrameSettings();

        names = new ArrayList<>(Arrays.asList("Bodies", "Engines", "Accessories", "Cars", "Sold cars", "Workers"));
        shiftX = width / 10;
        shiftY = height / (2 * names.size() + 1);

        currentShiftX = shiftX / 2;
        currentShiftY = shiftY;

        for (var name : names) {
            addRow(name);
        }

        capacity = new ArrayList<>(Arrays.asList(storage.getBodiesStorageCapacity(),
                        storage.getEnginesStorageCapacity(),
                        storage.getAccessoriesStorageCapacity(),
                        storage.getCarsStorageCapacity()));

        currentShiftX += shiftX;
        currentShiftY = shiftY;

        for (int i = 0; i < names.size(); i++) {
            addRow(String.valueOf(i));
        }
    }

    private void initFrameSettings() {
        setTitle("Menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GRAY);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
        setLayout(null);
    }

    private void addRow(String name) {
        JLabel currentLabel = new JLabel(name);
        currentLabel.setBounds(currentShiftX, currentShiftY, shiftX, shiftY);
        currentShiftY += 2 * shiftY;
        add(currentLabel);
        currentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentLabel.setVerticalAlignment(SwingConstants.CENTER);
        setVisible(true);
    }

    @Override
    public void updateBodiesCount(int count) {
        //System.out.println("Body Storage : " + count + "/" + storage.getBodiesStorageCapacity());
    }

    @Override
    public void updateEnginesCount(int count) {
        //System.out.println("Engine Storage : " + count + "/" + storage.getEnginesStorageCapacity());
    }

    @Override
    public void updateAccessoriesCount(int count) {
        //System.out.println("Accessory Storage : " + count + "/" + storage.getAccessoriesStorageCapacity());
    }

    @Override
    public void updateWorkersStatus(int status, int workerNumber) {
        //System.out.println("Worker " + workerNumber + " : " + (0 == status ? "Sleeping" : "Working"));
    }

    @Override
    public void updateCarsCount(int count) {
        //System.out.println("Cars Storage : " + count + "/" + storage.getCarsStorageCapacity());
    }

    @Override
    public void updateSoldCarsCount(int count) {
        //System.out.println("Sold cars : " + count);
    }
}
