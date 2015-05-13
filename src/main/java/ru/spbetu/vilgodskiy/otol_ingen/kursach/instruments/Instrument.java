package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments;

import javax.swing.*;

/**
 * Created by Vred.L.Hom on 06.05.2015.
 */
public abstract class Instrument {

    private String name = "Instrument";

    public final String getNameSuperClass(){
        return this.name;
    }

    public abstract String getName();

    protected TYPES_INSTRUMENTS instrumentType;

    protected BOOLEAN_PARAM multyPlatform;

    public BOOLEAN_PARAM isMultyPlatform() {
        return multyPlatform;
    }

    public void setMultyPlatform(BOOLEAN_PARAM multyPlatform) {
        this.multyPlatform = multyPlatform;
    }

    /**
     * Записать инструмент в онтологию
     */
    public abstract void writeInOntol();

    public TYPES_INSTRUMENTS getInstrumentType() {
        return instrumentType;
    }

    public abstract JPanel toDesignReviewPanel();

    public static String getSuperName(){
        return "Instrument";
    }
}
