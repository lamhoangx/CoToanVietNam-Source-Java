package cotoan.quanco;

public class Quan5 extends QuanCo {
	
	@Override
	public int checkChess(int i, int j) {
		turn = false;
		{
			valueChess(i, j, this.Giatri);
		}
		
		if (turn == true)
			return 1;
		else
			return 0;
	}
	
}
