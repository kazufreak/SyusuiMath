package Logic.EarthForce;

public class WsArea extends Area {
	public double[][] createPos(double H,double tb,double rad) {
		/*修正座標 引数：高さ、底版厚*/
		double A = 0;
		double Bd = H * (1 / Math.tan(rad));
		double[][] pos = {{0, H + tb},{0,0},{Bd, H + tb}};

		return pos;
	}

}
