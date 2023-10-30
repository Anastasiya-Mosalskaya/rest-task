package org.epam.resttask.cloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.epam.resttask.cloud.converter.SubscriptionsConverter;
import org.epam.resttask.controller.SubscriptionController;
import org.epam.resttask.dto.Subscription;
import org.epam.resttask.dto.request.SubscriptionRequestDto;
import org.epam.resttask.dto.response.SubscriptionResponseDto;
import org.epam.resttask.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionRestController implements SubscriptionController {

    @Autowired
    private SubscriptionsConverter subscriptionsConverter;
    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    @Operation(summary = "Create new subscription")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public HttpEntity<SubscriptionResponseDto> createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionsConverter.convertToSubscription(subscriptionRequestDto);
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return ResponseEntity.created(linkTo(methodOn(SubscriptionRestController.class)
                        .getSubscription(createdSubscription.getId())).toUri())
                .body(subscriptionsConverter.convertToResponseDto(createdSubscription));
    }

    @Override
    @Operation(summary = "Update subscription according to provided id")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public HttpEntity<SubscriptionResponseDto> updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionsConverter.convertToSubscription(subscriptionRequestDto);
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);
        return ResponseEntity.created(linkTo(methodOn(SubscriptionRestController.class)
                        .getSubscription(updatedSubscription.getId())).toUri())
                .body(subscriptionsConverter.convertToResponseDto(updatedSubscription));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a subscription by id")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSubscription(Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a subscription by id")
    @GetMapping("/{id}")
    public HttpEntity<SubscriptionResponseDto> getSubscription(Long id) {
        Subscription subscription = subscriptionService.getSubscription(id);
        SubscriptionResponseDto subscriptionResponseDto = subscriptionsConverter.convertToResponseDto(subscription);
        subscriptionResponseDto.add(linkTo(methodOn(SubscriptionRestController.class).getSubscription(id)).withSelfRel());
        subscriptionResponseDto.add(linkTo(methodOn(UserRestController.class).getUser(subscription.getUser().getId()))
                .withSelfRel());
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Return all subscriptions")
    @GetMapping
    public HttpEntity<List<SubscriptionResponseDto>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        List<SubscriptionResponseDto> responseDto = subscriptions.stream()
                .map(subscription -> {
                    SubscriptionResponseDto subscriptionResponseDto = subscriptionsConverter.convertToResponseDto(subscription);
                    subscriptionResponseDto.add(linkTo(methodOn(SubscriptionRestController.class)
                            .getSubscription(subscription.getId()))
                            .withSelfRel());
                    subscriptionResponseDto.add(linkTo(methodOn(UserRestController.class)
                            .getUser(subscription.getUser().getId()))
                            .withSelfRel());
                    return subscriptionResponseDto;
                }).toList();
        return ResponseEntity.ok(responseDto);
    }
}
