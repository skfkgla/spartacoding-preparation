package com.sparta.springprepare;

import com.sparta.springprepare.calculator.Calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Memo {
	private final Calculator calculator;
	private String username;
	private String contents;
}
