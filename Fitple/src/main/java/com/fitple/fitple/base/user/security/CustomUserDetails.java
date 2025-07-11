package com.fitple.fitple.base.user.security;

import com.fitple.fitple.base.user.domain.User;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getNickname() {
        return user.getNickname();
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(user.getAuth().equals("ADMIN")){
            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return auths;
    }

    @Override public String getPassword() {
        return user.getPassword();
    }
    @Override public String getUsername() {
        return user.getEmail();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
