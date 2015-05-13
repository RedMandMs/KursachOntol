package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.GUILib;

/**
 * Created by Vred.L.Hom on 10.05.2015.
 */
public enum GUI_ADV {
    DOCUMENTATION("Хорошо поддерживаемая документация"),
    VERIETY_ELEMENTS("Разнообразные графические компоненты"),
    FORM_EDITOR("Редактор форм");

    private String title;

    GUI_ADV(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public static GUI_ADV getInd(String title){
        GUI_ADV [] values = GUI_ADV.values();
        for(GUI_ADV guiAdv : values){
            if(guiAdv.toString().equals(title)){
                return guiAdv;
            }
        }
        return null;
    }
}
