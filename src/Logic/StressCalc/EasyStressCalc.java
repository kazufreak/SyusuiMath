package Logic.StressCalc;

public class EasyStressCalc {
	/*簡易版
	 */
	double M;//設計モーメント
	double S;//設計せん断力

	public void sectionforce(double H,double B,double tw,double tb,double pamax,double KA,double rs) {
		//断面力計算
		//算出値
		double M1;//固定端モーメント
		double M1max;//中央モーメント
		double S1;//せん断力

		double M2;//固定端モーメント
		double S2;//せん断力

		double h1 = (H - (B + tw)/2 + tb / 2);
		double h2 = (B + tw) / 2;
		double p2 = h2 * rs * KA;
		//①断面
		M1 = pamax * Math.pow(B+tw,2) / 12;//固定端モーメント
		M1max = pamax * Math.pow(B+tw,2) / 24;//中央モーメント
		S1 = pamax * (B+tw) / 2;//せん断力
		//②断面
		M2 = 1 / 2 * (pamax / 2+ p2 / 6) * Math.pow(h2, 2);//固定端モーメント
		S2 = (pamax + p2 / 2) * h2;//中央モーメント

		this.M = Math.max(M1, M2);//大きさの比較
		this.S = Math.max(S1, S2);//大きさの比較
	}
	public void stresscalc(double tw, double b) {
		//b 計算距離?

	}

}
