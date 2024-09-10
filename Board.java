import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a board consisting of rows and columns of cells.
 */
public class Board {
    private List<Row> rows;
    private final RowService rowService;

    /**
     * Constructs a Board with a specified number of rows and columns.
     *
     * @param initialRows the number of rows in the board
     * @param initialColumns the number of columns in each row
     */
    public Board(int initialRows, int initialColumns) {
        this(new RowService(), initialRows, initialColumns);
    }

    /**
     * Constructs a Board with a specified number of rows and columns using a custom RowService.
     *
     * @param rowService the RowService to use for creating rows
     * @param initialRows the number of rows in the board
     * @param initialColumns the number of columns in each row
     */
    public Board(RowService rowService, int initialRows, int initialColumns) {
        this.rowService = rowService;
        this.rows = new ArrayList<>();
        for (int i = 0; i < initialRows; i++) {
            Row row = rowService.createRow(initialColumns);
            rows.add(row);
        }
    }

    /**
     * Returns an unmodifiable list of rows in the board.
     *
     * @return the list of rows
     */
    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
    }

    /**
     * Sets the list of rows for the board.
     *
     * @param rows the new list of rows
     */
    public void setRows(List<Row> rows) {
        this.rows = new ArrayList<>(rows); // Ensure the internal list is a new copy
    }

    /**
     * Returns the number of rows in the board.
     *
     * @return the number of rows
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Returns the number of columns in the board. Assumes all rows have the same number of columns.
     *
     * @return the number of columns
     */
    public int getColumnCount() {
        return rows.isEmpty() ? 0 : rows.get(0).getColumnCount();
    }
}
