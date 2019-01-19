package com.example.restfulinpeace.controller;

import com.example.restfulinpeace.exception.ResourceNotFoundException;
import com.example.restfulinpeace.model.Doctor;
import com.example.restfulinpeace.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    // Get All Doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Create a new Doctor
    @PostMapping("/insert/doctor/new")
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get a Single Doctor
    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable(value = "id") Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
    }

    // Update a Doctor
    @PutMapping("/update/doctors/{id}")
    public Doctor updateDoctor(@PathVariable(value = "id") Long doctorId,
                           @Valid @RequestBody Doctor doctorDetails) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        doctor.setName(doctorDetails.getName());
        doctor.setDept(doctorDetails.getName());
        doctor.setJoining(doctorDetails.getJoining());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return updatedDoctor;
    }

    // Delete a Doctor
    @DeleteMapping("/delete/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable(value = "id") Long doctorId) {
       Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        doctorRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }
}