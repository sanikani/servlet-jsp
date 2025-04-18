package com.nhnacademy.di;

import com.nhnacademy.reflection.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    public User findByName(String userName) {
        return users.stream()
                .filter(u -> u.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void save(User user) {
        users.add(user);
    }
}
