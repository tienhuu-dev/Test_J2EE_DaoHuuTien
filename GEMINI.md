# Clinic Appointment Application - Project Context

## Tech Stack & Version
- **Language:** Java 21
- **Framework:** Spring Boot 4.0.4
- **Security:** Spring Security 6 (BCrypt encoding, Remember Me 7 days)
- **Data:** Spring Data JPA, MSSQL
- **Template Engine:** Thymeleaf
- **Tools:** Maven, Lombok, Spring Boot DevTools

## Current Status (Development Phase)
- **Security:** Tạm thời vô hiệu hóa (`permitAll()`) để thuận tiện phát triển và kiểm tra các chức năng CRUD.
- **Database:** Tự động khởi tạo dữ liệu mẫu (Roles, Departments, Doctors) thông qua `DataInitializer`.

## Implemented Features
### 1. Home Page (`/`)
- Hiển thị danh sách bác sĩ với các thông tin: Tên, Chuyên khoa, Khoa, Hình ảnh.
- **Phân trang:** 5 bác sĩ mỗi trang.

### 2. Admin CRUD Doctors (`/admin/doctors`)
- **Danh sách:** Quản lý toàn bộ bác sĩ.
- **Thêm/Sửa:** Form nhập liệu có hỗ trợ chọn Khoa (Department).
- **Xóa:** Xóa bác sĩ khỏi hệ thống với thông báo xác nhận.

### 3. Authentication & Authorization
- **Đăng ký (`/register`):** Tạo tài khoản bệnh nhân, mã hóa mật khẩu BCrypt, gán quyền `PATIENT` mặc định.
- **Đăng nhập (`/login`):** Giao diện đã sẵn sàng (Hiện đang bypass qua SecurityConfig).
- **CustomUserDetailsService:** Đã cấu hình xác thực từ Database.

## Entity Schema
- **Patient:** (id, username, password, email)
- **Role:** (role_id, name) - [ADMIN, PATIENT]
- **Patient_Role:** (patient_id, role_id)
- **Doctor:** (id, name, image, specialty, department_id)
- **Department:** (id, name)
- **Appointment:** (id, patient_id, doctor_id, appointment_date)

## Development Guidelines
- Luôn sử dụng `columnDefinition = "NVARCHAR(255)"` cho các trường tiếng Việt.
- Tuân thủ mô hình Controller-Service-Repository.
- Khi kích hoạt lại bảo mật, sử dụng `.hasRole("ADMIN")` cho các đường dẫn `/admin/**`.
