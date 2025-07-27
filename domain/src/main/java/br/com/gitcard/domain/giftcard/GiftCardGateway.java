package br.com.gitcard.domain.giftcard;

import br.com.shared.pagination.Pagination;
import br.com.shared.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface GiftCardGateway {
    GiftCard create(GiftCard aGiftCard);

    void deleteById(GiftCardID anId);

    Optional<GiftCard> findById(GiftCardID anId);

    GiftCard update(GiftCard aGiftCard);

    Pagination<GiftCard> findAll(SearchQuery aQuery);

    List<GiftCardID> existsByIds(Iterable<GiftCardID> ids);
}
