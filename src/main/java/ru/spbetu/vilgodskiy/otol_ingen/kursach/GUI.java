package ru.spbetu.vilgodskiy.otol_ingen.kursach;

import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.*;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BSP.BSP;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib.GUILib;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib.LogLib;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.MSDB.MSDB;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.VCS.VCS;
import ru.spbetu.vilgodskiy.otol_ingen.kursach.user_query.UserQuery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class GUI {

    File defaultDir= new File("C://Users/������/Desktop");      //���������� ��-���������
    File processedFile;                                         //�������������� ����
    JFrame mainFrame;

    //������� ������
    JPanel mainPanel;
    //�������
    JTabbedPane tabbedPane;
    //������ ������ � ��������� ������������
    JPanel reviewPanel;
    //��������������� ����������� ����������� (������ � reviewPanel)
    JPanel currentReviwInstrPanel = new JPanel();
    //������ �������� ������ �����������
    JPanel createPanel = new JPanel();
    //������ ������ ������������ �����������
    JPanel currentCreateInstrPanel = new JPanel();

    JButton editBtn = new JButton("�������������");
    JButton deleteBtn = new JButton("�������");
    JComboBox<TYPES_INSTRUMENTS> typeInstrumentBoxReview;
    JComboBox intrumentBoxReview;

    Content content = new Content();

    public void toDesign(){

        /** ������� ���� */
        mainFrame = new JFrame("������ Java-������������");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** ������� ������ */
        mainPanel = new JPanel(new BorderLayout());
        mainFrame.add(mainPanel);


        /**
         *  ������ � ���������
         */
        tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        toCreateTabs();

        /**
         *  ������ ��� ������ ���������� ���������
         */
        JPanel buttonPanel = new JPanel(new FlowLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        //������� ����� ���� ���������
        JButton createButton = new JButton("������� ���������");
        createButton.addActionListener(new CreateOWLBtnListener());
        buttonPanel.add(createButton);

        //��������� ��������� �� �����
        JButton loadButton = new JButton("��������� ��������� �� �����");
        loadButton.addActionListener(new OpenOWLBtnListener());
        buttonPanel.add(loadButton);

        //���������� ���������
        JButton saveButton = new JButton("���������...");
        saveButton.addActionListener(new SaveOWLBtnListener());
        buttonPanel.add(saveButton);

        //���������� ��������� � ����� ����
        JButton saveAsButton = new JButton("��������� ���...");
        saveAsButton.addActionListener(new SaveAsOWLBtnListener());
        buttonPanel.add(saveAsButton);


        /**
         * ������ � ��������� ����
         */
        mainFrame.setSize(1000, 700);
        mainFrame.setVisible(true);
    }

    private void toCreateTabs(){
        toDesignReviewTab();
        toDesignCreateTab();
        toDesignQueryTab();
    }

    /**
     * �������������� ������ ������ ���� ����������� � ���������� ����������
     * @return - ������ ������ �����������
     */
    private JPanel toDesignChooseReviewPanel(){
        JPanel choiseInstrumentPanel = new JPanel();
        choiseInstrumentPanel.add(new JLabel("�������� ����������:"));
        typeInstrumentBoxReview = new JComboBox<TYPES_INSTRUMENTS>(TYPES_INSTRUMENTS.values());
        intrumentBoxReview = new JComboBox();
        typeInstrumentBoxReview.addActionListener(new ChangeTypeInstrumentListner(typeInstrumentBoxReview, intrumentBoxReview, choiseInstrumentPanel));
        intrumentBoxReview.addActionListener(new ChangeChoiseInstrListner(intrumentBoxReview));
        choiseInstrumentPanel.add(typeInstrumentBoxReview);
        choiseInstrumentPanel.add(intrumentBoxReview);

        return choiseInstrumentPanel;
    }

    private JPanel toDesignChooseCeatePanel(){
        JPanel choiseInstrumentPanel = new JPanel();
        choiseInstrumentPanel.add(new JLabel("�������� ����������:"));
        JComboBox<TYPES_INSTRUMENTS> typeInstrumentBox = new JComboBox<TYPES_INSTRUMENTS>(TYPES_INSTRUMENTS.values());
        typeInstrumentBox.addActionListener(new ChoiseCreateInstrument(typeInstrumentBox));
        choiseInstrumentPanel.add(typeInstrumentBox);

        return choiseInstrumentPanel;
    }

    /**
     * �������������� ������� ��������� ��������� ������������
     */
    private void toDesignReviewTab(){
        reviewPanel = new JPanel();
        reviewPanel.add(toDesignChooseReviewPanel(), BorderLayout.NORTH);
        tabbedPane.add("��������� �����������", reviewPanel);
        /**
         * ������ ������ ��������������
         */
        JPanel editPanel = new JPanel();
        editPanel.add(deleteBtn);
        deleteBtn.addActionListener(new DeleteInstrListener());
        reviewPanel.add(editPanel, BorderLayout.SOUTH);
    }

    /**
     * ������� ��������������� ����������
     * @param newPanel - ������ � ����� ������������
     */
    private void changeInstrReview(JPanel newPanel){
        reviewPanel.remove(currentReviwInstrPanel);
        currentReviwInstrPanel = newPanel;
        reviewPanel.add(currentReviwInstrPanel);
        reviewPanel.updateUI();
    }

    private void changeTypeInstrCreate(JPanel newPanel){
        createPanel.remove(currentCreateInstrPanel);
        currentReviwInstrPanel = newPanel;
        createPanel.add(currentReviwInstrPanel);
        createPanel.updateUI();
    }

    /**
     * �������������� ������� �������� ����� ������������
     */
    private void toDesignCreateTab(){
        JPanel choisePanel = toDesignChooseCeatePanel();
        createPanel.add(choisePanel, BorderLayout.NORTH);
        JButton createInstrumBtn = new JButton("������� ����������");
        createInstrumBtn.addActionListener(new CreateButtonListner
                ((JComboBox<TYPES_INSTRUMENTS>)(choisePanel.getComponent(1))));
        createPanel.add(createInstrumBtn, BorderLayout.SOUTH);
        tabbedPane.add("�������� �����������", createPanel);

    }


    /**
     * �������������� ������� ������� �������
     */
    private void toDesignQueryTab(){
        UserQuery query = new UserQuery(this.content);
        content.setUserQuery(query);
        JPanel queryPanel = new JPanel(new BorderLayout());
        queryPanel.add(query.toDesignQueryPanal(), BorderLayout.NORTH);
        tabbedPane.add("�������� �������", queryPanel);
    }

    private class ChangeTypeInstrumentListner implements ActionListener{

        //�����-���� � ������ ������������
        private JComboBox<TYPES_INSTRUMENTS> typeInstrCB;
        //������ ������ ������������
        private JPanel choisePanel;


        public ChangeTypeInstrumentListner(JComboBox<TYPES_INSTRUMENTS> typeInstrCB, JComboBox instrumentsCB, JPanel choisePanel) {
            this.typeInstrCB = typeInstrCB;
            this.choisePanel = choisePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TYPES_INSTRUMENTS selectedType = (TYPES_INSTRUMENTS) typeInstrCB.getSelectedItem();
            choisePanel.remove(intrumentBoxReview);
            switch (selectedType){
                case BSP:
                    intrumentBoxReview = new JComboBox<>(content.getBspList().toArray());
                    break;
                case GUI_LIB:
                    intrumentBoxReview = new JComboBox<>(content.getGuiLibList().toArray());
                    break;
                case LOG_LIB:
                    intrumentBoxReview = new JComboBox<>(content.getLogLibList().toArray());
                    break;
                case MSDB:
                    intrumentBoxReview = new JComboBox<>(content.getMsdbList().toArray());
                    break;
                case VCS:
                    intrumentBoxReview = new JComboBox<>(content.getVcsList().toArray());
                    break;
            }
            choisePanel.add(intrumentBoxReview);
            intrumentBoxReview.addActionListener(new ChangeChoiseInstrListner(intrumentBoxReview));
            choisePanel.updateUI();
        }
    }

    private class ChangeChoiseInstrListner implements ActionListener{

        private JComboBox<Instrument> instrumentsCB;

        public ChangeChoiseInstrListner(JComboBox<Instrument> instrumentsCB) {
            this.instrumentsCB = instrumentsCB;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Instrument newReviewingInstrument = (Instrument) instrumentsCB.getSelectedItem();
            changeInstrReview(newReviewingInstrument.toDesignReviewPanel());
        }
    }

    private class ChoiseCreateInstrument implements ActionListener{

        private JComboBox<TYPES_INSTRUMENTS> typeInstrumentsCB;

        public ChoiseCreateInstrument(JComboBox<TYPES_INSTRUMENTS> typeInstrumentsCB) {
            this.typeInstrumentsCB = typeInstrumentsCB;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TYPES_INSTRUMENTS selectedType = (TYPES_INSTRUMENTS)typeInstrumentsCB.getSelectedItem();
            createPanel.remove(currentCreateInstrPanel);
            switch (selectedType){
                case BSP:
                    currentCreateInstrPanel = BSP.toDesignCreatePanel();
                    break;
                case GUI_LIB:
                    currentCreateInstrPanel = GUILib.toDesignCreatePanel();
                    break;
                case LOG_LIB:
                    currentCreateInstrPanel = LogLib.toDesignCreatePanel();
                    break;
                case MSDB:
                    currentCreateInstrPanel = MSDB.toDesignCreatePanel();
                    break;
                case VCS:
                    currentCreateInstrPanel = VCS.toDesignCreatePanel();
                    break;
            }
            createPanel.add(currentCreateInstrPanel);
            createPanel.updateUI();
        }
    }

    private class CreateButtonListner implements ActionListener{

        private JComboBox<TYPES_INSTRUMENTS> typeInstrumentsCB;

        public CreateButtonListner(JComboBox<TYPES_INSTRUMENTS> typeInstrumentsCB) {
            this.typeInstrumentsCB = typeInstrumentsCB;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TYPES_INSTRUMENTS selectedType = (TYPES_INSTRUMENTS)typeInstrumentsCB.getSelectedItem();
            switch (selectedType){
                case BSP:
                    BSP bsp = BSP.getNewBSP(createPanel);
                    for(BSP bspIn : content.bspList){
                        if(bspIn.getName().equals(bsp.getName())){
                            return;
                        }
                    }
                    content.getBspList().add(bsp);
                    break;
                case GUI_LIB:
                    GUILib gui = GUILib.getNewGUILib(createPanel);
                    for(GUILib guiLib : content.guiLibList){
                        if(guiLib.getName().equals(gui.getName())){
                            return;
                        }
                    }
                    content.getGuiLibList().add(gui);
                    break;
                case LOG_LIB:
                    LogLib logLib = LogLib.getNewLogLib(createPanel);
                    for(LogLib logLibIn : content.logLibList){
                        if(logLibIn.getName().equals(logLib.getName())){
                            return;
                        }
                    }
                    content.getLogLibList().add(logLib);
                    break;
                case MSDB:
                    MSDB msdb = MSDB.getNewMSDB(createPanel);
                    for(MSDB msdbIn : content.msdbList){
                        if(msdbIn.getName().equals(msdb.getName())){
                            return;
                        }
                    }
                    content.getMsdbList().add(msdb);
                    break;
                case VCS:
                    VCS vcs = VCS.getNewVCS(createPanel);
                    for(VCS vcsIn : content.vcsList){
                        if(vcsIn.getName().equals(vcs.getName())){
                            return;
                        }
                    }
                    content.getVcsList().add(vcs);
                    break;
            }
        }
    }

    private class OpenOWLBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //������ � ������� � ���������� �����
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(defaultDir);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            MyFileFilter myFileFilter = new MyFileFilter(".owl");
            jFileChooser.setFileFilter(myFileFilter);
            jFileChooser.showOpenDialog(mainFrame);

            //���������� ��������������� �����
            processedFile = jFileChooser.getSelectedFile();

            //TODO:content.openFile(processedFile);
            //������������ ��������� ����������� ���������� ��-���������
            defaultDir = new File(processedFile.getAbsolutePath());
        }
    }

    private class SaveAsOWLBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //�������� ������ ������ ���������� ��� ���������� �����
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(defaultDir);
            jFileChooser.setDialogTitle("Save");
            jFileChooser.setApproveButtonText("Save");
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setFileSelectionMode(jFileChooser.DIRECTORIES_ONLY);
            MyFileFilter myFileFilter = new MyFileFilter(".owl");
            jFileChooser.setFileFilter(myFileFilter);
            jFileChooser.showOpenDialog(mainFrame);
            File dir = jFileChooser.getSelectedFile();

            //TODO:content.saveFile(dir);

            //������������ ��������� ����������� ���������� ��-���������
            defaultDir = dir;
        }
    }

    private class SaveOWLBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            content.runSWRL();
        }
    }

    private class CreateOWLBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }

    private class DeleteInstrListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            TYPES_INSTRUMENTS selectedType = (TYPES_INSTRUMENTS) typeInstrumentBoxReview.getSelectedItem();
            switch (selectedType){
                case BSP:
                    BSP bsp = (BSP) intrumentBoxReview.getSelectedItem();
                    for(BSP bspIn : content.bspList){
                        if(bsp == bspIn){
                            content.bspList.remove(bspIn);
                        }
                    }
                    break;
                case GUI_LIB:
                    GUILib guiLib = (GUILib) intrumentBoxReview.getSelectedItem();
                    for(GUILib guiLib1In : content.guiLibList){
                        if(guiLib == guiLib1In){
                            content.guiLibList.remove(guiLib1In);
                        }
                    }
                    break;
                case LOG_LIB:
                    LogLib logLib = (LogLib) intrumentBoxReview.getSelectedItem();
                    for(LogLib guiLibIn : content.logLibList){
                        if(logLib == guiLibIn){
                            content.guiLibList.remove(guiLibIn);
                        }
                    }
                    break;
                case MSDB:
                    MSDB msdb = (MSDB) intrumentBoxReview.getSelectedItem();
                    for(MSDB msdbIn : content.msdbList){
                        if(msdb == msdbIn){
                            content.guiLibList.remove(msdbIn);
                        }
                    }
                    break;
                case VCS:
                    VCS vcs = (VCS) intrumentBoxReview.getSelectedItem();
                    for(VCS vcsIn : content.vcsList){
                        if(vcs == vcsIn){
                            content.guiLibList.remove(vcsIn);
                        }
                    }
                    break;
            }
            createPanel.updateUI();
        }
    }
}
