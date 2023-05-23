package com.firstProject.demo.security;

import com.firstProject.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterationDetailsService implements UserDetailsService {
  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    return userRepository.findById(id)
            .map(UserRegistrationDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
