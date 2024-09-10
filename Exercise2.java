public class Exercise2 {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        BoardService boardService = new BoardService();

        String fileName = args.length > 0 ? args[0] : "file.txt";

        Board board = fileService.loadBoardFromTextFile(fileName);

        if (board == null) {
            System.out.println("Creating a new board.");
            int rows = Utils.getIntegerInput("Rows: ");
            int columns = Utils.getIntegerInput("Columns: ");
            board = new Board(rows, columns);
            fileService.saveBoardToTextFile(board, fileName);
        }
        
        boardService.print(board);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n****************************************");
            System.out.println("               Main Menu");
            System.out.println("****************************************");
            System.out.println("1. Search    - Search for an item");
            System.out.println("2. Edit      - Edit an existing item");
            System.out.println("3. Add row   - Inserts a row in the board");
            System.out.println("4. Sort      - Sorts the board");
            System.out.println("5. Print     - Print item details");
            System.out.println("6. Reset     - Reset the system");
            System.out.println("7. Exit      - Exit the application");
            System.out.println("****************************************");

            int choice = Utils.getIntegerInput("Please enter your choice (1-7): ");

            switch (choice) {
                case 1 -> boardService.search(board);
                case 2 -> boardService.edit(board, fileName);
                case 3 -> boardService.add(board, fileName);
                case 4 -> boardService.sort(board, fileName);
                case 5 -> boardService.print(board);
                case 6 -> board = boardService.reset(fileName);
                case 7 -> exit = Utils.terminate();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
