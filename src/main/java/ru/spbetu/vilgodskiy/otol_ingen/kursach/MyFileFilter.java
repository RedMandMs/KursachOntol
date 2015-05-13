package ru.spbetu.vilgodskiy.otol_ingen.kursach;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Vred.L.Hom on 11.05.2015.
 */
public class MyFileFilter extends FileFilter {

    private String ext, description;            //������� � ��������

    /**
     * ����������� ������ � ������������� ��������
     * @param ext - �������
     */
    public MyFileFilter(String ext){
        this.ext = ext;
        this.description = ext;
    }

    /**
     * ��� �������� ������
     * @param f - �������������� (��������� ����)
     * @return - ��������� �� ��������
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
     * ������ ��������
     * @return - ��������
     */
    public String getDescription() {
        return description;
    }

}
