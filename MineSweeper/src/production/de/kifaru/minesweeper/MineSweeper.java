package de.kifaru.minesweeper;

import static de.kifaru.minesweeper.MineSweeperException.ErrorCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MineSweeper {

    private String inputName;
    private String outputName;
    
    public static void main(final String[] args) {
        try {
            final MineSweeper theGame = new MineSweeper();
            theGame.processCheatSheet(args);
        } catch (MineSweeperException e) {
            handleException(e);
        }
    }

    private void processCheatSheet(final String[] args) {
        evaluateArgs(args);
        final List<String> config = readStringsFromFile();
        final List<String> cheatSheet = makeCheatSheet(config);
        writeStringsToFile(cheatSheet);
    }
    
    private void evaluateArgs(final String[] args) {
        if (args.length < 2) {
            printUsage();
            throw new MineSweeperException(ErrorCode.WRONG_USAGE);
        }
        inputName = args[0];
        outputName = args[1];
    }

    private static void printUsage() {
        System.out.println("Usage: MineSweeper <input file> <output file>");
    }

    private List<String> readStringsFromFile() {
        try {
            final FileReader reader = new FileReader(inputName);
            final BufferedReader in = new BufferedReader(reader);
            try {
                return readStrings(in);
            } finally {
                in.close();
                reader.close();
            }
        } catch (FileNotFoundException e) {
            throw new MineSweeperException(ErrorCode.FILE_NOT_FOUND, inputName);            
        } catch (IOException e) {
            throw new MineSweeperException(ErrorCode.FILE_READ_ERROR, inputName);            
        }
    }

    private List<String> readStrings(final BufferedReader in) throws IOException {
        final List<String> result = new ArrayList<String>();
        String string;
        while ((string = in.readLine()) != null) {
            result.add(string);
        }
        return result;
    }
    
    static List<String> makeCheatSheet(final List<String> givenConfig) {
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        final Board board = BoardImpl.createBoard(config);
        return CheatSheet.formatBoard(board);
    }
    
    private void writeStringsToFile(final List<String> strings) {
        try {
            final FileWriter writer = new FileWriter(outputName);
            final BufferedWriter out = new BufferedWriter(writer);
            try {
                writeStrings(out, strings);
            } finally {
                out.close();
                writer.close();
            }
        } catch (IOException e) {
            throw new MineSweeperException(ErrorCode.FILE_WRITE_ERROR, outputName);            
        }
    }

    private void writeStrings(final BufferedWriter out, final List<String> strings) throws IOException {
        for (String string : strings) {
            out.write(string);
            out.newLine();
        }
    }
    
    private static void handleException(MineSweeperException e) {
        System.out.println(e.getMessage());
    }

}