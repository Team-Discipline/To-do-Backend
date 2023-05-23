package com.firstProject.demo.registration;

import com.firstProject.demo.event.RegistrationCompleteEvent;
import com.firstProject.demo.registration.token.VerificationToken;
import com.firstProject.demo.registration.token.VerificationTokenRepository;
import com.firstProject.demo.user.User;
import com.firstProject.demo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
  private final UserService userService;
  private final ApplicationEventPublisher publisher;
  private final VerificationTokenRepository tokenRepository;

  @PostMapping
  public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
    User user = userService.registerUser(registrationRequest);
    //publish registration event
    publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
    return "Success";
  }

  public String applicationUrl(HttpServletRequest request){
    return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
  }
}
