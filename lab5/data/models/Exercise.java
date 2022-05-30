package lab5.data.models;

import java.util.Objects;

public record Exercise(String name, String type, int efficiency) implements Comparable {

    @Override
    public String toString() {
        return String.format("\nName: %s\nType: %s\nEfficiency: %s\n", name, type, efficiency);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return Objects.equals(((Exercise) obj).name, this.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + type.hashCode() + efficiency + 30;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj.getClass() == this.getClass()) {
            int objEfficiency = ((Exercise) obj).efficiency;
            return Integer.compare(objEfficiency, this.efficiency);
        }

        if (obj.getClass() == Integer.class) {
            return Integer.compare(((Integer) obj), this.efficiency);
        }

        throw new IllegalArgumentException("Exercise.compareTo accepts only Exercise and Integer");
    }
}
