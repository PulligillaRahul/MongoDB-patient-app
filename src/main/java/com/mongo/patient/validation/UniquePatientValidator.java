package com.mongo.patient.validation;


import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.mongo.patient.model.Patient;
import com.mongo.patient.repository.PatientRepository;

public class UniquePatientValidator implements ConstraintValidator<UniquePatient, Patient> {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void initialize(UniquePatient constraintAnnotation) {
    }

    @Override
    public boolean isValid(Patient patient, ConstraintValidatorContext context) {
        // Check if a patient with the same name and email already exists
        Patient existingPatient = patientRepository.findByNameAndEmail(patient.getName(), patient.getEmail());
        return existingPatient == null;
    }
}

