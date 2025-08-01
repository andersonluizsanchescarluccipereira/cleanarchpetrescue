package br.com.rescue;

import br.com.rescue.enums.HealthStatus;
import br.com.rescue.enums.RescueStatus;
import br.com.shared.validation.Error;
import br.com.shared.validation.ValidationHandler;
import br.com.shared.validation.Validator;

import java.time.Instant;
import java.util.List;

public class AnimalRescueValidator extends Validator {

    private final AnimalRescue animalRescue;

    public static final int NAME_MIN_LENGTH = 1;
    public static final int NAME_MAX_LENGTH = 255;

    public AnimalRescueValidator(final AnimalRescue aAnimalrescue, final ValidationHandler handler) {
        super(handler);
        this.animalRescue = aAnimalrescue;
    }

    @Override
    public void validate() {
        checkConstraints();
    }

    private void checkConstraints() {
        final var name = animalRescue.getName();
        final int length = name.trim().length();
        final Instant rescueDate = animalRescue.getRescueDate();
        final HealthStatus healthStatuStatus = animalRescue.getHealthStatus();
        final RescueStatus status = animalRescue.getStatus();
        final List<String> photos = animalRescue.getPhotoUrls();

        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        if (rescueDate == null) {
            this.validationHandler().append(new Error("'rescueDate' should not be null"));
            return;
        }

        if (animalRescue.getLocation() == null) {
            this.validationHandler().append(new Error("'location' should not be null"));
            return;
        }

        if (animalRescue.getRescuer() == null) {
            this.validationHandler().append(new Error("'rescuer' should not be null"));
            return;
        }

        if (healthStatuStatus == null) {
            this.validationHandler().append(new Error("'healthStatus' should not be null"));
            return;
        }

        if (status == null) {
            this.validationHandler().append(new Error("'status' should not be null"));
            return;
        }

        if (photos == null) {
            this.validationHandler().append(new Error("'photoUrls' should not be null"));
            return;
        }

        if (photos.isEmpty()) {
            this.validationHandler().append(new Error("'photoUrls' should not be empty"));
            return;
        }

        for (String url : photos) {
            if (url == null || url.isBlank()) {
                this.validationHandler().append(new Error("All 'photoUrls' must be non-null and non-blank"));
                break;
            }
        }
        if (length < NAME_MIN_LENGTH || length > NAME_MAX_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 1 and 255 characters"));
        }
    }
}
