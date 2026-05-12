package wb.tasks_backend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import wb.tasks_backend.domain.User;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private String name;

    public UserDetailsImpl(User user) {
        username = user.getUsername();
        password = user.getPassword();
        name = user.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
