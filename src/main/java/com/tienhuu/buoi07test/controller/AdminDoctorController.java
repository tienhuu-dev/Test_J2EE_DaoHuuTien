package com.tienhuu.buoi07test.controller;

import com.tienhuu.buoi07test.entity.Doctor;
import com.tienhuu.buoi07test.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/doctors")
@RequiredArgsConstructor
public class AdminDoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "admin/doctors";
    }

    @GetMapping("/new")
    public String showNewDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", doctorService.getAllDepartments());
        return "admin/doctor-form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditDoctorForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        model.addAttribute("departments", doctorService.getAllDepartments());
        return "admin/doctor-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/admin/doctors";
    }
}
