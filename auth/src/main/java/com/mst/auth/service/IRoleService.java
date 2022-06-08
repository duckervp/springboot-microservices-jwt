package com.mst.auth.service;

import com.mst.auth.domain.entity.Role;

public interface IRoleService {
    Role findById(Integer id);
}
