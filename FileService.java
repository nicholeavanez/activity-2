import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final char KEY_VALUE_SEPARATOR = '\u001D';  // Group separator
    private static final char CELL_SEPARATOR = '\u001F';       // Unit separator

    /**
     * Saves the given board to a text file. Each cell is stored in the format key=value,
     * and rows are stored line by line.
     *
     * @param board   The board object to be saved
     * @param fileName The file where the board data will be stored
     */
    public void saveBoardToTextFile(Board board, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            List<Row> rows = board.getRows();
            for (Row row : rows) {
                List<Cell> cells = row.getCells();
                for (Cell cell : cells) {
                    writer.write(cell.getKey() + KEY_VALUE_SEPARATOR + cell.getValue() + CELL_SEPARATOR);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred while saving the board to the file.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Loads the board data from a text file, assuming that the format matches the way it was saved.
     *
     * @param fileName The file from which the board data is to be loaded
     * @return The reconstructed Board object
     */
    public Board loadBoardFromTextFile(String fileName) {
        List<Row> rows = new ArrayList<>();
        Board board = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                String[] cellValues = line.split(Character.toString(CELL_SEPARATOR));

                if (isFirstLine) {
                    board = new Board(0, cellValues.length);
                    isFirstLine = false;
                }

                Row row = new Row();
                for (String cellValue : cellValues) {
                    String[] splitCell = cellValue.split(Character.toString(KEY_VALUE_SEPARATOR));

                    if (splitCell.length == 2) {
                        Cell cell = new Cell();
                        cell.setKey(splitCell[0]);
                        cell.setValue(splitCell[1]);
                        row.getCells().add(cell);
                    } else {
                        System.err.println("Error: Invalid cell data format in file.");
                    }
                }

                rows.add(row);
            }

            if (board != null) {
                board.setRows(rows);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: The file '" + fileName + "' was not found.");
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred while loading the board from the file.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return board;
    }
}