package Logic.EarthForce;

import java.util.ArrayList;
import java.util.List;

public class Area {
	//座標面積計算
	double area;//面積

	public void AreaCalc(ArrayList<ArrayList<Double>> areapos) {
		//座標引数 リスト
		ArrayList<Double> x = null;
		ArrayList<Double> y = null;
		for(int i=0; i < areapos.size(); i++) {
			if(i+1 <= areapos.size()-1) {
				x.add(areapos.get(i).get(0) + areapos.get(i+1).get(0));
				y.add(areapos.get(i+1).get(1) - areapos.get(i).get(1));
			}else if( i+1 > areapos.size()-1) {
				x.add(areapos.get(i).get(0) + areapos.get(0).get(0));
				y.add(areapos.get(0).get(1) - areapos.get(i).get(1));
			}
		}
		ArrayList<Double> xyd = null;
		for(int i= 0; i < x.size();i++) {
			xyd.add(x.get(i) * y.get(i));
		}
		double sum = 0;
		for(double i: xyd) {
			sum += i;
		}
		this.area += sum / 2;
		System.out.println(area);
	}
	public void AreaCalc(double[][] areapos) {
		//座標引数 配列
		List<Double> x = new ArrayList<>();
		List<Double> y = new ArrayList<>();
		for(int i=0; i < areapos.length; i++) {
			if(i+1 <= areapos.length-1) {
				x.add(areapos[i][0] + areapos[i+1][0]);
				y.add(areapos[i+1][1] - areapos[i][1]);
			}else if( i+1 > areapos.length-1) {
				x.add(areapos[i][0] + areapos[0][0]);
				y.add(areapos[0][1] - areapos[i][1]);
			}
		}
		List<Double> xyd = new ArrayList<>();
		for(int i= 0; i < x.size(); i++){
			xyd.add(x.get(i) * y.get(i));
		}
		double sum = 0;
		for(double i: xyd) {
			sum += i;
		}
		this.area += sum / 2;
		System.out.println("面積："+ area);
	}
	public double getArea() {
		return this.area;
	}

}
