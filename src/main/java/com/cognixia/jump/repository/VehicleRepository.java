package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

	@Query("{'vehicles.customer':?0}")
	List<Vehicle> findByCustomer(Integer customer);

}
