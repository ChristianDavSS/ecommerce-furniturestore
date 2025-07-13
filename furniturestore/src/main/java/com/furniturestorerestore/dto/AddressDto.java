package com.furniturestorerestore.dto;

import com.furniturestorerestore.dto.response.StateDto;
import com.furniturestorerestore.repository.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private String street;
    private String houseNumber;

    private StateDto state;
    private MunicipalityDto municipality;
    private ZipCodeDto zipCode;
    private NeighborhoodDto neighborhood;
}
