package com.fooddelivery.jwt;



import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final String role;
    private final int userid;

    public AuthenticationResponse(String jwt,String role,int userid) {
        this.jwt = jwt;
        this.role=role;
        this.userid=userid;
    }

	public String getJwt() {
		return jwt;
	}

	public String getRole() {
		return role;
	}

	public int getUserid() {
		return userid;
	}

   
    
}
