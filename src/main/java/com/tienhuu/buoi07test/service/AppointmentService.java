package com.tienhuu.buoi07test.service;

import com.tienhuu.buoi07test.entity.Appointment;
import com.tienhuu.buoi07test.entity.Doctor;
import com.tienhuu.buoi07test.entity.Patient;
import com.tienhuu.buoi07test.repository.AppointmentRepository;
import com.tienhuu.buoi07test.repository.DoctorRepository;
import com.tienhuu.buoi07test.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public void createAppointment(Long doctorId, String username, LocalDateTime date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ"));
        
        Patient patient = patientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân. Hãy đăng ký tài khoản trước."));

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .appointmentDate(date)
                .build();

        appointmentRepository.save(appointment);
    }
}
