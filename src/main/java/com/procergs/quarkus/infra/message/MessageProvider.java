package com.procergs.quarkus.infra.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import lombok.extern.slf4j.Slf4j;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
public class MessageProvider {

  private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

  public String getMessage(String key, Object... args) {
    return MessageFormat.format(getMessage(key), args);
  }

  public String getMessage(String key) {
    try {
      return bundle.getString(key);
    } catch (MissingResourceException e) {
      log.error(String.format("Missing resource: %s", key), e);
      return "??" + key + "??";
    }
  }
}
