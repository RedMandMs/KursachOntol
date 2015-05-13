package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments;

/**
 * Created by Vred.L.Hom on 10.05.2015.
 */
public enum BOOLEAN_PARAM {

    TRUE("True", true), FALSE("False", false);

    private String title;

    BOOLEAN_PARAM(String title, boolean bul) {
        this.title = title;
        this.bul = bul;
    }


    @Override
    public String toString() {
        return title;
    }

    public static BOOLEAN_PARAM getInd(boolean bul){
        BOOLEAN_PARAM [] values = BOOLEAN_PARAM.values();
        for(BOOLEAN_PARAM booleanParam : values){
            if(booleanParam.bul == bul){
                return booleanParam;
            }
        }
        return null;
    }

    public boolean getBool() {
        return bul;
    }

    public boolean bul;
}
