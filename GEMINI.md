# Clinic Appointment Application - Project Context

## Tech Stack & Version
- **Language:** Java 21
- **Framework:** Spring Boot 3.4.3 (Hạ cấp từ 4.0.4 để đảm bảo ổn định)
- **Security:** Spring Security 6 (BCrypt encoding, Remember Me 7 ngày)
- **Data:** Spring Data JPA, MSSQL
- **Template Engine:** Thymeleaf
- **Tools:** Maven, Lombok, Spring Boot DevTools

## Current Status (Development Phase)
- **Security Configuration:**
    - Tạm thời vô hiệu hóa (`permitAll()`) để thuận tiện phát triển.
    - Đã cấu hình sẵn phân quyền: `/admin/**` (ADMIN), `/enroll/**` (PATIENT), `/courses` (Public).
- **Authentication Architecture:**
    - `Patient` entity implement `UserDetails`.
    - `PatientService` implement `UserDetailsService`.
    - `PasswordEncoder` được định nghĩa trong `AppConfig` để tránh lỗi **Circular Dependency**.
- **Database:** Tự động khởi tạo dữ liệu mẫu (Roles, Departments, Doctors) qua `DataInitializer`.

## Implemented Features
### 1. Home Page (`/`)
- Hiển thị danh sách bác sĩ (Tên, Chuyên khoa, Khoa, Hình ảnh).
- **Phân trang:** 5 bác sĩ mỗi trang.

### 2. Admin CRUD Doctors (`/admin/doctors`)
- Quản lý toàn bộ danh sách bác sĩ (Thêm, Sửa, Xóa).
- Hỗ trợ chọn khoa (Department) thông qua dropdown.

### 3. Authentication & Authorization
- **Đăng ký (`/register`):** Tạo tài khoản, mã hóa mật khẩu, gán quyền `PATIENT` mặc định.
- **Tự động đăng nhập:** Đăng nhập ngay sau khi đăng ký thành công.
- **Đăng nhập (`/login`):** Giao diện sẵn sàng với chức năng Remember Me.

### 4. Appointment Booking (`/enroll`)
- Bệnh nhân có thể chọn bác sĩ từ trang chủ và đặt lịch khám (chọn ngày/giờ).
- Dữ liệu được lưu vào bảng `appointment`.

## Entity Schema
- **Patient:** (id, username, password, email) - Implements `UserDetails`
- **Role:** (role_id, name) - [ADMIN, PATIENT]
- **Patient_Role:** (patient_id, role_id)
- **Doctor:** (id, name, image, specialty, department_id)
- **Department:** (id, name)
- **Appointment:** (id, patient_id, doctor_id, appointment_date)

## Development Guidelines
- **NVARCHAR:** Luôn sử dụng `columnDefinition = "NVARCHAR(255)"` cho các trường tiếng Việt.
- **Lombok:** Tránh dùng `@Data` cho các thực thể có quan hệ `@ManyToMany`; ưu tiên `@Getter`, `@Setter` và loại bỏ collection khỏi `@ToString`.
- **Transaction:** Sử dụng `@Transactional` cho các phương thức lưu dữ liệu ở lớp Service.
