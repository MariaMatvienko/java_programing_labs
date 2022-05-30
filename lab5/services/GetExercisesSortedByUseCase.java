package lab5.services;

import lab5.data.models.Exercise;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GetExercisesSortedByUseCase {

    private final lab5.data.DAO<Exercise> DAO;

    public GetExercisesSortedByUseCase(lab5.data.DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getSortedExercisesBy(int count, Function<Exercise, String> field) {
        return DAO.getAll().stream().sorted(Comparator.comparing(field)).limit(count).toList();
    }

}
