package com.arcane.observer;

public interface Observer {
    // This call is an example of a tightly coupled push model
    // This could be fine, if the GameBoard is the only subject
    // that we observe.
//    void notifyOfChange(Object board);

    // Here's a more decoupled pull model. In this case the implementor
    // of this interface would have to keep a reference to the subject.
    // Something like this:
    //
    //      board = new GameBoard()
    //      consoleDisplay = new ConsoleGameDisplay(GameBoard)
    //
    // then in the ConsoleGameDisplay constructor:
    //
    //
    void notifyOfChange();
}
