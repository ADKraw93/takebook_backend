package com.project.takebook.facade;

import lombok.Getter;

@Getter
public enum SingletonForFacades {
    GET_ALL_USERS("UserController", "getAllUsers"),
    GET_USER("UserController", "getUser"),
    DELETE_USER("UserController", "deleteUser"),
    UPDATE_USER("UserController", "updateUser"),
    CREATE_USER("UserController", "createUser"),
    GET_ALL_RENTS("RentController", "getAllRents"),
    GET_RENT("RentController", "getRent"),
    DELETE_RENT("RentController", "deleteRent"),
    UPDATE_RENT("RentController", "updateRent"),
    CREATE_RENT("RentController", "createRent"),
    GET_ALL_BOOKS("BookController", "getAllBooks"),
    GET_BOOK("BookController", "getBook"),
    DELETE_BOOK("BookController", "deleteBook"),
    UPDATE_BOOK("BookController", "updateBook"),
    CREATE_BOOK("BookController", "createBook"),
    SEARCH_BOOKS("BNController", "searchBooks"),
    CREATE_BOOK_FROM_BIBS("BNController", "createBookFromBibs");

    private String controller;
    private String method;

    SingletonForFacades(String controller, String method) {
        this.controller = controller;
        this.method = method;
    }
}
