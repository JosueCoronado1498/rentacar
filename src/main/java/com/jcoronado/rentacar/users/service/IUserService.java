package com.jcoronado.rentacar.users.service;

import com.jcoronado.rentacar.users.model.User;

public interface IUserService {

    public User createUser(User user);

    public User findById(Long id);

    public User findByEmail(String email);

}
