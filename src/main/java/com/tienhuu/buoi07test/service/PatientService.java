package com.tienhuu.buoi07test.service;

import com.tienhuu.buoi07test.entity.Patient;
import com.tienhuu.buoi07test.entity.Role;
import com.tienhuu.buoi07test.repository.PatientRepository;
import com.tienhuu.buoi07test.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientService implements UserDetailsService {
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));
    }

    public void registerPatient(Patient patient) {
        if (patientRepository.existsByUsername(patient.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Mã hóa mật khẩu
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));

        // Gán quyền PATIENT mặc định
        Role patientRole = roleRepository.findByName("PATIENT")
                .orElseThrow(() -> new RuntimeException("Lỗi: Quyền PATIENT chưa được khởi tạo"));
        
        Set<Role> roles = new HashSet<>();
        roles.add(patientRole);
        patient.setRoles(roles);

        patientRepository.save(patient);
    }
}
