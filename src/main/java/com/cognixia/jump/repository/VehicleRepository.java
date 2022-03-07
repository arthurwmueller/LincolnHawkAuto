package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
