package controllers;

import data.Data;
import models.User;
import models.Team;
import java.util.Scanner;

public class UserController {

    Scanner input = new Scanner(System.in);
    private SharedController sharedController;

    Data data;
    User currentUser;

    public UserController(Data data, User currentUser) {
        this.data = data;
        this.currentUser = currentUser;
        this.sharedController = new SharedController();
    }


    public void showParticipantMenu() {
        int choice;

        do {
            System.out.println("Deltager menu");
            System.out.println("1) Tilknyt et hold");
            System.out.println("2) Ændr i egne oplysninger");
            System.out.println("3) Vis alle oplysningerne om et hold og dets deltagere");
            System.out.println("4) Vis oplysningerne om alle tilmeldte hold og dets deltagere"); //ændret i tekst
            System.out.println("5) Vis statistik over gennemsnitsalderen på forskellige hold");
            System.out.println("6) Vis oplysninger om én bestemt deltager");
            System.out.println("7) Log ud");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    joinTeam();
                    break;
                case 2:
                    chanceInformation();
                    break;
                case 3:
                    sharedController.showParticipantsOfOneTeam(data.getTeams());
                    break;
                case 4:
                    printAllLimited();
                    break;
                case 5:
                    sharedController.printStatistics(data.getTeams());
                    break;
                case 6:
                    printSingleParticipant();
                    break;
                case 7:
                    currentUser = null;
                    break;
                default:
                    System.out.println("Den indtastede input ligger uden for valgmulighederne, prøv igen.");
            }
        }
        while (currentUser != null);
    }

    private void joinTeam() {
        System.out.println("Du kan tilmelde dig følgende hold: ");
        System.out.printf("%-5s %-15s %-15s\n", "ID", "Holdnavn", "Virksomhed");
        for (Team team : data.getTeams()) {
            int holdIndex = data.getTeams().indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n", holdIndex, team.getTeam(), team.getFirm());
        }
        int TilknytValg = input.nextInt();
        data.getTeams().get(TilknytValg).getTeamParticipants().add(currentUser);
    } //Done

    private void chanceInformation() {
        {
            String newName;
            int newAge;
            String newFirm;
            String newEmail;
            String newCyclistType;
            int newPassword;

            System.out.println("Indtast nyt navn: ");
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
            //Der skal tastes "Enter" en ekstra gang hér, ved ikke hvorfor.

            input.nextLine();
            System.out.println("Indtast nyt fire cifret password: ");
            newPassword = input.nextInt();
            currentUser.setnewPassword(newPassword);

        }
    }

    //Deltagere ud fra hold sortering
    private void printAllLimited() {
        System.out.println("Oversigt over alle hold og tilhørende deltagere, samt utilmeldte deltagere");

        for (Team team : data.getTeams()) {
            System.out.println("\n");
            System.out.printf("%-30s %-30s %-30s\n", "Hold nr.", "Hold Navn", "Firma");
            int holdIndex = data.getTeams().indexOf(team);
            System.out.printf("%-30d %-30s %-30s\n", holdIndex, team.getTeam(), team.getFirm());
            //Kører 1 gang for hvert hold

            System.out.println("Deltagere:");
            System.out.printf("%-15s %-15s %-15s %-15s\n", "Navn", "Alder", "Virksomhed", "Cyklisttype");
            for (User user : team.getTeamParticipants()) {
                System.out.printf("%-15s %-15s %-15s %-15s\n", user.getName(), user.getAge(), user.getFirm(), user.getCyclistType());
                //Kører 1 gang for hver deltager på et hold
            }
        }

        System.out.println("\n");
        System.out.println("Følgende deltagere er endnu ikke tilmeldt et hold: ");
        System.out.printf("%-15s %-15s %-15s %-15s\n", "Navn", "Alder", "Virksomhed", "Cyklisttype");

        for(User user : data.getUsers()){
            if (user.getaddToTeam() == false){
                System.out.printf("%-15s %-15s %-15s %-15s\n", user.getName(), user.getAge(), user.getFirm(), user.getCyclistType());
            }
        }
        System.out.println("\n");
    } //Done

    //note til rapporten: man kunne sagtens lave vælg mellem hold først, dernæst deltager og få print.
    private void printSingleParticipant() {
        System.out.println("Vælg deltager for at se oplysninger");
        System.out.printf("%-5s %-15s\n", "ID", "Navn");
            for (User user : data.getUsers()) {
                int deltagerIndex = data.getUsers().indexOf(user);
                System.out.printf("%-5d %-15s\n", deltagerIndex, user.getName());
            }
        int valgteBrugerIndex = input.nextInt();
        System.out.printf("%-15s %-10s %-15s %-15s\n", "Navn", "Alder", "Virksomhed", "Cyklisttype");
        User valgUser = data.getUsers().get(valgteBrugerIndex);
        System.out.printf("%-15s %-10s %-15s %-15s\n", valgUser.getName(), valgUser.getAge(), valgUser.getFirm(), valgUser.getCyclistType());
    } //Næsten Done
}
