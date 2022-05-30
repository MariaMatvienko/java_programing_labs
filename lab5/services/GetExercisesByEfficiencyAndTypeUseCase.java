package lab5.services;

import lab5.data.models.Exercise;

import java.util.List;

public class GetExercisesByEfficiencyAndTypeUseCase {

    private final lab5.data.DAO<Exercise> DAO;

    public GetExercisesByEfficiencyAndTypeUseCase(lab5.data.DAO<Exercise> DAO) {
        this.DAO = DAO;
    }

    public List<Exercise> getExercisesByEfficiencyAndType(Integer efficiency, String type) {
        return DAO.getAll()
                .stream()
                .filter(exercise -> exercise.compareTo(efficiency) > 0 && exercise.type().equals(type))
                .toList();
    }

}
