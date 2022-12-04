class Game {
	
	private Player playerX;
	private Player playerO;
	private Renderer renderer;
	
	Game(Player playerX, Player playerO, Renderer renderer) {
		this.playerX = playerX;
		this.playerO = playerO;
		this.renderer = renderer;
	}
	
	public GameStatus run(){
		Board board = new Board();
		GameStatus gameStatus;
		this.renderer.renderBoard(board);
		while(true){
			for(int i = 0 ; i < 2 ; i++){
				if(i==0){
					this.playerX.playTurn(board,Mark.X);
					this.renderer.renderBoard(board);
					gameStatus = board.getGameStatus();
				}else{
					this.playerO.playTurn(board,Mark.O);
					this.renderer.renderBoard(board);
					gameStatus = board.getGameStatus();
				}
				if(gameStatus != GameStatus.IN_PROGRESS){
					return gameStatus;
				}
			}
		}

	}
	
	
	public static void main (String[] args) {
		
		Renderer renderer = new Renderer();
		Player playerX = new Player();
		Player playerO= new Player();
		Game game = new Game(playerX, playerO, renderer);
		GameStatus whosWon = game.run();
		if(whosWon == GameStatus.X_WIN){
			System.out.println("X Won");
		} else if(whosWon == GameStatus.O_WIN){
			System.out.println("O Won");
		} else{
			System.out.println("Draw");
		}
	}
}