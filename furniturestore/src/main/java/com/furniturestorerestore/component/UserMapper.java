package com.furniturestorerestore.component;

import com.furniturestorerestore.dto.register.*;
import com.furniturestorerestore.repository.entity.Address;
import com.furniturestorerestore.repository.entity.MyUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    // Method to convert from MyUser to UserDto, this way we can show whatever we want on the get endpoint.
    public UserDto toDto(MyUser user){
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

        PhoneNumberDto phoneNumber = PhoneNumberDto.builder()
                .id(user.getPhoneNumber().getId())
                .phoneNumber(user.getPhoneNumber().getPhoneNumber())
                .build();

        // Returning the response as a UserDto object.
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .paternalSurname(user.getPaternalSurname())
                .maternalSurname(user.getMaternalSurname())
                .email(user.getEmail())
                .role(user.getRole())
                .phoneNumber(phoneNumber)
                .address(addressDto)
                .build();
    }
}
