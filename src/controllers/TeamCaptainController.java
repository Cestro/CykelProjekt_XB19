package controllers;

import data.Data;
import models.Team;
import models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class TeamCaptainController {

    Scanner input = new Scanner(System.in);
    private SharedController sharedController;

    Data data;
    User currentUser;

    public TeamCaptainController(Data data, User currentUser) {
        this.data = data;
        this.currentUser = currentUser;
        this.sharedController = new SharedController();
    }

    public void showTeamCaptainMenu() {

        do {
            System.out.println("Holdkaptajn menu");
            System.out.println("1) Ændr i et holds oplysninger");
            System.out.println("2) Slet en deltager");
            System.out.println("3) Slet et hold");
            System.out.println("4) Vis alle oplysningerne om et bestemt hold og dets deltagere");
            System.out.println("5) Vis alle oplysningerne om alle hold og dets deltagere");
            System.out.println("6) Vis statistik over gennemsnitsalderen på forskellige hold");
            System.out.println("7) Vis alle oplysninger om én bestemt deltager");
            System.out.println("8) Log ud");

            switch (input.nextInt()) {
                case 1:
                    changeTeamInformation();
                    break;
                case 2:
                    changeOwnInformation();
                case 3:
                    deleteUser(data.getTeams());
                    break;
                case 4:
                    deleteTeam();
                    break;
                case 5:
                    sharedController.showParticipantsOfOneTeam(data.getTeams());
                    break;
                case 6:
                    printAll();
                    break;
                case 7:
                    sharedController.printStatistics(data.getTeams());
                    break;
                case 8:
                    printSingleParticipant();
                    break;
                case 9:
                    currentUser = null;
                    break;
                default:
                    System.out.println("Den indtastede input ligger uden for valgmulighederne, prøv igen.");
            }
        } while (currentUser != null);

    }

    private void changeTeamInformation() {
        String newTeamName;
        String newTeamFirm;

        int indexOfTeamInformationToChange;
        Team teamToChange;

        System.out.println("Vælg Team som skal ændres: \n");
        System.out.printf("%-5s %-15s\n", "ID", "Navn");
        for (Team team : data.getTeams()) {
            int holdIndex = data.getTeams().indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n", holdIndex, team.getTeam(), team.getFirm());
        }

        indexOfTeamInformationToChange = input.nextInt();

        teamToChange = data.getTeams().get(indexOfTeamInformationToChange);

        System.out.println("Indtast nyt hold navn: ");
        input.nextLine(); //Ellers skipper den nextLine inputtet
        newTeamName = input.nextLine();

        System.out.println("Indtast nyt firma: ");
        newTeamFirm = input.nextLine();

        teamToChange.setNewTeamName(newTeamName);
        teamToChange.setNewTeamFirm(newTeamFirm);

        System.out.println("Nyt navn er: " + teamToChange.getTeam());
        System.out.println("Nyt firma er: " + teamToChange.getFirm());

    } //Done

    private void deleteUser(ArrayList<Team> theTeams) {

        System.out.println("Tast User-ID for at slette en bruger\n");
        System.out.printf("%-5s %-15s %-15s %-15s %-30s %-15s %-15s\n", "ID", "Navn", "Alder", "Virksomhed", "E-mail", "Cyklisttype", "Kodeord");
        for (User user : data.getUsers()) {
            int deltagerIndex = data.getUsers().indexOf(user);
            System.out.printf("%-5s %-15s %-15s %-15s %-30s %-15s %-15s\n", deltagerIndex, user.getName(), user.getAge(), user.getFirm(), user.getEmail(), user.getCyclistType(), user.getPassword());
        }

        int i = input.nextInt();
        User sletValg = data.getUsers().get(i);//skal get i være der
        System.out.printf("%-15s %-15s\n", sletValg.getName(), sletValg.getAge());
//        String sletID = data.getUsers().get(i).getEmail(); //ved ikke om den her linje er vigtig længere
        this.data.getUsers().remove(sletValg);

        for (Team theTeam : theTeams) {
            if(theTeam.getTeamParticipants().contains(sletValg)){
                theTeam.getTeamParticipants().remove(sletValg);
            }
        }

        System.out.println("Deltageren er nu slettet fra holdet");

//        printAll();
    } //Ikke done mere lige pludseligt??

    private void deleteTeam() {
        int SletHoldIndex;

        System.out.println("Vælg Team som skal slettes: \n");
        System.out.printf("%-5s %-15s\n", "ID", "Navn");
        for (Team team : data.getTeams()){
            int holdIndex = data.getTeams().indexOf(team);
            System.out.printf("%-5d %-15s %-15s\n", holdIndex, team.getTeam(), team.getFirm());
        }
        SletHoldIndex = input.nextInt();

        this.data.getTeams().remove(SletHoldIndex);

        System.out.println("Holdet er nu slettet, her ses den nye oversigt: ");
        System.out.printf("%-15s %-15s\n", "Index Nr.", "Navn");
        for (Team team : data.getTeams()){
            int holdIndex = data.getTeams().indexOf(team);
            System.out.printf("%-15d %-15s %-15s\n", holdIndex, team.getTeam(), team.getFirm());

        }
    } //Virker som det skal, med mangler.

    private void printAll() {
        System.out.println("Oversigt over alle hold og tilhørende deltagere, samt utilmeldte deltagere");

        for(Team team: data.getTeams()) {
            System.out.println("\n");
            System.out.printf("%-30s %-30s %-30s\n", "Hold nr.", "Hold Navn", "Firma");
            int holdIndex = data.getTeams().indexOf(team);
            System.out.printf("%-30d %-30s %-30s\n", holdIndex, team.getTeam(), team.getFirm());
            //Kører 1 gang for hvert hold

            System.out.println("Deltagere:");
            System.out.printf("%-15s %-15s %-15s %-30s %-15s %-15s\n", "Navn", "Alder", "Virksomhed", "E-mail", "Cyklist-type", "Password");
            for (User user : team.getTeamParticipants()) {
                System.out.printf("%-15s %-15s %-15s %-30s %-15s %-15s\n", user.getName(), user.getAge(), user.getFirm(), user.getEmail(), user.getCyclistType(), user.getPassword());
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

    } // Næsten Done, den laver enorme mellemrum mellem hver liste.

    //note til rapporten: man kunne evt. gøre sådan man kan vælge mellem hold først, dernæst deltager og få print.
    // Her skal der også tilføjes en: if type == null, abort, så holdkaptajner ikke kan ses hinandens data, hvis muligt.
    private void printSingleParticipant() {
        System.out.println("Vælg deltager for at se alle oplysningerne");
        System.out.printf("%-5s %-15s\n", "ID", "Navn");
        for (User user : data.getUsers()) {
            int deltagerIndex = data.getUsers().indexOf(user);
            System.out.printf("%-5d %-15s\n", deltagerIndex, user.getName());
        }
        int valgteBrugerIndex = input.nextInt();
        System.out.printf("%-15s %-15s %-15s %-20s %-15s %-15s\n", "Navn", "Alder", "Virksomhed", "E-mail", "Cyklisttype", "Kodeord");
        User valgUser = data.getUsers().get(valgteBrugerIndex);
        System.out.printf("%-15s %-15s %-15s %-20s %-15s %-15s\n", valgUser.getName(), valgUser.getAge(), valgUser.getFirm(), valgUser.getEmail(), valgUser.getCyclistType(), valgUser.getPassword());



    } // Done

    private void changeOwnInformation() {
            String newName;
            int newAge;
            String newFirm;
            String newEmail;
            String newCyclistType;
            String newPassword;

            System.out.println("Indtast nyt holdkaptajn navn: " + currentUser.getName());
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

