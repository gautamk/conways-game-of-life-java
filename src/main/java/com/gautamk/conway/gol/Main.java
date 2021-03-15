package com.gautamk.conway.gol;

import com.gautamk.conway.gol.concepts.Board;
import com.gautamk.conway.gol.rules.BoardRules;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        Screen screen;
        try {
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();

            // turn off the cursor
            screen.setCursorPosition(null);
            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

            final Window window = new BasicWindow("My Root Window");
            Panel panel = new Panel(new GridLayout(2));

            Table<String> table = new Table<>("temp");

            Board board = new Board(10, 10);
            int[][] aliveCells = new int[][] {
                    {5,5},
                    {5,4},
                    {5,6},
                    {4,5},
                    {6,5},
                    {4,4},
                    {4,6},
                    {6,4},
                    {6,6}
            };
            for (int[] cell : aliveCells) {
                board.put(cell[0],cell[1], true);
            }

            TableModel<String> model = BoardToTableConverter.convert(board);
            table.setTableModel(model);

            Button nextGen = new Button("Next Gen", () -> {
                BoardRules boardRules = new BoardRules();
                boardRules.accept(board);
                table.setTableModel(BoardToTableConverter.convert(board));
            });

            panel.addComponent(table);
            panel.addComponent(nextGen);
            window.setComponent(panel);
            textGUI.addWindowAndWait(window);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
