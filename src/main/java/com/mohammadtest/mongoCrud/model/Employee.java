package com.mohammadtest.mongoCrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String id;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String birthdate;
}
