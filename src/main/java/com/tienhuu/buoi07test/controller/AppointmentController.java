package com.tienhuu.buoi07test.controller;

import com.tienhuu.buoi07test.entity.Doctor;
import com.tienhuu.buoi07test.service.AppointmentService;
import com.tienhuu.buoi07test.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/enroll")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    @GetMapping("/new/{doctorId}")
    public String showAppointmentForm(@PathVariable Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        return "appointment-form";
    }

    @PostMapping("/save")
    public String saveAppointment(@RequestParam Long doctorId,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate,
                                  RedirectAttributes redirectAttributes) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với tư cách Bệnh nhân để đặt lịch.");
            return "redirect:/login";
        }

        try {
            appointmentService.createAppointment(doctorId, auth.getName(), appointmentDate);
            redirectAttributes.addFlashAttribute("message", "Đặt lịch hẹn thành công!");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/enroll/new/" + doctorId;
        }
    }
}
