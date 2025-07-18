package com.furniturestorerestore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String email;
    private String password;

    private String phoneNumber;

    private String state;
    private String municipality;
    private String zipCode;
    private String neighborhood;
    private String street;
    private String houseNumber;
}
