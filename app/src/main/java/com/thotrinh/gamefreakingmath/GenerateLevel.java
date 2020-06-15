package com.thotrinh.gamefreakingmath;

import java.util.Random;

public class GenerateLevel {
    // Level score
    public static final int SCORE_EASY = 10;
    public static final int SCORE_MEDIUM = 20;
    public static final int SCORE_HARD = 150;

    public static LevelModel generateLevel(int score){
        LevelModel level = new LevelModel();
        Random random = new Random();
        if(score <= SCORE_EASY)
            level.difficultLevel = 1;
        else if (score <= SCORE_MEDIUM)
            level.difficultLevel = 2;
        else if (score <= SCORE_HARD)
            level.difficultLevel = 3;
        else
            level.difficultLevel = 4;

        level.operator = random.nextInt(level.difficultLevel);
        level.x = random.nextInt(LevelModel.LEVEL_LIST[level.difficultLevel-1]) + 1;
        level.y = random.nextInt(LevelModel.LEVEL_LIST[level.difficultLevel-1]) + 1;
        level.correct = random.nextBoolean();

        if(level.correct){
            switch (level.operator){
                case LevelModel.ADD:
                    level.result = level.x + level.y;
                    break;
                case LevelModel.SUB:
                    level.result = level.x - level.y;
                    break;
                case LevelModel.MUL:
                    level.result = level.x * level.y;
                    break;
                case LevelModel.DIV:
                    level.result = level.x / level.y;
                    break;
                default:
                    break;
            }
        }
        else{
            switch(level.operator){
                case LevelModel.ADD:
                    do
                        level.result = random.nextInt(LevelModel.LEVEL_LIST[level.operator]);
                    while (level.result == (level.x + level.y));
                    break;
                case LevelModel.SUB:
                    do
                        level.result = random.nextInt(LevelModel.LEVEL_LIST[level.operator]);
                    while (level.result == (level.x - level.y));
                    break;
                case LevelModel.MUL:
                    do
                        level.result = random.nextInt(LevelModel.LEVEL_LIST[level.operator]);
                    while (level.result == (level.x * level.y));
                    break;
                case LevelModel.DIV:
                    do
                        level.result = random.nextInt(LevelModel.LEVEL_LIST[level.operator]);
                    while (level.result == (level.x / level.y));
                    break;
                default:
                    break;
            }
        }

        level.strOperator = String.valueOf(level.x)
                            + LevelModel.OPERATOR_LIST[level.operator]
                            + String.valueOf(level.y);
        level.strResult = LevelModel.EQUAL_TEXT + String.valueOf(level.result);
        return level;
    }
}
