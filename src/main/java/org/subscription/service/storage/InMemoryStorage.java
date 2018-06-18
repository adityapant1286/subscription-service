package org.subscription.service.storage;

import org.springframework.stereotype.Service;
import org.subscription.entities.Subscription;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryStorage implements Storage {

    private static final Map<UUID, Map<UUID, Subscription>> store = new ConcurrentHashMap<>(0);

    @Override
    public int size() { return store.size(); }

    @Override
    public Subscription store(String username, Subscription subscription) {
        store.computeIfAbsent(UUID.nameUUIDFromBytes(username.getBytes()), m -> new ConcurrentHashMap<>())
                .put(subscription.getId(), subscription);

        return subscription;
    }

    @Override
    public Map<UUID, Subscription> getAll(String username) {
        return store.getOrDefault(UUID.nameUUIDFromBytes(username.getBytes()), new HashMap<>());
    }

    @Override
    public Optional<Subscription> getById(String username, UUID subscriptionId) {

        Map<UUID, Subscription> subscriptions = getAll(username);

        if (subscriptions.isEmpty())
            return Optional.empty();

        return Optional.of(subscriptions.get(subscriptionId));
    }
}
