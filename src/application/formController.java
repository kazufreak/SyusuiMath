package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class formController implements Initializable{
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
	double h;//天端上部法面 type2の時は0とする。
	double a;//壁面傾斜各＝現状考慮無し0
	double ih;//法面勾配 type1のときは0とする。

	//Input関連
	@FXML TextField in_H;
	@FXML TextField in_B;
	@FXML TextField in_L;
	@FXML TextField in_tw;
	@FXML TextField in_tb;
	@FXML TextField in_rs;
	@FXML TextField in_dang;
	@FXML TextField in_wq;
	@FXML TextField in_h;
	@FXML TextField in_ih;
	@FXML TextField in_a;
	@FXML ComboBox<String> in_rc;

	//中央ペイン
	@FXML AnchorPane view_canvas;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
        // ここに初期化処理を入力
		in_rc.getItems().addAll("無筋","有筋");
    }



}
