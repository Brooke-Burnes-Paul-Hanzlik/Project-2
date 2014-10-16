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
			while(!createShip());
		}
		if(m>=6 && n>=6)
		{
			while(!createShip());
		}
		if(m>=3 && n>=3) 
		{
			while(!createShip());
		}
	}
	
	private boolean createShip()
	{
		int x, y;
		// 50 / 50 chance to be horizontal or vertical
		boolean horizontal = Math.floor(Math.random() + .5) == 1;
		
		if(horizontal)
		{
			x = (int)((Board.length - 3) * Math.random());
			y = (int)((Board[x].length - 1) * Math.random());
		}
		else
		{
			x = (int)((Board.length - 1) * Math.random());
			y = (int)((Board[x].length - 3) * Math.random());
		}
		
		return createShip(x, y, 3, horizontal);
	}
	
	// createShip only if there isnt a ship already there
	private boolean createShip(int x, int y, int len, boolean horizontal)
	{
		int i = 0;
		if (horizontal)
		{
			while(i < len && peek((x + i), y))
			{
				i++;
			}
			if(i == len)
			{
				createHorizontalLine(x, x + len - 1, y, 0);
				makeRect(x - 1, y - 1, x + len, y + 1, 1);
				makeRect(x - 2, y - 2, x + len + 1, y + 2, 2);
			}
		}
		else
		{
			while(i < len && peek(x, (y + i)))
			{
				i++;
			}
			if(i == len)
			{
				createVerticalLine(y, y + len - 1, x, 0);
				makeRect(x - 1, y - 1, x + 1, y + len, 1);
				makeRect(x - 2, y - 2, x + 2, y + len + 1, 2);
			}		
		}
		
		return i == len;
	}
	
	// Make sure spot is a valid place for a ship
	private boolean peek(int x, int y)
	{
		return x >= 0 && y >= 0 && x < Board.length && y < Board[x].length && Board[x][y] != 0;
	}
	
	/* createSquare method created by Brooke Burnes
	private void createSquare(int x1, int y1, int x2, int y2)
	{
		for(int x = x1; x < x2; x++) 
		{
			Board[x1][x] = 1;
			//Board[x1+1][x] = 2;
		}
		for(int x = x2; x > x1; x--)
		{
			Board[x][x2] = 1;
			//Board[x][x2+1] = 2;
		}
		for(int y = y1; y > y2; y--)
		{
			Board[y1][y] = 1;
			//Board[y1+1][y] = 2;
		}
		for(int y = y2; y < y1; y++) 
		{
			Board[y][y2] = 1;
			//Board[y][y2+1] = 2;
		}
	}
	*/
	
	private void makeRect(int x1, int y1, int x2, int y2, int fill)
	{
		createHorizontalLine(x1, x2, y1, fill);
		createHorizontalLine(x1, x2, y2, fill);
		createVerticalLine(y1, y2, x1, fill);
		createVerticalLine(y1, y2, x2, fill);
	}
	
	private void createVerticalLine(int y1, int y2, int x, int fill)
	{
		for(int y = y1; y <= y2; y++)
		{
			makeMark(y, x, fill);
		}
	}
	
	private void createHorizontalLine(int x1, int x2, int y, int fill)
	{
		for(int x = x1; x <= x2; x++)
		{
			makeMark(y, x, fill);
		}
	}
	
	// Mark spot on board if valid and the fill is less than the current value, return whether spot was marked or not
	private boolean makeMark(int x, int y, int fill)
	{
		boolean marked = false;
		if(x >= 0 && x < Board.length && y >= 0 && y < Board[x].length && fill < Board[x][y])
		{
			Board[x][y] = fill;
			marked = true;
		}
		
		return marked;
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
