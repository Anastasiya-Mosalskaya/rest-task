package org.epam.resttask.controller;

import org.epam.resttask.dto.request.SubscriptionRequestDto;
import org.epam.resttask.dto.response.SubscriptionResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SubscriptionController {

    HttpEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto);
    HttpEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto);
    HttpEntity<?> deleteSubscription(@PathVariable Long id);
    HttpEntity<SubscriptionResponseDto> getSubscription(@PathVariable Long id);
    HttpEntity<List<SubscriptionResponseDto>> getAllSubscriptions();
}
