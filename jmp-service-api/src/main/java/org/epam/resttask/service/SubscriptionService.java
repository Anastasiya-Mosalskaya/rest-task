package org.epam.resttask.service;

import org.epam.resttask.dto.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription createSubscription(Subscription subscription);
    Subscription updateSubscription(Subscription subscription);
    void deleteSubscription(Long id);
    void deleteAllSubscriptionsByUserId(Long id);
    Subscription getSubscription(Long id);
    List<Subscription> getAllSubscriptions();
}
