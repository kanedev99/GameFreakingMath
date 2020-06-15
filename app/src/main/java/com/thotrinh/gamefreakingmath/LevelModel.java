package com.thotrinh.gamefreakingmath;

public class LevelModel {
    public int difficultLevel = 1; // Easy

    // Operator
    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;

    // Operator text
    public static final String ADD_TEXT = "+";
    public static final String SUB_TEXT = "-";
    public static final String MUL_TEXT = "*";
    public static final String DIV_TEXT = ":";
    public static final String EQUAL_TEXT = "=";
    public static final String[] OPERATOR_LIST = {ADD_TEXT, SUB_TEXT, MUL_TEXT, DIV_TEXT};

    // Components of formular
    public int x;
    public int y;
    public int operator;
    public int result;
    public String strOperator;
    public String strResult;
    public boolean correct;

    // Game level
    public static final int MAX_NUMBER_EASY_LEVEL = 5;
    public static final int MAX_NUMBER_MEDIUM_LEVEL = 10;
    public static final int MAX_NUMBER_HIGH_LEVEL = 50;
    public static final int MAX_NUMBER_MAX_LEVEL = 200;
    public static final int[] LEVEL_LIST = {MAX_NUMBER_EASY_LEVEL, MAX_NUMBER_MEDIUM_LEVEL, MAX_NUMBER_HIGH_LEVEL, MAX_NUMBER_MAX_LEVEL};
}
