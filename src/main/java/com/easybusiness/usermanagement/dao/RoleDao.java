package com.easybusiness.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybusiness.usermanagement.entity.Role;
import com.easybusiness.usermanagement.repository.RoleRepository;

/*
 * DAO class for ROLE_DETAILS table
 */

@Component
public class RoleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Role> findAll() throws Exception {
		LOGGER.info("DATASOURCE = " + dataSource);
		return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findByRoleName(String RoleName) {
    	return roleRepository.findByRole(RoleName).get(0);
    }

    @Transactional(readOnly = true)
    public Role findRoleById(Long id) {
    	return roleRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public void findByRoleNameStream(String RoleName) {
		try (Stream<Role> stream = roleRepository.findByRoleReturnStream(RoleName)) {
		    stream.forEach(x -> {
			LOGGER.info("Role : " + x);
		    });
		}
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role Role) {
		roleRepository.save(Role);
		LOGGER.info("Role added successfully " + Role.toString());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Long RoleId) {
		roleRepository.delete(RoleId);
		LOGGER.info("Role with id " + RoleId + " deleted successfully ");
    }

}
