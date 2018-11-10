写文本比敲代码难多了！！！

---
## 前言

在我公布了要和“方方土的堃”进行项目合作之后，本以为只要老老实实按照书本的项目一步一步研发就可以了，但是没想到我的合作伙伴把需求改成运行在命令行上的系统原型即可，本来我们决定在代码中不要连数据库，但是等我把几个存储类写得差不多的时候呢，*方方土的堃* 就开始用上 *SQL server* 了。代码写了一半我也不太好改，于是，我打算把剩余部分写完就结束该项目了，接下来的日子就好好专心学习了。

## 架构

我的架构是按照MVC模式设计的，其中 *Database* 类本来可以直接与数据库相连，然后写相应的业务SQL查询就好了，而我自己手写了一个，但是个bug重发区，曾经费了不少时间去调试。其次，Core类只是对当作程序的入口而用，承担 *Database* 与 *UI* 连接的工作统统写在 *Process* 类里， 这个类负责逻辑处理。 *UI* 类则针对每一个用户可能遇到的每一个Page都写了一个方法，然后交由 *Process* 调用，显示窗口。未来有时间的话，可以考虑将UI类改写成 *GUI* 。

## 功能介绍

该酒店管理系统现已实现的功能有：
* 用户登陆
* 用户权限控制
* 前台开单
* 智能结帐
* 修改菜单
* 前后台交换下单信息
* 智能结帐
* ~~生成日汇报账单~~

## 部分具体设计思路

* **IO**

*IO* 类只是包括了从命令行获取输入的方法和将类序列化与分序列化的方法，其中方法 `getInput`、`getNum`、`getInt`都是通过 *Scanner* 类来获取字符串、浮点数、整数， `writeData`、`readData`则是通过 *ObjectInputStream* 和 *ObjectOutPutStream* 来实现序列化和反序列化。
*IO* 类里所有的方法都是 *static* 的，即类方法。

* **Database**

首先，我先确定好数据模型，并设计出如下数据表：

    1. 菜单表（菜名， 价格， 是否可供应）
    1. 下单表（订单号， 桌号， 订单时间， 订单金额， 订单菜名列别， 剩余未上菜列表）
    1. 日结帐表（日期， 金额）
    1. 用户表（员工好， 员工民， 登陆密码, 权限角色）
    1. 权限表(权限角色名， 授权列表)

把这些表每一行数据项用一个类去写，然后再以各表主码为key,其类实例为value的hashmp作为 *Database* 的属性，通过这种方式将不同数据聚合于 *Database* 类。比如说以菜单表为例子， *Database* 的结构将会像这样子的。

```java
public class Database implements Serializable {
    private Hashmap<String, Dish> dishTable = new  Hashmap<String, Dish>();//菜名是字符串
    //...其他表
    //...具体业务方法
}

class Dish implements Serializable {
    float price;
    boolean isAvailable;
}
```

然后 *Database* 的方法与具体某项业务有关，为了省时间，则不做抽象，每一个方法实现业务的目标就好了。

为了持久化， *Database* 及数据项类都应用了 *Serializable* 接口。而 *Database* 的序列化和反序列化将由 *Process* 类调用 *IO* 方法执行。

* **Core/Process**

首先， *Core* 类只是对程序的初始化而已，具体代码像这样:

```java
public class Core {
	public static void main(String[] args) {
		Process app = new Process();
		UI ui = new UI();
		IO io = new IO();//初始化以免发生某些错误
		app.start();
	}
}
```

*Process* 就是程序主体，在程序一开始，则需要加载判断数据库文件是否存在，以决定是执行登陆还是重新注册。于是 *start* 方法可以这么写:

```java
class Process {
    Database database;
    //...其他类成员变量

    public void exit() {                //程序结束时保存数据库
		IO.writeData(database, fileDir);
		System.exit(0);
    }
	public void start() {
		UI.welcomePage();               //欢迎页
		if (IO.isFileExists(fileDir)) { //判断指定路径的数据库文件是否存在
			database = (Database) IO.readData(fileDir);
			login();
		} else {
			DBAregister();              //重新安装，注册管理员
		}
		on();                           //进入菜单，选择具体动作
	}
}
```

*Process* 还实现了权限控制。首先应该知道 `Process.on` 的结构是这样子的:

```java
class Process {
    public void on() {
        while(true) {
            int choice =  UI.mainMenuPage(rolePermission);  //UI展示菜单页
            switch (choice) {
                case 0:
                    exit();break;
                //...case n 分别对应一次具体业务动作
                default:
                    break;
            }
        }
    }
}
```

其中关键是数组 `rolePermission` ，具体思路是用户在登录时会得到自己的权限角色，然后通过查询数据库的权限表，将获得的许可数组赋值到 `ArrayList<Integer> Process.rolePermission` ，在 *UI* 菜单展示页则会根据 *rolePermission* 里涉及的选项让用户选择 *rolePermission* 的 *index*， 然后 `return rolePermission.get(index);` 便实现权限的控制了。【即你能选到的只可能是 *rolePermission* 里对应的case】

* **UI**

*UI* 类的设计反而是最简单的，因为在这个类你只需要考虑你想看到什么，然后处理用户输入的步骤以及反馈就好了，所以可以先写一些小的组件比如说 `titleShow()`, `inputTip()`等等，再对应与 *Process* 的具体业务组合成一个个 `xxxxxPage()`，比如说 `welcomePage`、`mainMenuPage`等。 *UI* 类所有方法也都是 *static* 修饰的。

有一点想要提一下的是，在我编程编到后面的时候，突然觉得 *Database* 方法以及 *UI*方法 的接口并不是一致的，然后我相当多的数据转换都在 *Process* 里进行， 再加上出现类似新添菜式是，需要 `UI.addDishPage()` 返回一个 `String dishName` 和 `float price` ，而java并不像Python那样支持多返回值，因此我專門设计了一个类 *Data* ，它是用于 *Database* 和 *UI* 返回多个数据值使用,每次返回的时候， `new Data()` 然后向相应的成员进行赋值。

```java
class Data {
    int intNum;
    float floatNum;
    //...其他出现的数据类型
}
```