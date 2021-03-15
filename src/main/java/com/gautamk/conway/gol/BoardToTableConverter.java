package com.gautamk.conway.gol;

import com.gautamk.conway.gol.concepts.Board;
import com.gautamk.conway.gol.concepts.Cell;
import com.googlecode.lanterna.gui2.table.TableModel;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardToTableConverter {

    public static TableModel<String> convert(Board board) {
        int height = board.getHeight();
        int width = board.getWidth();
        String[] yValues = new String[height];
        String[] xValues = new String[width];
        IntStream.range(0, width).mapToObj(String::valueOf).collect(Collectors.toList()).toArray(xValues);
        TableModel<String> model = new TableModel<>(xValues);
        for (int y = 0; y < height; y++) {
            int finalY = y;
            String[] row = new String[width];
            IntStream.range(0, width).mapToObj(x -> {
                Cell cell = board.get(x, finalY);
                return cell.isAlive()?"1":" ";
            }).collect(Collectors.toList()).toArray(row);
            model.addRow(row);
        }
        return model;
    }


}
