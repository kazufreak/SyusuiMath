package Test;

import Logic.EarthForce.FirstCalc;

public class trying {


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		double H = 2.0;//桝の高さ
		double B = 1.0;//桝の幅
		double L = 1.0;//桝の奥行
		double tw = 0.2;//壁厚
		double tb = 0.2;//底版厚
		String rc = "有筋";//無筋 or 有筋
		double rs = 16.0;//土の単位体積重量
		double dang = 30;//土の内部摩擦角
		double wq = 10;//載荷重
		double h = 0;//盛土高
		double a = 0;//壁面傾斜角
		double ih = 0;//法面勾配

		//テスト
		FirstCalc FC = new FirstCalc(H,B,L,tw,tb,rc,rs,dang,wq,h,a,ih);
		FC.EarthForce();
		FC.BottomForce();
	}

}
