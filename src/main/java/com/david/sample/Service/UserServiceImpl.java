package com.david.sample.Service;

import com.david.sample.model.Joke;
import com.david.sample.model.Role;
import com.david.sample.model.User;
import com.david.sample.repository.RoleDao;
import com.david.sample.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findUserByName(String theUserName) {
        return userDao.findByUserName(theUserName);
    }

    @Override
    @Transactional
    public void save(User theUser) {
        User user = new User();
        user.setUsername(theUser.getUsername());
        user.setPassword(passwordEncoder.encode(theUser.getPassword()));

        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

        userDao.save(user);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
