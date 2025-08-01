package br.com.rescue;

import br.com.shared.exceptions.DomainException;
import br.com.rescue.enums.HealthStatus;
import br.com.rescue.enums.RescueStatus;
import br.com.shared.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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


}