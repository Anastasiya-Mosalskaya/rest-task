package org.epam.resttask.cloud.service;

import org.epam.resttask.cloud.jpa.SubscriptionRepository;
import org.epam.resttask.cloud.jpa.UserRepository;
import org.epam.resttask.dto.Subscription;
import org.epam.resttask.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final String USER_DOES_NOT_EXIST_MESSAGE = "User %s does not exist.";
    private static final String SUBSCRIPTION_DOES_NOT_EXIST_MESSAGE = "Subscription %s does not exist.";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        checkUserExists(subscription.getUser().getId());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        checkUserExists(subscription.getUser().getId());
        checkSubscriptionExists(subscription.getId());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        checkSubscriptionExists(id);
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException(String.format(SUBSCRIPTION_DOES_NOT_EXIST_MESSAGE, id)));
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }

    @Override
    public void deleteAllSubscriptionsByUserId(Long id) {
        List<Subscription> subscriptions = (List<Subscription>) subscriptionRepository.findAllByUserId(id);
        subscriptions.forEach(subscription -> deleteSubscription(subscription.getId()));
    }

    private void checkUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(USER_DOES_NOT_EXIST_MESSAGE, id));
        }
    }

    private void checkSubscriptionExists(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(SUBSCRIPTION_DOES_NOT_EXIST_MESSAGE, id));
        }
    }
}
