package ru.spbetu.vilgodskiy.otol_ingen.kursach.user_query;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.Content;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BSP.BSP_ADV;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib.GUI_ADV;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib.COMPLEXITY;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.VCS.TEAM_SIZE;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class UserQuery {

    Content content;

    ArrayList<Component> components = new ArrayList<Component>();

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

    public List<String> suitableBSP = new ArrayList<String>();
    public List<String> suitableGUILib = new ArrayList<String>();
    public List<String> suitableLogLib = new ArrayList<String>();
    public List<String> suitableMSDB = new ArrayList<String>();
    public List<String> suitableVCS = new ArrayList<String>();

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
        suitableBtn.addActionListener(new SuitableInstrumentsBtnListner());
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
        readAllRequeres();
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
        queryPanel.updateUI();
        for(Component component : components){
            queryPanel.remove(component);
        }
        components = new ArrayList<Component>();

        if(suitableBSP.size() != 0) {
            JLabel jLabel1 = new JLabel("Подходящие системы сборки проектов:");
            components.add(jLabel1);
            queryPanel.add(jLabel1);
            String sutableBSP = "";
            for (String bsp : suitableBSP) {
                sutableBSP = sutableBSP + ", " + bsp;
            }
            JLabel jLabel2 = new JLabel(sutableBSP.substring(1));
            components.add(jLabel2);
            queryPanel.add(jLabel2);
        }

        if(suitableGUILib.size() != 0) {
            JLabel jLabel3 = new JLabel("Подходящие библиотеки грфического интерфейса:");
            components.add(jLabel3);
            queryPanel.add(jLabel3);
            String sutableGUILib = "";
            for (String guiLib : suitableGUILib) {
                sutableGUILib = sutableGUILib + ", " + guiLib;
            }
            JLabel jLabel4 = new JLabel(sutableGUILib.substring(1));
            components.add(jLabel4);
            queryPanel.add(jLabel4);
        }

        if(suitableLogLib.size() != 0) {
            JLabel jLabel5 = new JLabel("Подходящие библиотеки логирования:");
            components.add(jLabel5);
            queryPanel.add(jLabel5);
            String sutableLogLib = "";
            for (String logLib : suitableLogLib) {
                sutableLogLib = sutableLogLib + ", " + logLib;
            }
            JLabel jLabel6 = new JLabel(sutableLogLib.substring(1));
            components.add(jLabel6);
            queryPanel.add(jLabel6);
        }

        if(suitableMSDB.size() !=0) {
            JLabel jLabel7 = new JLabel("Подходящие СУБД:");
            components.add(jLabel7);
            queryPanel.add(jLabel7);
            String sutableMSDB = "";
            for (String msdb : suitableMSDB) {
                sutableMSDB = sutableMSDB + ", " + msdb;
            }
            JLabel jLabel8 = new JLabel(sutableMSDB.substring(1));
            components.add(jLabel8);
            queryPanel.add(jLabel8);
        }

        if(suitableVCS.size() != 0) {
            JLabel jLabel9 = new JLabel("Подходящие система контроля версий:");
            components.add(jLabel9);
            queryPanel.add(jLabel9);
            String sutableVCS = "";
            for (String vcs : suitableVCS) {
                sutableVCS = sutableVCS + ", " + vcs;
            }
            JLabel jLabel10 = new JLabel(sutableVCS.substring(1));
            components.add(jLabel10);
            queryPanel.add(jLabel10);
        }
        queryPanel.updateUI();
    }



    private class SuitableInstrumentsBtnListner implements ActionListener{

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
