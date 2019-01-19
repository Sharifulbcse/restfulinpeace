package com.example.restfulinpeace.controller;

import com.example.restfulinpeace.exception.ResourceNotFoundException;
import com.example.restfulinpeace.model.Patient;
import com.example.restfulinpeace.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    // Get All Patients
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Create a new Patient
    @PostMapping("/insert/patient/new")
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Get a Single Patient
    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
    }

    // Update a Patient
    @PutMapping("/update/patients/{id}")
    public Patient updatePatient(@PathVariable(value = "id") Long patientId,
                               @Valid @RequestBody Patient patientDetails) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        patient.setName(patientDetails.getName());
        patient.setMobile(patientDetails.getMobile());
        patient.setAge(patientDetails.getAge());
        patient.setGender(patientDetails.getGender());
        patient.setOccupation(patientDetails.getGender());
        patient.setSymptom_summary(patientDetails.getSymptom_summary());

        Patient updatedPatient = patientRepository.save(patient);
        return updatedPatient;
    }

    // Delete a Patient
    @DeleteMapping("/delete/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

        patientRepository.delete(patient);

        return ResponseEntity.ok().build();
    }
}