package esanchez.devel.jwt.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetCouponWithoutAuth_Unauthorized() throws Exception {
		
		mockMvc.perform(get("/couponapi/coupons/SUPERSALE")).andExpect(status().isUnauthorized());
	}
	
	@Test
	void testGetCouponWithAuth_Success() throws Exception {
	    
		String accessToken = obtainAccessToken("doug@bailey.com", "doug");
		
		mockMvc.perform(get("/couponapi/coupons/SUPERSALE").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk());
	}
	
	/*
	 * special method for request an accessToken for be able to use in the requests to the API and 
	 * test the controller endpoints
	 */
	private String obtainAccessToken(String username, String password) throws Exception {
		 
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", "jwtclientapp");
	    params.add("username", username);
	    params.add("password", password);

	    ResultActions result 
	      = mockMvc.perform(post("/oauth/token")
	        .params(params)
	        .with(httpBasic("jwtclientapp","qwertyui"))
	        .accept("application/json;charset=UTF-8"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"));

	    String resultString = result.andReturn().getResponse().getContentAsString();

	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("access_token").toString();
	}
}
