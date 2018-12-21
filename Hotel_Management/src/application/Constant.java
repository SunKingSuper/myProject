package application;

/**
 * 
 * @author SKS
 * 我觉得这些都可以写在properties里面，只是直接硬编码在这里
 */

final public class Constant {
	public static final String LOGO = "/Resource/hotel.png";
	public static final String NAME = "SuperKingSun酒店管理系统";

	// 出现的文本
	public static final String LoginTip = "只能输入数字";
	public static final String LoginError = "用户名或密码错误";

	// mainStage
	public static final long FreshPeriod = 5000L;
	// registerStage & bookStage
	public static final long rFreshPeriod = 1000L;

	// funcmenu
	public static final String MenuBook = "预定房间";
	public static final String MenuRegister = "入住房间";
	public static final String MenuLeave = "退房处理";
		public static final String OrderNotFound = "对应房间无订单";
	public static final String MenuShow = "查看订单信息";
		public static final String QueryShow = "查找相关订单";
		public static final String OrderShow = "显示过去所有订单";
	public static final String MenuExit = "退出系统";

	// console
	public static final String StatusText = "目前空余房间:%4d 间, 已预定房间:%4d 间, 入住中房间:%4d 间, 清理中房间:%4d 间";
	
	//status of rooms
	public static final Long FREE = 1001L; //采用long是因为跟数据库保持一致
	public static final Long BOOKED = 1002L;
	public static final Long REGISTED = 1003L;
	public static final Long CLEANNING = 1004L;
	
	//status of orders
	public static final Long oBOOK = 2001L;	// 用o命名做区别
	public static final Long oREGISTE = 2002L;
	public static final Long oDONE = 2003L;
	
	// color
	public static final String Background = "-fx-background-color:";
	public static final String Blue = "#3366cc";
	public static final String Gray = "#cccc99";
	public static final String Red = "#ff0033";
	public static final String Green = "#339933";
	public static final String Purple = "#993399";
	public static final String Yellow = "#ffff00";
	public static final String BackgroundBlue = "#6699cc";
	public static final String BackgroundRed = "#F5B7B1";
	
	//imageResource
	public static final String LogoImgUrl = "Resource/hotel.png";
	public static final String RoomInitImgUrl = "Resource/home.png";
	public static final String RoomFreeImgUrl = "Resource/homeGreen.png";
	public static final String RoomBookedImgUrl = "Resource/homePurple.png";
	public static final String RoomRegistedImgUrl = "Resource/homeBlue.png";
	public static final String RoomCleanningImgUrl = "Resource/homeYellow.png";
	public static final String AddImgUrl = "Resource/add.png";
	public static final String MinusImgUrl = "Resource/minus.png";
//	public static final String ImgUrl = "Resource/";
}
