package esanchez.devel.jwt.security;

public interface SecurityService {

	boolean login(String username, String password);
}
