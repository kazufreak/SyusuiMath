package Logic.EarthForce;

import Logic.common.round;

public class BotttomForce {
	/*上から順番にメソッドを使用の事*/

	//基本情報　コンストラクタで初期化する。
	double H;
	double B;
	double L;
	double tw;
	double tb;
	String rc;

	//自重計算
	double Wm;
	//鉛直土圧
	double Wp;
	//鉛直力合力
	double Wt;
	//地盤反力
	double w;
	
	//ラウンド処理
	round r = new round();

	public BotttomForce(double H,double B,double L,double tw,double tb,String rc) {
		//コンストラクタ
		this.H = H;
		this.B = B;
		this.L = L;
		this.tw = tw;
		this.tb = tb;
		this.rc = rc;
	}

	void MasuWeight(){
		//桝空隙
		double V1 = B * L * H;
		//桝全体
		double V2 = (B + 2*tw) * (L + 2 * tw) * H;
		//桝重量
		switch(rc){
		case "無筋":
			this.Wm = r.rd((V2 - V1) * 23.0,3);
			break;
		case "有筋":
			this.Wm = r.rd((V2 - V1) * 24.5,3);
			break;
		}
		System.out.println("自重：" + Wm);
	}
	void Bottommasu(double pv) {
		//鉛直土圧
		this.Wp = r.rd(pv  * (L + 2 * tw),3);
		//鉛直力合計
		this.Wt = r.rd(this.Wm + this.Wp,3);
		System.out.println("鉛直合力：" + Wt);
	}
	void Reactionforce() {
		//底版面積
		double A = r.rd((B + tw) * (L + tw),3);
		//地盤反力
		this.w = r.rd(Wt / A,3);
		System.out.println("底版面積：" + A + "\n"+"地盤反力："+ w);
	}
	public double getWm(){
		return this.Wm;
	}
	public double getWp(){
		return this.Wp;
	}
	public double getWt(){
		return this.Wt;
	}
	public double getw(){
		return this.w;
	}

}
