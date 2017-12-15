package controllers;

import data.Data;

public class MainController {


    private Data data;

    public MainController() {
        this.data = new Data();

    }

    public void run() {
        boolean runProgram = true;

        do {

            GuestController guestController = new GuestController(data);
            guestController.ShowGuestMenu();
        } while (runProgram);
    }
}