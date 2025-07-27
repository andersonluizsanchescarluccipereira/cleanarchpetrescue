package br.com.shared.exceptions;

import br.com.shared.AggregateRoot;
import br.com.shared.Identifier;
import br.com.shared.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {


    protected NotFoundException(String aMessage, List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, Collections.emptyList());
    }
}