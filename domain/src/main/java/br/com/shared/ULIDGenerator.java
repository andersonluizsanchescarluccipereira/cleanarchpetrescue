package br.com.shared;

import de.huxhorn.sulky.ulid.ULID;

public class ULIDGenerator extends Identifier {
    private final ULID ulid;

    public ULIDGenerator() {
        this.ulid = new ULID();
    }

    @Override
    public String getValue() {
        return ulid.nextULID();
    }
}
