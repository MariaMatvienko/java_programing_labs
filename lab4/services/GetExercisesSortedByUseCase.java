package lab4.services;

import lab4.data.DAO;
import lab4.data.models.Exercise;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GetExercisesSortedByUseCase {

    private final DAO<Exercise> DAO;

    public GetExercisesSortedByUseCase(DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getSortedExercisesBy(int count, Function<Exercise, String> field) {
        return DAO.getAll().stream().sorted(Comparator.comparing(field)).limit(count).toList();
    }

}
