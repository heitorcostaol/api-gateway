package com.procergs.quarkus.infra.message;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Bundle {

  private static MessageProvider messageProvider;

  private static MessageProvider getInstance() {
    if (Objects.isNull(messageProvider)) {
      messageProvider = new MessageProvider();
    }
    return messageProvider;
  }

  public static String get(String key) {
    return getInstance().getMessage(key);
  }

  public static String get(String key, Object... args) {
    return getInstance().getMessage(key, args);
  }
}
