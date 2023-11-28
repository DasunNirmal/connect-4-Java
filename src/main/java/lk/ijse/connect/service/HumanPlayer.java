package lk.ijse.connect.service;

public class HumanPlayer extends Player { // Human Player uses "extends" key word which means that player class has inherited to the HumanPLayer class
                                          // In here we use the "Inheritance" concept
    public HumanPlayer(Board board) {
        super(board);
    }

    //In here we use "Polymorphism" concept
    @Override
    public void movePiece(int col) {
        if (board.isLegalMove(col)){
            board.updateMove(col,Piece.BLUE);
            board.getBoardUI().update(col,true);
            Winner winner = board.findWinner();
            if (winner != null){
                board.getBoardUI().notifyWinner(winner);
            } else if (!board.existLegalMoves()) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
