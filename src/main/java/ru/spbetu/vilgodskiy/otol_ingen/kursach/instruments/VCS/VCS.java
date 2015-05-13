package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.VCS;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BOOLEAN_PARAM;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.Instrument;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.TYPES_INSTRUMENTS;

import javax.swing.*;
import java.awt.*;

public class VCS extends Instrument {

    public VCS(String name) {
        this.instrumentType = TYPES_INSTRUMENTS.VCS;
        this.name = name;
    }

    public VCS(String name, TEAM_SIZE teamSize, BOOLEAN_PARAM multyPlatform) {
        this.instrumentType = TYPES_INSTRUMENTS.VCS;
        this.name = name;
        this.teamSize = teamSize;
        this.multyPlatform = multyPlatform;
    }

    private String name;

    private TEAM_SIZE teamSize;

    public TEAM_SIZE getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(TEAM_SIZE teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void writeInOntol() {

    }

    @Override
    public JPanel toDesignReviewPanel() {
        JPanel vcsPanel = new JPanel(new GridLayout(5, 2));

        vcsPanel.add(new JLabel("Величена команды разработчиков:"));
        JComboBox<TEAM_SIZE> teamSizeBox = new JComboBox(TEAM_SIZE.values());
        vcsPanel.add(teamSizeBox);
        teamSizeBox.setSelectedItem(teamSize);
        vcsPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        vcsPanel.add(multyPlatfBox);
        multyPlatfBox.setSelectedItem(multyPlatform);

        return vcsPanel;
    }

    public static JPanel toDesignCreatePanel(){
        JPanel vcsPanel = new JPanel(new GridLayout(5, 2));

        vcsPanel.add(new JLabel("Имя новой системы контроля версий:"));
        vcsPanel.add(new JTextField());
        vcsPanel.add(new JLabel("Величена команды разработчиков:"));
        JComboBox<TEAM_SIZE> teamSizeBox = new JComboBox(TEAM_SIZE.values());
        vcsPanel.add(teamSizeBox);
        vcsPanel.add(new JLabel("Поддержка мультиплатформенности:"));
        JComboBox multyPlatfBox = new JComboBox(BOOLEAN_PARAM.values());
        vcsPanel.add(multyPlatfBox);

        return vcsPanel;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static VCS getNewVCS(JPanel createPanel){
        JPanel createVCSPanel = (JPanel) createPanel.getComponent(2);
        JTextField nameTF = (JTextField) createVCSPanel.getComponent(1);
        JComboBox<TEAM_SIZE> teamSizeCB = (JComboBox<TEAM_SIZE>) createVCSPanel.getComponent(3);
        JComboBox<BOOLEAN_PARAM> multyPlatfCB = (JComboBox<BOOLEAN_PARAM>) createVCSPanel.getComponent(5);

        VCS vcs = new VCS(nameTF.getText(),
                (TEAM_SIZE) teamSizeCB.getSelectedItem(),
                (BOOLEAN_PARAM) multyPlatfCB.getSelectedItem());

        return vcs;
    }
}
