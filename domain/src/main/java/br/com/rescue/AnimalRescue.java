package br.com.rescue;

import br.com.rescue.enums.HealthStatus;
import br.com.rescue.enums.RescueStatus;
import br.com.shared.AggregateRoot;
import br.com.shared.validation.ValidationHandler;

import java.time.Instant;
import java.util.List;

public class AnimalRescue extends AggregateRoot<AnimalRescueID> implements Cloneable {

    private final String name;
    private final Instant rescueDate;
    private final Location location;
    private final Rescuer rescuer;
    private final HealthStatus healthStatus;
    private final List<String> photoUrls;
    private RescueStatus status;

    private AnimalRescue(
            final AnimalRescueID id,
            final String name,
            final Instant rescueDate,
            final Location location,
            final Rescuer rescuer,
            final HealthStatus healthStatus,
            final RescueStatus status,
            final List<String> photoUrls
    ) {
        super(id);
        this.name = name;
        this.rescueDate = rescueDate;
        this.location = location;
        this.rescuer = rescuer;
        this.healthStatus = healthStatus;
        this.status = status;
        this.photoUrls = photoUrls;
    }

    public static AnimalRescue newRescue(
            final String name,
            final Location location,
            final Rescuer rescuer,
            final HealthStatus healthStatus,
            final List<String> photoUrls
    ) {
        final var id = AnimalRescueID.unique();
        return new AnimalRescue(id, name, Instant.now(), location, rescuer, healthStatus, RescueStatus.RESCUED, photoUrls);
    }

    public static AnimalRescue with(
            final AnimalRescueID id,
            final String name,
            final Instant rescueDate,
            final Location location,
            final Rescuer rescuer,
            final HealthStatus healthStatus,
            final RescueStatus status,
            final List<String> photoUrls
    ) {
        return new AnimalRescue(id, name, rescueDate, location, rescuer, healthStatus, status, photoUrls);
    }

    public static AnimalRescue with(final AnimalRescue animalRescue) {
        return with(
                animalRescue.getId(),
                animalRescue.name,
                animalRescue.rescueDate,
                animalRescue.location,
                animalRescue.rescuer,
                animalRescue.healthStatus,
                animalRescue.status,
                animalRescue.photoUrls
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new AnimalRescueValidator(this, handler).validate();
    }

    public void updateStatus(final RescueStatus newStatus) {
        this.status = newStatus;
    }

    public String getName() {
        return name;
    }

    public Instant getRescueDate() {
        return rescueDate;
    }

    public Location getLocation() {
        return location;
    }

    public Rescuer getRescuer() {
        return rescuer;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public RescueStatus getStatus() {
        return status;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    @Override
    public AnimalRescue clone() {
        try {
            return (AnimalRescue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
