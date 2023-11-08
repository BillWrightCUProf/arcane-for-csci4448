package com.arcane.ui;

import com.arcane.board.GameBoard;
import com.arcane.observer.Observer;

public class ConsoleGameDisplay implements Observer {
    GameBoard board;

    public ConsoleGameDisplay(GameBoard board) {
        // We keep a reference to our subject, so that we can
        // use the pull model -- we'll decide what we want to
        // inspect from the subject's public interface
        this.board = board;

        // Here, we add ourselves as an observer of this subject
        board.addObserver(this);
    }

//    @Override
//    public void notifyOfChange(GameBoard board) {
//
//    }

    @Override
    public void notifyOfChange() {
        // Print out board
        System.out.println(board);
    }
}
