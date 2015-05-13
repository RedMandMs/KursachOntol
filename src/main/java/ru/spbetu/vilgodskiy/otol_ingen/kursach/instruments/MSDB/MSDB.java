package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.MSDB;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.Instrument;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib.COMPLEXITY;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.TYPES_INSTRUMENTS;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class MSDB extends Instrument {

    public MSDB(String name) {
        this.instrumentType = TYPES_INSTRUMENTS.MSDB;
        this.name = name;
    }

    public MSDB(String name, BOOLEAN_PARAM avalibDB, BOOLEAN_PARAM avalibAndroid, BOOLEAN_PARAM multyPlatform) {
        this.instrumentType = TYPES_INSTRUMENTS.MSDB;
        this.name = name;
        this.avalibDB = avalibDB;
        this.avalibAndroid = avalibAndroid;
        this.multyPlatform = multyPlatform;
    }

    private String name;

    private BOOLEAN_PARAM avalibDB;

    private BOOLEAN_PARAM avalibAndroid;

    public BOOLEAN_PARAM isAvalibDB() {
        return avalibDB;
    }

    public void setAvalibDB(BOOLEAN_PARAM avalibDB) {
        this.avalibDB = avalibDB;
    }

    public BOOLEAN_PARAM isAvalibAndroid() {
        return avalibAndroid;
    }

    public void setAvalibAndroid(BOOLEAN_PARAM avalibAndroid) {
        this.avalibAndroid = avalibAndroid;
    }

    @Override
    public void writeInOntol() {

    }

    @Override
    public JPanel toDesignReviewPanel() {
        JPanel msdbPanel = new JPanel(new GridLayout(5, 2));
        msdbPanel.add(new JLabel("Необходимость для работы с БД:"));
        JComboBox<BOOLEAN_PARAM> avalibDBBox = new JComboBox(BOOLEAN_PARAM.values());
        msdbPanel.add(avalibDBBox);
        avalibDBBox.setSelectedItem(avalibDB);
        msdbPanel.add(new JLabel("Поддержка проектов для ОС Android:"));
        JComboBox<BOOLEAN_PARAM> avalibAndroidBox = new JComboBox(BOOLEAN_PARAM.values());
        msdbPanel.add(avalibAndroidBox);
        avalibAndroidBox.setSelectedItem(avalibAndroid);
        msdbPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        msdbPanel.add(multyPlatfBox);
        multyPlatfBox.setSelectedItem(multyPlatform);

        return msdbPanel;
    }

    @Override
    public String getName() {
        return name;
    }

    public static JPanel toDesignCreatePanel(){
        JPanel msdbPanel = new JPanel(new GridLayout(5, 2));

        msdbPanel.add(new JLabel("Имя новой СУБД:"));
        msdbPanel.add(new JTextField());
        msdbPanel.add(new JLabel("Необходимость дл работы с БД:"));
        JComboBox<BOOLEAN_PARAM> avalibDBBox = new JComboBox(BOOLEAN_PARAM.values());
        msdbPanel.add(avalibDBBox);
        msdbPanel.add(new JLabel("Поддержка проектов для ОС Android:"));
        JComboBox<BOOLEAN_PARAM> avalibAndriodBox = new JComboBox(BOOLEAN_PARAM.values());
        msdbPanel.add(avalibAndriodBox);
        msdbPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        msdbPanel.add(multyPlatfBox);

        return msdbPanel;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static MSDB getNewMSDB(JPanel createPanel){
        JPanel createMSDBPanel = (JPanel) createPanel.getComponent(2);
        JTextField nameTF = (JTextField) createMSDBPanel.getComponent(1);
        JComboBox<BOOLEAN_PARAM> avalibDBCB = (JComboBox<BOOLEAN_PARAM>) createMSDBPanel.getComponent(3);
        JComboBox<BOOLEAN_PARAM> avalibAndroidCB = (JComboBox<BOOLEAN_PARAM>) createMSDBPanel.getComponent(5);
        JComboBox<BOOLEAN_PARAM> multyPlatfCB = (JComboBox<BOOLEAN_PARAM>) createMSDBPanel.getComponent(7);

        MSDB msdb = new MSDB(nameTF.getText(),
                (BOOLEAN_PARAM) avalibDBCB.getSelectedItem(),
                (BOOLEAN_PARAM) avalibAndroidCB.getSelectedItem(),
                (BOOLEAN_PARAM) multyPlatfCB.getSelectedItem());

        return msdb;
    }
}
