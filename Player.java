import java.util.Scanner;

class Player {
	
	Scanner in = new Scanner(System.in);
	
	public void playTurn(Board board, Mark mark){
		int rowAndCol = in.nextInt();
		int row = rowAndCol/10-1;
		int col = rowAndCol%10-1;
		boolean isPlayedTurn = board.putMark(mark, row, col);
		while(!isPlayedTurn){
			rowAndCol = in.nextInt();
			row = rowAndCol/10-1;
			col = rowAndCol%10-1;
			isPlayedTurn = board.putMark(mark, row, col);
		}
	}	
}