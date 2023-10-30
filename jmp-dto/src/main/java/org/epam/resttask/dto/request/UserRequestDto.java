package org.epam.resttask.dto.request;

public class UserRequestDto {

    private final Long id;
    private final String name;
    private final String surname;
    private final String birthday;

    public UserRequestDto(Long id, String name, String surname, String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }
}
