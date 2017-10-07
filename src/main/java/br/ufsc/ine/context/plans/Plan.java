package br.ufsc.ine.context.plans;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Plan {
	
	private String type;
	private String somethingToBeTrue;
	private Set<String> preConditions = new HashSet<>();
	private Set<String> posConditions = new HashSet<>();
	private Set<Action> actions = new HashSet<>();

	 
}
