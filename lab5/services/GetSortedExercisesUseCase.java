package lab5.services;

import lab5.data.models.Exercise;

import java.util.Comparator;
import java.util.List;

public class GetSortedExercisesUseCase {

    private final lab5.data.DAO<Exercise> DAO;

    public GetSortedExercisesUseCase(lab5.data.DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getSortedExercises() {
        return DAO.getAll().stream().sorted(Comparator.comparing(Exercise::name)).toList();
    }

}
