package com.mst.major.controller;

import com.mst.major.domain.entity.Major;
import com.mst.major.service.IMajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/majors")
@RequiredArgsConstructor
public class MajorController extends BaseController {
    private final IMajorService majorService;

    @GetMapping()
    public ResponseEntity<?> findAllMajors() {
        return createSuccessResponse(majorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findMajorById(@PathVariable Integer id) {
        return createSuccessResponse(majorService.findById(id));
    }

    @GetMapping("/q")
    public ResponseEntity<?> findMajorByCode(@RequestParam String code) {
        return createSuccessResponse(majorService.findByCode(code));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Major major) {
        return createSuccessResponse(majorService.save(major));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        majorService.delete(id);
        return createSuccessResponse();
    }
}
