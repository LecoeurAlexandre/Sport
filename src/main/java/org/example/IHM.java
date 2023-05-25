package org.example;

import org.example.entities.User;
import org.example.service.ActivityService;
import org.example.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IHM {
    private UserService userService;
    private ActivityService activityService;
    private Scanner sc;

    public IHM() {
        userService = new UserService();
        activityService = new ActivityService();
        sc = new Scanner(System.in);
    }
    public void start() {
        String choice;
        do {
            menu();
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addNewUser();
                    break;
            }

        } while(!choice.equals("0"));
    };

    public void menu() {
        System.out.println("++++++++  Menu  ++++++++");
        System.out.println("++ Espace adhérent ++");
        System.out.println("1 -- Ajouter un nouvel adhérent ");
        System.out.println("2 -- Afficher un nouvel adhérent");
        System.out.println("3 -- Modifier un adhérent");
        System.out.println("++ Espace activités ++");
        System.out.println("4 -- Ajouter une nouvelle activité");
        System.out.println("5 -- Afficher toutes les activités disponibles");
        System.out.println("6 -- Réserver une activité");
        System.out.println("0 -- Quitter ");
    }

    public void addNewUser() {
        System.out.println("-- Ajouter un nouvel adhérent --");
        System.out.println("Veuillez saisir le prénom");
        String firstName = sc.nextLine();
        System.out.println("Veuillez saisir le nom");
        String lastName = sc.nextLine();
        System.out.println("Veuillez saisir la date de naissance (format aaaa-mm-jj)");
        String birthDate = sc.nextLine();
        try {
            LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE);
            userService.create(new User(firstName, lastName, date));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }




    }

}
