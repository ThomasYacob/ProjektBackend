package com.spring.backend.config;

import com.spring.backend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class provides core User Information for the framework.
 * Spring security invokes following methods during
 * the authentication process.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
public class CustomUserDetails implements UserDetails{

    private static final long serialVersionUID = 1L;
    private User user;
    private Long id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Creates the User details of the Authenticated User.
     * @param id the User ID.
     * @param username the username of the User.
     * @param email the email of the User.
     * @param password the password of the User.
     * @param authorities Collection of Authorities the User has.
     */
    public CustomUserDetails(Long id, String username, String email, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Builds the User details of the Authenticated User.
     * @param user the User.
     * @return new Details for a User.
     */
    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    /**
     * Creates the User details of the Authenticated User.
     * @param user the User.
     * @return the User.
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Get the Authorities of the User.
     * @return the Authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Get the ID of the User.
     * @return the ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the password of the User.
     * @return the password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get the username of the User.
     * @return the username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get the email of the User.
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Checks if the User's account has expired.
     * @return true if non-expired, false if expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the User's account is locked.
     * @return true if non-locked, false if locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the User's credentials has expired.
     * @return true if non-expired, false if expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the User's account is enabled.
     * @return true if enabled, false if not.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Compares two CustomUserDetails objects,
     * @return true if the objects are equal and false if not.
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomUserDetails user = (CustomUserDetails) o;
        return Objects.equals(username, user.username);
    }
}
