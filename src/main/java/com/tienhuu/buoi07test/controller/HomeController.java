package com.tienhuu.buoi07test.controller;

import com.tienhuu.buoi07test.entity.Doctor;
import com.tienhuu.buoi07test.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final DoctorService doctorService;

    @GetMapping("/")
    public String viewHomePage(Model model, 
                               @RequestParam(defaultValue = "1") int pageNo) {
        int pageSize = 5;
        Page<Doctor> page = doctorService.getPaginatedDoctors(pageNo, pageSize);
        
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listDoctors", page.getContent());
        
        return "home";
    }
}
