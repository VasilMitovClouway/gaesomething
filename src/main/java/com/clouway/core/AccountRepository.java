package com.clouway.core;

import com.google.common.base.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface AccountRepository {

  String register(User user);

  Optional<User> find(String userName);
}
