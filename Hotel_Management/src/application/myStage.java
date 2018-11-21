package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

abstract class myStage {
	protected Stage stage = new Stage();
	protected Parent root;
	protected App platform; 							//每个窗口可能需要用到平台相关属性和方法
	protected void ui(App platform) {							//之所以这么写是我一开始以为构造器可以继承的，但是我错了，所以兜了个弯
		this.platform = platform;
		stage.setTitle(Constant.NAME);
		stage.getIcons().add(new Image(Constant.LOGO));
		init();
		stage.show();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	abstract protected void init();
}
