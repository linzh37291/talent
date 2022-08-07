package com.lin.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 主要是用于回话跟踪
 *
 * @author linzihao
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true)
public class JwtOperator {

	/**
	 * 秘钥 - 默认aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt
	 */
	private String secret;
	/**
	 * 有效期，单位秒 - 默认2周
	 */
	private Long expirationTimeInSecond;

	/**
	 * true 是过期
	 */
	public static boolean validateToken(Claims claimsFromToken) {
		if (claimsFromToken == null) {
			return false;
		}
		Date expiration = claimsFromToken.getExpiration();
		return expiration.before(new Date());
	}

	public static void main(String[] args) {
		System.out.println("aaaaaaaaaaaaaaa");
		// 1. 初始化
		JwtOperator jwtOperator = new JwtOperator();
		jwtOperator.expirationTimeInSecond = 1209600L;
		jwtOperator.secret = "aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt";

		// 2.设置用户信息
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", "1");
		map.put("user_name", "zs");
		map.put("login_name", "aa");

		// 测试1: 生成token
		String token = jwtOperator.generateToken(map);
		// 会生成类似该字符串的内容:
		// eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk4MTcsImV4cCI6MTU2Njc5OTQxN30.27_QgdtTg4SUgxidW6ALHFsZPgMtjCQ4ZYTRmZroKCQ
		System.out.println(token);

		Claims claimsFromToken = jwtOperator.getClaimsFromToken(token);
		System.out.println(claimsFromToken.getId());
		System.out.println(claimsFromToken.get("id"));

		if (token != null) {
			return;
		}
		// 将我改成上面生成的token!!!
		String someToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk4MTcsImV4cCI6MTU2Njc5OTQxN30.27_QgdtTg4SUgxidW6ALHFsZPgMtjCQ4ZYTRmZroKCQ";
		// 测试2: 如果能token合法且未过期，返回true
		Boolean validateToken = jwtOperator.validateToken(someToken);
		System.out.println(validateToken);

		// 测试3: 获取用户信息
		Claims claims = jwtOperator.getClaimsFromToken(someToken);
		System.out.println(claims);

		// 将我改成你生成的token的第一段（以.为边界）
		String encodedHeader = "eyJhbGciOiJIUzI1NiJ9";
		// 测试4: 解密Header
		byte[] header = Base64.decodeBase64(encodedHeader.getBytes());
		System.out.println(new String(header));

		// 将我改成你生成的token的第二段（以.为边界）
		String encodedPayload = "eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk1NDEsImV4cCI6MTU2Njc5OTE0MX0";
		// 测试5: 解密Payload
		byte[] payload = Base64.decodeBase64(encodedPayload.getBytes());
		System.out.println(new String(payload));

		// 测试6: 这是一个被篡改的token，因此会报异常，说明JWT是安全的
		// jwtOperator.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk3MzIsImV4cCI6MTU2Njc5OTMzMn0.nDv25ex7XuTlmXgNzGX46LqMZItVFyNHQpmL9UQf-aUx");
	}

	/**
	 * 从token中获取claim
	 *
	 * @param token token
	 * @return claim
	 */
	public Claims getClaimsFromToken(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(this.secret.getBytes()).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.error("token解析错误:" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 判断token是否过期
	 *
	 * @param token token
	 * @return 已过期返回true，未过期返回false
	 */
	private boolean isTokenExpired(String token) {
		Claims claimsFromToken = getClaimsFromToken(token);
		if (claimsFromToken == null) {
			return false;
		}
		Date expiration = claimsFromToken.getExpiration();
		return expiration.before(new Date());
	}

	/**
	 * 计算token的过期时间
	 *
	 * @return 过期时间
	 */
	private Date getExpirationTime() {
		return new Date(System.currentTimeMillis() + this.expirationTimeInSecond * 1000);
	}

	/**
	 * 为指定用户生成token
	 *
	 * @param claims 用户信息
	 * @return token
	 */
	public String generateToken(Map<String, Object> claims) {
		Date createdTime = new Date();
		Date expirationTime = this.getExpirationTime();

		byte[] keyBytes = secret.getBytes();
		SecretKey key = Keys.hmacShaKeyFor(keyBytes);

		return Jwts.builder().setClaims(claims).setIssuedAt(createdTime).setExpiration(expirationTime)
				// 你也可以改用你喜欢的算法
				// 支持的算法详见：https://github.com/jwtk/jjwt#features
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	/**
	 * 判断token是否非法
	 *
	 * @param token token
	 * @return 未过期返回true，否则返回false
	 */
	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}
}
