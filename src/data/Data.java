package data;

import models.Team;
import models.User;


import java.util.ArrayList;

public class Data {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();

    public Data() {

        generateData();
    }

    private void generateData() {
        User user = new User("Andreas", 22, "Acacia", "Andreas@mail.dk", "Amatør", "1234", 1);
        User user1 = new User("Nadia", 20, "CBS", "Nadia@mail.dk", "Amatør", "1234", 1);
        User user2 = new User("Frederik", 21, "Acacia", "Frederik@mail.dk", "Pro", "1234", 2);
        User user3 = new User("Carl", 22, "CBS", "Carl@mail.dk", "Professionel", "1234", 2);
        User user4 = new User("Bjarne",54,"Falck","Bjarne@mail.dk","Erfaren","1234", 1);
        User user5 = new User("Peter",60,"Falck","Peter@mail.dk","Professionel","1234", 2);
        User user6 = new User("testDeltager",22,"test","del","t","1234", 1);
        User user7 = new User("testKaptajn",22,"test","kap","t","1234", 2);

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);


        Team team = new Team("Team EasyOn", "CBS");
        Team team1 = new Team("XB 19", "CBS");
        Team team2 = new Team("Novo's Drenge","Novo Nordisk");

        teams.add(team);
        teams.add(team1);
        teams.add(team2);

        team.getTeamParticipants().add(user);
        user.setAddToTeam(true);

        team.getTeamParticipants().add(user1);
        user1.setAddToTeam(true);

        team1.getTeamParticipants().add(user2);
        user2.setAddToTeam(true);

        team1.getTeamParticipants().add(user3);
        user3.setAddToTeam(true);

        team2.getTeamParticipants().add(user4);
        user4.setAddToTeam(true);

        team2.getTeamParticipants().add(user5);
        user5.setAddToTeam(true);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
