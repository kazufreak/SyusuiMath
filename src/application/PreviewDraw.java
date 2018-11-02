package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PreviewDraw {
	/*作画クラス*/

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
		double a;//壁面傾斜各＝現状考慮無し0
		double ih;//法面勾配
		//スケール
		double sc;
		double yo;//余白
		double sy1;//寸法余白

		public PreviewDraw(double H,double B,double L,double tw,double tb,String rc, double rs,double dang,double wq,double h,double a,double ih) {
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

			this.sc = 100;
			this.yo = 100;//余白
			this.sy1 = 10;//寸法余白
		}
		void scale() {
			//6.25 - > 2.5*2.5  12.25 -> 3.5*3.5
			double a = 10;
			double b = 10;
			double c = 10;

			if(this.H * this.B <= 6.25*sc) {
				this.sc = a;
				this.yo = a;
				this.sy1 = c;
			}if(this.H * this.B >= 6.25 && this.H * this.B <= 12.25*sc) {
				double kc = 0.6;
				this.sc = a * kc;
				this.yo = b * kc;
				this.sy1 = c * kc;
			}
		}
		void draw(Canvas canvas) {
			//scale();//スケール設定

			//Group root = new Group();
			GraphicsContext gc = canvas.getGraphicsContext2D();
			//図形作画
			double[][] pos = strpoint();//集水桝ます座標化メソッド

			double[] x = new double[pos.length];
			double[] y = new double[pos.length];
			for(int i = 0; i < pos.length; i++) {
				x[i] = pos[i][0];
				y[i] = pos[i][1];
			}

			gc.setLineWidth(1.0);
			gc.setStroke(Color.DARKGRAY);
			gc.strokePolyline(x,y,x.length);//集水桝作画

			//root.getChildren().addAll(canvas);



		}
		double[][] strpoint() {
			//集水桝を座標変換
			double[][] pos = {{yo,yo},{yo,yo+(H+tb)*sc},{yo+(tw*2+B)*sc,yo+(H+tb)*sc},{yo+(tw*2+B)*sc,yo},{yo+(tw+B)*sc,yo},
					{yo+(tw+B)*sc,yo+H*sc},{yo+tw*sc,yo+H*sc},{yo+tw*sc,yo},{yo,yo}};
			for(int i = 0; i < pos.length ; i++) {
				System.out.println("座標値" + i + ":"+ pos[i][0] + ","+ pos[i][1]);
			}

			return pos;
		}
		double[][] rspoint(){
			//土工作画
			double[][] pos = null;
			if(h <=0 && ih <= 0) {
				//type1
				return pos = new double[][]{{yo+tw*2+B, yo},{yo + tw * 2 + B + 1 / Math.tan(Math.toRadians(45)),yo}};
			}else if(h <=0 && ih >=0) {
				//type2
				return pos = new double[][]{{yo+tw*2+B, yo},{yo + tw * 2 + B + H * ih, yo - H}};
			}else if(h >0 && ih >=0) {
				//type3
				return pos = new double[][]{{yo+tw*2+B, yo},{yo + tw * 2 + B + h * ih, yo - h},{yo + tw * 2 + B + h * ih + B/2, yo - h}};
			}
			//未確認
			return pos;
		}


}
