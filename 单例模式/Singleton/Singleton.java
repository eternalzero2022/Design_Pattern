/**单例模式实例代码**/

import java.util.ArrayList;

/** 饿汉式单例模式：在类加载阶段创建对象 **/
class Controller1 {
    // 类中存放一个表示该实例的静态成员变量，必须是静态，并且直接new出来
    private static final Controller1 instance = new Controller1();

    // 将构造函数设置为私有，防止其他类创建该对象
    private Controller1() {

    }

    // 对外提供唯一可以获取该实例的函数，该函数必须为static，来让其他类可以直接调用该函数
    public static Controller1 GetInstance() {
        return instance;
    }

}

/** 懒汉式单例模式：在第一次使用时创建该对象 **/
class Controller2 {
    // 类中存放一个表示该实例的静态成员变量，必须是静态
    private static Controller2 instance;

    // 将构造函数设置为私有，防止其他类创建该对象
    private Controller2() {

    }

    // 对外提供唯一可以获取该实例的函数，该函数必须为static，来让其他类可以直接调用该函数,并且需要判断是否存在该实例
    public static Controller2 GetInstance() {
        if (instance == null)
            instance = new Controller2();
        return instance;
    }
}

// 实际演示使用
class ControllerTest {
    private static ControllerTest instance;
    //一个存放物品的列表，在获取该实例时由于为单例模式，故访问的一直是同一个列表
     public ArrayList<Integer> list = new ArrayList<>();

    private ControllerTest() {

    }

    // 对外提供唯一可以获取该实例的函数，该函数必须为static，来让其他类可以直接调用该函数,并且需要判断是否存在该实例
    public static ControllerTest GetInstance() {
        if (instance == null)
            instance = new ControllerTest();
        return instance;
    }
}

public class Singleton {
    public static void main(String[] args)
    {
        //获取实例，并添加内容
        ControllerTest.GetInstance().list.add(1);
        System.out.println(ControllerTest.GetInstance().list.size());
        ControllerTest.GetInstance().list.add(2);
        System.out.println(ControllerTest.GetInstance().list.size());
        //两次添加都为同一个列表进行添加
    }
}
