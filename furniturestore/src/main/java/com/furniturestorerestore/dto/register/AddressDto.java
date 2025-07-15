package com.furniturestorerestore.dto.register;

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
