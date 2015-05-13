package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.LogLib;

/**
 * Created by Vred.L.Hom on 10.05.2015.
 */
public enum COMPLEXITY {
    ESYE("Лёгкий проект"),
    NORMAL("Средняя сложность проекта"),
    DIFFICULT("Повышенная сложность проекта");

    private String title;

    COMPLEXITY(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
