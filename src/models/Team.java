package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String firm;
    private ArrayList<User> users;

    public Team(String teamName, String firm) {
        this.teamName = teamName;
        this.firm = firm;
        this.users = new ArrayList<>();
    }

    public String getTeam() {
        return teamName;
    }

    public String getFirm() {
        return firm;
    }

    public ArrayList<User> getTeamParticipants() { //renamed pga. potentiel navnekonflikt (hvis det er til at løse)
        return users;
    }

    public double getAvarageAge(){
        double age = 0;

        for(User user : users){
            age += user.getAge();
        }

        age = age / users.size();

        return age;
    }
}