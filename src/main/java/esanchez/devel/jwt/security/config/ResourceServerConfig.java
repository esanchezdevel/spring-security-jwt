package esanchez.devel.jwt.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 
 * The Resource Server of our oauth2 security application
 * where we can configure the endpoints (resources) that are available
 * the any role
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "customjwtservice";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID);
	}
	
	@Override
		public void configure(HttpSecurity http) throws Exception {
		
			http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET, "/couponapi/coupons/**").hasAnyRole("USER", "ADMIN")
			.mvcMatchers(HttpMethod.POST, "/couponapi/coupons", "/saveCoupon", "/getCoupon").hasRole("ADMIN")
			.anyRequest().denyAll() //Deny all the requests that not match with the pattern provided
			.and().csrf().disable();
		}
}
