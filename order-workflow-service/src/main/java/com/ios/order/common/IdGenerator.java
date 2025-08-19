package com.ios.order.common;
import java.util.UUID;
public final class IdGenerator {
  private IdGenerator() {}
  public static String newId(String prefix) { return prefix + "-" + UUID.randomUUID(); }
}
