package com.EventBeltReviewer.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.EventBeltReviewer.demo.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAll();
}
