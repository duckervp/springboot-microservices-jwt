package com.mst.major.service.impl;

import com.mst.major.domain.entity.Major;
import com.mst.major.repository.MajorRepository;
import com.mst.major.service.IMajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService implements IMajorService {
    private final MajorRepository majorRepository;

    @Override
    public Major save(Major major) {
        return majorRepository.save(major);
    }

    @Override
    public void delete(Integer id) {
        majorRepository.deleteById(id);
    }

    @Override
    public List<Major> findAll() {
        return majorRepository.findAll();
    }

    @Override
    public Major findById(Integer id) {
        return majorRepository.findById(id).orElse(null);
    }

    @Override
    public Major findByCode(String code) {
        return majorRepository.findByCode(code).orElse(null);
    }
}
