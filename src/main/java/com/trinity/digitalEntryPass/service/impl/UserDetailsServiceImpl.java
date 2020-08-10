package com.trinity.digitalEntryPass.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;

import com.trinity.digitalEntryPass.model.AccountInfoModel;
import com.trinity.digitalEntryPass.repository.AccountInfoMongoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	AccountInfoMongoRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder bCryptPasswordEncoder;
	
//	public void saveUser(AccountInfoModel user) {
//	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//	    user.setIsRegistered(false);
//	    userRepository.save(user);
//	}

	public UserDetails loadUserByUsername(String sso) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AccountInfoModel user=userRepository.findBysso(sso);
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(sso));
		if(user != null) {
	        return getUserForAuth(user,grantedAuthorities);
	    } else {
	        throw new UsernameNotFoundException("user with sso: "+sso+" not found");
	    }
		//return new User("212604431","password", new ArrayList());
	}
	
	private UserDetails getUserForAuth(AccountInfoModel user, Collection<GrantedAuthority> grantedAuthorities) {
	    return new org.springframework.security.core.userdetails.User(user.getSso(), user.getPassword(), grantedAuthorities);
	}
	
	public String getCurrentUserfromToken()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
		
	}
	
	public Authentication getCurrentUserToken()
	{
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
