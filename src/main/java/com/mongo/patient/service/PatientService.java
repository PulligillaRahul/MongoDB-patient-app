package com.mongo.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mongo.patient.exception.DuplicatePatientException;
import com.mongo.patient.exception.EntityNotFoundException;
import com.mongo.patient.model.Patient;
import com.mongo.patient.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        try {
            return patientRepository.save(patient);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicatePatientException("Duplicate patient record");
        }
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }

    public Patient updatePatient(String id, Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setName(updatedPatient.getName());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setBloodTestReports(updatedPatient.getBloodTestReports());
            existingPatient.setScanReports(updatedPatient.getScanReports());
            existingPatient.setUrineTestReports(updatedPatient.getUrineTestReports());

            try {
                return patientRepository.save(existingPatient);
            } catch (DataIntegrityViolationException ex) {
                throw new DuplicatePatientException("Duplicate patient record");
            }
        } else {
            throw new EntityNotFoundException("Patient not found");
        }
    }

    public void deletePatient(String id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}
