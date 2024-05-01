package security_context;

import java.util.Set;

public class Authentication {
    private Long id;
    private String username;
    private boolean isAuthenticated;
    private Set<String> roles;

    public Authentication(Long id, String username, boolean isAuthenticated, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.isAuthenticated = isAuthenticated;
        this.roles = roles;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }
}
