package ru.spbetu.vilgodskiy.otol_ingen.kursach;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Vred.L.Hom on 11.05.2015.
 */
public class MyFileFilter extends FileFilter {

    private String ext, description;            //суффикс и описание

    /**
     * Конструктор класса с использование суффикса
     * @param ext - суффикс
     */
    public MyFileFilter(String ext){
        this.ext = ext;
        this.description = ext;
    }

    /**
     * При принятии выбора
     * @param f - обрабатываемый (выбранный файл)
     * @return - выполнена ли операция
     */
    @Override
    public boolean accept(File f) {
        if(f != null) {
            if(f.isDirectory()) {
                return true;
            }

            return f.toString().endsWith(ext);
        }
        return false;
    }

    /**
     * Геттер описания
     * @return - описание
     */
    public String getDescription() {
        return description;
    }

}
