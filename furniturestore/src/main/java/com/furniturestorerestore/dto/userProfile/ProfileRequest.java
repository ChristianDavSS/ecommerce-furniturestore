package com.furniturestorerestore.dto.userProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {
    private String name;
    private String paternalSurname;
    private String maternalSurname;

    private String phoneNumber;

    private String street;
    private String houseNumber;
    private String state;
    private String municipality;
    private String zipCode;
    private String neighborhood;
}
