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

import com.easybusiness.usermanagement.DTO.UserDTO;
import com.easybusiness.usermanagement.entity.User;
import com.easybusiness.usermanagement.entity.UserGroup;
import com.easybusiness.usermanagement.entity.UserGroupMap;
import com.easybusiness.usermanagement.repository.UserGroupMapRepository;

/*
 * DAO class for USER_GROUP_MAP_MASTER table
 */

@Component
public class UserGroupMapDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupMapDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    UserGroupMapRepository userGroupMapRepository;

    @Transactional(readOnly = true)
    public List<UserGroupMap> findAll() throws Exception {
		LOGGER.info("DATASOURCE = " + dataSource);
		return userGroupMapRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserGroupMap> findByUser(User user) {
    	return userGroupMapRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserGroupMap> findUserGroupMapById(Long id) {
    	return userGroupMapRepository.findById(id);
    }
    
    @Transactional
    public List<UserDTO> getUsers(long groupid){
    	return userGroupMapRepository.userIds(groupid);
    }

    @Transactional(readOnly = true)
    public List<UserGroupMap> findUserGroupMapByGroupId(UserGroup userGroup) {
    	return userGroupMapRepository.findByUserGroup(userGroup);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserGroupMap addUserGroupMap(UserGroupMap userGroupMap) {

	LOGGER.info("in dao for mapping user group to user");
	LOGGER.info("size is " + userGroupMapRepository.findByUser(userGroupMap.getUser()).size());
		return userGroupMapRepository.save(userGroupMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserGroupMap(Long UserGroupMapId) {
		userGroupMapRepository.delete(UserGroupMapId);
		LOGGER.info("UserGroupMap with id " + UserGroupMapId + " deleted successfully ");
    }

	public void save(UserGroupMap userGroupMapEntity) {
		userGroupMapRepository.save(userGroupMapRepository.findOne(userGroupMapEntity.getId()));
	}

}
