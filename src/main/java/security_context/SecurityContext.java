package security_context;

public class SecurityContext {
    private static final ThreadLocal<Authentication> context = new ThreadLocal<>();

    public static Authentication getAuthentication() {
        return context.get();
    }

    public static void setAuthentication(Authentication authentication) {
        context.set(authentication);
    }

    public static void clear() {
        context.remove();
    }
}
