package lab5.data;

import lab5.data.models.Exercise;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExercisesDAO implements DAO<Exercise> {
    private final String pathToDir;

    private final String fileNamePattern = "Exercise-%s.txt";

    public FileExercisesDAO(String pathToDir) {
        this.pathToDir = pathToDir;

        try {
            Files.createDirectory(Paths.get(pathToDir));
        } catch (FileAlreadyExistsException e) {
            // Directory exist
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory!");
        }
    }

    @Override
    public void add(Exercise item) throws ArrayStoreException {
        Path path = getPath(item.name());

        if (Files.exists(path)) {
            throw new ArrayStoreException("File already exists");
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(item.toString());
        } catch (IOException e) {
            throw new ArrayStoreException(String.format("Failed to add Exercise %s", item.name()));
        }

    }

    @Override
    public void add(Collection<Exercise> item) {
        item.forEach(this::add);
    }

    @Override
    public void delete(Exercise item) {
        try {
            Files.delete(getPath(item.name()));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to delete Exercise %s", item.name()));
        }
    }

    @Override
    public void delete(Collection<Exercise> item) {
        item.forEach(this::delete);
    }

    @Override
    public void delete(int index) {
        delete(get(index));
    }

    @Override
    public Exercise get(int index) {
        return (Exercise) getAll().toArray()[index];
    }

    @Override
    public Collection<Exercise> getAll() {
        try {
            Path pathToDir = Paths.get(this.pathToDir);
            Stream<Path> files = Files.list(pathToDir);
            return files.map(this::readFile).filter(Objects::nonNull).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read!");
        }
    }

    @Override
    public void update(int index, Exercise newItem) {
        delete(index);
        add(newItem);
    }

    private Exercise readFile(Path path) {
        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String data = reader.lines().collect(Collectors.joining("\n"));
            return parse(data);
        } catch (Exception e) {
            return null;
        }
    }

    private Path getPath(String name) {
        String fileName = String.format(fileNamePattern, name.toLowerCase());
        return FileSystems.getDefault().getPath(pathToDir, fileName);
    }

    private Exercise parse(String data) {
        try {
            List<String> fields = Arrays.stream(data.split("\n")).filter(str -> !str.isEmpty()).toList();
            String name = fields.get(0).split("Name: ")[1];
            String type = fields.get(1).split("Type: ")[1];
            int efficiency = Integer.parseInt(fields.get(2).split("Efficiency: ")[1]);

            return new Exercise(name, type, efficiency);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed parse to Exercise: %s", data));
        }
    }
}
