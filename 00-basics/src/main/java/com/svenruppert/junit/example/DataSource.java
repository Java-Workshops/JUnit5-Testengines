package com.svenruppert.junit.example;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toConcurrentMap;

public class DataSource {

  private final AtomicLong idGenerator = new AtomicLong(0);
  private final Map<Long, User> persistenceStore
      = Stream.of(
          new User(idGenerator.incrementAndGet(), "admin", "admin", "Mr Admin"),
          new User(idGenerator.incrementAndGet(), "user", "user", "Mr User"))
      .collect(toConcurrentMap(User::id, u -> u));

  public User load(Long id) {
    return persistenceStore.get(id);
  }

  public void addUser(String login, String passwd, String name) {
    final User u = new User(idGenerator.incrementAndGet(), login, passwd, name);
    persistenceStore.put(u.id(), u);
  }

  public void deleteUser(User u) {
    persistenceStore.remove(u.id());
  }

  public Stream<User> queryForLogin(String login, String password) {
    return persistenceStore.values()
        .stream()
        .filter(u -> u.login().equals(login))
        .filter(u -> u.password().equals(password));
  }

  public record User(Long id, String login, String password, String name) {
  }

}
