package lab4.services;

import lab4.data.DAO;
import lab4.data.models.Exercise;

import java.util.List;

public class GetExercisesByEfficiencyUseCase {

    private final DAO<Exercise> DAO;

    public GetExercisesByEfficiencyUseCase(DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getExercisesByEfficiency(int efficiency) {
        return DAO.getAll().stream().filter(exercise -> exercise.compareTo(efficiency) > 0).toList();
    }

}
