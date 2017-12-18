package controllers;

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

    /**
     * Her benyttes først en switch til at
     */
    public void showGuestMenu() {
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

        User currentUser = authBruger(email, Password);

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
        System.out.println("3) Gå tilbage");

        valg2 = input.nextInt();
        input.nextLine(); //Indsættes for at undgå scanneren skipper input.

        switch (valg2) {
            case 1:
                newParticipant();
                break;
            case 2:
                newTeamCaptain();
                break;
            case 3:
                showGuestMenu();
        }

    }

    /**
     * Først oprettes de variable som skal oprettes.
     * Efterfølgende indtastes hver lokale variable.
     * Til slut tilføjes de nye lokale variable til User ArrayListen.
     */
    private void newParticipant() {
        String name;
        int age;
        String firm;
        String email;
        String cyclistType;
        String password;
        int type = 1;

        System.out.println("Du har valgt at oprette dig som user, indtast følgende informationer:");
        System.out.println("Fulde navn: ");
        name = input.nextLine();

        System.out.println("Alder: ");                                                                                       //lav en redo funktion, hvis man skriver forkert, fx. hvis man skriver bogstaver i stedet for tal.
        age = input.nextInt();

        //Laver den efterfølgende nextLine, fordi scanneren for koden til at springe en linje over.
        input.nextLine();
        System.out.println("Virksomhed: ");
        firm = input.nextLine();

        System.out.println("E-mail (skal indeholde @ og . for at være en gyldig E-mail): ");
        do { email = input.nextLine(); }
        while (!email.contains("@") || !email.contains("."));
        //System.out.println("Du har ikke indtastet en gyldig Email, prøv igen");

        System.out.println("Cyklisttype (Fx: Amatør, Øvet eller professionel): ");
        cyclistType = input.nextLine();

        System.out.println("Opret et password på fire cifre til login: ");
        password = input.nextLine();

        //tilføj if statements
        //evt. flere paramtre til de andre, fx. email SKAL indeholde @.

        User user = new User(name, age, firm, email, cyclistType, password, type);
        this.data.getUsers().add(user);

        System.out.println("Deltager oprettet");

    }
    /**
     * Først oprettes de variable som skal oprettes.
     * Efterfølgende indtastes hver lokale variable.
     * Til slut tilføjes de nye lokale variable til henholdsvis User og Team ArrayListen. Så der bliver oprettet en deltager og et hold.
     */
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

        System.out.println("Holdkaptajnen er nu oprettet");
    }
}
