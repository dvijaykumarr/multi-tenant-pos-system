package com.tap.service.impl;

import com.tap.configuration.JwtProvider;
import com.tap.domain.UserRole;
import com.tap.exceptions.UserException;
import com.tap.mapper.UserMapper;
import com.tap.modal.User;
import com.tap.payload.dto.UserDto;
import com.tap.payload.response.AuthResponse;
import com.tap.repository.UserRepository;
import com.tap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user != null){
            throw new UserException("email is already registered !");
        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("role admin is not allowed !");
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setRole(userDto.getRole());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        // FIXED: added role as authority so JWT contains the role
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDto.getEmail(),
                userDto.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userDto.getRole().toString()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.genrateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully!");
        authResponse.setUser(UserMapper.toDto(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {

        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.genrateToken(authentication);

        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully!");
        authResponse.setUser(UserMapper.toDto(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);

        if(userDetails == null){
            throw new UserException("email id does not exist" + email);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new UserException("Password doesn't match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}


//package com.tap.service.impl;
//
//import com.tap.configuration.JwtProvider;
//import com.tap.domain.UserRole;
//import com.tap.exceptions.UserException;
//import com.tap.mapper.UserMapper;
//import com.tap.modal.User;
//import com.tap.payload.dto.UserDto;
//import com.tap.payload.response.AuthResponse;
//import com.tap.repository.UserRepository;
//import com.tap.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    //we don't need to annotate with @Autowired as we are using @RequiredArgsConstructor from lombok
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtProvider jwtProvider;
//    private final CustomUserImplementation customUserImplementation;
//
//    @Override
//    public AuthResponse signup(UserDto userDto) throws UserException {
//        User user = userRepository.findByEmail(userDto.getEmail());
//        if(user != null){
//            throw new UserException("email is already registered !");
//        }
//        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
//            throw  new UserException("role admin is not allowed !");
//        }
//
//        User newUser = new User();
//        newUser.setEmail(userDto.getEmail());
//        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        newUser.setFullName(userDto.getFullName());
//        newUser.setPhone(userDto.getPhone());
//        newUser.setRole(userDto.getRole());
//        newUser.setLastLogin(LocalDateTime.now());
//        newUser.setCreatedAt(LocalDateTime.now());
//
//        User savedUser = userRepository.save(newUser);
//
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtProvider.genrateToken(authentication);
//
//
////        Authentication authentication = authenticate(userDto.getEmail(), userDto.getPassword());
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        String jwt = jwtProvider.genrateToken(authentication);
//
//
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setJwt(jwt);
//        authResponse.setMessage("Registered Successfully!");
//        authResponse.setUser(UserMapper.toDto(savedUser));
//
//        return authResponse;
//    }
//
//    @Override
//    public AuthResponse login(UserDto userDto) throws UserException {
//
//        String email = userDto.getEmail();
//        String password = userDto.getPassword();
//        Authentication authentication = authenticate(email,password);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        String role = authorities.iterator().next().getAuthority();
//
//        String jwt = jwtProvider.genrateToken(authentication);
//
//        User user = userRepository.findByEmail(email);
//
//        user.setLastLogin(LocalDateTime.now());
//        userRepository.save(user);
//
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setJwt(jwt);
//        authResponse.setMessage("Login Successfully!");
//        authResponse.setUser(UserMapper.toDto(user));
//        return authResponse;
//    }
//
//    private Authentication authenticate(String email, String password) throws UserException {
//
//        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
//
//        if(userDetails == null){
//            throw  new UserException("email id does not exist" + email);
//        }
//
//        if(!passwordEncoder.matches(password, userDetails.getPassword())){
//            throw new UserException("Password doesn't match");
//        }
//
//
//
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
//}
