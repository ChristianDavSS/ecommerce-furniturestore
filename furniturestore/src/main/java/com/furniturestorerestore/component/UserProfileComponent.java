package com.furniturestorerestore.component;

import com.furniturestorerestore.repository.*;
import com.furniturestorerestore.repository.entity.*;
import org.springframework.stereotype.Component;

@Component
public class UserProfileComponent {
    private final StateRepository stateRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final AddressRepository addressRepository;

    public UserProfileComponent(StateRepository stateRepository, MunicipalityRepository municipalityRepository,
                                ZipCodeRepository zipCodeRepository, NeighborhoodRepository neighborhoodRepository,
                                PhoneNumberRepository phoneNumberRepository, AddressRepository addressRepository) {
        this.stateRepository = stateRepository;
        this.municipalityRepository = municipalityRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.neighborhoodRepository = neighborhoodRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.addressRepository = addressRepository;
    }

    public State getOrCreateState(String name){
        return stateRepository.findByName(name).orElseGet(()->
                stateRepository.save(
                        State.builder()
                                .name(name)
                                .build()
                )
        );
    }

    public Municipality getOrCreateMunicipality(String name, State state){
        return municipalityRepository.findByName(name).orElseGet(()->
                municipalityRepository.save(
                        Municipality.builder()
                                .name(name)
                                .state(state)
                                .build()
                )
        );
    }

    public ZipCode getOrCreateZipCode(String name, Municipality municipality) {
        return zipCodeRepository.findByZipCode(name).orElseGet(()->
                zipCodeRepository.save(
                        ZipCode.builder()
                                .zipCode(name)
                                .municipality(municipality)
                                .build()
                )
        );
    }

    public Neighborhood getOrCreateNeighborhood(String name, ZipCode zipCode){
        return neighborhoodRepository.findByName(name).orElseGet(()->
                neighborhoodRepository.save(
                        Neighborhood.builder()
                                .name(name)
                                .zipCode(zipCode)
                                .build()
                )
        );
    }

    public PhoneNumber getOrCreatePhoneNumber(String phone){
        /*
        * Method to get a phone number by the phone or to create it.
        * */
        return phoneNumberRepository.findByPhoneNumber(phone).orElseGet(()->
                phoneNumberRepository.save(
                        PhoneNumber.builder()
                                .phoneNumber(phone)
                                .build()
                )
        );
    }

    public Address getOrCreateAddress(String street, String houseNumber, State state, Municipality municipality,
                                      ZipCode zipCode, Neighborhood neighborhood){
        /*
        * Method to get an address by it´s whole data or to create it.
        * */
        return addressRepository.findAddressByAllData(street, houseNumber, state,
                municipality, zipCode, neighborhood).orElseGet(()->
                addressRepository.save(
                        Address.builder()
                                .street(street)
                                .houseNumber(houseNumber)
                                .state(state)
                                .municipality(municipality)
                                .zipCode(zipCode)
                                .neighborhood(neighborhood)
                                .build()
                )
        );
    }
}
