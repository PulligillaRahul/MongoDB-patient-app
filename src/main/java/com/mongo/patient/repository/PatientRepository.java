package com.mongo.patient.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.patient.model.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Patient findByEmail(String email);
    Patient findByNameAndEmail(String name, String email);
}

