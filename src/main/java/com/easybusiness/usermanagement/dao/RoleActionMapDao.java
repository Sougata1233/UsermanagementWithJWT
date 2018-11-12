package com.easybusiness.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybusiness.usermanagement.entity.Role;
import com.easybusiness.usermanagement.entity.RoleActionMap;
import com.easybusiness.usermanagement.repository.RoleActionMapRepository;

/*
 * DAO class for ROLE_ACTION_MAP_MASTER
 */

@Component
public class RoleActionMapDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleActionMapDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    RoleActionMapRepository roleActionMapRepository;

    @Transactional(readOnly = true)
    public List<RoleActionMap> findAll() throws Exception {
    	LOGGER.info("DATASOURCE = " + dataSource);
		return roleActionMapRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<RoleActionMap> findByRole(Role role) {
    	return roleActionMapRepository.findByRole(role);
    }

    @Transactional(readOnly = true)
    public RoleActionMap findRoleActionMapById(Long id) {
    	return roleActionMapRepository.findOne(id);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoleActionMap(RoleActionMap roleActionMap) {
		roleActionMapRepository.save(roleActionMap);
		LOGGER.info("RoleActionMap added successfully " + roleActionMap.toString());
	}

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRoleActionMap(Long roleActionMapId) {
		roleActionMapRepository.delete(roleActionMapId);
		LOGGER.info("RoleActionMap with id " + roleActionMapId + " deleted successfully ");
    }

}
