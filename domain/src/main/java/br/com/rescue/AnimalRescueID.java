package br.com.rescue;

import br.com.shared.Identifier;
import br.com.shared.ULIDGenerator;

import java.util.Objects;

public class AnimalRescueID extends Identifier {
    private final String value;

    private AnimalRescueID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static AnimalRescueID unique() {
        var ulid = new ULIDGenerator().getValue();
        return AnimalRescueID.from(ulid);
    }

    public static AnimalRescueID from(final String anId) {
        return new AnimalRescueID(anId);
    }

    public static AnimalRescueID from(final ULIDGenerator anId) {
        return new AnimalRescueID(anId.getValue().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AnimalRescueID that = (AnimalRescueID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
