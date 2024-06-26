package com.example.rentavehicleagency.users.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.example.rentavehicleagency.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="users")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class UserEntity implements User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "nickname", unique = true)
	private String nickname;

	@Column(name = "role")
	private String role;

	@Column(name = "gender")
	private String gender;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@Lob
	@Column(name="profile_image")
	private String profileImage;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(email, userEntity.email) &&
                Objects.equals(nickname, userEntity.nickname);
    }
}
