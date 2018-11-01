package Logic.EarthForce;

public class FirstCalc {
	//構造条件
	double H;//桝の高さ
	double B;//桝の幅
	double L;//桝の奥行
	double tw;//壁厚
	double tb;//底版厚
	String rc;//無筋 or 有筋
	double rs;//土の単位体積重量
	double dang;//土の内部摩擦角
	double wq;//載荷重
	double h;//天端上部法面
	double a;//壁面傾斜各
	double ih;

	//ゲッター変数
	double ph;
	double pv;

	public FirstCalc(double H,double B,double L,double tw,double tb,String rc, double rs,double dang,double wq,double h,double a,double ih) {
		this.H = H;//桝の高さ
		this.B = B;//桝の幅
		this.L = L;//桝の奥行
		this.tw = tw;//壁厚
		this.tb = tb;//底版厚
		this.rc = rc;//無筋 or 有筋
		this.rs = rs;//土の単位体積重量
		this.dang = dang;//土の内部摩擦角
		this.wq = wq;//載荷重
		this.h = h;
		this.a = a;
		this.ih = ih;
	}
	public void EarthForce() {
		//土圧計算
		EarthPressure EP = new EarthPressure(H,tb,dang,rs,wq,h,a,ih);
		EP.radian();//計算範囲角度のラジアン変換
		EP.WsPsCalc();//最大土圧計算
		EP.PhPvForce(EP.getPamax());//土圧の成分計算
		//各種ゲッター
		this.ph = EP.getPh();//土圧 水平成分
		this.pv = EP.getPv();//土圧 鉛直成分
	}
	public void BottomForce() {
		//底版反力計算
		BotttomForce BF = new BotttomForce(H,B,L,tw,tb,rc);
		BF.MasuWeight();//桝の重量計算
		BF.Bottommasu(this.pv);//底版鉛直土圧
		BF.Reactionforce();//地盤反力計算
		//各種ゲッター

	}



}
