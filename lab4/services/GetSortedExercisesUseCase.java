package lab4.services;

import lab4.data.DAO;
import lab4.data.models.Exercise;

import java.util.Comparator;
import java.util.List;

public class GetSortedExercisesUseCase {

    private final DAO<Exercise> DAO;

    public GetSortedExercisesUseCase(DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getSortedExercises() {
        return DAO.getAll().stream().sorted(Comparator.comparing(Exercise::name)).toList();
    }

}
