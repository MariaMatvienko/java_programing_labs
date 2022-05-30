package lab4.services;

import lab4.data.DAO;
import lab4.data.models.Exercise;

import java.util.List;

public class GetExercisesByEfficiencyAndTypeUseCase {

    private final DAO<Exercise> DAO;

    public GetExercisesByEfficiencyAndTypeUseCase(DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getExercisesByEfficiencyAndType(Integer efficiency, String type) {
        return DAO.getAll()
                .stream()
                .filter(exercise -> exercise.compareTo(efficiency) > 0 && exercise.type().equals(type))
                .toList();
    }

}
