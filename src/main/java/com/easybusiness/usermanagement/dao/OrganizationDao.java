package com.easybusiness.usermanagement.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybusiness.usermanagement.entity.Organization;
import com.easybusiness.usermanagement.repository.OrganizationRepository;

/*
 * DAO class for ORGANIZATION table
 */

@Component
public class OrganizationDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    OrganizationRepository organizationRepository;

    @Transactional(readOnly = true)
    public List<Organization> findAll() throws Exception {
		LOGGER.info("DATASOURCE = " + dataSource);
		return organizationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Organization findByOrgName(String orgName) {
    	return organizationRepository.findByOrgName(orgName).get(0);
    }

    @Transactional(readOnly = true)
    public Organization findOrganizationById(Long id) {
    	return organizationRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addOrganization(Organization organization) {
		organizationRepository.save(organization);
		LOGGER.info("Organization added successfully " + organization.toString());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOrganization(Long id) {
		organizationRepository.delete(id);
		LOGGER.info("Organization with id " + id + " deleted successfully ");
    }

}
