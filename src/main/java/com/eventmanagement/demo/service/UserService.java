package com.eventmanagement.demo.service;

import com.eventmanagement.demo.entity.Users;
import com.eventmanagement.demo.enums.Role;
import com.eventmanagement.demo.repository.UserRepository;
import com.eventmanagement.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JWTUtils jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }
    public String verify(Users user){
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                        (user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }



//    public Users signUp(Users user){
//        if(userRepository.findByName(user.getName()).isPresent()){
//          throw new RuntimeException("ALready exists");
//        }
//        user.setRole(Role.Role_USER);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//   public String login(String email, String password){
//       Optional<Users> userOptional = userRepository.findByEmail(email);
//       Users user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
//       if (!passwordEncoder.matches(password, user.getPassword())) {
//           throw new RuntimeException("Invalid credentials");
//       }
//       return jwtUtils.generateToken(user.getEmail());
//
//
//   }

//  public  Users getUserById(Long userId){
//      Users user = userRepo.findById(userId)
//              .orElseThrow(()->new RuntimeException("user noit fount"));
//      return user;
//
//  }
//
//    public Users makeAdmin(Long userId){
//        Users user= userRepo.findById(userId).orElseThrow(()->new RuntimeException("usernot found"));
//        user.setRole(Role.Role_ADMIN);
//        return userRepo.save(user);
//
//    }

//    Users signUp(Users user);
//    Users login(String email, String password);
//    Users getUserById(Long userId);
//    Users makeAdmin(Long userId);
}
