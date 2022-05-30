package lab5.ui;

import lab5.data.DAO;
import lab5.data.FileExercisesDAO;
import lab5.data.ListExercisesDAO;
import lab5.data.models.Exercise;
import lab5.services.GetExercisesByEfficiencyAndTypeUseCase;
import lab5.services.GetExercisesByEfficiencyUseCase;
import lab5.services.GetExercisesSortedByUseCase;
import lab5.services.GetSortedExercisesUseCase;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
    private final Scanner scanner = new Scanner(System.in);

    private final GetSortedExercisesUseCase getSortedExercisesUseCase;

    private final GetExercisesSortedByUseCase getExercisesSortedByUseCase;

    private final GetExercisesByEfficiencyAndTypeUseCase getExercisesByEfficiencyAndTypeUseCase;

    private final GetExercisesByEfficiencyUseCase getExercisesByEfficiencyUseCase;

    private final lab5.data.DAO<Exercise> DAO;

    public UserController(
            lab5.data.DAO<Exercise> DAO,
            GetSortedExercisesUseCase getSortedExercisesUseCase,
            GetExercisesSortedByUseCase getExercisesSortedByUseCase,
            GetExercisesByEfficiencyAndTypeUseCase getExercisesByEfficiencyAndTypeUseCase,
            GetExercisesByEfficiencyUseCase getExercisesByEfficiencyUseCase
    ) {
        this.DAO = DAO;
        this.getSortedExercisesUseCase = getSortedExercisesUseCase;
        this.getExercisesSortedByUseCase = getExercisesSortedByUseCase;
        this.getExercisesByEfficiencyUseCase = getExercisesByEfficiencyUseCase;
        this.getExercisesByEfficiencyAndTypeUseCase = getExercisesByEfficiencyAndTypeUseCase;
    }

    public void start() {
        while (true) {
            System.out.flush();
            System.out.println("1: Show all exercises");
            System.out.println("2: Show sorted exercises");
            System.out.println("3: Show sorted exercises by ...");
            System.out.println("4: Show exercises by efficiency");
            System.out.println("5: Show exercises by efficiency and name");
            System.out.println("6: Add exercise");
            System.out.println("7: Delete exercise");
            System.out.println("0: Exit");

            int parametr = scanner.nextInt();

            switch (parametr) {
                case 0: return;
                case 1: showAll(); break;
                case 2: getSortedExercisesController(); break;
                case 3: getSortedExercisesByController(); break;
                case 4: getExercisesByEfficiencyController(); break;
                case 5: getExercisesByEfficiencyAndNameController(); break;
                case 6: addNewExercise(); break;
                case 7: deleteExercise(); break;
            }

            try {
                System.out.println("Enter for continue");
                System.in.read();
            } catch (IOException e) {
                System.out.println("Oops! Something went wrong");
                return;
            }
        }
    }

    private void showAll() {
        System.out.println(Arrays.toString(DAO.getAll().toArray(new Exercise[0])));
    }

    private void getSortedExercisesController() {
        System.out.println(Arrays.toString(getSortedExercisesUseCase.getSortedExercises().toArray(new Exercise[0])));
    }

    private void getSortedExercisesByController() {
        int count = scanner.nextInt();
        String field = scanner.next();
        if (field.equals("name")) {
            System.out.println(Arrays.toString(getExercisesSortedByUseCase.getSortedExercisesBy(count, Exercise::name).toArray(new Exercise[0])));
        } else {
            System.out.println(Arrays.toString(getExercisesSortedByUseCase.getSortedExercisesBy(count, Exercise::type).toArray(new Exercise[0])));
        }
    }

    private void getExercisesByEfficiencyController() {
        int efficiency = scanner.nextInt();
        System.out.println(Arrays.toString(getExercisesByEfficiencyUseCase.getExercisesByEfficiency(efficiency).toArray(new Exercise[0])));
    }

    private void getExercisesByEfficiencyAndNameController() {
        int efficiency = scanner.nextInt();
        String type = scanner.next();
        System.out.println(Arrays.toString(getExercisesByEfficiencyAndTypeUseCase.getExercisesByEfficiencyAndType(efficiency, type).toArray(new Exercise[0])));
    }

    private void addNewExercise() {
        String type = scanner.next();
        String name = scanner.next();
        int efficiency = scanner.nextInt();

        try {
            DAO.add(new Exercise(name, type, efficiency));
        } catch (ArrayStoreException e) {
            System.out.println("This exercise is already in repository");
        }
    }

    private void deleteExercise() {
        String name = scanner.next();

        Optional<Exercise> exercise = getSortedExercisesUseCase.getSortedExercises().stream().filter(item -> Objects.equals(item.name(), name)).findFirst();
        if (exercise.isPresent()) {
                DAO.delete(exercise.get());
        } else {
            System.out.printf("Can not find a exercise with name %s\n", name);
        }
    }

    public static UserController create(String storagePath) {
        lab5.data.DAO<Exercise> dao;
        if (storagePath.isEmpty()) {
            dao = new ListExercisesDAO();
        } else {
            dao = new FileExercisesDAO(storagePath);
        }

        dao.add(new Exercise("Incline leg press", "Hips", 3));
        dao.add(new Exercise("Sitting leg curl", "Hips", 6));
        dao.add(new Exercise("Lunges (with dumbbells, with a barbell on the shoulders)", "Hips", 2));
        dao.add(new Exercise("Standing single leg curl", "Shin", 4));
        dao.add(new Exercise("Alternating calf raises in a standing position", "Shin", 3));
        dao.add(new Exercise("Leg presses on the leg press machine", "Shin", 6));

        return new UserController(
                dao,
                new GetSortedExercisesUseCase(dao),
                new GetExercisesSortedByUseCase(dao),
                new GetExercisesByEfficiencyAndTypeUseCase(dao),
                new GetExercisesByEfficiencyUseCase(dao)
        );
    }

}
