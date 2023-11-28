package lk.ijse.connect.service;
public class BoardImpl implements Board { // BoardImpl uses "implement" key word which means that the Board is a  interface.In order to inherit interfaces we cannot use "extends" key word.
                                          // also we need to override all the methods in a interface when the interface is implemented to another class
                                          // In here we use "abstraction" concept
    private final Piece [][] pieces = new Piece[6][5];
    private final BoardUI boardUI;
    public BoardImpl(BoardUI boardUI) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                this.pieces[i][j] = Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
            for (int i = 0; i < 5; i++) {
                if (this.pieces[col][i] == Piece.EMPTY) {
                    return i;
                }
            }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i=0; i<6; i++){
            for (int j=0; j<5; j++){
                if (pieces[i][j].equals(Piece.EMPTY)){
                    return true;
                }
            }
        }
        return false;
    }

    //In here we use "Polymorphism" concept
    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }
    @Override
    public void updateMove(int col,int row, Piece move) {
        this.pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        //Human Player vertical win possibilities
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5 - 3; j++) {
                if (pieces[i][j] == Piece.BLUE & pieces[i][j + 1] == Piece.BLUE & pieces[i][j + 2] == Piece.BLUE & pieces[i][j + 3] == Piece.BLUE) {
                    int col1 = i;
                    int row1 = j;
                    int col2 = i;
                    int row2 = (j + 3);
                    return new Winner(Piece.BLUE, col1, row1, col2, row2);
                }
            }
        }
        //Human Player horizontal win possibilities
        for (int i = 0; i < 6 - 3; i++) {
            for (int j = 0; j < 5; j++) {
                // Check if four consecutive slots in a column are occupied by Piece.BLUE
                if (pieces[i][j] == Piece.BLUE & pieces[i + 1][j] == Piece.BLUE & pieces[i + 2][j] == Piece.BLUE & pieces[i + 3][j] == Piece.BLUE) {
                    // If found, create a Winner object indicating the winning piece and the positions
                    int col1 = i;
                    int row1 = j;
                    int col2 = (i + 3);
                    int row2 = j;
                    return new Winner(Piece.BLUE, col1, row1, col2, row2);
                }
            }
        }
        //AI Player vertical win possibilities
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length - 3; j++) {
                // Check if four consecutive slots in a column are occupied by Piece.GREEN
                if (pieces[i][j] == Piece.GREEN & pieces[i][j + 1] == Piece.GREEN & pieces[i][j + 2] == Piece.GREEN & pieces[i][j + 3] == Piece.GREEN) {
                    // If found, create a Winner object indicating the winning piece and the positions
                    int col1 = i;
                    int row1 = j;
                    int col2 = i;
                    int row2 = (j + 3);
                    return new Winner(Piece.GREEN, col1, row1, col2, row2);
                }
            }
        }
        //AI Player horizontal win possibilities
        for (int i = 0; i < pieces.length - 3; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // Check if four consecutive slots in a row are occupied by Piece.GREEN
                if (pieces[i][j] == Piece.GREEN & pieces[i + 1][j] == Piece.GREEN & pieces[i + 2][j] == Piece.GREEN & pieces[i + 3][j] == Piece.GREEN) {
                    // If found, create a Winner object indicating the winning piece and the positions
                    int col1 = i;
                    int row1 = j;
                    int col2 = (i+3);
                    int row2 = j;
                    return new Winner(Piece.GREEN, col1, row1, col2, row2);
                }
            }
        }
        return null;
    }
}
