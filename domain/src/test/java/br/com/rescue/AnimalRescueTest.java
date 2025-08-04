package br.com.rescue;

import br.com.shared.exceptions.DomainException;
import br.com.rescue.enums.HealthStatus;
import br.com.rescue.enums.RescueStatus;
import br.com.shared.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static br.com.rescue.enums.HealthStatus.HEALTHY;


public class AnimalRescueTest {
    private final Location validLocation = new Location("Rua A", "123", "São Paulo", "SP", "01234-567");
    private final Rescuer validRescuer = new Rescuer("João", "ONG Animal Livre");
    private final List<String> validPhotos = List.of("https://example.com/photo.jpg");

    @Test
    public void givenValidData_whenCreateNewRescue_shouldInstantiateSuccessfully() {
        final var rescue = AnimalRescue.newRescue(
                "Cachorro ferido",
                validLocation,
                validRescuer,
                HEALTHY,
                List.of("https://example.com/photo1.jpg")
        );

        Assertions.assertNotNull(rescue);
        Assertions.assertEquals("Cachorro ferido", rescue.getName());
        Assertions.assertEquals(HEALTHY, rescue.getHealthStatus());
        Assertions.assertEquals(RescueStatus.RESCUED, rescue.getStatus());
        Assertions.assertEquals(validLocation, rescue.getLocation());
        Assertions.assertEquals(validRescuer, rescue.getRescuer());
        Assertions.assertEquals(1, rescue.getPhotoUrls().size());
    }

    @Test
    public void givenBlankName_whenValidate_shouldReturnNotificationException() {
        final var expectedName = "   ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualRescue = AnimalRescue.newRescue(
                expectedName,
                validLocation,
                validRescuer,
                HEALTHY,
                validPhotos
        );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualRescue.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
    @Test
    public void givenNullName_whenValidate_shouldReturnNotificationException() {
        final String expectedName = null;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualRescue = AnimalRescue.newRescue(
                expectedName,
                validLocation,
                validRescuer,
                HEALTHY,
                validPhotos
        );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualRescue.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
    @Test
    public void givenNullRescueDate_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.with(
                AnimalRescueID.unique(),
                "Valid name",
                null,
                validLocation,
                validRescuer,
                HEALTHY,
                RescueStatus.RESCUED,
                validPhotos
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'rescueDate' should not be null", exception.getErrors().get(0).message());
    }
    @Test
    public void givenNullLocation_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.newRescue(
                "Valid name",
                null,
                validRescuer,
                HEALTHY,
                validPhotos
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'location' should not be null", exception.getErrors().get(0).message());
    }
    @Test
    public void givenNullRescuer_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.newRescue(
                "Valid name",
                validLocation,
                null,
                HEALTHY,
                validPhotos
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'rescuer' should not be null", exception.getErrors().get(0).message());
    }
    @Test
    public void givenNullHealthStatus_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.with(
                AnimalRescueID.unique(),
                "Valid name",
                Instant.now(),
                validLocation,
                validRescuer,
                null,
                RescueStatus.RESCUED,
                validPhotos
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'healthStatus' should not be null", exception.getErrors().get(0).message());
    }
    @Test
    public void givenNullRescueStatus_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.with(
                AnimalRescueID.unique(),
                "Valid name",
                Instant.now(),
                validLocation,
                validRescuer,
                HEALTHY,
                null,
                validPhotos
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'status' should not be null", exception.getErrors().get(0).message());
    }
    @Test
    public void givenNullPhotoUrls_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.newRescue(
                "Valid name",
                validLocation,
                validRescuer,
                HEALTHY,
                null
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'photoUrls' should not be null", exception.getErrors().get(0).message());
    }
    @Test
    public void givenEmptyPhotoUrls_whenValidate_shouldReturnNotificationException() {
        final var actual = AnimalRescue.newRescue(
                "Valid name",
                validLocation,
                validRescuer,
                HEALTHY,
                List.of()
        );

        final var exception = Assertions.assertThrows(DomainException.class, () -> {
            actual.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(1, exception.getErrors().size());
        Assertions.assertEquals("'photoUrls' should not be empty", exception.getErrors().get(0).message());
    }
    @Test
    public void givenInvalidPhotoIndexToMoveDown_whenValidate_shouldReturnDomainException() {
        final var expectedName = "Rescue Animal";
        final var invalidIndex = 5; // maior que o tamanho da lista
        final var expectedErrorMessage = "'photoUrls' index 5 is out of bounds";

        final var photos = List.of("photo1.jpg", "photo2.jpg"); // size = 2

        final var actualRescue = AnimalRescue.newRescue(
                expectedName,
                validLocation,
                validRescuer,
                HEALTHY,
                photos
        );

        // Simula um método interno ou público que move e valida o índice
        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualRescue.movePhotoDown(photos,invalidIndex);
        });

        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
    @Test
    public void givenNameWithMoreThan255Characters_whenValidate_shouldReturnNotificationException() {
        final var longName = "a".repeat(256);

        final var actualRescue = AnimalRescue.newRescue(
                longName,
                validLocation,
                validRescuer,
                HEALTHY,
                validPhotos
        );

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualRescue.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("'name' must be between 1 and 255 characters", actualException.getErrors().get(0).message());
    }
}