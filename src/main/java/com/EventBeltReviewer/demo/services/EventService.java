package com.EventBeltReviewer.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.EventBeltReviewer.demo.models.Event;
import com.EventBeltReviewer.demo.repositories.EventRepository;



@Service
public class EventService {
	private final EventRepository repository;
	
	public EventService(EventRepository repository) {
		this.repository = repository;
	}
	
	public List<?> all() {
		return this.repository.findAll();
	}
	
	public List<?> eventsInState(String state){
		
		return this.repository.findByState(state);
	}
	
	public List<?> eventsNotInState(String state){
		return this.repository.findByNotInState(state);
	}
	
	public Event create(Event newEntity) {
		return this.repository.save(newEntity);
	}
	
	public Event findById(Long id) {
		Optional<Event> optional = this.repository.findById(id);
		if( optional.isPresent() ) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void save(Event newEntity) {
		this.repository.save(newEntity);
	}
	
	public boolean update(Long id, Event entityUpdates) {
		
		Event entity = this.findById(id);
		
		if( entity != null ) {
			entity.setHost(entityUpdates.getHost());
			entity.setName(entityUpdates.getName());
			entity.setDate(entityUpdates.getDate());
			entity.setCity(entityUpdates.getCity());
			entity.setState(entityUpdates.getState());
			this.save(entity);
			return true;
		}
		
		return false;
	}
	
	public void delete(Event entity) {
		this.repository.delete(entity);
	}
}
