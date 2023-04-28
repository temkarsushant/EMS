package com.ems.enums;

public enum GreadeAndDesignationEnum {

	T1("Trainee"), 
	E1("Software Engg"), 
	E2("Sr. Software Engg"), 
	E3("Module Lead"), 
	E4("Tech Lead"), 
	E5("Project Manager"), 
	E6("Delivery Manager"), 
	E7("TrainAVPee"),
	E8("CEO");
	
	public final String label;

    private GreadeAndDesignationEnum(String label) {
        this.label = label;
    }
}
