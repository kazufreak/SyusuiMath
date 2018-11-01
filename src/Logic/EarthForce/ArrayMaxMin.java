package Logic.EarthForce;

import java.util.ArrayList;

public class ArrayMaxMin {
	//取り合えずdouble型の配列最大値
	public double ArrayMax(double[] array) {
		double max = 0;
        // 最大値を求める
        for(int i = 0; i < array.length; i++)
                max = Math.max(max, array[i]);
		return max;
	}
	public double ArrayMax(ArrayList<Double> array) {
		double max = 0;
        // 最大値を求める
        for(int i = 0; i < array.size(); i++)
                max = Math.max(max, array.get(i));
		return max;
	}
	public double ArrayMin(double[] array) {
		double min = 0;
        // 最小値を求める
        for(int i = 0; i < array.length; i++)
                min = Math.min(min, array[i]);
		return min;
	}
	public double ArrayMin(ArrayList<Double> array) {
		double min = 0;
        // 最小値を求める
        for(int i = 0; i < array.size(); i++)
                min = Math.min(min, array.get(i));
		return min;
	}
}
