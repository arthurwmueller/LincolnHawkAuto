package com.cognixia.jump.model;

public class AuthenticationResponse {
	private final String JWT;
	public AuthenticationResponse(String jwt) {
		this.JWT=jwt;
	}
	public String getJWT() {
		return JWT;
	}
}
