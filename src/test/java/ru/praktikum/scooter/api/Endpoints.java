package ru.praktikum.scooter.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
    public static final String BASE_URI = "praktikum-services.ru";
    public static final String COURIER = "/api/v1/courier";
    public static final String COURIER_LOGIN = "/api/v1/courier/login";
    public static final String COURIER_DELETE = "/api/v1/courier/";
    public static final String ORDERS = "/api/v1/orders";
    public static final String ORDERS_CANCEL = "/api/v1/orders/cancel";

    public static final RequestSpecification REQ_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();
}
