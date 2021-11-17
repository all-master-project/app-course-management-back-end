package education.org.main.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerAuthorizationFilter extends  OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/login") || request.getServletPath().equals("/api/etudiants/refresh/token"))
		{
			filterChain.doFilter(request, response);
		}else {
			String authorizationHeader = request.getHeader("AUTHORIZATION");
			if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
			{
				try {
					String token= authorizationHeader.substring("Bearer ".length());
					Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT decodeJWT= jwtVerifier.verify(token);
					String username = decodeJWT.getSubject();
					String[] roles = decodeJWT.getClaim("roles").asArray(String.class);
					Collection<SimpleGrantedAuthority>  authorities= new ArrayList<SimpleGrantedAuthority>();
					java.util.Arrays.stream(roles).forEach(role->
					{
						authorities.add(new SimpleGrantedAuthority(role));
					});
					UsernamePasswordAuthenticationToken authentication =
								new UsernamePasswordAuthenticationToken(username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					filterChain.doFilter(request, response);
				} catch (Exception exception) {
					log.info("Error logging in: {}", exception.getMessage());
					response.setHeader("error", exception.getMessage());
					response.setStatus(org.springframework.http.HttpStatus.FORBIDDEN.value());
					Map<String, String> error= new HashMap<String, String>();
					error.put("error", exception.getMessage());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				}
			}else {
				filterChain.doFilter(request, response);
			}
		}
	}

}
