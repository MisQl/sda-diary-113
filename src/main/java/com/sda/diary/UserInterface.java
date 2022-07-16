package com.sda.diary;

import java.util.Scanner;

public class UserInterface {

    private EntryController entryController = new EntryController();

    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Aplikacja jest uruchomiona\n");

        while (true) {
            System.out.println("Witaj w elektorniczym dzienniku, co chcesz zrobić?");
            System.out.println(" 1. Dodać nowy wpis");
            System.out.println(" 0. Zamknać aplikację");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    createEntry();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void createEntry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj tytuł wpisu:");
        String title = scanner.nextLine();
        System.out.println("Podaj treść wpisu:");
        String content = scanner.nextLine();
        String json = String.format("{\"title\": \"%s\",\"content\":\"%s\"}", title, content);
        System.out.println("Wysyłam HTTP request: " + json);
        String httpResponse = entryController.createEntry(json);
        System.out.println("Odpowiedź z serwera: " + httpResponse);
    }
}




