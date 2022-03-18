package main.java;

import org.omg.CORBA.UNKNOWN;

public enum GameState {
    //TODO: States with UNSAFE are yet to be confirmed
    UNKNOWN_STATE_1(-9),
    REPLAY_NOT_AVAILABLE(-8),
    GAME_PAUSED_2_BLURRED(-7),
    GAME_PAUSED(-6),
    END_RACE_CONGRATS(-5),
    END_RACE_ANIMATION(-4),
    GAME_HIGHLIGHT_2(-3),
    GAME_HIGHLIGHT_1(-2),
    PLAY_REPLAY(-1),
    GAMEPLAY(0),
    STAGE_PREVIEW(1),
    LOADING_STAGE_PREVIEW(2),
    UNKNOWN_STATE_2(3),
    STAGE_LOCKED_UNSAFE(4),
    LOADING_STAGE_1(5),
    LOADING_STAGE_COMPLETE(6),
    CAR_SELECT(7),
    CREDITS(8),
    UNKNOWN_STATE_4(9),
    MAIN_MENU(10),
    GAME_INSTRUCTIONS(11),
    LOADING_STAGE_2(176),

    UNKNOWN_STATE_3(111);

    private int value;

    GameState(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
