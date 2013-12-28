package de.kifaru.minesweeper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MineSweeper {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        if (args.length >= 2) {
            System.out.println(args[0]);
            System.out.println(args[1]);
        } else {
            System.out.println("Usage: MineSweeper <input file> <output file>");
            return;
        }
        final List<String> config = readConfigFromFile(args[0]);
        final List<String> cheatSheet = makeCheatSheetForConfig(config);
        writeCheatSheetToFile(args[1], cheatSheet);
    }
    
    private static List<String> readConfigFromFile(final String filename) throws FileNotFoundException, IOException {
        final List<String> result = new ArrayList<String>();
        try {
            final FileReader input = new FileReader(filename);
            final BufferedReader in = new BufferedReader(input);
            try {
                String string;
                while ((string = in.readLine()) != null) {
                    result.add(string);
                }
            } finally {
                in.close();
                input.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found");
            throw e;            
        } catch (IOException e) {
            System.out.println("Error while reading from " + filename);
            throw e;
        }
        return result;
    }
    
    static List<String> makeCheatSheetForConfig(final List<String> givenConfig) {
        final Configuration config = ConfigurationImpl.parse(givenConfig);
        final Board board = BoardImpl.createBoard(config);
        return CheatSheet.formatBoard(board);
    }
    
    private static void writeCheatSheetToFile(String filename, final List<String> strings) throws FileNotFoundException, IOException {
        try {
            final FileWriter output = new FileWriter(filename);
            final BufferedWriter out = new BufferedWriter(output);
            try {
                for (String string : strings) {
                    out.write(string);
                    out.newLine();
                }
            } finally {
                out.close();
                output.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found");
            throw e;            
        } catch (IOException e) {
            System.out.println("Error while writing to " + filename);
            throw e;
        }
    }
}