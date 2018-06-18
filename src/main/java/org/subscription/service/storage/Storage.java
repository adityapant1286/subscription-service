package org.subscription.service.storage;

import org.subscription.entities.Subscription;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface Storage {

    int size();

    Subscription store(String username, Subscription subscription);

    Map<UUID, Subscription> getAll(String username);

    Optional<Subscription> getById(String username, UUID subscriptionId);
}
