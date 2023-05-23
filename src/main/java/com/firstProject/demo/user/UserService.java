//OK
package com.firstProject.demo.user;

import com.firstProject.demo.exception.UserAlreadyExistsException;
import com.firstProject.demo.registration.RegistrationRequest;
import com.firstProject.demo.registration.token.VerificationToken;
import com.firstProject.demo.registration.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final VerificationTokenRepository tokenRepository;
  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  //유저 생성
  @Override
  public User registerUser(RegistrationRequest request) {
    //id를 기준으로 모든 유저들을 찾는다.
    Optional<User> user = this.findById(request.id());
    //같은 아이디가 있는 경우
    if(user.isPresent()){
      throw new UserAlreadyExistsException(
              "User with ID " +  request.id() + " already exists");
    }
    var newUser = new User();
    newUser.setId(request.id());
    newUser.setPassword(passwordEncoder.encode(request.password()));
    newUser.setRole(request.role());
    return userRepository.save(newUser);
  }

  @Override
  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  @Override
  public void saveUserVerificationToken(User theUser, String token) {
    var verificationToken = new VerificationToken(token, theUser);
    tokenRepository.save(verificationToken);
  }

  @Override
  public String validateToken(String theToken) {
    VerificationToken token = tokenRepository.findByToken(theToken);
    if(token == null){
      return "Invalid verification token";
    }
    User user = token.getUser();
    Calendar calendar = Calendar.getInstance();
    if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
      tokenRepository.delete(token);
      return "Token already Expired";
    }
    user.setEnabled(true);
    userRepository.save(user);
    return "vaild";
  }
}
