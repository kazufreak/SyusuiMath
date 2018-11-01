package Logic.common;

public class round {
	public double rd(double a,int place) {
		//使い方：　ans\\2500.035 = rd(2500.035148,3)
		double ans;

		int k = (int)Math.round(a * Math.pow(10,place));
		ans = k / Math.pow(10, place);
			
		return ans;
	}

}
