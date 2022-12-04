class Board {
	
	public static final int SIZE = 5;
	public static final int WIN_STREAK = 3;
	
	private Mark[][] boardArr;
	private int blankCells;
	private GameStatus gameStatus;
	
	Board() {
		this.blankCells = SIZE*SIZE;
		this.gameStatus = GameStatus.IN_PROGRESS;
		this.boardArr = new Mark[SIZE][SIZE];
		for(int row = 0 ; row < boardArr.length ; row++){
			for(int col = 0 ; col < boardArr[row].length ; col++){
				boardArr[row][col] = Mark.BLANK;
			}
		}
	}
	
	public boolean putMark(Mark mark, int row, int col){	
		if(row < 0 || row >= SIZE || col < 0 || col >= SIZE){
			return false;
		}
		if(this.boardArr[row][col] != Mark.BLANK){
			return false;
		}
		this.boardArr[row][col] = mark;
		this.blankCells--;
		updateGameStatus(mark, row, col);
		return true;
		
	}
	
	public Mark getMark(int row, int col) {
		if(row < 0 || row > SIZE || col < 0 || col > SIZE){
			return Mark.BLANK;
		}
		return this.boardArr[row][col];
	}
	
	public GameStatus getGameStatus(){
		return this.gameStatus;
	}
	
	private void updateGameStatus(Mark mark, int row, int col){
		if(this.blankCells == 0){
			this.gameStatus = GameStatus.DRAW;
		}
		for(int i = -1 ; i <= 1 ; i++){
			for(int j = -1 ; j <= 1; j++){
				if((i != 0 || j != 0) && (this.gameStatus != GameStatus.X_WIN && this.gameStatus != GameStatus.O_WIN && this.gameStatus != GameStatus.DRAW)){
					if(countMarkInDirection(row, col, i, j, mark) == WIN_STREAK){
						if(mark == Mark.X){
							this.gameStatus = GameStatus.X_WIN;
							System.out.println("game status: " + gameStatus);
						}else{
							this.gameStatus = GameStatus.O_WIN;
							System.out.println("game status: " + gameStatus);
						}
					} else if(this.gameStatus!=GameStatus.DRAW){
						this.gameStatus = GameStatus.IN_PROGRESS;
						System.out.println("game status: " + gameStatus);

					}	
				}	
			}
		}
	
	}
	
	private int countMarkInDirection(int row, int col, int rowDelta, int colDelta, Mark mark) {
		int count = 0;
		while(row < SIZE && row >= 0 && col < SIZE && col >= 0 && this.boardArr[row][col] == mark) {
			count++;
			row += rowDelta;
			col += colDelta;
		}
		return count;
	}
	
}