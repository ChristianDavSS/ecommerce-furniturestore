package com.furniturestorerestore.dto;

import com.furniturestorerestore.repository.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String email;
    private Role role;

    private PhoneNumberDto phoneNumber;

    private AddressDto address;
}
