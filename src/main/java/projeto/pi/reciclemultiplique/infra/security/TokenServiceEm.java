package projeto.pi.reciclemultiplique.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import projeto.pi.reciclemultiplique.domain.Empresa;

@Service
public class TokenServiceEm {
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(Empresa empresa) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("login-auth-api")
					.withSubject(empresa.getEmail())
					.withExpiresAt(this.generateExpirationDate())
					.sign(algorithm);
			return token;
		} catch(JWTCreationException exception) {
			throw new RuntimeException("Erro na autenticação");
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("login-auth-api")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException exception) {
			return null;
		}
	}
	
	private Instant generateExpirationDate(){
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
