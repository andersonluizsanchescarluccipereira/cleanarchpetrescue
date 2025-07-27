package br.com.shared;

import de.huxhorn.sulky.ulid.ULID;

public class ULIDGenerator extends Identifier {
    private final ULID ulid;

    public ULIDGenerator(ULID ulid) {
        this.ulid = ulid;
    }

    @Override
    public String getValue() {
        return null;
    }
}
