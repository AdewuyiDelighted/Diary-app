import controllers.DiaryController;
import data.models.Entry;
import data.repositories.DiaryRepository;
import data.repositories.DiaryRepositoryImpl;
import services.DiaryService;
import services.DiaryServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    private static Scanner scanner = new Scanner(System.in);
    private static DiaryController diaryController = new DiaryController();



    public static void mainMenu() {
        String answer = input("""
                =============================
                1.Register
                2.Login
                3.Write on diary
                4.Find Entry
                5.exit
                =============================
                """);

        switch (answer) {
            case "1" -> register();
            case "2" -> login();
            case "3" -> writeOnDiary();
            case "4" -> read();
            case "5" -> exit();
        }


    }

    private static void exit() {
        System.exit(0);
    }

    private static void read() {
        String username = input("Enter your username");
        System.out.println(diaryController.findEntryBelongingTo(username));
        mainMenu();


    }


    private static void writeOnDiary() {
        String username = input("Enter your diary username");
        String title = input("Enter title ");
        String body = input("Start writing ");
        System.out.println(diaryController.creatEntry(username, title, body));
        mainMenu();
    }

    private static void login() {
        String name = input("Enter your username");
        String password = input("Enter your password");
        System.out.println(diaryController.login(name, password));
        mainMenu();

    }

    private static void register() {
        String name = input("Enter your username");
        String password = input("Enter your password");
        System.out.println(diaryController.register(name, password));
        mainMenu();
    }


    public static String input(String message) {
        System.out.println(message);
        return scanner.nextLine();

    }


}