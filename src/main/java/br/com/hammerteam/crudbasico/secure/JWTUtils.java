package br.com.hammerteam.crudbasico.secure;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.UUID;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;

import br.com.hammerteam.crudbasico.business.dto.UserDTO;

public class JWTUtils {

	public static String generateJWT(UserDTO userDTO) throws IOException {
		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).keyID("theKeyId").build();

		MPJWTToken token = new MPJWTToken();
		token.setAud("targetService");
		token.setIss("https://server.example.com"); // Must match the expected issues configuration values
		token.setJti(UUID.randomUUID().toString());

		token.setSub(userDTO.getEmail()); // Sub is required for WildFly Swarm
		token.setUpn(userDTO.getEmail());
		token.setPreferredUsername(userDTO.getEmail());

		token.setIat(System.currentTimeMillis());
		token.setExp(System.currentTimeMillis() + 300000); // 30 Seconds expiration!

		token.addAdditionalClaims("nationality", userDTO.getNationality());

		token.setGroups(Arrays.asList("user", "protected"));

		JWSObject jwsObject = new JWSObject(header, new Payload(token.toJSONString()));

		// Apply the Signing protection
		JWSSigner signer = new RSASSASigner(JWTUtils.readPrivateKey());
		try {
			jwsObject.sign(signer);
		} catch (JOSEException e) {
			e.printStackTrace();
		}

		return jwsObject.serialize();
	}

	public static PrivateKey readPrivateKey() throws IOException {

		InputStream inputStream = JWTUtils.class.getResourceAsStream("/privateKey.pem");

		PEMParser pemParser = new PEMParser(new InputStreamReader(inputStream));
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(new BouncyCastleProvider());
		Object object = pemParser.readObject();
		KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
		return kp.getPrivate();
	}

}
