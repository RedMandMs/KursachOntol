package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.BSP;

/**
 * Created by Vred.L.Hom on 10.05.2015.
 */
public enum BSP_ADV {

    AVTOINCLUDE("Автоматически подключаемые библиотеки"), SPECFUNCTIONS("Специальные указания при сборке"), ANDROID("Сборка проектов под ОС Android");

    private String title;

    BSP_ADV(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public static BSP_ADV getBspAdv(String title){
        BSP_ADV [] values = BSP_ADV.values();
        for(BSP_ADV bspAdv : values){
            if(bspAdv.toString().equals(title)){
                return bspAdv;
            }
        }
        return null;
    }
}
