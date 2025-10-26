package api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class User {
	private String firstName;
	private String lastName;
	private String password;
	private Integer userStatus;
	private String phone;
	private Integer id;
	private String email;
	private String username;
}


