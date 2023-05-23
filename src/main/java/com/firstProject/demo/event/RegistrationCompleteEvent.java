//OK
package com.firstProject.demo.event;

import com.firstProject.demo.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Setter
@Getter
public class RegistrationCompleteEvent extends ApplicationEvent {
  private User user;
  private String applicationUrl;

  public RegistrationCompleteEvent(User user, String applicationUrl) {
    super(user);
    this.user = user;
    this.applicationUrl = applicationUrl;
  }
}

