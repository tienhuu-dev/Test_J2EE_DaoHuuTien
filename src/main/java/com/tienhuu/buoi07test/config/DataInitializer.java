package com.tienhuu.buoi07test.config;

import com.tienhuu.buoi07test.entity.Department;
import com.tienhuu.buoi07test.entity.Doctor;
import com.tienhuu.buoi07test.entity.Role;
import com.tienhuu.buoi07test.repository.DepartmentRepository;
import com.tienhuu.buoi07test.repository.DoctorRepository;
import com.tienhuu.buoi07test.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Thêm Roles mẫu nếu chưa tồn tại
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(Arrays.asList(
                    Role.builder().name("ADMIN").build(),
                    Role.builder().name("PATIENT").build()
            ));
        }

        // 2. Thêm Departments mẫu nếu chưa tồn tại
        if (departmentRepository.count() == 0) {
            List<Department> departments = departmentRepository.saveAll(Arrays.asList(
                    Department.builder().name("Khoa Nội").build(),
                    Department.builder().name("Khoa Ngoại").build(),
                    Department.builder().name("Khoa Nhi").build(),
                    Department.builder().name("Khoa Mắt").build(),
                    Department.builder().name("Khoa Răng Hàm Mặt").build()
            ));

            // 3. Thêm Doctors mẫu gắn với các Department đã tạo
            if (doctorRepository.count() == 0) {
                doctorRepository.saveAll(Arrays.asList(
                        Doctor.builder().name("Bác sĩ Nguyễn Văn A").specialty("Nội tổng quát").image("https://via.placeholder.com/200").department(departments.get(0)).build(),
                        Doctor.builder().name("Bác sĩ Trần Thị B").specialty("Phẫu thuật chấn thương").image("https://via.placeholder.com/200").department(departments.get(1)).build(),
                        Doctor.builder().name("Bác sĩ Lê Văn C").specialty("Nhi khoa tổng quát").image("https://via.placeholder.com/200").department(departments.get(2)).build(),
                        Doctor.builder().name("Bác sĩ Phạm Văn D").specialty("Nhãn khoa").image("https://via.placeholder.com/200").department(departments.get(3)).build(),
                        Doctor.builder().name("Bác sĩ Hoàng Thị E").specialty("Răng Hàm Mặt").image("https://via.placeholder.com/200").department(departments.get(4)).build(),
                        Doctor.builder().name("Bác sĩ Ngô Văn F").specialty("Tim mạch").image("https://via.placeholder.com/200").department(departments.get(0)).build(),
                        Doctor.builder().name("Bác sĩ Đỗ Thị G").specialty("Chỉnh hình").image("https://via.placeholder.com/200").department(departments.get(1)).build(),
                        Doctor.builder().name("Bác sĩ Vũ Văn H").specialty("Nhi khoa sơ sinh").image("https://via.placeholder.com/200").department(departments.get(2)).build()
                ));
            }
        }
    }
}
