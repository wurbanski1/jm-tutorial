package com.acme.craft.fixme.commons.string;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class NameValidator {

	public boolean valid(String name) {
		if (StringUtils.isNotBlank(name)) {
			return true;
		}
		return false;
	}

	public boolean isJohn(String name) {
		String johnName = "John";
		return johnName.equals(name);
	}
	
	
	public String validationMessage(String firstName, String lastName, String nick) {
		StringBuilder sb = new StringBuilder();
		sb.append("Provided name is not valid. First name: ").append(firstName).append(", lastName: ");
		log.info("Provided name is not valid. First name: (), last name: (), nick: ()", firstName, lastName, nick);
		return String.format("%s: %s, %s: %s %s:%s", "Provided name is not valid. First name: " + firstName + ", lastName: " + lastName + " nick:" + nick);
	}

}
