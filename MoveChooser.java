import java.util.ArrayList;  
import java.lang.Math; 

public class MoveChooser {
  
    public static Move chooseMove(BoardState boardState){
	int searchDepth= Othello.searchDepth;
    ArrayList<Move> moves= boardState.getLegalMoves();
    int node = 0;
    Move move = null;
    int a = Integer.MIN_VALUE;
    int b = Integer.MAX_VALUE;
    if (moves.isEmpty()){
        return null; 
    }
    else {
        for(int i=0; i<moves.size();i++){
            BoardState node1 = boardState.deepCopy();
            node1.makeLegalMove(moves.get(i).x, moves.get(i).y);
            int m = miniMax(node1, searchDepth-1, a, b, false);
            if(a<m){
                a=m;
                node = i;
            }
        }
        move = moves.get(node);
        return move; 
    }
}

    public static int Evaluation(BoardState boardState){
        int sEvaluation = 0;
        int [][] node = {
            {120,-20,20,5,5,20,-20,120},
            {-20,-40,-5,-5,-5,-5,-40,-20},
            {20,-5,15,3,3,15,-5,20},
            {5,-5,3,3,3,3,-5,5},
            {5,-5,3,3,3,3,-5,5},
            {20,-5,15,3,3,15,-5,20},
            {-20,-40,-5,-5,-5,-5,-40,-20},
            {120,-20,20,5,5,20,-20,120}
        }; 
        int white = 0; 
        int black = 0; 

        for (int i = 0; i <=7; i++ ){
            for (int j = 0; j<=7; j++){
                if (boardState.getContents(i,j)== -1){
                    black += node[i][j];
                }
                else if(boardState.getContents(i,j) == 1){
                    white += node[i][j];
                }
            sEvaluation = white - black; 
            }
        }
        return sEvaluation;
        }
    public static int miniMax(BoardState boardState, int searchDepth, int alpha, int beta, Boolean isMax){
        ArrayList<Move> moves = boardState.getLegalMoves();
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;
        int eval = 0;
        if(searchDepth==0 || boardState.gameOver()){
            return Evaluation(boardState);
        }

        else if(isMax){
            for(int i =0 ; i<moves.size();i++){
                //if(alpha <= beta || moves != null){
                BoardState node1 = boardState.deepCopy();
                node1.makeLegalMove(moves.get(i).x, moves.get(i).y);
                eval = miniMax(node1, searchDepth-1, alpha, beta, false);
                maxValue = Math.max(alpha, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha){
                    break;
        }
    }
            return maxValue;
    }
        else{
            for(int i = 0;i<moves.size();i++){
                //if(alpha <= beta||moves != null){
                BoardState node1 = boardState.deepCopy();
                node1.makeLegalMove(moves.get(i).x, moves.get(i).y);
                eval = miniMax(node1, searchDepth-1, alpha, beta, true);
                minValue = Math.min(beta, eval);
                beta = Math.min(beta, eval); 
                if (beta <= alpha){
                    break; 
                }
            }
        }
                return minValue;

}
}



    /*public static int miniMax(BoardState boardState, int searchdepth, int alpha, int beta){
        ArrayList<Move> moves = boardState.getLegalMoves();
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
        if(searchdepth==0 || boardState.gameOver()){
            return Evaluation(boardState);
        }
        if (boardState.colour == 1)
            {
            int count = 0;
            while((alpha >beta) && (count < moves.size())&& (moves != null) ){
            BoardState node1 = boardState.deepCopy();
            node1.makeLegalMove(moves.get(count).x, moves.get(count).y);
            alpha = Math.max(alpha,miniMax(node1, searchdepth-1, alpha, beta));
            count ++;
            }
            return alpha;
        }
        else if (boardState.colour == -1){
            int count = 0;
            while((alpha > beta) && (count < moves.size())&& (moves != null)) {
                BoardState node1 = boardState.deepCopy();
                node1.makeLegalMove(moves.get(count).x, moves.get(count).y);
                beta = Math.min(beta,miniMax(node1, searchdepth-1, alpha, beta));
                count ++; 
            }
        }
            return beta;
    }*/


