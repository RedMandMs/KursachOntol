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

    File defaultDir= new File("C://Users/Сергей/Desktop");      //директория по-умолчанию
    File processedFile;                                         //обрабатываемый файл
    JFrame mainFrame;

    //Главная панель
    JPanel mainPanel;
    //Вкладки
    JTabbedPane tabbedPane;
    //Панель выбора и просмотра инструментов
    JPanel reviewPanel;
    //Панельпросмотра конкретного инструмента (входит в reviewPanel)
    JPanel currentReviwInstrPanel = new JPanel();
    //Панель создания нового инструмента
    JPanel createPanel = new JPanel();
    //Панель нового создоваемого инструмента
    JPanel currentCreateInstrPanel = new JPanel();

    Content content = new Content();

    public void toDesign(){

        /** Главное окно */
        mainFrame = new JFrame("Подбор Java-инструментов");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Главная панель */
        mainPanel = new JPanel(new BorderLayout());
        mainFrame.add(mainPanel);


        /**
         *  Панель с вкладками
         */
        tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        toCreateTabs();

        /**
         *  Панель для кнопок управления процессом
         */
        JPanel buttonPanel = new JPanel(new FlowLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        //Создать новый файл онтологии
        JButton createButton = new JButton("Создать онтологию");
        createButton.addActionListener(new CreateOWLBtnListener());
        buttonPanel.add(createButton);

        //Загрузить онтологию из файла
        JButton loadButton = new JButton("Загрузить онтологию из файла");
        loadButton.addActionListener(new OpenOWLBtnListener());
        buttonPanel.add(loadButton);

        //Сохранение онтологии
        JButton saveButton = new JButton("Сохранить...");
        saveButton.addActionListener(new SaveOWLBtnListener());
        buttonPanel.add(saveButton);

        //Сохранение онтологии в новый файл
        JButton saveAsButton = new JButton("Сохранить как...");
        saveAsButton.addActionListener(new SaveAsOWLBtnListener());
        buttonPanel.add(saveAsButton);

        /**
         * Размер и видимость окна
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
     * Спроектировать Панель выбора типа инструмента и конкретный инструмент
     * @return - панель выбора инструмента
     */
    private JPanel toDesignChooseReviewPanel(){
        JPanel choiseInstrumentPanel = new JPanel();
        choiseInstrumentPanel.add(new JLabel("Выберите инструмент:"));
        JComboBox<TYPES_INSTRUMENTS> typeInstrumentBox = new JComboBox<TYPES_INSTRUMENTS>(TYPES_INSTRUMENTS.values());
        JComboBox intrumentBox = new JComboBox();
        typeInstrumentBox.addActionListener(new ChangeTypeInstrumentListner(typeInstrumentBox, intrumentBox,choiseInstrumentPanel));
        intrumentBox.addActionListener(new ChangeChoiseInstrListner(intrumentBox));
        choiseInstrumentPanel.add(typeInstrumentBox);
        choiseInstrumentPanel.add(intrumentBox);

        return choiseInstrumentPanel;
    }

    private JPanel toDesignChooseCeatePanel(){
        JPanel choiseInstrumentPanel = new JPanel();
        choiseInstrumentPanel.add(new JLabel("Выберите инструмент:"));
        JComboBox<TYPES_INSTRUMENTS> typeInstrumentBox = new JComboBox<TYPES_INSTRUMENTS>(TYPES_INSTRUMENTS.values());
        typeInstrumentBox.addActionListener(new ChoiseCreateInstrument(typeInstrumentBox));
        choiseInstrumentPanel.add(typeInstrumentBox);

        return choiseInstrumentPanel;
    }

    /**
     * Спроектировать вкладку просмотра имеющихся инструментов
     */
    private void toDesignReviewTab(){
        reviewPanel = new JPanel();
        reviewPanel.add(toDesignChooseReviewPanel(), BorderLayout.NORTH);
        tabbedPane.add("Имеющиеся инструменты", reviewPanel);
    }

    /**
     * Сменить просматриваемый инструмент
     * @param newPanel - панель с новым инструментом
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
     * Спроектировать вкладку создания новых инструментов
     */
    private void toDesignCreateTab(){
        JPanel choisePanel = toDesignChooseCeatePanel();
        createPanel.add(choisePanel, BorderLayout.NORTH);
        JButton createInstrumBtn = new JButton("Создать инструмент");
        createInstrumBtn.addActionListener(new CreateButtonListner
                ((JComboBox<TYPES_INSTRUMENTS>)(choisePanel.getComponent(1))));
        createPanel.add(createInstrumBtn, BorderLayout.SOUTH);
        tabbedPane.add("Создание инструмента", createPanel);

    }


    /**
     * Спроектировать вкладку задания запроса
     */
    private void toDesignQueryTab(){
        UserQuery query = new UserQuery(this.content);
        content.setQuery(query);
        JPanel queryPanel = new JPanel(new BorderLayout());
        queryPanel.add(query.toDesignQueryPanal(), BorderLayout.NORTH);
        tabbedPane.add("Создание запроса", queryPanel);
    }

    private class ChangeTypeInstrumentListner implements ActionListener{

        //Комбо-бокс с типами инструментов
        private JComboBox<TYPES_INSTRUMENTS> typeInstrCB;
        //Комбо-бокс с конкретными инструментами
        private JComboBox instrumentsCB;
        //Панель выбора инструментов
        private JPanel choisePanel;


        public ChangeTypeInstrumentListner(JComboBox<TYPES_INSTRUMENTS> typeInstrCB, JComboBox instrumentsCB, JPanel choisePanel) {
            this.typeInstrCB = typeInstrCB;
            this.instrumentsCB = instrumentsCB;
            this.choisePanel = choisePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TYPES_INSTRUMENTS selectedType = (TYPES_INSTRUMENTS) typeInstrCB.getSelectedItem();
            choisePanel.remove(instrumentsCB);
            switch (selectedType){
                case BSP:
                    instrumentsCB = new JComboBox<>(content.getBspList().toArray());
                    break;
                case GUI_LIB:
                    instrumentsCB = new JComboBox<>(content.getGuiLibList().toArray());
                    break;
                case LOG_LIB:
                    instrumentsCB = new JComboBox<>(content.getLogLibList().toArray());
                    break;
                case MSDB:
                    instrumentsCB = new JComboBox<>(content.getMsdbList().toArray());
                    break;
                case VCS:
                    instrumentsCB = new JComboBox<>(content.getVcsList().toArray());
                    break;
            }
            choisePanel.add(instrumentsCB);
            instrumentsCB.addActionListener(new ChangeChoiseInstrListner(instrumentsCB));
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
                    content.getBspList().add(bsp);
                    break;
                case GUI_LIB:
                    GUILib gui = GUILib.getNewGUILib(createPanel);
                    content.getGuiLibList().add(gui);
                    break;
                case LOG_LIB:
                    LogLib logLib = LogLib.getNewLogLib(createPanel);
                    content.getLogLibList().add(logLib);
                    break;
                case MSDB:
                    MSDB msdb = MSDB.getNewMSDB(createPanel);
                    content.getMsdbList().add(msdb);
                    break;
                case VCS:
                    VCS vcs = VCS.getNewVCS(createPanel);
                    content.getVcsList().add(vcs);
                    break;
            }
        }
    }

    private class OpenOWLBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //Работа с выбором и обработкой файла
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(defaultDir);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            MyFileFilter myFileFilter = new MyFileFilter(".owl");
            jFileChooser.setFileFilter(myFileFilter);
            jFileChooser.showOpenDialog(mainFrame);

            //назначение обрабатываемого файла
            processedFile = jFileChooser.getSelectedFile();

            content.openFile(processedFile);
            //установление выбранной дирректории директоией по-умолчанию
            defaultDir = new File(processedFile.getAbsolutePath());
        }
    }

    private class SaveAsOWLBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //Создание панели выбора директории для сохранения файла
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

            content.saveFile(dir);

            //установление выбранной дирректории директоией по-умолчанию
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
}
