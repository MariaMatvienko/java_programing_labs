package lab5;

import lab5.ui.UserController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        UserController controller = UserController.create(filePath);
        controller.start();
    }

}
