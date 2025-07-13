package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = """
            SELECT ad FROM Address ad WHERE ad.street = :street AND ad.houseNumber = :houseNumber
            AND ad.state = :state AND ad.municipality = :municipality AND ad.zipCode = :zipCode
            AND ad.neighborhood = :neighborhood
            """)
    Optional<Address> findAddressByAllData(@Param("street") String street, @Param("houseNumber") String houseNumber,
                                           @Param("state") State state, @Param("municipality") Municipality municipality,
                                           @Param("zipCode") ZipCode zipCode, @Param("neighborhood") Neighborhood neighborhood);
}
