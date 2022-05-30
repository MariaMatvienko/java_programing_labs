package lab5.services;

import lab5.data.models.Exercise;

import java.util.List;

public class GetExercisesByEfficiencyUseCase {

    private final lab5.data.DAO<Exercise> DAO;

    public GetExercisesByEfficiencyUseCase(lab5.data.DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getExercisesByEfficiency(int efficiency) {
        return DAO.getAll().stream().filter(exercise -> exercise.compareTo(efficiency) > 0).toList();
    }

}
