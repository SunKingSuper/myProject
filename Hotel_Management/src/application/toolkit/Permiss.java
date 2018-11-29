package application.toolkit;

import control.Core;
import application.App;

abstract public class Permiss {
	App platform;
	public void getPermiss() {
		if(Core.getPermission()) {
			open();
		}
	}
	abstract public void open();
}
