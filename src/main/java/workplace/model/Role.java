package workplace.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROlE_ADMIN,
    ROlE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
