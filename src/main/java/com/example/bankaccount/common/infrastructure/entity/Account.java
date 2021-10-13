package com.example.bankaccount.common.infrastructure.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String iban;

    @OneToMany
    private List<Operation> operations;

    public Account() {

    }

    public Account(String firstName, String lastName, String iban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.iban = iban;
    }

    public Account(int id, List<Operation> operations) {
        this.id = id;
        this.operations = operations;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIban() {
        return iban;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
