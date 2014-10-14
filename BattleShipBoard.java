import java.util.Scanner;


public class BattleShipBoard 
{

	//hanzl034 and burne188 worked together
	private int [][] Board;

	public BattleShipBoard(int m, int n) 
	{
		//instantiates a 2-dimensional array with dimensions m x n
		//if(m>3 && m<10 && n>3 && n<10){
		Board = new int[m][n];

		for(int x = 0; x < Board.length; x++)
		{
			for(int y = 0; y < Board[x].length; y++)
			{
				Board[x][y] = 9;
			}
		}

		if(m>=10 && n>=10)
		{
			createShip();
		}
		if(m>=6 && n>=6)
		{
			createShip();
		}
		if(m>=3 && n>=3) 
		{
			createShip();
		}
	}

	private void createShip()
	{
		// 50 / 50 chance for ship to be vertical or horizontal 
		if (Math.floor(Math.random() + .5) == 1)
		{
			int verx = (int)Math.floor((Board.length-3)*Math.random());
			int very = (int)Math.floor((Board[0].length-1)*Math.random());

			Board[verx][very] = 0;
			Board[verx+1][very] = 0;
			Board[verx+2][very] = 0;
		}
		else
		{
			int horx = (int)Math.floor((Board.length-1)*Math.random());
			int hory = (int)Math.floor((Board[0].length-3)*Math.random());	

			Board[horx][hory] = 0;
			Board[horx][hory+1] = 0;
			Board[horx][hory+2] = 0;	
		}
	}

	public String toString()
	{
		String tmp = "";

		for(int[] row : Board)
		{
			tmp += "\n";

			for(int spot : row)
			{
				tmp += spot + " ";
			}
		}

		return tmp;
	}

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Rows: ");
		int m = scan.nextInt();
		System.out.print("Cols: ");
		int n = scan.nextInt();
		BattleShipBoard testBoard = new BattleShipBoard(m,n);
		System.out.println(testBoard);
	}

}