package application;

final public class Constant {
	public static final String LOGO = "/Resource/hotel.png";
	public static final String NAME = "SuperKingSun酒店管理系统";

	// 出现的文本
	public static final String LoginTip = "只能输入数字";
	public static final String LoginError = "用户名或密码错误";

	// mainStage
	public static final long FreshPeriod = 5000L;

	// funcmenu
	public static final String MenuBook = "预定房间";
	public static final String MenuRegister = "入住房间";
	public static final String MenuLeave = "退房处理";
	public static final String MenuShow = "查看运营信息";
	public static final String MenuExit = "退出系统";

	// console
	public static final String StatusText = "目前空余房间:%4d 间, 已预定房间:%4d 间, 入住中房间:%4d 间, 清理中房间:%4d 间";

	// color
	public static final String Blue = "#3366cc";
	public static final String Gray = "#cccc99";
	public static final String Red = "#ff0033";
	public static final String Green = "#339933";
	public static final String Purple = "#993399";
	public static final String Yellow = "#ffff00";
	public static final String BackgroundBlue = "#6699cc";
	
	//statuscode
	public static final int FREE = 1001;
	public static final int BOOKED = 1002;
	public static final int REGISTERED = 1003;
	public static final int CLEANNING = 1004;
}
