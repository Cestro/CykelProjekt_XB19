package controllers;

import data.Data;

public class MainController {

    private Data data;

    public MainController() {
        this.data = new Data();
    }

    /**
     * run metoden sørger for at programmet køre
     *
     */
    public void run() {
        boolean runProgram = true;

        do {
            GuestController guestController = new GuestController(data);
            guestController.showGuestMenu();
        } while (runProgram);
    }
}