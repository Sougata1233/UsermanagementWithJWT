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

import com.easybusiness.usermanagement.DTO.UserGroupMenuSubMenuDTO;
import com.easybusiness.usermanagement.entity.Menu;
import com.easybusiness.usermanagement.entity.UserGroup;
import com.easybusiness.usermanagement.entity.UserGroupMenuSubMenu;
import com.easybusiness.usermanagement.repository.UserGroupMenuSubMenuRepository;

/*
 * DAO class for USER_GRP_MENU_SUBMENU_MASTER table 
 */

@Component
public class UserGroupMenuSubMenuDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupMenuSubMenuDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    UserGroupMenuSubMenuRepository userGroupSubMenuRepository;

    @Transactional(readOnly = true)
    public List<UserGroupMenuSubMenu> findAll() throws Exception {
		LOGGER.info("DATASOURCE = " + dataSource);
		return userGroupSubMenuRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<UserGroupMenuSubMenu> findUserGroupMenuById(Long id) {
    	return userGroupSubMenuRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public UserGroupMenuSubMenu findUserGroupMenuByMappingId(Long id) {
    	return userGroupSubMenuRepository.findByMappingId(id);
    }

    @Transactional(readOnly = true)
    public List<UserGroupMenuSubMenu> findUserGroupMenuByUserGroup(UserGroup userGroup) {
    	return userGroupSubMenuRepository.findByUserGroup(userGroup);
    }

    @Transactional(readOnly = true)
    public List<UserGroupMenuSubMenu> findUserGroupMenuByMenu(Menu menu) {
    	return userGroupSubMenuRepository.findByMenuItem(menu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserGroupMenuSubMenu addUserGroupMenu(UserGroupMenuSubMenu userGroupMenu) {
		if (userGroupSubMenuRepository.findByUserGroupAndSubMenuItem(
				userGroupMenu.getUserGroup(),userGroupMenu.getSubMenuItem()).size()>0){
		    userGroupMenu.setId(userGroupSubMenuRepository.findByUserGroupAndSubMenuItem(
		    		userGroupMenu.getUserGroup(),userGroupMenu.getSubMenuItem()).get(0).getId());
		}
		return userGroupSubMenuRepository.save(userGroupMenu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserGroupMenu(Long userGroupMenuId) {
		userGroupSubMenuRepository.delete(userGroupMenuId);
		LOGGER.info("UserGroupMenu with id " + userGroupMenuId + " deleted successfully ");
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserGroupMenu(UserGroupMenuSubMenu userGroupMenuSubMenu) {
		userGroupSubMenuRepository.save(userGroupSubMenuRepository.findOne(userGroupMenuSubMenu.getId()));
	//  userGroupSubMenuRepository.updateUserGroupMenu(userGroupMenuSubMenu);
		LOGGER.info("UserGroupMenu with id " + userGroupMenuSubMenu + " updated successfully ");
    }
    
    @Transactional
    public List<UserGroupMenuSubMenu> getMappingByUserGroupAndMenu(UserGroup group, Menu menu){
    	return userGroupSubMenuRepository.findByUserGroupAndMenuItem(group, menu);
    }

}
