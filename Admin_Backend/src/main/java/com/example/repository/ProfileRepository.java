package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.User;

public interface ProfileRepository extends CrudRepository<User, Integer> {

}
