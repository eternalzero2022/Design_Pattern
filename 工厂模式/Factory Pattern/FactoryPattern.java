//工厂模式实例
/*
 抽象工厂模式
  定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
 */
//以星穹铁道中的战斗中敌方为例
// 不同的敌方类,所有的敌方都继承自同一个产品抽象类
abstract class Enemy
{
    public String name;
    public int level;
    public void showDetails()
    {
        System.out.println(name);
        System.out.println("等级"+level);
    }
}
class Wolf extends Enemy
{
    public Wolf(int level)
    {
        this.name = "丰饶灵兽·奎木";
        this.level = level;
    }
}
class Ice extends Enemy
{
    public Ice(int level)
    {
        this.name = "外宇宙之冰";
        this.level = level;
    }
}
enum EnemyType
{
    WOLF,
    ICE
}

//简单工厂模式
//定义一个工厂类，客户端只需调用工厂类中的创建方法来创建，省去创建的具体细节
class SimpleFactroy
{
    //可以根据世界等级来设置敌方的等级
    public static Enemy CreateEnemy(EnemyType type,int difficulty)
    {
        //根据不同的输入的类型来创建不同的对象
        if(type == EnemyType.WOLF)
        {
            //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
            return new Wolf(difficulty*10);
        }
        if(type == EnemyType.ICE)
        {
            //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
            return new Ice(difficulty*10);
        }
        return null;
    }
}


//工厂模式
//定义一个抽象工厂类，不同的实例工厂可以创建不同的产品
//客户调用不同的工厂实例中的创建代码来创建不太的产品对象
interface Factroy
{
    Enemy CreateEnemy(int difficulty);
}
//具体的工厂类，可以分别生产不同种类的产品
class Factory1 implements Factroy
{

    @Override
    public Enemy CreateEnemy(int difficulty) {

        //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
        return new Wolf(difficulty*10);
    }
}

class Factory2 implements Factroy
{

    @Override
    public Enemy CreateEnemy(int difficulty) {
        //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
        return new Ice(difficulty*10);
    }
}


//具体操作演示
public class FactoryPattern
{
    public static void main(String[] args) {
        //使用简单工厂来创建对象
        Enemy a = SimpleFactroy.CreateEnemy(EnemyType.WOLF,6);
        a.showDetails();
        //使用工厂来创建对象
        Enemy b = new Factory2().CreateEnemy(3);
        b.showDetails();
    }
}