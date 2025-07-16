package com.furniturestorerestore.service;

import com.furniturestorerestore.component.UserMapper;
import com.furniturestorerestore.component.UserProfileComponent;
import com.furniturestorerestore.dto.UserDto;
import com.furniturestorerestore.dto.request.*;
import com.furniturestorerestore.dto.request.ProfileRequest;
import com.furniturestorerestore.repository.*;
import com.furniturestorerestore.repository.entity.*;
import com.furniturestorerestore.repository.entity.enums.Role;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
* Logic to make the CRUD of Users with all the relationships.
* */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProfileComponent userProfileComponent;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserProfileComponent userProfileComponent){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userProfileComponent = userProfileComponent;
    }

    public UserDto registerUser(RegisterRequest newUser) {
        // Verify the email isn´t registered yet.
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new DataIntegrityViolationException("This email address is already in use");
        }
        // Logic for the address
        State state = userProfileComponent.getOrCreateState(newUser.getState());

        Municipality municipality = userProfileComponent.getOrCreateMunicipality(newUser.getMunicipality(), state);

        ZipCode zipCode = userProfileComponent.getOrCreateZipCode(newUser.getZipCode(), municipality);

        Neighborhood neighborhood = userProfileComponent.getOrCreateNeighborhood(newUser.getNeighborhood(), zipCode);


        PhoneNumber phoneNumber = userProfileComponent.getOrCreatePhoneNumber(newUser.getPhoneNumber());

        // Verifying there´s no a double register in the database.
        Address address = userProfileComponent.getOrCreateAddress(newUser.getStreet(), newUser.getHouseNumber(), state,
                municipality, zipCode, neighborhood);

        // Creating the user and saving it on the database.
        MyUser user = MyUser.builder()
                .name(newUser.getName())
                .paternalSurname(newUser.getPaternalSurname())
                .maternalSurname(newUser.getMaternalSurname())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(Role.USER)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();

        // Save the instance on the database
        user = userRepository.save(user);

        // Build and return the response
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        /*
        * Method to get all the users using the dto class.
        * */
        List<MyUser> users = userRepository.findAll();

        // We return the new list where we applied toDto() method to every object.
        return users.stream().map(userMapper::toDto).toList();
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUserProfile(Long id, ProfileRequest userRequest) {
        MyUser myUser = userRepository.findById(id).orElseThrow(()->
                new DataIntegrityViolationException("User not found")
        );
        // Obtain the data from the component
        State state = userProfileComponent.getOrCreateState(userRequest.getState());
        Municipality municipality = userProfileComponent.getOrCreateMunicipality(userRequest.getMunicipality(), state);
        ZipCode zipCode = userProfileComponent.getOrCreateZipCode(userRequest.getZipCode(), municipality);
        Neighborhood neighborhood = userProfileComponent.getOrCreateNeighborhood(userRequest.getNeighborhood(), zipCode);

        // Set all the new data
        myUser.setName(userRequest.getName());
        myUser.setPaternalSurname(userRequest.getPaternalSurname());
        myUser.setMaternalSurname(userRequest.getMaternalSurname());
        myUser.setPhoneNumber(
                userProfileComponent.getOrCreatePhoneNumber(
                        userRequest.getPhoneNumber()
                )
        );
        myUser.setAddress(
                userProfileComponent.getOrCreateAddress(
                        userRequest.getStreet(),
                        userRequest.getHouseNumber(),
                        state, municipality, zipCode, neighborhood
                )
        );

        // Return the user converted to his dto.
        return userMapper.toDto(userRepository.save(myUser));
    }
}
