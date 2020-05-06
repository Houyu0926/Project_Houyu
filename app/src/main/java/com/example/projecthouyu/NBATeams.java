package com.example.projecthouyu;

public class NBATeams {
    private String name;
    private String fullname;
    private String coachname;
    private int yearcreation;
    private int numchampions;

    public NBATeams(String name, String fullname, String coachname, int yearcreation, int numchampions) {
        this.name = name;
        this.fullname = fullname;
        this.coachname = coachname;
        this.yearcreation = yearcreation;
        this.numchampions = numchampions;
    }
    //as
    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullname;
    }

    public String getCoachname() {
        return coachname;
    }

    public int getYearcreation() {
        return yearcreation;
    }

    public int getNumchampions() {
        return numchampions;
    }
}
