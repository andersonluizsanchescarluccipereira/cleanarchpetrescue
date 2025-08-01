package br.com.gitcard.domain.giftcard;

import br.com.shared.validation.Error;
import br.com.shared.validation.ValidationHandler;
import br.com.shared.validation.Validator;

import java.math.BigDecimal;

public class GiftCardValidator extends Validator {

    private final GiftCard card;

    public GiftCardValidator(final GiftCard aCard, final ValidationHandler aHandler) {
        super(aHandler);
        this.card = aCard;
    }

    @Override
    public void validate() {
        checkConstraints();
    }

    private void checkConstraints() {
        final var token = this.card.getRedemptionToken();
        final var code = this.card.getRedemptionCode();
        final var balance = this.card.getBalance();
        final var currencyCode = this.card.getCurrencyCode();
        final var tx = this.card.getTransactions();

        if (token == null || token.isBlank()) {
            this.validationHandler().append(new Error("'redemptionToken' should not be null or empty"));
            return;
        }

        if (code == null || code.isBlank()) {
            this.validationHandler().append(new Error("'redemptionCode' should not be null or empty"));
            return;
        }


        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            this.validationHandler().append(new Error("'balance' should not be negative"));
            return;
        }

        if (card.getEmissionDate().isAfter(card.getExpiringDate())) {
            this.validationHandler().append(new Error("'emissionDate' must be before 'expiringDate'"));
            return;
        }

        if (currencyCode == null || currencyCode.isBlank()) {
            this.validationHandler().append(new Error("'currencyCode' should not be null or empty"));
            return;
        }

        if (tx.href() == null || tx.href().isBlank()) {
            this.validationHandler().append(new Error("'transactions.href' should not be null or empty"));
        }
    }
}
