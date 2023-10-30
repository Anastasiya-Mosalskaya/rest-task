package org.epam.resttask.dto.request;

public class SubscriptionRequestDto {

    private final Long id;
    private final Long userId;
    private final String startDate;

    public SubscriptionRequestDto(Long id, Long userId, String startDate) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStartDate() {
        return startDate;
    }
}
