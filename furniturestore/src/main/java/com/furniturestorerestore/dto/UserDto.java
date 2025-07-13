package com.furniturestorerestore.dto;

import com.furniturestorerestore.dto.response.StateDto;
import com.furniturestorerestore.repository.entity.*;
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

    private AddressDto address;

    // Method to convert from MyUser to UserDto, this way we can show whatever we want on the get endpoint.
    public static UserDto toDto(MyUser user){
        // Getting the address where we can get all the info from.
        Address address = user.getAddress();

        // Creating the object with all the values we need to show.
        AddressDto addressDto = AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .state(
                        StateDto.builder()
                                .id(address.getState().getId())
                                .name(address.getState().getName())
                                .build()
                )
                .municipality(
                        MunicipalityDto.builder()
                                .id(address.getMunicipality().getId())
                                .name(address.getMunicipality().getName())
                                .build()
                )
                .zipCode(
                        ZipCodeDto.builder()
                                .id(address.getZipCode().getId())
                                .zipCode(address.getZipCode().getZipCode())
                                .build()
                )
                .neighborhood(
                        NeighborhoodDto.builder()
                                .id(address.getNeighborhood().getId())
                                .name(address.getNeighborhood().getName())
                                .build()
                )
                .build();

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .paternalSurname(user.getPaternalSurname())
                .maternalSurname(user.getMaternalSurname())
                .email(user.getEmail())
                .role(user.getRole())
                .address(addressDto)
                .build();
    }
}
