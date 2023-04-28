package com.ems.controlleradvice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ExceptionResponseModel {
	private int status;
	private String message;
	private long timeStamp;

}
