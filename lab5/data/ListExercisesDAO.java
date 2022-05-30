package lab5.data;

import lab5.data.models.Exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListExercisesDAO implements DAO<Exercise> {

    private final List<Exercise> exercises = new ArrayList<>();

    @Override
    public void add(Exercise item) throws ArrayStoreException {
        if (exercises.contains(item)) {
            throw new ArrayStoreException("Exercise with the same name also included");
        }

        exercises.add(item);
    }

    @Override
    public void add(Collection<Exercise> items) throws ArrayStoreException {
        items.forEach(this::add);
    }

    @Override
    public void delete(Exercise item) {
        exercises.remove(item);
    }

    @Override
    public void delete(Collection<Exercise> items) {
        exercises.removeAll(items);
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        exercises.remove(index);
    }

    @Override
    public Exercise get(int index) {
        checkIndex(index);
        return exercises.get(index);
    }

    @Override
    public Collection<Exercise> getAll() {
        return exercises;
    }

    @Override
    public void update(int index, Exercise newItem) {
        checkIndex(index);
        exercises.remove(index);
        exercises.add(index, newItem);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= exercises.size()) {
            throw new IllegalArgumentException("Index out of bound");
        }
    }

}
