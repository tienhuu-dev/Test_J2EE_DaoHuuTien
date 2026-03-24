package com.tienhuu.buoi07test.service;

import com.tienhuu.buoi07test.entity.Department;
import com.tienhuu.buoi07test.entity.Doctor;
import com.tienhuu.buoi07test.repository.DepartmentRepository;
import com.tienhuu.buoi07test.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public Page<Doctor> getPaginatedDoctors(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return doctorRepository.findAll(pageable);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ với ID: " + id));
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
