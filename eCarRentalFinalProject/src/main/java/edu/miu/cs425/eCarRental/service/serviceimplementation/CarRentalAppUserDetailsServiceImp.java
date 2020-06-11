package edu.miu.cs425.eCarRental.service.serviceimplementation;


import edu.miu.cs425.eCarRental.model.Credential;
import edu.miu.cs425.eCarRental.repository.ICredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CarRentalAppUserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private ICredentialRepository credRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential user = credRepository.findByUserName(username)
                     .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
               getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Credential user) {
        String[] userRoles = user.getUser().getRoles().stream().map((role) -> role.getRoleName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

}
