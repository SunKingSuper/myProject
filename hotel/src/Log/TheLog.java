package Log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.MainStage;

public class TheLog {
	static Logger infoLog = Logger.getLogger("runRecord");
	static Logger warnLog = Logger.getLogger("Error");
	private static MainStage mainStage = null;
	static {
		FileHandler infoFile = null;
		FileHandler warnFile = null;
		try {
			infoFile = new FileHandler("info.log");
			warnFile = new FileHandler("error.log");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infoLog.setLevel(Level.INFO);
		warnLog.setLevel(Level.WARNING);
		if(infoFile != null & warnFile != null) {
			infoLog.addHandler(infoFile);
			warnLog.addHandler(warnFile);
		}
	}
	public static void setMainStage(MainStage stage) {
		mainStage = stage;
	}
	public static void info(String msg) {
		infoLog.info(msg);
		if(mainStage != null) {
			mainStage.infoMessage(msg);
		}
	}
	public static void warn(String error) {
		warnLog.warning(error);
	}
}
