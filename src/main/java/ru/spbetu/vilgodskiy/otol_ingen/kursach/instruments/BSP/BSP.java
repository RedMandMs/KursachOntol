package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BSP;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib.GUI_ADV;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.Instrument;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.TYPES_INSTRUMENTS;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class BSP extends Instrument {

    public BSP(String name) {
        this.instrumentType = TYPES_INSTRUMENTS.BSP;
        this.name = name;
    }

    public BSP(String name, BSP_ADV bspAdv, BOOLEAN_PARAM avalibAndroid, BOOLEAN_PARAM multyplatform) {
        this.instrumentType = TYPES_INSTRUMENTS.BSP;
        this.name = name;
        this.bspAdv = bspAdv;
        this.avalibAndroid = avalibAndroid;
        this.multyPlatform = multyplatform;
    }

    private String name;

    private BSP_ADV bspAdv;

    private BOOLEAN_PARAM avalibAndroid;

    public BSP_ADV getBspAdv() {
        return bspAdv;
    }

    public void setBspAdv(BSP_ADV bspAdv) {
        this.bspAdv = bspAdv;
    }

    public BOOLEAN_PARAM isAvalibAndroid() {
        return avalibAndroid;
    }

    public void setAvalibAndroid(BOOLEAN_PARAM avalibAndroid) {
        this.avalibAndroid = avalibAndroid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void writeInOntol() {

    }

    @Override
    public JPanel toDesignReviewPanel() {
        JPanel bspPanel = new JPanel(new GridLayout(5, 2));

        bspPanel.add(new JLabel("Преимущество системы сборки:"));
        JComboBox<BSP_ADV> bspAdvBox = new JComboBox(BSP_ADV.values());
        bspPanel.add(bspAdvBox);
        bspAdvBox.setSelectedItem(bspAdv);
        bspPanel.add(new JLabel("Поддержка андроид:"));
        JComboBox avalAndBox = new JComboBox(BOOLEAN_PARAM.values());
        bspPanel.add(avalAndBox);
        avalAndBox.setSelectedItem(avalibAndroid);
        bspPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        bspPanel.add(multyPlatfBox);
        multyPlatfBox.setSelectedItem(multyPlatform);

        return bspPanel;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static JPanel toDesignCreatePanel(){
        JPanel bspPanel = new JPanel(new GridLayout(5, 2));

        bspPanel.add(new JLabel("Имя новой системы сборки проектов:"));
        bspPanel.add(new JTextField());
        bspPanel.add(new JLabel("Преимущество системы сборки:"));
        JComboBox<BSP_ADV> bspAdvBox = new JComboBox(BSP_ADV.values());
        bspPanel.add(bspAdvBox);
        bspPanel.add(new JLabel("Поддержка проектов для ОС Android:"));
        JComboBox<BOOLEAN_PARAM> booleanBox = new JComboBox(BOOLEAN_PARAM.values());
        bspPanel.add(booleanBox);
        bspPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        bspPanel.add(multyPlatfBox);

        return bspPanel;
    }

    public static BSP getNewBSP(JPanel createPanel){
        JPanel createBSPPanel = (JPanel) createPanel.getComponent(2);
        JTextField nameTF = (JTextField) createBSPPanel.getComponent(1);
        JComboBox<BSP_ADV> bspAdvCB = (JComboBox<BSP_ADV>) createBSPPanel.getComponent(3);
        JComboBox<BOOLEAN_PARAM> avalibAndroid = (JComboBox<BOOLEAN_PARAM>) createBSPPanel.getComponent(5);
        JComboBox<BOOLEAN_PARAM> multyPlatfCB = (JComboBox<BOOLEAN_PARAM>) createBSPPanel.getComponent(7);

        BSP bsp = new BSP(nameTF.getText(),
                (BSP_ADV) bspAdvCB.getSelectedItem(),
                (BOOLEAN_PARAM) avalibAndroid.getSelectedItem(),
                (BOOLEAN_PARAM) multyPlatfCB.getSelectedItem());

        return bsp;
    }

    public BOOLEAN_PARAM getAvalibAndroid() {
        return avalibAndroid;
    }
}
