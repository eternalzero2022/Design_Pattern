/*桥接模式
将抽象部分与实现部分分离，使它们都可以独立的变化。
当一个类中包含两个不同的特性的时候，可能需要将这两种特性两两组合，因此代码量非常巨大（如果有分别有5个特性，那么就会有25个不同的组合后的子类）
通过桥接模式，我们可以在抽象类中定义一个接口的成员变量，这样的话不同的具体类中就可以通过赋予不同被实现的成员变量的方式来降低类的数量
 */

//以不同的手机型号为例
//不同的手机可能会有不同的充电接头，我们需要将每一种手机型号与接头对应起来
//定义一个手机抽象类,其中包含接头的接口,也可以调用接头的充电方法
abstract class Phone
{
    JointType joint;
    public void Charge()
    {
        joint.Charge();
    }

    public abstract void Use();
}

//接头的抽象类，里面可能会有不同的充电方式
interface JointType
{
    void Charge();
}

//具体的接头类
class Joint1 implements JointType
{
    @Override
    public void Charge()
    {
        System.out.println("充电方式1");
    }
}

class Joint2 implements JointType
{
    @Override
    public void Charge()
    {
        System.out.println("充电方式2");
    }
}

//具体手机类
class iPhone extends Phone
{
    public iPhone(JointType joint)
    {
        this.joint = joint;
    }
    public void Use()
    {
        System.out.println("使用iPhone");
    }
}

class Mate60 extends Phone
{
    public Mate60(JointType joint)
    {
        this.joint = joint;
    }
    public void Use()
    {
        System.out.println("使用Mate60");
    }
}


//代码测试
public class BridgePattern
{
    public static void main(String[] args) {
        new iPhone(new Joint1()).Charge();
        new Mate60(new Joint1()).Charge();
        new iPhone(new Joint2()).Charge();
        new Mate60(new Joint2()).Charge();
        new iPhone(new Joint1()).Use();
        new Mate60(new Joint1()).Use();
        new iPhone(new Joint2()).Use();
        new Mate60(new Joint2()).Use();
    }
}