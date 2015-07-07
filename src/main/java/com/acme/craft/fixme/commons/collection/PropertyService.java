package com.acme.craft.fixme.commons.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

public class PropertyService {

	public List<String> defaultProperties() {
		//inicjalizcja
		List<String> properties = Arrays.asList("1", "2", "3");
		properties = Lists.newArrayList("1", "2", "3");

		return properties;
	}
	
	//null check
	public boolean valid(List<String> properties) {
		if (CollectionUtils.isEmpty(properties)) {
			boolean isValid = true;
			for (String property : properties) {
				isValid = isValid && valid(property);
			}
		}
		return false;
	}

	private boolean valid(String property) {
		return property != null && !property.isEmpty();
	}
}
