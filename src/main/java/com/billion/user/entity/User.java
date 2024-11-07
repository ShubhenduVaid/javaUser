package com.billion.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("users")
public class User {

	@Id
	private UUID id;
	private String name;
	private String email;
	private LocalDate dob;
	private String address;
	private String phone;
	private String country;
	private LocalDateTime date_created;
}
