package com.furniturestorerestore.service;

import com.furniturestorerestore.dto.UserDto;
import com.furniturestorerestore.repository.*;
import com.furniturestorerestore.repository.entity.*;
import com.furniturestorerestore.repository.entity.enums.Role;
import com.furniturestorerestore.dto.request.RegisterRequest;
import com.furniturestorerestore.dto.response.RegisterResponse;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private StateRepository stateRepository;
    private MunicipalityRepository municipalityRepository;
    private ZipCodeRepository zipCodeRepository;
    private NeighborhoodRepository neighborhoodRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, StateRepository stateRepository,
                       MunicipalityRepository municipalityRepository, ZipCodeRepository zipCodeRepository,
                       NeighborhoodRepository neighborhoodRepository, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.stateRepository = stateRepository;
        this.municipalityRepository = municipalityRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.neighborhoodRepository = neighborhoodRepository;
        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
    }

    public RegisterResponse registerUser(RegisterRequest newUser) {
        // Verify the email isn´t registered yet.
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new DataIntegrityViolationException("This email address is already in use");
        }
        // Logic for the address
        State state = stateRepository.findByName(newUser.getState()).orElseGet(()->
                stateRepository.save(
                        State.builder()
                                .name(newUser.getState())
                                .build()
                )
        );

        Municipality municipality = municipalityRepository.findByName(newUser.getMunicipality()).orElseGet(()->
                municipalityRepository.save(
                        Municipality.builder()
                                .name(newUser.getMunicipality())
                                .state(state)
                                .build()
                )
        );

        ZipCode zipCode = zipCodeRepository.findByZipCode(newUser.getZipCode()).orElseGet(()->
                zipCodeRepository.save(
                        ZipCode.builder()
                                .zipCode(newUser.getZipCode())
                                .municipality(municipality)
                                .build()
                )
        );

        Neighborhood neighborhood = neighborhoodRepository.findByName(newUser.getNeighborhood()).orElseGet(()->
                neighborhoodRepository.save(
                        Neighborhood.builder()
                                .name(newUser.getNeighborhood())
                                .zipCode(zipCode)
                                .build()
                )
        );

        // Verifying there´s no a double register in the database.
        Address address = addressRepository.findAddressByAllData(newUser.getStreet(), newUser.getHouseNumber(), state,
                municipality, zipCode, neighborhood).orElseGet(()->
                addressRepository.save(
                        Address.builder()
                                .street(newUser.getStreet())
                                .houseNumber(newUser.getHouseNumber())
                                .state(state)
                                .municipality(municipality)
                                .zipCode(zipCode)
                                .neighborhood(neighborhood)
                                .build()
                )
        );

        // Creating the user and saving it on the database.
        MyUser user = MyUser.builder()
                .name(newUser.getName())
                .paternalSurname(newUser.getPaternalSurname())
                .maternalSurname(newUser.getMaternalSurname())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(Role.USER)
                .address(address)
                .build();

        // Save the instance on the database
        user = userRepository.save(user);

        // Build and return the response
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .paternalSurname(user.getPaternalSurname())
                .maternalSurname(user.getMaternalSurname())
                .email(user.getEmail())
                .build();
    }

    public List<UserDto> getAllUsers() {
        /*
        * Method to get all the users using the dto class.
        * */
        List<MyUser> users = userRepository.findAll();

        // We return the new list where we applied toDto() method to every object.
        return users.stream().map(UserDto::toDto).toList();
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(UserDto::toDto);
    }
}
