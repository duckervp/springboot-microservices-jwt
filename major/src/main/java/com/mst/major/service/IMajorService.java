package com.mst.major.service;

import com.mst.major.domain.entity.Major;

import java.util.List;

public interface IMajorService {
    Major save(Major major);
    void delete(Integer id);
    List<Major> findAll();
    Major findById(Integer id);
    Major findByCode(String code);
}
