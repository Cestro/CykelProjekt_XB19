package controllers;

import com.sun.org.apache.xpath.internal.SourceTree;
import models.Team;
import models.User;
import data.Data;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuestController {

    Scanner input = new Scanner(System.in);
    Data data;

    public GuestController(Data data) {
        this.data = data;
    }

    public void ShowGuestMenu() {
        int choice = 3; //Det her er bare en basisværdi der bliver ændret senere


        System.out.println("Velkommen til Login menuen for ''Vi cykler på arbejde''.");
        System.out.println(" o__     o__     o__   ");
        System.out.println(" ,>/_    ,>/_    ,>/_  ");
        System.out.println("(*)`(*) (*)`(*) (*)`(*)");
        System.out.println("Du har nu følgende muligheder:");
        System.out.println("1) Login med dine brugeroplysninger");
        System.out.println("2) Ny bruger");
        System.out.println("0) Afslut programmet");

        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har tastet forkert, prøv igen");
            System.out.println("");
        }


        //Der sker en fejl i scanneren når en nextLine følger en int, der gør at den springer en linje over.
        //Dette bliver undgået her ved at indfører en ekstra nextLine.
        switch (choice) {
            case 1:
                loginFunction();
                break;
            case 2:
                newUser();
                break;
            case 0:
                System.exit(0);
        }
    }

    private void loginFunction() {


        String email;
        String Password;


        System.out.println("");
        System.out.println("Indtast email: ");
        email = input.next();
        System.out.println("Indtast Password: ");
        Password = input.next();

        User
                currentUser = authBruger(email, Password);
//        deltagerController.setCurrentBruger(currentUser);
//        holdkaptajnController.setCurrentBruger(currentUser);

        if (currentUser != null) {
            if (currentUser.getType() == 1) {
                UserController userController = new UserController(data, currentUser);
                userController.showParticipantMenu();
            } else if (currentUser.getType() == 2) {
                TeamCaptainController teamCaptainController = new TeamCaptainController(data, currentUser);
                teamCaptainController.showTeamCaptainMenu();
            }
        } else {
            System.out.println("Du har indtastet forkert brugernavn eller Password");
        }
    }

    /**
     *
     * @param email
     * @param kodeord
     * @return
     */
    public User authBruger(String email, String kodeord) {

        for (User user : data.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(kodeord)) {
                return user;
            }
        }
        return null;

    }

    private void newUser() {
        int valg2;

        System.out.println("Du kan nu vælge følgende: ");
        System.out.println("1) Opret som ny deltager");
        System.out.println("2) Opret som ny holdkaptajn og lav et nyt hold");

        valg2 = input.nextInt();
        input.nextLine();

        switch (valg2) {
            case 1:
                NewParticipant();
                break;

            case 2:
                newTeamCaptain();
                break;
        }

    }

    private void NewParticipant() {
        String name;
        int age;
        String firm;
        String email = "";
        String cyclistType;
        String password;
        int type = 1;

        System.out.println("Du har valgt at oprette dig som user, indtast følgende informationer:");
        System.out.println("Fulde name: ");
        name = input.nextLine();

        System.out.println("Alder: ");      //lav en redo funktion, hvis man skriver forkert, fx. hvis man skriver bogstaver i stedet for tal.
        age = input.nextInt();

        input.nextLine();   //Laver denne nextLine, fordi scanneren for koden til at springe en linje over.
        System.out.println("Virksomhed: ");
        firm = input.nextLine();

        System.out.println("E-mail: ");
        while(!email.contains("@") || !email.contains(".")); {
            email = input.nextLine();
        }

        System.out.println("Cyklisttype: ");
        cyclistType = input.nextLine();

        System.out.println("Opret et password på fire cifre til login: ");
        password = input.nextLine();

        //tilføj if statements
        //evt. flere paramtre til de andre, fx. email SKAL indeholde @.

        User user = new User(name, age, firm, email, cyclistType, password, type);
        this.data.getUsers().add(user);

    }

    private void newTeamCaptain() {
        String name;
        int age;
        String firm;
        String email;
        String cyclistType;
        String password;
        String teamName;
        int type = 2;

        System.out.println("Du har valgt at oprette dig som holdkaptajn, indtast følgende informationer:");
        System.out.println("Fulde name: ");
        name = input.nextLine();

        System.out.println("Alder: ");
        age = input.nextInt();

        input.nextLine();   //Laver denne nextLine, fordi scanneren for koden til at springe en linje over.
        System.out.println("Virksomhed: ");
        firm = input.nextLine();

        System.out.println("E-mail: ");
        email = input.nextLine();

        System.out.println("Cyklisttype: ");
        cyclistType = input.nextLine();

        System.out.println("Opret et password på fire cifre til login: ");
        password = input.nextLine();

        System.out.println("Holdnavn: ");
        teamName = input.nextLine();

        //tilføj if statements
        //evt. flere paramtre til de andre, fx. email SKAL indeholde @.

        User user = new User(name, age, firm, email, cyclistType, password, type);
        Team team = new Team(firm, teamName);

        team.getTeamParticipants().add(user);

        this.data.getUsers().add(user);
        this.data.getTeams().add(team);

        for (User user1 : this.data.getUsers()) {
            System.out.println(user1);

        }
    }
}
