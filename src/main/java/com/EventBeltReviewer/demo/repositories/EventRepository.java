package com.EventBeltReviewer.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.EventBeltReviewer.demo.models.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	List<Event> findAll();
}
