open module jmp.cloud.service.impl {
    requires jmp.dto;
    requires jmp.service.api;
    requires spring.beans;
    requires spring.context;
    requires jmp.service.rest;
    requires modelmapper;
    requires spring.web;
    requires spring.hateoas;
    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires java.sql;
    requires spring.aop;
    requires spring.data.commons;
    requires io.swagger.v3.oas.annotations;
    exports org.epam.resttask.cloud;
    exports org.epam.resttask.cloud.service;
    exports org.epam.resttask.cloud.controller;
    exports org.epam.resttask.cloud.converter;
    exports org.epam.resttask.cloud.jpa;
}