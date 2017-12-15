package controllers;

import models.Team;
import models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class SharedController {

    Scanner input = new Scanner(System.in);

    public void showParticipantsOfOneTeam(ArrayList<Team> theTeams) {
        System.out.println("Vælg hold for at se deltagerlisten: ");
        System.out.printf("%-5s %-15s\n", "ID", "Navn");
        for (Team team : theTeams) {
            int teamIndex = theTeams.indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n", teamIndex, team.getTeam(), team.getFirm());
        }
        int i = input.nextInt();
        for (User user : theTeams.get(i).getTeamParticipants()) {
            System.out.printf("%-5s %-15s\n", theTeams.get(i).getTeamParticipants().indexOf(user), user.getName());
        }
    }

    public void printStatistics(ArrayList<Team> statistics) {
        System.out.printf("%-5s %-15s %-15s\n", "ID", "Holdnavn","Gennemsnits-alder");
        for (Team team : statistics){
            int teamIndex = statistics.indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n",teamIndex, team.getTeam(),team.getAvarageAge());
        }
    }
}