/**单例模式实例代码**/
/**饿汉式单例模式：在类加载阶段创建对象**/
class Controller1
{
    //类中存放一个表示该实例的静态成员变量，必须是静态，并且直接new出来
    private static Controller1 instance = new Controller1();

    //将构造函数设置为私有，防止其他类创建该对象
    private Controller1()
    {

    }
    //对外提供唯一可以获取该实例的函数，该函数必须为static，来让其他类可以直接调用该函数
    public static Controller1 GetInstance()
    {
        return instance;
    }
}

/**懒汉式单例模式：在第一次使用时创建该对象**/
class Controller2
{
    //类中存放一个表示该实例的静态成员变量，必须是静态
    private static Controller2 instance;

    //将构造函数设置为私有，防止其他类创建该对象
    private Controller2()
    {

    }

    //对外提供唯一可以获取该实例的函数，该函数必须为static，来让其他类可以直接调用该函数,并且需要判断是否存在该实例
    public static Controller2 GetInstance()
    {
        if(instance==null)
            instance = new Controller2();
        return instance;
    }
}
