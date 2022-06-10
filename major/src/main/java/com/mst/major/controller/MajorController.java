package com.mst.major.controller;

import com.mst.major.domain.dto.MessageDto;
import com.mst.major.domain.entity.Major;
import com.mst.major.service.IMajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
@RequiredArgsConstructor
public class MajorController extends BaseController {
    private final IMajorService majorService;

    @GetMapping()
    public MessageDto findAllMajors() {
        return createSuccessResponse(majorService.findAll());
    }

    @GetMapping("/{id}")
    public MessageDto findMajorById(@PathVariable Integer id) {
        return createSuccessResponse(majorService.findById(id));
    }

    @GetMapping("/q")
    public MessageDto findMajorByCode(@RequestParam String code) {
        return createSuccessResponse(majorService.findByCode(code));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping()
    public MessageDto save(@RequestBody Major major) {
        return createSuccessResponse(majorService.save(major));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/{id}")
    public MessageDto delete(@PathVariable Integer id) {
        majorService.delete(new Major(id, "", ""));
        return createSuccessResponse();
    }
}
