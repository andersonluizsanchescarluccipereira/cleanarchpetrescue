package br.com.giftcard;

import br.com.shared.AggregateRoot;
import br.com.shared.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;

public class GiftCard extends AggregateRoot<GiftCardID> implements Cloneable {
    private final String redemptionToken;
    private final String redemptionCode;
    private final BigDecimal balance;
    private final Instant emissionDate;
    private final Instant expiringDate;
    private final String currencyCode;
    private final Transactions transactions;

    private GiftCard(
            final GiftCardID anId,
            final String redemptionToken,
            final String redemptionCode,
            final BigDecimal balance,
            final Instant emissionDate,
            final Instant expiringDate,
            final String currencyCode,
            final Transactions transactions
    ) {
        super(anId);
        this.redemptionToken = redemptionToken;
        this.redemptionCode = redemptionCode;
        this.balance = balance;
        this.emissionDate = emissionDate;
        this.expiringDate = expiringDate;
        this.currencyCode = currencyCode;
        this.transactions = transactions;
    }

    public static GiftCard newCard(
            final String redemptionToken,
            final String redemptionCode,
            final BigDecimal balance,
            final Instant emissionDate,
            final Instant expiringDate,
            final String currencyCode,
            final Transactions transactions
    ) {
        final var id = GiftCardID.unique();
        return new GiftCard(id, redemptionToken, redemptionCode, balance, emissionDate, expiringDate, currencyCode, transactions);
    }

    public static GiftCard with(
            final GiftCardID id,
            final String redemptionToken,
            final String redemptionCode,
            final BigDecimal balance,
            final Instant emissionDate,
            final Instant expiringDate,
            final String currencyCode,
            final Transactions transactions
    ) {
        return new GiftCard(id, redemptionToken, redemptionCode, balance, emissionDate, expiringDate, currencyCode, transactions);
    }

    public static GiftCard with(final GiftCard card) {
        return with(
                card.getId(),
                card.redemptionToken,
                card.redemptionCode,
                card.balance,
                card.emissionDate,
                card.expiringDate,
                card.currencyCode,
                card.transactions
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new GiftCardValidator(this, handler).validate();
    }

    public String getRedemptionToken() {
        return redemptionToken;
    }

    public String getRedemptionCode() {
        return redemptionCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Instant getEmissionDate() {
        return emissionDate;
    }

    public Instant getExpiringDate() {
        return expiringDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    @Override
    public GiftCard clone() {
        try {
            return (GiftCard) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
