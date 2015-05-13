package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.Instrument;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.TYPES_INSTRUMENTS;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class LogLib extends Instrument {

    public LogLib(String name) {
        this.instrumentType = TYPES_INSTRUMENTS.LOG_LIB;
        this.name = name;
    }

    public LogLib(String name, COMPLEXITY complexity, BOOLEAN_PARAM needLogging,BOOLEAN_PARAM multyPaltform) {
        this.instrumentType = TYPES_INSTRUMENTS.LOG_LIB;
        this.complexity = complexity;
        this.name = name;
        this.needLogging = needLogging;
        this.multyPlatform = multyPaltform;
    }

    private COMPLEXITY complexity;

    private String name;

    private BOOLEAN_PARAM needLogging;

    public COMPLEXITY getComplexity() {
        return complexity;
    }

    public void setComplexity(COMPLEXITY complexity) {
        this.complexity = complexity;
    }

    @Override
    public void writeInOntol() {

    }

    @Override
    public JPanel toDesignReviewPanel() {
        JPanel logPanel = new JPanel(new GridLayout(5, 2));

        logPanel.add(new JLabel("Применима для проектов:"));
        JComboBox<COMPLEXITY> complexityBox = new JComboBox(COMPLEXITY.values());
        logPanel.add(complexityBox);
        complexityBox.setSelectedItem(complexity);
        logPanel.add(new JLabel("Необходимость логирования:"));
        JComboBox needLogBox = new JComboBox(BOOLEAN_PARAM.values());
        logPanel.add(needLogBox);
        logPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        logPanel.add(multyPlatfBox);
        multyPlatfBox.setSelectedItem(multyPlatform);

        return logPanel;
    }

    @Override
    public String getName() {
        return name;
    }

    public static JPanel toDesignCreatePanel(){
        JPanel logPanel = new JPanel(new GridLayout(5, 2));

        logPanel.add(new JLabel("Имя новой библиотеки логирования:"));
        logPanel.add(new JTextField());
        logPanel.add(new JLabel("Применима для проектов:"));
        JComboBox<COMPLEXITY> complexityBox = new JComboBox(COMPLEXITY.values());
        logPanel.add(complexityBox);
        logPanel.add(new JLabel("Необходимость логгирования:"));
        JComboBox needLogfBox = new JComboBox(BOOLEAN_PARAM.values());
        logPanel.add(needLogfBox);
        logPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        logPanel.add(multyPlatfBox);

        return logPanel;
    }

    public static LogLib getNewLogLib(JPanel createPanel){
        JPanel createGUIPanel = (JPanel) createPanel.getComponent(2);
        JTextField nameTF = (JTextField) createGUIPanel.getComponent(1);
        JComboBox<COMPLEXITY> guiadvCB = (JComboBox<COMPLEXITY>) createGUIPanel.getComponent(3);
        JComboBox<BOOLEAN_PARAM> needLoggingCB = (JComboBox<BOOLEAN_PARAM>) createGUIPanel.getComponent(5);
        JComboBox<BOOLEAN_PARAM> multyplatformCB = (JComboBox<BOOLEAN_PARAM>) createGUIPanel.getComponent(7);

        LogLib LogLib = new LogLib(nameTF.getText(),
                (COMPLEXITY) guiadvCB.getSelectedItem(),
                (BOOLEAN_PARAM) needLoggingCB.getSelectedItem(),
                (BOOLEAN_PARAM) multyplatformCB.getSelectedItem());

        return LogLib;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public BOOLEAN_PARAM getNeedLogging() {
        return needLogging;
    }
}
