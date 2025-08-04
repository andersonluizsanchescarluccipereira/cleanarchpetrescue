package br.com.giftcard;

import br.com.giftcard.GiftCard;
import br.com.giftcard.Transactions;
import br.com.shared.exceptions.DomainException;
import br.com.shared.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class GiftCardTest {

    @Test
    public void givenValidParams_whenCallNewGiftCard_shouldInstantiateGiftCard() {
        final var expectedToken = "32ScL57220Vapb8pc50HJ3mWH1cl1L8x";
        final var expectedCode = "***********ASDQ";
        final var expectedBalance = BigDecimal.ZERO;
        final var expectedEmissionDate = Instant.parse("2014-04-24T20:22:58.163Z");
        final var expectedExpiringDate = Instant.parse("2016-01-01T00:00:00Z");
        final var expectedCurrency = "USD";
        final var expectedHref = "cards/954/transactions";

        final var transactions = new Transactions(expectedHref);
        final var giftCard = GiftCard.newCard(
                expectedToken,
                expectedCode,
                expectedBalance,
                expectedEmissionDate,
                expectedExpiringDate,
                expectedCurrency,
                transactions
        );

        Assertions.assertNotNull(giftCard);
        Assertions.assertNotNull(giftCard.getId());
        Assertions.assertEquals(expectedToken, giftCard.getRedemptionToken());
        Assertions.assertEquals(expectedCode, giftCard.getRedemptionCode());
        Assertions.assertEquals(expectedBalance, giftCard.getBalance());
        Assertions.assertEquals(expectedEmissionDate, giftCard.getEmissionDate());
        Assertions.assertEquals(expectedExpiringDate, giftCard.getExpiringDate());
        Assertions.assertEquals(expectedCurrency, giftCard.getCurrencyCode());
        Assertions.assertEquals(expectedHref, giftCard.getTransactions().href());
    }

    @Test
    public void givenNullRedemptionToken_whenCallNewGiftCardAndValidate_shouldReturnNotificationException() {
        final String expectedToken = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'redemptionToken' should not be null or empty";

        final var actualGiftCard = GiftCard.newCard(
                expectedToken,
                "CODE123",
                BigDecimal.ONE,
                Instant.now(),
                Instant.now().plusSeconds(3600),
                "USD",
                new Transactions("href")
        );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualGiftCard.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}