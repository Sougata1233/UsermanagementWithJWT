package com.easybusiness.usermanagement.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.easybusiness.usermanagement.dao.UserDao;
import com.easybusiness.usermanagement.dao.UserRoleMapDao;
import com.easybusiness.usermanagement.entity.User;
import com.easybusiness.usermanagement.entity.UserRoleMap;

@Component
public class AppUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRoleMapDao userroleDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName).get();
		List<UserRoleMap> roleMap =  userroleDao.findByUser(user);
		if(user == null) {
			throw new UsernameNotFoundException(userName+" not present");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		roleMap.forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getRole().getRole()));
		});
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getPassword(), authorities);
		return userDetails;
	}

}
