package br.com.gitcard.domain.giftcard;

import br.com.shared.Identifier;
import br.com.shared.UUIDGenerator;

import java.util.Objects;
import java.util.UUID;

public class GiftCardID extends Identifier {
    private final String value;

    private GiftCardID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static GiftCardID unique() {
        return GiftCardID.from(new UUIDGenerator().getValue());
    }

    public static GiftCardID from(final String anId) {
        return new GiftCardID(anId);
    }

    public static GiftCardID from(final UUID anId) {
        return new GiftCardID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GiftCardID that = (GiftCardID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
