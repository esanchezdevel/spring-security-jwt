package esanchez.devel.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public boolean login(String username, String password) {

		/*
		 * first get the User details searching by the username
		 */
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		/*
		 * with the user details we can create a token
		 */
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		
		/*
		 * with the token we can authenticate the user with his credentials and this authorities
		 */
		authenticationManager.authenticate(token);
		
		/*
		 * if is authenticated, then add the token to the security context
		 */
		boolean result = token.isAuthenticated();
		
		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		
		return result;
	}

}
