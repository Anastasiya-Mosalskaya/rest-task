package org.epam.resttask.dto.response;

import org.springframework.hateoas.RepresentationModel;

public class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {

    private Long id;
    private Long userId;
    private String startDate;

    public SubscriptionResponseDto() {
    }

    public SubscriptionResponseDto(Long id, Long userId, String startDate) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
