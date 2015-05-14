package ru.spbetu.vilgodskiy.otol_ingen.kursach;

import com.hp.hpl.jena.query.QueryException;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.query.QueryResults;
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
import ru.spbetu.vilgodskiy.otol_ingen.kursach.user_query.UserQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public class Content {

    // a variable of ontology
    JenaOWLModel owlModel = null;
    // a namespace of ontology
    String owlURI = "http://www.owl-ontologies.com/JavaInstruments.owl#";
    // a name of new file of ontology
    String owlNEWFileName = "Lab1_new.owl";
    // a variable of new file of ontology
    File owlNEWFile = new File(owlNEWFileName);

    //dataPropertyNames
    String available_android_property_name = "available_android";
    String avalib_db_property_name = "avalib_db";
    String bsp_advanteges_property_name = "bsp_advanteges";
    String complexity_project_property_name = "complexity_project";
    String gui_advant_property_name = "gui_advant";
    String multy_platform_property_name = "multy_platform";
    String need_logging_property_name = "need_logging";
    String team_size_property_name = "team_size";

    //dataProperty
    OWLDatatypeProperty available_android_property = null;
    OWLDatatypeProperty avalib_db_property = null;
    OWLDatatypeProperty bsp_advanteges_property = null;
    OWLDatatypeProperty complexity_project_property = null;
    OWLDatatypeProperty gui_advant_property = null;
    OWLDatatypeProperty multy_platform_property = null;
    OWLDatatypeProperty need_logging_property = null;
    OWLDatatypeProperty team_size_property = null;


    //Class names
    String Instrument_class_name = "Instrument";
    String BuildSystemProject_class_name = "BuildSystemProject";
    String DBMS_class_name = "DBMS";
    String GUILib_class_name = "GUILib";
    String LogLib_class_name = "LogLib";
    String VersionControlSystem_class_name = "VersionControlSystem";
    String NeedUser_class_name = "NeedUser";

    //Class OWL
    OWLNamedClass Instrument_class = null;
    OWLNamedClass BuildSystemProject_class = null;
    OWLNamedClass DBMS_class = null;
    OWLNamedClass GUILib_class = null;
    OWLNamedClass LogLib_class = null;
    OWLNamedClass VersionControlSystem_class = null;
    OWLNamedClass NeedUser_class = null;

    //ObjectPropertiesNames
    String suitable_LogLib_relation_name = "suitable_LogLib";
    String sutable_BSP_relation_name = "sutable_BSP";
    String sutable_DBMS_relation_name = "sutable_DBMS";
    String sutable_GUILib_relation_name = "sutable_GUILib";
    String sutable_VCS_relation_name = "sutable_VCS";

    //ObjectProperties
    OWLObjectProperty suitable_LogLib_relation = null;
    OWLObjectProperty sutable_BSP_relation = null;
    OWLObjectProperty sutable_DBMS_relation = null;
    OWLObjectProperty sutable_GUILib_relation = null;
    OWLObjectProperty sutable_VCS_relation = null;


    /**
     * Компонент GUI
     */
    GUI gui;
    List<BSP> bspList = new ArrayList<BSP>();
    List<GUILib> guiLibList = new ArrayList<GUILib>();
    List<LogLib> logLibList = new ArrayList<LogLib>();
    List<MSDB> msdbList = new ArrayList<MSDB>();
    List<VCS> vcsList = new ArrayList<VCS>();
    UserQuery userQuery;

    /**
     * Заполнить компоненты gui всеми имеющимися значениями инструментов
     */
    public void fillComponents(){

    }

    /**
     * Сохранение онтологии
     */
    public void saveOntology(){

    }

    /**
     * Подгрузка онтологии из файла
     */
    public void loadOntology(){

    }

    /**
     * Выполнить SWRL-правила
     */
    public void executeSWRL(){
        runSWRL();
    }


    public List<VCS> getVcsList() {
        return vcsList;
    }

    public List<MSDB> getMsdbList() {
        return msdbList;
    }

    public List<LogLib> getLogLibList() {
        return logLibList;
    }

    public List<GUILib> getGuiLibList() {
        return guiLibList;
    }

    public List<BSP> getBspList() {
        return bspList;
    }

    public UserQuery getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(UserQuery userQuery) {
        this.userQuery = userQuery;
    }

    private void runSWRL() {
        readOntology();
        runRules();
        updateContent();
    }

    private void updateContent() {
        //TODO:
    }

    private void runRules() {
        //TODO:
    }

    private void readOntology() {

    }

    private void createOWL(JenaOWLModel owlModel) {
        try {
            owlModel = ProtegeOWL.createJenaOWLModel();
        } catch (OntologyLoadException e) {
            System.out.println("ERROR in creating ontology!");
            return;
        }
    }

    private void createClassesOWL(JenaOWLModel owlModel){
        owlModel.getNamespaceManager().setDefaultNamespace(owlURI);


        Instrument_class = owlModel.createOWLNamedClass(owlURI + Instrument_class_name);

        BuildSystemProject_class = owlModel.createOWLNamedClass(owlURI + BuildSystemProject_class_name);
        BuildSystemProject_class.addSuperclass(Instrument_class);

        DBMS_class = owlModel.createOWLNamedClass(owlURI + DBMS_class_name);
        DBMS_class.addSuperclass(Instrument_class);

        GUILib_class = owlModel.createOWLNamedClass(owlURI + GUILib_class_name);
        GUILib_class.addSuperclass(Instrument_class);

        LogLib_class = owlModel.createOWLNamedClass(owlURI + LogLib_class_name);
        LogLib_class.addSuperclass(Instrument_class);

        VersionControlSystem_class = owlModel.createOWLNamedClass(owlURI + VersionControlSystem_class_name);
        VersionControlSystem_class.addSuperclass(Instrument_class);

        NeedUser_class = owlModel.createOWLNamedClass(owlURI + NeedUser_class_name);
    }

    private void createDataProperties(JenaOWLModel owlModel){

        available_android_property = owlModel.createOWLDatatypeProperty(owlURI + available_android_property_name);
        ArrayList<OWLNamedClass> listClassAvAnd = new ArrayList<OWLNamedClass>(3);
        listClassAvAnd.add(DBMS_class);
        listClassAvAnd.add(BuildSystemProject_class);
        listClassAvAnd.add(NeedUser_class);
        OWLUnionClass domain_AvAnd_class = owlModel.createOWLUnionClass(listClassAvAnd);
        available_android_property.setDomain(domain_AvAnd_class);
        available_android_property.setRange(owlModel.getXSDboolean());

        avalib_db_property = owlModel.createOWLDatatypeProperty(owlURI +  avalib_db_property_name);
        ArrayList<OWLNamedClass> listClassAvDB = new ArrayList<OWLNamedClass>(2);
        listClassAvDB.add(DBMS_class);
        listClassAvDB.add(NeedUser_class);
        OWLUnionClass domain_AvDB_class = owlModel.createOWLUnionClass(listClassAvDB);
        avalib_db_property.setDomain(domain_AvDB_class);
        avalib_db_property.setRange(owlModel.getXSDboolean());

        bsp_advanteges_property = owlModel.createOWLDatatypeProperty(owlURI + bsp_advanteges_property_name);
        ArrayList<OWLNamedClass> listClassAvBSP = new ArrayList<OWLNamedClass>(2);
        listClassAvBSP.add(BuildSystemProject_class);
        listClassAvBSP.add(NeedUser_class);
        OWLUnionClass domain_AvBSP_class = owlModel.createOWLUnionClass(listClassAvBSP);
        bsp_advanteges_property.setDomain(domain_AvBSP_class);
        bsp_advanteges_property.setRange(owlModel.getXSDstring());

        complexity_project_property = owlModel.createOWLDatatypeProperty(owlURI +   complexity_project_property_name);
        ArrayList<OWLNamedClass> listClassAvCP = new ArrayList<OWLNamedClass>(2);
        listClassAvCP.add(LogLib_class);
        listClassAvCP.add(NeedUser_class);
        OWLUnionClass domain_AvCP_class = owlModel.createOWLUnionClass(listClassAvCP);
        complexity_project_property.setDomain(domain_AvCP_class);
        complexity_project_property.setRange(owlModel.getXSDstring());

        gui_advant_property = owlModel.createOWLDatatypeProperty(owlURI +  gui_advant_property_name);
        ArrayList<OWLNamedClass> listClassGUI = new ArrayList<OWLNamedClass>(2);
        listClassGUI.add(GUILib_class);
        listClassGUI.add(NeedUser_class);
        OWLUnionClass domain_GUI_class = owlModel.createOWLUnionClass(listClassGUI);
        gui_advant_property.setDomain(domain_GUI_class);
        gui_advant_property.setRange(owlModel.getXSDstring());

        multy_platform_property = owlModel.createOWLDatatypeProperty(owlURI +  multy_platform_property_name);
        ArrayList<OWLNamedClass> listClassMult = new ArrayList<OWLNamedClass>(2);
        listClassMult.add(Instrument_class);
        listClassMult.add(NeedUser_class);
        OWLUnionClass domain_Mult_class = owlModel.createOWLUnionClass(listClassGUI);
        multy_platform_property.setDomain(domain_Mult_class);
        multy_platform_property.setRange(owlModel.getXSDboolean());

        need_logging_property = owlModel.createOWLDatatypeProperty(owlURI +  need_logging_property_name);
        ArrayList<OWLNamedClass> listClassLog = new ArrayList<OWLNamedClass>(2);
        listClassLog.add(LogLib_class);
        listClassLog.add(NeedUser_class);
        OWLUnionClass domain_Log_class = owlModel.createOWLUnionClass(listClassLog);
        need_logging_property.setDomain(domain_Log_class);
        need_logging_property.setRange(owlModel.getXSDboolean());

        team_size_property = owlModel.createOWLDatatypeProperty(owlURI +  team_size_property_name);
        ArrayList<OWLNamedClass> listClassTeam = new ArrayList<OWLNamedClass>(2);
        listClassTeam.add(VersionControlSystem_class);
        listClassTeam.add(NeedUser_class);
        OWLUnionClass domain_Team_class = owlModel.createOWLUnionClass(listClassTeam);
        team_size_property.setDomain(domain_Team_class);
        team_size_property.setRange(owlModel.getXSDstring());
    }

    /**
     * Создание объектных полей
     */
    private void createObjectProperty(){

        //Sutable LogLib
        suitable_LogLib_relation = owlModel.createOWLObjectProperty(owlURI + suitable_LogLib_relation_name);
        suitable_LogLib_relation.setDomain(NeedUser_class);
        suitable_LogLib_relation.setRange(LogLib_class);

        //Sutable BSP
        sutable_BSP_relation = owlModel.createOWLObjectProperty(owlURI + sutable_BSP_relation_name);
        sutable_BSP_relation.setDomain(NeedUser_class);
        sutable_BSP_relation.setRange(BuildSystemProject_class);

        //Sutable DBMS
        sutable_DBMS_relation = owlModel.createOWLObjectProperty(owlURI + sutable_DBMS_relation_name);
        sutable_DBMS_relation.setDomain(NeedUser_class);
        sutable_DBMS_relation.setRange(DBMS_class);

        //Sutable GUILib
        sutable_GUILib_relation = owlModel.createOWLObjectProperty(owlURI + sutable_GUILib_relation_name);
        sutable_GUILib_relation.setDomain(NeedUser_class);
        sutable_GUILib_relation.setRange(GUILib_class);

        //Sutable VCS
        sutable_VCS_relation = owlModel.createOWLObjectProperty(owlURI + sutable_VCS_relation_name);
        sutable_VCS_relation.setDomain(NeedUser_class);
        sutable_VCS_relation.setRange(VersionControlSystem_class);

    }

    /**
     * Создание экземпляров инструментов
     */
    private void createIndividuals(){

        //BSP
        for (BSP bsp : bspList) {
            OWLIndividual individual = BuildSystemProject_class.createOWLIndividual(owlURI + BuildSystemProject_class_name);
            individual.setPropertyValue(bsp_advanteges_property, bsp.getBspAdv().toString());
            individual.setPropertyValue(available_android_property, bsp.getAvalibAndroid().getBool());
            individual.setPropertyValue(multy_platform_property, bsp.isMultyPlatform().getBool());
        }

        //GUILib
        for (GUILib guiLib : guiLibList) {
            OWLIndividual individual = GUILib_class.createOWLIndividual(owlURI + guiLib.getName());
            individual.setPropertyValue(gui_advant_property, guiLib.getGuiAdv().toString());
            individual.setPropertyValue(multy_platform_property, guiLib.isMultyPlatform().getBool());
        }

        //LogLib
        for(LogLib logLib : logLibList){
            OWLIndividual individual = LogLib_class.createOWLIndividual(owlURI + logLib.getName());
            individual.setPropertyValue(complexity_project_property, logLib.getComplexity().toString());
            individual.setPropertyValue(need_logging_property, logLib.getNeedLogging().getBool());
            individual.setPropertyValue(multy_platform_property, logLib.isMultyPlatform().getBool());
        }

        //MSDB
        for(MSDB msdb : msdbList){
            OWLIndividual individual = LogLib_class.createOWLIndividual(owlURI + msdb.getName());
            individual.setPropertyValue(available_android_property, msdb.isAvalibAndroid().getBool());
            individual.setPropertyValue(avalib_db_property, msdb.isAvalibDB().getBool());
            individual.setPropertyValue(multy_platform_property, msdb.isMultyPlatform().getBool());
        }

        //VCS
        for(VCS vcs : vcsList){
            OWLIndividual individual = LogLib_class.createOWLIndividual(owlURI + vcs.getName());
            individual.setPropertyValue(team_size_property, vcs.getTeamSize().toString());
            individual.setPropertyValue(multy_platform_property, vcs.isMultyPlatform().getBool());
        }

        OWLIndividual userQueryIndiv = NeedUser_class.createOWLIndividual(owlURI + userQuery.toString());
        userQueryIndiv.setPropertyValue(bsp_advanteges_property, userQuery.getBspAdv().toString());
        userQueryIndiv.setPropertyValue(available_android_property, userQuery.getAvalibAndroid().getBool());
        userQueryIndiv.setPropertyValue(multy_platform_property, userQuery.getMultyPlatform().getBool());
        userQueryIndiv.setPropertyValue(gui_advant_property, userQuery.getGuiAdv().toString());
        userQueryIndiv.setPropertyValue(complexity_project_property, userQuery.getComplexity().toString());
        userQueryIndiv.setPropertyValue(need_logging_property, userQuery.getNeedLogging().getBool());
        userQueryIndiv.setPropertyValue(avalib_db_property, userQuery.getAvalibDB().getBool());
        userQueryIndiv.setPropertyValue(team_size_property, userQuery.getTeamSize().toString());
    }

    /**
     * Считываются экземпляров класса и добавление их в список
     */
    private void readIndividuals(){

        bspList = new ArrayList<>();
        guiLibList = new ArrayList<>();
        logLibList = new ArrayList<>();
        msdbList = new ArrayList<>();
        vcsList = new ArrayList<>();

        List<RDFObject> bspOWLList = readOWLClassIndividuals(BuildSystemProject_class_name);
        List<RDFObject> guiLibOWLList = readOWLClassIndividuals(GUILib_class_name);
        List<RDFObject> logLibOWLList = readOWLClassIndividuals(LogLib_class_name);
        List<RDFObject> msdbOWLList = readOWLClassIndividuals(DBMS_class_name);
        List<RDFObject> vcsOWLList = readOWLClassIndividuals(VersionControlSystem_class_name);
        List<RDFObject> userQueryOWLList = readOWLClassIndividuals(NeedUser_class_name);

        readIndividuals(bspOWLList, guiLibOWLList, logLibOWLList, msdbOWLList, vcsOWLList, userQueryOWLList);
    }

    /**
     * Считывание из OWL RDF-экземпляры инструментов
     * @param className - имя класса интструментов
     * @return - список всех экземпляров заданного класса
     */
    private List<RDFObject> readOWLClassIndividuals(String className){
        ArrayList<RDFObject> instrumentsOWL = new ArrayList<>();
        String sparql_text = "PREFIX f: <http://www.owl-ontologies.com/JavaInstruments.owl#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?instrument WHERE { ?instrument rdf:type f:"+ className +". }";
        try
        {
            QueryResults results = owlModel.executeSPARQLQuery(sparql_text);
            if(results.hasNext())
            {
                while(results.hasNext())
                {
                    Map map = results.next();
                    RDFObject value = (RDFObject) map.get("instrument");
                    instrumentsOWL.add(value);
                }
            }
        }
        catch(QueryException e)
        {
            System.out.println("ERROR in parsing the userQuery to the ontology!");
        }
        catch(Exception e)
        {
            System.out.println("ERROR in quering the ontology!");
        }
        return instrumentsOWL;
    }

    /**
     * Считывание OWL-экземпляров классов, создание из них програмной реализации классов и добавления их в список
     *
     */
    private void readIndividuals(List<RDFObject> bspOWLList, List<RDFObject> guiOWLList,
                                 List<RDFObject> logLibOWLList, List<RDFObject> msdbOWLList,
                                 List<RDFObject> vcsOWLList, List<RDFObject> userQueryOWLList){

        //BSP
        for(RDFObject bspRDF : bspOWLList){
            OWLIndividual bspOWL = owlModel.getOWLIndividual(bspRDF.getBrowserText());
            String name = bspOWL.getName().substring(owlURI.length());
            BSP_ADV bspAdv = BSP_ADV.getInd((String) bspOWL.getPropertyValue(bsp_advanteges_property));
            BOOLEAN_PARAM avalAndr = BOOLEAN_PARAM.getInd((Boolean) bspOWL.getPropertyValue(available_android_property));
            BOOLEAN_PARAM multyplat = BOOLEAN_PARAM.getInd((Boolean) bspOWL.getPropertyValue(multy_platform_property));
            bspList.add(new BSP(name, bspAdv, avalAndr, multyplat));
        }

        //GUILib
        for(RDFObject guiRDF : guiOWLList){
            OWLIndividual guiOWL = owlModel.getOWLIndividual(guiRDF.getBrowserText());
            String name = guiOWL.getName().substring(owlURI.length());
            GUI_ADV guiAdv = GUI_ADV.getInd((String) guiOWL.getPropertyValue(gui_advant_property));
            BOOLEAN_PARAM multyplat = BOOLEAN_PARAM.getInd((Boolean) guiOWL.getPropertyValue(multy_platform_property));
            guiLibList.add(new GUILib(name, guiAdv, multyplat));
        }

        //LogLib
        for(RDFObject logRDF : logLibOWLList){
            OWLIndividual logOWL = owlModel.getOWLIndividual(logRDF.getBrowserText());
            String name = logOWL.getName().substring(owlURI.length());
            COMPLEXITY complexity = COMPLEXITY.getInd((String) logOWL.getPropertyValue(complexity_project_property));
            BOOLEAN_PARAM needlogging = BOOLEAN_PARAM.getInd((Boolean) logOWL.getPropertyValue(need_logging_property));
            BOOLEAN_PARAM multyplat = BOOLEAN_PARAM.getInd((Boolean) logOWL.getPropertyValue(multy_platform_property));
            logLibList.add(new LogLib(name, complexity, needlogging, multyplat));
        }

        //MSDB
        for(RDFObject msdbRDF : msdbOWLList){
            OWLIndividual msdbOWL = owlModel.getOWLIndividual(msdbRDF.getBrowserText());
            String name = msdbOWL.getName().substring(owlURI.length());
            BOOLEAN_PARAM avalibAnd = BOOLEAN_PARAM.getInd((Boolean) msdbOWL.getPropertyValue(available_android_property));
            BOOLEAN_PARAM avalibDB = BOOLEAN_PARAM.getInd((Boolean) msdbOWL.getPropertyValue(avalib_db_property));
            BOOLEAN_PARAM multyplat = BOOLEAN_PARAM.getInd((Boolean) msdbOWL.getPropertyValue(multy_platform_property));
            msdbList.add(new MSDB(name, avalibDB, avalibAnd, multyplat));
        }

        //VCS
        for(RDFObject vcsRDF : vcsOWLList){
            OWLIndividual vcsOWL = owlModel.getOWLIndividual(vcsRDF.getBrowserText());
            String name = vcsOWL.getName().substring(owlURI.length());
            TEAM_SIZE teamSize = TEAM_SIZE.getInd((String) vcsOWL.getPropertyValue(team_size_property));
            BOOLEAN_PARAM multyplat = BOOLEAN_PARAM.getInd((Boolean) vcsOWL.getPropertyValue(multy_platform_property));
            vcsList.add(new VCS(name, teamSize, multyplat));
        }

        for(RDFObject userQueryRDF : userQueryOWLList){
            OWLIndividual userQueryOWL = owlModel.getOWLIndividual(userQueryRDF.getBrowserText());
            String name = userQueryOWL.getName().substring(owlURI.length());
            BSP_ADV bspAdv = BSP_ADV.getInd((String) userQueryOWL.getPropertyValue(bsp_advanteges_property));
            BOOLEAN_PARAM avalAndr = BOOLEAN_PARAM.getInd((Boolean) userQueryOWL.getPropertyValue(available_android_property));
            GUI_ADV guiAdv = GUI_ADV.getInd((String) userQueryOWL.getPropertyValue(gui_advant_property));
            COMPLEXITY complexity = COMPLEXITY.getInd((String) userQueryOWL.getPropertyValue(complexity_project_property));
            BOOLEAN_PARAM needlogging = BOOLEAN_PARAM.getInd((Boolean) userQueryOWL.getPropertyValue(need_logging_property));
            BOOLEAN_PARAM avalibDB = BOOLEAN_PARAM.getInd((Boolean) userQueryOWL.getPropertyValue(avalib_db_property));
            TEAM_SIZE teamSize = TEAM_SIZE.getInd((String) userQueryOWL.getPropertyValue(team_size_property));
            BOOLEAN_PARAM multyplat = BOOLEAN_PARAM.getInd((Boolean) userQueryOWL.getPropertyValue(multy_platform_property));
            userQuery = new UserQuery(this, complexity, avalibDB, teamSize, bspAdv, guiAdv, multyplat, avalAndr, needlogging);
            //suitable instruments
            //BSP
            List<RDFObject> sutBSP_RDF = readSutableObjProperty(sutable_BSP_relation_name);
            for(RDFObject bspRDF : sutBSP_RDF){
                String bsp = bspRDF.getBrowserText().substring(owlURI.length());
                userQuery.suitableBSP.add(bsp);
            }
            //GUILib
            List<RDFObject> sutGUILibRDF = readSutableObjProperty(sutable_GUILib_relation_name);
            for(RDFObject guiRDF : sutGUILibRDF){
                String gui = guiRDF.getBrowserText().substring(owlURI.length());
                userQuery.suitableGUILib.add(gui);
            }
            //LogLib
            List<RDFObject> sutLogLibRDF = readSutableObjProperty(suitable_LogLib_relation_name);
            for(RDFObject logRDF : sutLogLibRDF){
                String log = logRDF.getBrowserText().substring(owlURI.length());
                userQuery.suitableLogLib.add(log);
            }
            //MSDB
            List<RDFObject> sutMSDBRDF = readSutableObjProperty(sutable_DBMS_relation_name);
            for(RDFObject msdbRDF : sutMSDBRDF){
                String msdb = msdbRDF.getBrowserText().substring(owlURI.length());
                userQuery.suitableMSDB.add(msdb);
            }
            //VCS
            List<RDFObject> sutVCS_RDF = readSutableObjProperty(sutable_VCS_relation_name);
            for(RDFObject vcsRDF : sutVCS_RDF){
                String vcs = vcsRDF.getBrowserText().substring(owlURI.length());
                userQuery.suitableVCS.add(vcs);
            }
        }

    }

    private List<RDFObject> readSutableObjProperty(String properyName){

        ArrayList<RDFObject> sutInstrumentsOWL = new ArrayList<>();

        String sparql_text = "PREFIX f: <http://www.owl-ontologies.com/JavaInstruments.owl#> " +
                "SELECT ?instrument WHERE { f:" + userQuery.toString() + " f:"+ properyName + " ?instrument.}";
        try
        {
            QueryResults results = owlModel.executeSPARQLQuery(sparql_text);
            if(results.hasNext())
            {
                while(results.hasNext())
                {
                    Map map = results.next();
                    RDFObject value = (RDFObject) map.get("instrument");
                    sutInstrumentsOWL.add(value);
                }
            }
        }
        catch(QueryException e)
        {
            System.out.println("ERROR in parsing the userQuery to the ontology!");
        }
        catch(Exception e)
        {
            System.out.println("ERROR in quering the ontology!");
        }
        return sutInstrumentsOWL;
    }

}
