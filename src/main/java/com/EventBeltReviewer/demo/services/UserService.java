package com.EventBeltReviewer.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.EventBeltReviewer.demo.models.User;
import com.EventBeltReviewer.demo.repositories.UserRepository;


@Service
public class UserService {
	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<?> all() {
		return this.repository.findAll();
	}
	
	public User create(User newEntity) {
		return this.repository.save(newEntity);
	}
	
	public User findById(Long id) {
		Optional<User> optional = this.repository.findById(id);
		if( optional.isPresent() ) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void save(User newEntity) {
		this.repository.save(newEntity);
	}
	
	public boolean update(Long id, User entityUpdates) {
		
		User entity = this.findById(id);
		
		if( entity != null ) {
//			entity.setTitle(entityUpdates.getTitle());
//			entity.setDescription(entityUpdates.getDescription());
//			entity.setLanguage(entityUpdates.getLanguage());
//			entity.setNumberOfPages(entityUpdates.getNumberOfPages());
			this.save(entity);
			return true;
		}
		
		return false;
	}
	
	public void delete(User entity) {
		this.repository.delete(entity);
	}
}
