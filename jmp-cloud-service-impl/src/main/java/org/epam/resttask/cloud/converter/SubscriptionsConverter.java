package org.epam.resttask.cloud.converter;

import org.epam.resttask.dto.Subscription;
import org.epam.resttask.dto.request.SubscriptionRequestDto;
import org.epam.resttask.dto.response.SubscriptionResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionsConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Subscription convertToSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);
        subscription.setStartDate(LocalDate.parse(subscriptionRequestDto.getStartDate()));
        return subscription;
    }

    public SubscriptionResponseDto convertToResponseDto(Subscription subscription) {
        SubscriptionResponseDto subscriptionResponseDto = modelMapper.map(subscription, SubscriptionResponseDto.class);
        subscriptionResponseDto.setUserId(subscription.getUser().getId());
        return modelMapper.map(subscription, SubscriptionResponseDto.class);
    }
}
