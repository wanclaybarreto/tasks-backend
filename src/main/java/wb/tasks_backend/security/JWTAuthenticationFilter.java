package wb.tasks_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wb.tasks_backend.domain.User;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            // TODO: Fazer tratamento para verificar se o json enviado contem as informações corretas e criar exception
            ObjectMapper mapper = new ObjectMapper();
            LinkedHashMap<String, String> dataUser = mapper.readValue(request.getInputStream(), LinkedHashMap.class);

            User user = new User();
            user.setUsername(dataUser.get("username"));
            user.setPassword(dataUser.get("password"));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        byte[] keyBytes = SecurityConstants.JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String jwtToken = Jwts.builder()
                .subject(userDetails.getUsername())
                .expiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME))
                .claim("name", userDetails.getName())
                .signWith(key, Jwts.SIG.HS512)
                .compact();

        response.addHeader(
                SecurityConstants.JWT_AUTHORIZATION_HEADER,
                SecurityConstants.JWT_TOKEN_PREFIX.concat(jwtToken)
        );

    }
}
