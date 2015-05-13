package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.Instrument;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.TYPES_INSTRUMENTS;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class GUILib extends Instrument {

    public static final String namePanelCreate = "newGUILib";
    public static final String nameAdvGUIBox = "newAdvGUIBox";
    public static final String nameMultyBox= "newMultyBox";

    public GUILib(String name) {
        this.instrumentType = TYPES_INSTRUMENTS.GUI_LIB;
        this.name = name;
    }

    public GUILib(String name, GUI_ADV guiAdv, BOOLEAN_PARAM multyPlatform) {
        this.instrumentType = TYPES_INSTRUMENTS.GUI_LIB;
        this.name = name;
        this.guiAdv = guiAdv;
        this.multyPlatform = multyPlatform;
    }

    private String name;

    private GUI_ADV guiAdv;

    public GUI_ADV getGuiAdv() {
        return guiAdv;
    }

    public void setGuiAdv(GUI_ADV guiAdv) {
        this.guiAdv = guiAdv;
    }

    @Override
    public void writeInOntol() {

    }

    @Override
    public JPanel toDesignReviewPanel() {
        JPanel guiPanel = new JPanel(new GridLayout(5, 2));

        guiPanel.add(new JLabel("Преимущество данной графической библиотеки:"));
        JComboBox<GUI_ADV> guiAdvBox = new JComboBox(GUI_ADV.values());
        guiPanel.add(guiAdvBox);
        guiAdvBox.setSelectedItem(guiAdv);
        guiPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        guiPanel.add(multyPlatfBox);
        multyPlatfBox.setSelectedItem(multyPlatform);

        return guiPanel;
    }

    @Override
    public String getName() {
        return name;
    }

    public static JPanel toDesignCreatePanel(){
        JPanel guiPanel = new JPanel(new GridLayout(5, 2));

        guiPanel.add(new JLabel("Имя новой граической библиотеки:   "));
        guiPanel.add(new JTextField());
        guiPanel.add(new JLabel("Преимущество данной графической библиотеки:    "));
        JComboBox<GUI_ADV> guiAdvBox = new JComboBox(GUI_ADV.values());
        guiAdvBox.setName("guiAdvBox");
        guiPanel.add(guiAdvBox);
        guiPanel.add(new JLabel("Поддержка мультиплатформенности:    "));
        JComboBox<BOOLEAN_PARAM> multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        guiPanel.add(multyPlatfBox);

        guiPanel.setName("newGUILib");
        return guiPanel;
    }

    public static GUILib getNewGUILib(JPanel createPanel){
        JPanel createGUIPanel = (JPanel) createPanel.getComponent(2);
        JTextField nameTF = (JTextField) createGUIPanel.getComponent(1);
        JComboBox<GUI_ADV> guiadvCB = (JComboBox<GUI_ADV>) createGUIPanel.getComponent(3);
        JComboBox<BOOLEAN_PARAM> multyPlatfCB = (JComboBox<BOOLEAN_PARAM>) createGUIPanel.getComponent(5);

        GUILib guiLib = new GUILib(nameTF.getText(),
                (GUI_ADV) guiadvCB.getSelectedItem(),
                (BOOLEAN_PARAM) multyPlatfCB.getSelectedItem());

        return guiLib;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
