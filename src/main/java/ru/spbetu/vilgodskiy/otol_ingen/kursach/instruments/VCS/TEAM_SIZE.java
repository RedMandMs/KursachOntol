package ru.spbetu.vilgodskiy.otol_ingen.kursach.instruments.VCS;

/**
 * Created by Vred.L.Hom on 10.05.2015.
 */
public enum TEAM_SIZE {
    ALONE("Независимый разработчик"),
    SMALL_TEAM("Малая команда разработчиков"),
    NORMAL_TEAM("Средняя команда разработчиков"),
    BIG_TEAM("Большая команда разработчиков");

    private String title;

    TEAM_SIZE(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public static TEAM_SIZE getBspAdv(String title){
        TEAM_SIZE [] values = TEAM_SIZE.values();
        for(TEAM_SIZE teamSize : values){
            if(teamSize.toString().equals(title)){
                return teamSize;
            }
        }
        return null;
    }
}
