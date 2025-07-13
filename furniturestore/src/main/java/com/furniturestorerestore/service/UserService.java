package com.furniturestorerestore.service;

import com.furniturestorerestore.repository.*;
import com.furniturestorerestore.repository.entity.*;
import com.furniturestorerestore.repository.entity.enums.Role;
import com.furniturestorerestore.request.RegisterRequest;
import com.furniturestorerestore.response.RegisterResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private StateRepository stateRepository;
    private MunicipalityRepository municipalityRepository;
    private ZipCodeRepository zipCodeRepository;
    private NeighborhoodRepository neighborhoodRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository,StateRepository stateRepository,
                       MunicipalityRepository municipalityRepository, ZipCodeRepository zipCodeRepository,
                       NeighborhoodRepository neighborhoodRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.stateRepository = stateRepository;
        this.municipalityRepository = municipalityRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.neighborhoodRepository = neighborhoodRepository;
    }

    public RegisterResponse registerUser(RegisterRequest newUser) {
        // Verify the email isn´t registered yet.
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new DataIntegrityViolationException("This email address is already in use");
        }
        // Logic for the address
        State state = stateRepository.findByName(newUser.getState());
        if (state == null){
            state = State.builder()
                    .name(newUser.getState())
                    .build();
            stateRepository.save(state);
        }
        Municipality municipality = municipalityRepository.findByName(newUser.getMunicipality());
        if (municipality == null){
            municipality = Municipality.builder()
                    .name(newUser.getMunicipality())
                    .state(state)
                    .build();
            municipalityRepository.save(municipality);
        }
        ZipCode zipCode = zipCodeRepository.findByZipCode(newUser.getZipCode());
        if (zipCode == null){
            zipCode = ZipCode.builder()
                    .zipCode(newUser.getZipCode())
                    .municipality(municipality)
                    .build();
            zipCodeRepository.save(zipCode);
        }
        Neighborhood neighborhood = neighborhoodRepository.findByName(newUser.getNeighborhood());
        if (neighborhood == null){
            neighborhood = Neighborhood.builder()
                    .name(newUser.getNeighborhood())
                    .zipCode(zipCode)
                    .build();
            neighborhoodRepository.save(neighborhood);
        }

        // Verifying there´s no a double register in the database.
        Address address = addressRepository.findAddressByAllData(newUser.getStreet(), newUser.getHouseNumber(), state,
                municipality, zipCode, neighborhood);
        if (address == null){
            // We create the address object
            address = Address.builder()
                    .street(newUser.getStreet())
                    .houseNumber(newUser.getHouseNumber())
                    .state(state)
                    .municipality(municipality)
                    .zipCode(zipCode)
                    .neighborhood(neighborhood)
                    .build();
            addressRepository.save(address);
        }

        // Creating the user
        MyUser user = MyUser.builder()
                .name(newUser.getName())
                .paternalSurname(newUser.getPaternalSurname())
                .maternalSurname(newUser.getMaternalSurname())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(Role.USER)
                .address(address)
                .build();

        // Almacenamos las instancias en la BD
        userRepository.save(user);

        // Construimos la response
        return RegisterResponse.builder()
                .name(user.getName())
                .paternalSurname(user.getPaternalSurname())
                .maternalSurname(user.getMaternalSurname())
                .email(user.getEmail())
                .build();
    }

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }
}
