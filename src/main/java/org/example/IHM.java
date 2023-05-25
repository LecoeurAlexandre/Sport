package org.example;

import org.example.entities.Activity;
import org.example.entities.FavoriteSport;
import org.example.entities.User;
import org.example.service.ActivityService;
import org.example.service.SportService;
import org.example.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class IHM {
    private UserService userService;
    private ActivityService activityService;
    private SportService sportService;
    private Scanner sc;

    public IHM() {
        userService = new UserService();
        activityService = new ActivityService();
        sportService = new SportService();
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
                case "2":
                    displayAllUsers();
                    break;
                case "3":
                    modifyUser();
                    break;
                case "4" :
                    addNewActivity();
                    break;
                case "5" :
                    displayAllActivities();
                    break;
                case "6" :
                    displayAvailableActivities();
                    break;
                case "7" :
                    bookActivity();
                    break;
                case "8" :
                    addNewSport();
                    break;
            }

        } while(!choice.equals("0"));
        activityService.end();
        userService.end();
        sportService.end();
    }

    public void menu() {
        System.out.println("++++++++  Menu  ++++++++");
        System.out.println("++ Espace adhérents ++");
        System.out.println("1 -- Ajouter un nouvel adhérent ");
        System.out.println("2 -- Afficher tous les adhérents");
        System.out.println("3 -- Modifier un adhérent");
        System.out.println("++ Espace activités ++");
        System.out.println("4 -- Ajouter une nouvelle activité");
        System.out.println("5 -- Afficher toutes les activités");
        System.out.println("6 -- Afficher toutes les activités disponibles");
        System.out.println("7 -- Réserver une activité");
        System.out.println("++ Espace activités ++");
        System.out.println("8 -- Ajouter un sport");
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
        System.out.println("Veuillez saisir l'id du sport favori ?");
        int sportId = sc.nextInt();
        sc.nextLine();
        FavoriteSport favoriteSport = sportService.findById(sportId);
        if (favoriteSport != null) {
            try {
                LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE);
                userService.create(new User(firstName, lastName, date, favoriteSport));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("L'id demandé n'est pas attribué à un sport favori");
        }

    }
    public void displayAllUsers() {
        System.out.println("-- Liste des adhérents --");
        List<User> users = userService.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }
    public void modifyUser() {
        System.out.println("-- Modifier les informations d'un adhérent --");
        System.out.println("Veuillez saisir l'id de l'adhérent ?");
        int id = sc.nextInt();
        sc.nextLine();
        User user = userService.findById(id);
        System.out.println("Veuillez saisir le prénom");
        String firstName = sc.nextLine();
        user.setFirstName(firstName);
        System.out.println("Veuillez saisir le nom");
        String lastName = sc.nextLine();
        user.setLastName(lastName);
        System.out.println("Veuillez saisir la date de naissance (format aaaa-mm-jj)");
        String birthDate = sc.nextLine();
        try {
            LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE);
            user.setBirthDate(date);
            userService.update(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addNewActivity() {
        System.out.println("-- Ajouter une nouvelle activité au programme --");
        System.out.println("Veuillez saisir le nom de l'activité");
        String name = sc.nextLine();
        System.out.println("Veuillez saisir la date de l'activité (format aaaa-mm-jj)");
        String activityDate = sc.nextLine();
        System.out.println("Veuillez saisir l'heure (format hh:mm:ss)");
        String activityHour = sc.nextLine();
        System.out.println("Veuillez saisir l'âge minimum ?");
        int minAge = sc.nextInt();
        sc.nextLine();
        System.out.println("Veuillez saisir le nombre de places ?");
        int nb = sc.nextInt();
        sc.nextLine();
        try {
            LocalDate date = LocalDate.parse(activityDate, DateTimeFormatter.ISO_DATE);
            LocalTime time = LocalTime.parse(activityHour);
            activityService.create(new Activity(name, date, time, minAge, nb));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void displayAllActivities() {
        System.out.println("-- Liste de toutes les activités --");
        List<Activity> activities = activityService.findAll();
        for (Activity u : activities) {
            System.out.println(u);
        }
    }

    public void displayAvailableActivities() {
        System.out.println("-- Liste des activités disponibles --");
        List<Activity> activities = activityService.findByAvailability();
        for (Activity u : activities) {
            System.out.println(u);
        }
    }
    public void bookActivity() {
        System.out.println("-- Réserver une activité --");
        System.out.println("Veuillez saisir l'id de l'adhérent ?");
        int userId = sc.nextInt();
        sc.nextLine();
        User user = userService.findById(userId);
        if (user != null) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
            System.out.println("Veuillez saisir l'id de l'activité ?");
            int ActivityId = sc.nextInt();
            sc.nextLine();
            Activity activity = activityService.findById(ActivityId);
            if (activity != null) {
                if (activity.getRegistrationsAvailable()>0) {
                    if (calcAge(user.getBirthDate()) > activity.getMinAge()) {
                        user.addActivity(activity);
                        activity.setRegistrationsAvailable();
                        userService.update(user);
                        activityService.update(activity);
                    } else {
                        System.out.println("L'adhérent est trop jeune pour cette activité");
                    }
                }else{
                    System.out.println("Il n'y a plus de place disponible pour cette activité");
                }
            }else{
                System.out.println("L'id de l'activité demandé n'existe pas");
            }
        } else {
            System.out.println("L'id de l'adhérent demandé n'existe pas");
        }
    }
    public long calcAge(LocalDate date) {
        LocalDate birthdate = date;
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birthdate, now);
        return years;
    }
    public void addNewSport() {
        System.out.println("-- Ajouter un nouveau sport --");
        System.out.println("Veuillez saisir le nom du sport");
        String name = sc.nextLine();
        sportService.create(new FavoriteSport(name));
    }

}
