package lab4;

import lab4.ui.UserController;

public class Main {

    public static void main(String[] args) {
        UserController controller = UserController.create();
        controller.start();
    }

}
