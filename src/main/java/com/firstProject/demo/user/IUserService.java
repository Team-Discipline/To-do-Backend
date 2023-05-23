package com.firstProject.demo.user;

import com.firstProject.demo.registration.RegistrationRequest;
import com.firstProject.demo.registration.token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface IUserService {
  List<User> getUsers();
  User registerUser(RegistrationRequest request);
  Optional<User> findById(String id);
  void saveUserVerificationToken(User theUser, String verificationToken);
  String validateToken(String theToken);
}
