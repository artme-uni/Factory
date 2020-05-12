package ru.nsu.g.akononov.factory.view;

import ru.nsu.g.akononov.factory.factory.Storage;
import ru.nsu.g.akononov.factory.factory.observable.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UI extends JFrame implements Observer {
    private final int width;
    private final int height;

    private final Storage storage;

    ArrayList<String> names;
    ArrayList<Integer> capacity;
    ArrayList<JLabel> values = new ArrayList<>();
    ArrayList<JProgressBar> bars = new ArrayList<>();

    private final int shiftX;
    private final int shiftY;

    private int currentShiftX;
    private int currentShiftY;

    private int carsTarget = 1000;


    public UI(Storage storage, int workersCount) {
        this.storage = storage;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        width = screenSize.width * 2 / 3;
        height = screenSize.height / 3;

        setSize(width, height + 25);
        initFrameSettings();

        names = new ArrayList<>(Arrays.asList("Bodies", "Engines", "Accessories", "Cars", "Sold cars"));
        for (int i = 0; i < workersCount; i++) {
            names.add("Worker" + i);
        }

        shiftX = width / 10;
        shiftY = height / (2 * names.size() + 1);

        currentShiftX = shiftX / 2;
        currentShiftY = shiftY;

        for (var name : names) {
            addRows(name, false);
        }

        capacity = new ArrayList<>(Arrays.asList(storage.getBodiesStorageCapacity(),
                storage.getEnginesStorageCapacity(),
                storage.getAccessoriesStorageCapacity(),
                storage.getCarsStorageCapacity(), 0, 0));

        currentShiftX += shiftX;
        currentShiftY = shiftY;

        for (int i = 0; i < names.size(); i++) {
            addRows("-", true);
        }

        currentShiftX += shiftX;
        currentShiftY = shiftY;

        for (int i = 0; i < names.size(); i++) {
            addBars();
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

    private void addRows(String name, boolean isValue) {
        JLabel currentLabel = new JLabel(name);
        currentLabel.setBounds(currentShiftX, currentShiftY, shiftX, shiftY);
        currentShiftY += 2 * shiftY;
        currentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(currentLabel);
        if (isValue)
            values.add(currentLabel);

        setVisible(true);
    }

    private void addBars()
    {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setBounds(currentShiftX, currentShiftY, width - currentShiftX - shiftX/2, shiftY);
        currentShiftY += 2 * shiftY;

        bars.add(progressBar);
        add(progressBar);
        setVisible(true);
    }

    @Override
    public void updateBodiesCount(int count) {
        //System.out.println("Body Storage : " + count + "/" + storage.getBodiesStorageCapacity());
        values.get(names.indexOf("Bodies")).setText(count + "/" + storage.getBodiesStorageCapacity());
        bars.get(names.indexOf("Bodies")).setValue((int) ((double) count / storage.getBodiesStorageCapacity()*100));
        repaint();
        revalidate();
    }

    @Override
    public void updateEnginesCount(int count) {
        //System.out.println("Engine Storage : " + count + "/" + storage.getEnginesStorageCapacity());
        values.get(names.indexOf("Engines")).setText(count + "/" + storage.getEnginesStorageCapacity());
        bars.get(names.indexOf("Engines")).setValue((int) ((double) count / storage.getEnginesStorageCapacity()*100));
        repaint();
    }

    @Override
    public void updateAccessoriesCount(int count) {
        //System.out.println("Accessory Storage : " + count + "/" + storage.getAccessoriesStorageCapacity());
        values.get(names.indexOf("Accessories")).setText(count + "/" + storage.getAccessoriesStorageCapacity());
        bars.get(names.indexOf("Accessories")).setValue((int) ((double) count / storage.getAccessoriesStorageCapacity()*100));
        repaint();
    }

    @Override
    public void updateWorkersStatus(int status, int workerNumber) {
        //System.out.println("Worker " + workerNumber + " : " + (0 == status ? "Sleeping" : "Working"));
        values.get(names.indexOf("Worker"+workerNumber)).setText((0 == status ? "Sleeping" : "Working"));
        bars.get(names.indexOf("Worker"+workerNumber)).setValue(0 == status ? 0 : 100);
        repaint();

    }

    @Override
    public void updateCarsCount(int count) {
        //System.out.println("Cars Storage : " + count + "/" + storage.getCarsStorageCapacity());
        values.get(names.indexOf("Cars")).setText(count + "/" + storage.getCarsStorageCapacity());
        bars.get(names.indexOf("Cars")).setValue((int) ((double) count / storage.getCarsStorageCapacity()*100));
        repaint();
    }

    @Override
    public void updateSoldCarsCount(int count) {
        //System.out.println("Sold cars : " + count);
        values.get(names.indexOf("Sold cars")).setText(String.valueOf(count));
        bars.get(names.indexOf("Sold cars")).setValue((int) ((double) count / carsTarget*100));
        repaint();
    }
}
