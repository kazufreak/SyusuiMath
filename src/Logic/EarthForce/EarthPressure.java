package Logic.EarthForce;

import java.util.ArrayList;
import java.util.List;

import Logic.common.round;

public class EarthPressure {
	//設計条件：コンストラクタで初期化
	double H;//桝高さ
	double tb;//底版厚
	double rs;//土の単位体積重量
	double dang;//土の内部摩擦角
	double wq;//載荷重
	double h;//天端上部法面
	double a;//壁面傾斜各
	double ih;//法面勾配 分勾配

	int[] hani = {45, 60};//必要に応じて編集できるようにするか
	ArrayList<Double> rad = new ArrayList<>();
	ArrayList<Double> ws = new ArrayList<>();
	ArrayList<Double> pa = new ArrayList<>();
	double degMax;
	double paMax;
	double wsMax;
	double KA;
	double ph;
	double pv;
	double pad;//最下端荷重
	//ラウンド処理
	round r = new round();

	public EarthPressure(double H,double tb,double dang,double rs,double wq,double h,double a,double ih) {
		//コンストラクタ
		this.H = H;
		this.tb = tb;
		this.rs = rs;
		this.dang = dang;
		this.wq = wq;
		this.h = h;
		this.a = a;
		this.ih = ih;

	}
	double Betarad(double ih) {
		//β角計算
		double beta = r.rd(Math.atan(1 / ih),3);
		return beta;
	}
	void radian() {
		//計算角度（Deg）をラジアン変換、リスト出力
		for(int i = hani[0]; i < hani[1]; i++) {
			rad.add(r.rd(Math.toRadians(i),3));
		}
		System.out.println("計算角度：" + rad);
	}
	void WsPsCalc(){
		//引数　radian()関数変換後のラジアン
		if(h >= 0 && ih <= 0) {
			System.out.println("土圧計算TYPE1：フラット");
			double b;
			for(int i =0; i < rad.size();i++) {
				double u = Math.tan(Math.toRadians(a));
				double d = 1 / Math.tan(rad.get(i));
				b = (this.H + tb) * (u + d);
				double n = Math.cos(rad.get(i) - Math.toRadians(a));//上辺
				double m = Math.sin(rad.get(i)) * Math.cos(Math.toRadians(a));//下辺
				ws.add(r.rd((0.5 * rs * Math.pow(H + tb,2) * n / m + wq * b),3));
			}
		}else if(ih > 0 && wq <= 0) {
			System.out.println("土圧計算TYPE2：法面一律（載荷重無）");
			for(int i =0; i < rad.size();i++) {
				//未確認
				double beta = Betarad(this.ih);
				ws.add(r.rd(0.5 * rs * Math.pow(H + tb, 2) * ((Math.cos(rad.get(i) - Math.toRadians(a)) * Math.cos((Math.toRadians(a) - beta)) /
						Math.sin((rad.get(i) - beta) * Math.pow(Math.cos(a),2)))),3));
			}
		}else if(ih > 0 && wq > 0 && h > 0) {
			System.out.println("土圧計算TYPE3：法面+載荷重有");
			double b;
			for(int i =0; i < rad.size();i++) {
				double beta = Betarad(this.ih);
				b = (Math.tan(a) + 1 / Math.tan(rad.get(i))) * (H+tb) + (1 / Math.tan(rad.get(i)) - 1 / Math.tan(beta)) * h;
				//未確認
				double n = Math.cos(rad.get(i) - Math.toRadians(a));//上辺
				double m = Math.sin(rad.get(i));//下辺
				double l = Math.pow((H+tb) + h, 2);
				double f = Math.cos(Math.toRadians(a) - beta);
				double d = Math.sin(beta);
				ws.add(r.rd(0.5 * rs * ( (n / m) * l - (f / d) * Math.pow(h, 2)) * 1/ Math.cos(Math.toRadians(a)) + wq * b,3));
			}
		}

		for(int i =0; i < ws.size();i++) {
			double sita = 0;//atan(設計水平震度)
			double u = Math.sin(rad.get(i) - Math.toRadians(dang) + sita);
			double d = Math.cos(rad.get(i) -  Math.toRadians(dang) -  Math.toRadians(dang / 3 * 2) - Math.toRadians(a)) * Math.cos(sita);
			pa.add(r.rd((u / d) * ws.get(i),3));
		}

		ArrayMaxMin Am = new ArrayMaxMin();//最大値
		paMax = Am.ArrayMax(pa);//主動土圧最大値
		int id = pa.indexOf(paMax);//最大値のインデックス取得
		degMax = r.rd(Math.toDegrees(rad.get(id)),1);//デグリーズ変換
		wsMax = ws.get(id);//主動土圧最大の土魂
		System.out.println("土くさび重量："+ ws +"\n"+"主動土圧:"+ pa);
		System.out.println("角度:"+degMax +"\n"+ "主動土圧最大値："+ paMax +"\n"+"土魂:"+ wsMax);
	}
	void PhPvForce(double pamax) {
		//最大土圧の水平成分・鉛直成分 引数：最大土圧
		ph = r.rd(pamax * Math.cos(Math.toRadians(dang / 3 * 2)),3);  // 水平成分 ;壁面摩擦角=常時　内部摩擦角の3/2
        pv = r.rd(pamax * Math.sin(Math.toRadians(dang / 3 * 2)),3);  // 鉛直成分 ;壁面摩擦角=常時　内部摩擦角の3/2
        double Ha = H + tb / 2;
        KA = r.rd(2 * ph / (rs * Math.pow(Ha,2)),3);//土圧係数
        pad = r.rd(rs * Ha * KA,3);
        //dh = xyh[1] / 3  # 土圧作用位置
        System.out.println("水平土圧："+ ph +"\n"+"鉛直土圧:"+ pv + "\n" + "主働土圧係数:"+ KA +"\n"+ "最下端の土圧：" + pad);
	}


	public List<Double> getRad(){
		return this.rad;
	}
	public List<Double> getWs(){
		return this.ws;
	}
	public List<Double> getPa(){
		return this.pa;
	}
	public double getDegmax() {
		return this.degMax;
	}
	public double getPamax() {
		return this.paMax;
	}
	public double getWsmax() {
		return this.wsMax;
	}
	public double getKa() {
		return this.KA;
	}
	public double getPh() {
		return this.ph;
	}
	public double getPv() {
		return this.pv;
	}
	public double getPad() {
		return this.pad;
	}
}
