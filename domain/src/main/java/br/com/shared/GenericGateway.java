package br.com.shared;

import br.com.shared.pagination.Pagination;
import br.com.shared.pagination.SearchQuery;

import java.util.Optional;

public interface GenericGateway<T, ID> {

    T create(T entity);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    T update(T entity);

    Pagination<T> findAll(SearchQuery query);
}
