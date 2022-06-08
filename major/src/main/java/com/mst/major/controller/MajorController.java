package com.mst.major.controller;

import com.mst.major.domain.entity.Major;
import com.mst.major.service.IMajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
@RequiredArgsConstructor
public class MajorController {
    private final IMajorService majorService;

    @GetMapping()
    public List<Major> findAll() {
        return majorService.findAll();
    }

    @GetMapping("/{id}")
    public Major findById(@PathVariable Integer id) {
        return majorService.findById(id);
    }

    @GetMapping("/q")
    public Major findByCode(@RequestParam String code) {
        return majorService.findByCode(code);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping()
    public Major save(@RequestBody Major major) {
        return majorService.save(major);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        majorService.delete(new Major(id, "", ""));
    }
}
