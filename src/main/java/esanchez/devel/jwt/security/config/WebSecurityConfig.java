package esanchez.devel.jwt.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import esanchez.devel.jwt.security.UserDetailsServiceImpl;

//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * the user details are provided via mysql
	 */
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * authorize each endpoint looking at the roles of the users
		 */
		//http.httpBasic();
		http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET, "/couponapi/coupons/**", "/index", "/showCoupon", "/getCoupon", "/couponDetails").hasAnyRole("USER", "ADMIN")
			.mvcMatchers(HttpMethod.POST, "/couponapi/coupons", "/saveCoupon").hasRole("ADMIN")
			.mvcMatchers(HttpMethod.GET, "/createCouponForm", "/createResponse").hasRole("ADMIN")
			.mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
			.mvcMatchers("/", "/showReg", "/registerUser").permitAll()
			.mvcMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().denyAll() //Deny all the requests that not match with the pattern provided
			.and().csrf().disable()
			.logout().logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * for be able to use the authenticationManager in the SecurityServiceImpl to create
	 * the custom login page
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
