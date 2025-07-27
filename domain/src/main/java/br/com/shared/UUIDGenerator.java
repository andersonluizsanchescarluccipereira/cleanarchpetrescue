package br.com.shared;

import java.util.UUID;

public class UUIDGenerator extends Identifier {
    @Override
    public String getValue() {
        return UUID.randomUUID().toString();
    }
}
