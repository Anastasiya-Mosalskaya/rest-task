package org.epam.resttask.cloud.jpa;

import org.epam.resttask.dto.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    Iterable<Subscription> findAllByUserId(Long id);
}
