open module jmp.dto {
    requires spring.hateoas;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    exports org.epam.resttask.dto;
    exports org.epam.resttask.dto.request;
    exports org.epam.resttask.dto.response;
}