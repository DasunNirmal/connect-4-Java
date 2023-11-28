package lk.ijse.connect.service;

public class  AiPlayer extends Player { // Ai Player uses "extends" key word which means that player class has inherited to the AiPLayer class
                                       // In here we use the "Inheritance" concept
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        // As the PDF said this is the code that tells the AI to play the game but randomly not intelligence

//        int randomValue;
//        do {
//            randomValue = (int) (Math.random() * 6);
//
//        }while (!board.isLegalMove(randomValue));
//        board.updateMove(randomValue,Piece.GREEN);
//        board.getBoardUI().update(randomValue,false);
//        Winner winner = board.findWinner();
//        if (winner != null){
//            board.getBoardUI().notifyWinner(winner);
//        } else if (!board.existLegalMoves()) {
//            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
//        }

        // Initialize variables to keep track of the best move and its evaluation
        int maxEval = (int) Double.NEGATIVE_INFINITY;
        int row = 0;
        // Iterate through all columns to find the best move
        for (int tempCol = 0; tempCol < 6; tempCol++){
            if (board.isLegalMove(tempCol)){
                int tempRow = board.findNextAvailableSpot(tempCol);
                // Make a hypothetical move for the AI player (Piece.GREEN)
                board.updateMove(tempCol,tempRow,Piece.GREEN);

                // Calculate the heuristic value for this move using Minimax
                int heuristicVal = minimax(0,false);

                // Undo the hypothetical move
                board.updateMove(tempCol,tempRow,Piece.EMPTY);
                // Update the best move if a better evaluation is found
                if (maxEval < heuristicVal){
                    maxEval=heuristicVal;
                    col=tempCol;
                    row=tempRow;
                }
            }
        }
        // Make the best move and update the board
        board.updateMove(col,row,Piece.GREEN);
        board.getBoardUI().update(col,false);

        // Check if the game is over and notify the winner
        Winner winner = board.findWinner();
        if (winner != null){
            board.getBoardUI().notifyWinner(winner);
        }else {
            // If no winner and no legal moves exist, it's a draw
            if (!board.existLegalMoves()){
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
    int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = board.findWinner();
        //check if player as already won or if the maximun depth has been reached
        if (depth == 4 || winner != null) {
            if (winner == null) {
                // If the game has ended in a draw, return 0 (neutral outcome)
                return 0;
            }
            if (winner.getWinningPiece() == Piece.GREEN) {
                // If AI (Piece.GREEN) wins, return a positive value (1)
                return 1;
            }
            if (winner.getWinningPiece() == Piece.BLUE) {
                // If the opponent (Piece.BLUE) wins, return a negative value (-1)
                return -1;
            }
        }
        if (maximizingPlayer) {
            //itarate through each colomn and try to make a move
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            for (int col = 0; col < 6; col++) {
                // make the move and call minimax recuisivly with the other plyaer
                if (board.isLegalMove(col)) {
                    int row = board.findNextAvailableSpot(col);
                    board.updateMove(col, row, Piece.GREEN); // Hypothetical move
                    int heuristicVal = minimax(depth + 1, false); // Recursively evaluate opponent's moves
                    board.updateMove(col, row, Piece.EMPTY); // Undo hypothetical move
                    maxEval = Math.max(heuristicVal, maxEval); // Maximize
                }
            }
            return maxEval;
        } else {
            int minEval = (int) Double.POSITIVE_INFINITY;
            for (int col = 0; col < 6; col++) {
                if (board.isLegalMove(col)) {
                    int row = board.findNextAvailableSpot(col);
                    board.updateMove(col, row, Piece.BLUE);  // Hypothetical move by opponent
                    int heuristicVal = minimax(depth + 1, true); // Recursively evaluate AI's moves
                    board.updateMove(col, row, Piece.EMPTY); // Undo hypothetical move
                    minEval = Math.min(heuristicVal, minEval); // Minimize
                }
            }
            return minEval;
        }
    }
}
