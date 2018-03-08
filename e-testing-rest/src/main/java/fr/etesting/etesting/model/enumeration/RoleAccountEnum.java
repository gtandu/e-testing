package fr.etesting.etesting.model.enumeration;

import java.util.Arrays;
import java.util.List;

public enum RoleAccountEnum {

	Administrator("Administrateur"), Student("Etudiant");

	private String role;

	private RoleAccountEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static List<String> listesAllRoles() {
		return Arrays.asList(Student.getRole(), Administrator.getRole());
	}

	public static RoleAccountEnum getRoleFromString(String role) {
		switch (role) {
		case "Administrateur":
			return Administrator;
		case "Etudiant":
			return Student;

		}
		return null;

	}

}