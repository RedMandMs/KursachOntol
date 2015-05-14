package ru.spbetu.vilgodskiy.otol_ingen.kursach.user_query;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.Content;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BSP.BSP;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BSP.BSP_ADV;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib.GUILib;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib.GUI_ADV;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib.COMPLEXITY;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib.LogLib;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.MSDB.MSDB;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.VCS.TEAM_SIZE;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.VCS.VCS;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class UserQuery {

    Content content;

    public UserQuery(Content content) {
        this.content = content;
    }

    public UserQuery(Content content, COMPLEXITY complexity, BOOLEAN_PARAM avalibDB, TEAM_SIZE teamSize,
                     BSP_ADV bspAdv, GUI_ADV guiAdv, BOOLEAN_PARAM multyPlatform,
                     BOOLEAN_PARAM avalibAndroid, BOOLEAN_PARAM needLogging) {
        this.content = content;
        this.complexity = complexity;
        this.avalibDB = avalibDB;
        this.teamSize = teamSize;
        this.bspAdv = bspAdv;
        this.guiAdv = guiAdv;
        this.multyPlatform = multyPlatform;
        this.avalibAndroid = avalibAndroid;
        this.needLogging = needLogging;
    }

    private COMPLEXITY complexity;
    private BOOLEAN_PARAM avalibDB;
    private TEAM_SIZE teamSize;
    private BSP_ADV bspAdv;
    private GUI_ADV guiAdv;
    private BOOLEAN_PARAM multyPlatform;
    private BOOLEAN_PARAM avalibAndroid;
    private BOOLEAN_PARAM needLogging;

    private JComboBox<BOOLEAN_PARAM> multyPlatfBox;
    private JComboBox<TEAM_SIZE> teamSizeBox;
    private JComboBox<GUI_ADV> guiAdvBox;
    private JComboBox<BOOLEAN_PARAM> avalibBDBox;
    private JComboBox<BOOLEAN_PARAM> avalibLogBox;
    private JComboBox<BOOLEAN_PARAM> avalibAndroidBox;
    private JComboBox<BSP_ADV> bspAdvBox;
    private JComboBox<COMPLEXITY> complexityBox;

    private JPanel queryPanel;

    public List<String> suitableBSP;
    public List<String> suitableGUILib;
    public List<String> suitableLogLib;
    public List<String> suitableMSDB;
    public List<String> suitableVCS;

    public static JButton suitableBtn = new JButton("Найти подходящие инструменты");

    public JPanel toDesignQueryPanal(){
        JPanel queryPanel = new JPanel(new GridLayout(15, 2));

        queryPanel.add(new JLabel("Какой сложности проект:"));
        complexityBox = new JComboBox(COMPLEXITY.values());
        queryPanel.add(complexityBox);
        queryPanel.add(new JLabel("Преимущество системы сборки:"));
        bspAdvBox = new JComboBox(BSP_ADV.values());
        queryPanel.add(bspAdvBox);
        queryPanel.add(new JLabel("Поддержка проектов для ОС Android:"));
        avalibAndroidBox = new JComboBox(BOOLEAN_PARAM.values());
        queryPanel.add(avalibAndroidBox);
        queryPanel.add(new JLabel("Необходимость работы с базой данных:"));
        avalibBDBox = new JComboBox(BOOLEAN_PARAM.values());
        queryPanel.add(avalibBDBox);
        queryPanel.add(new JLabel("Необходимость логирования:"));
        avalibLogBox = new JComboBox(BOOLEAN_PARAM.values());
        queryPanel.add(avalibLogBox);
        queryPanel.add(new JLabel("Преимущество графической библиотеки:    "));
        guiAdvBox = new JComboBox(GUI_ADV.values());
        queryPanel.add(guiAdvBox);
        queryPanel.add(new JLabel("Преимущество системы сборки:"));
        teamSizeBox = new JComboBox(TEAM_SIZE.values());
        queryPanel.add(teamSizeBox);
        queryPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        queryPanel.add(multyPlatfBox);
        queryPanel.add(suitableBtn);
        //TODO: Листнер
        queryPanel.add(new JLabel(""));
        /*queryPanel.add(new JLabel("Подходящая система сборки проекта:"));
        queryPanel.add(new JLabel(suitableBSP.toString()));
        queryPanel.add(new JLabel("Подходящая библиотека грфического интерфейса:"));
        queryPanel.add(new JLabel(suitableGUILib.toString()));
        queryPanel.add(new JLabel("Подходящая библиотека логирования:"));
        queryPanel.add(new JLabel(suitableLogLib.toString()));
        queryPanel.add(new JLabel("Подходящая СУБД:"));
        queryPanel.add(new JLabel(suitableMSDB.toString()));
        queryPanel.add(new JLabel("Подходящая система контроля версий:"));
        queryPanel.add(new JLabel(suitableVCS.toString()));*/

        this.queryPanel = queryPanel;
        return queryPanel;
    }

    private void readAllRequeres(){
        this.complexity = (COMPLEXITY) complexityBox.getSelectedItem();
        this.avalibDB = (BOOLEAN_PARAM) avalibBDBox.getSelectedItem();
        this.teamSize = (TEAM_SIZE) teamSizeBox.getSelectedItem();
        this.bspAdv = (BSP_ADV) bspAdvBox.getSelectedItem();
        this.guiAdv = (GUI_ADV) guiAdvBox.getSelectedItem();
        this.multyPlatform = (BOOLEAN_PARAM) multyPlatfBox.getSelectedItem();
        this.avalibAndroid = (BOOLEAN_PARAM) avalibAndroidBox.getSelectedItem();
        this.needLogging = (BOOLEAN_PARAM) avalibLogBox.getSelectedItem();
    }


    private void fillSuitableInstruments(UserQuery readyQuery) {

        queryPanel.add(new JLabel("Подходящие системы сборки проектов:"));
        String sutableBSP = "";
        for(String bsp : suitableBSP){
            sutableBSP = sutableBSP + ", " + bsp;
        }
        queryPanel.add(new JLabel(sutableBSP.substring(1)));
        queryPanel.add(new JLabel("Подходящие библиотеки грфического интерфейса:"));
        String sutableGUILib = "";
        for(String guiLib : suitableGUILib){
            sutableGUILib = sutableGUILib + ", " + guiLib;
        }
        queryPanel.add(new JLabel(sutableGUILib.substring(1)));
        queryPanel.add(new JLabel("Подходящие библиотеки логирования:"));
        String sutableLogLib = "";
        for(String logLib : suitableLogLib){
            sutableLogLib = sutableLogLib + ", " + logLib;
        }
        queryPanel.add(new JLabel(sutableLogLib.substring(1)));
        queryPanel.add(new JLabel("Подходящие СУБД:"));
        String sutableMSDB = "";
        for(String msdb : suitableMSDB){
            sutableMSDB = sutableMSDB + ", " + msdb;
        }
        queryPanel.add(new JLabel(sutableMSDB.substring(1)));
        queryPanel.add(new JLabel("Подходящие система контроля версий:"));
        String sutableVCS = "";
        for(String vcs : suitableVCS){
            sutableVCS = sutableVCS + ", " +vcs;
        }
        queryPanel.add(new JLabel(sutableVCS.substring(1)));
    }



    private class suitableInstrumentsBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            readAllRequeres();
            content.runSWRL();
            UserQuery readyQuery = content.getUserQuery();
            fillSuitableInstruments(readyQuery);
        }
    }

    public static String getClassName(){
        return "NeedUser";
    }

    public COMPLEXITY getComplexity() {
        return complexity;
    }

    public void setComplexity(COMPLEXITY complexity) {
        this.complexity = complexity;
    }

    public BOOLEAN_PARAM getAvalibDB() {
        return avalibDB;
    }

    public void setAvalibDB(BOOLEAN_PARAM avalibDB) {
        this.avalibDB = avalibDB;
    }

    public TEAM_SIZE getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(TEAM_SIZE teamSize) {
        this.teamSize = teamSize;
    }

    public BSP_ADV getBspAdv() {
        return bspAdv;
    }

    public void setBspAdv(BSP_ADV bspAdv) {
        this.bspAdv = bspAdv;
    }

    public GUI_ADV getGuiAdv() {
        return guiAdv;
    }

    public void setGuiAdv(GUI_ADV guiAdv) {
        this.guiAdv = guiAdv;
    }

    public BOOLEAN_PARAM getMultyPlatform() {
        return multyPlatform;
    }

    public void setMultyPlatform(BOOLEAN_PARAM multyPlatform) {
        this.multyPlatform = multyPlatform;
    }

    public BOOLEAN_PARAM getAvalibAndroid() {
        return avalibAndroid;
    }

    public void setAvalibAndroid(BOOLEAN_PARAM avalibAndroid) {
        this.avalibAndroid = avalibAndroid;
    }

    public BOOLEAN_PARAM getNeedLogging() {
        return needLogging;
    }

    public void setNeedLogging(BOOLEAN_PARAM needLogging) {
        this.needLogging = needLogging;
    }



    @Override
    public String toString() {
        return "QueryUser";
    }
}
