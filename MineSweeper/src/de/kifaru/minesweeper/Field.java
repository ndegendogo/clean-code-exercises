package de.kifaru.minesweeper;

// Review: In theory, could be an interface (abstract class with only abstract methods).
// Review: Because all subclasses have the same structure, it could be replaced with an enum.
// Review: As far as I can tell, format() is only used for the CheatSheet.
// The CheatSheet expects the format to be a single character.
// format() thus couild as well return char instead of String.
// If format() keeps its return type String, I suggest that it's renamed to toString().
public abstract class Field {

    abstract String format();
}
