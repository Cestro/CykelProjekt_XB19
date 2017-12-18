package controllers;

import models.Team;
import models.User;
import java.util.ArrayList;
import java.util.Scanner;

public class SharedController {

    Scanner input = new Scanner(System.in);

    User currentUser;

    public void showParticipantsOfOneTeam(ArrayList<Team> theTeams) {
        System.out.println("Vælg hold for at se deltagerlisten: ");
        System.out.printf("%-5s %-15s\n", "ID", "Navn");
        for (Team team : theTeams) {
            int teamIndex = theTeams.indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n", teamIndex, team.getTeam(), team.getFirm());
        }
        int i = input.nextInt();
        System.out.println("Deltagere: ");
        for (User user : theTeams.get(i).getTeamParticipants()) {
            System.out.printf("%-5s %-15s\n", theTeams.get(i).getTeamParticipants().indexOf(user), user.getName());
        }
    }

    public void printStatistics(ArrayList<Team> statistics) {
        System.out.printf("%-5s %-15s %-15s\n", "ID", "Holdnavn", "Gennemsnits-alder");
        for (Team team : statistics) {
            int teamIndex = statistics.indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n", teamIndex, team.getTeam(), team.getAvarageAge());
        }
    }
    /*
    private void changeInformation(ArrayList<User> ) {
            String newName;
            int newAge;
            String newFirm;
            String newEmail;
            String newCyclistType;
            String newPassword;

            System.out.println("Indtast nyt, navn: " + currentUser.getName());
            newName = input.nextLine();
            currentUser.setnewName(newName);

            input.nextLine();
            System.out.println("Indtast ny alder: ");
            newAge = input.nextInt();
            currentUser.setnewAge(newAge);

            System.out.println("Indtast nyt firma: ");
            newFirm = input.nextLine();
            currentUser.setnewFirm(newFirm);

            input.nextLine();
            System.out.println("Indtast ny Email: ");
            newEmail = input.nextLine();
            currentUser.setnewEmail(newEmail);

            System.out.println("Indtast ny CyclistType: ");
            newCyclistType = input.nextLine();
            currentUser.setnewCyclistType(newCyclistType);

            System.out.println("Indtast nyt fire cifret password: ");
            newPassword = input.nextLine();
            currentUser.setnewPassword(newPassword);

            System.out.println("Dine oplysninger er nu ændret");
        }
    }
    */
}