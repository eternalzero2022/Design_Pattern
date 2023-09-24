/*状态模式
允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。
我们可以定义几个不同的状态类，作为一个对象的成员。当我们的环境发生改变的时候，它自身的状态成员就会改变，从而在行动时调用不同的状态类中对应的这些方法。
 */

//以星穹铁道中的丰饶灵兽·长右为例
//猿神有两种状态：元气状态和元气耗尽状态
//在不同的状态下，它会做出不同的行为：在元气状态下，它会发动攻击力巨大的攻击，而当元气耗尽状态下时，他会补充元气

//定义一个表示状态的抽象类
interface State
{
    void Move(Ape enemy);
}

class Vitality implements State
{
    @Override
    public void Move(Ape enemy)
    {
        System.out.println("释放攻击力巨大的攻击！");
        //让元气减一
        enemy.ReduceVitality();
        //判断元气是否为0
        if(!enemy.hasVitality())
        {
            enemy.changeState(new VitalityRunningOut());
            System.out.println("元气已耗尽！");
        }

    }
}

class VitalityRunningOut implements State
{
    @Override
    public void Move(Ape enemy)
    {
        System.out.println("恢复元气！");
        enemy.setVitalityNum(3);
        enemy.changeState(new Vitality());
    }
}



//定义一个怪物抽象类和一个长右类
//定义一个长右的类
class Ape
{
    private State state;
    private int vitalityNum;//表示现在还存在多少层元气
    public void setVitalityNum(int vitalityNum)//设定当前存在的元气数量
    {
        this.vitalityNum = vitalityNum;
    }
    public void ReduceVitality()
    {
        vitalityNum--;
    }

    //判断当前元气是否为空
    public boolean hasVitality()
    {
        return vitalityNum!=0;
    }
    public void changeState(State state)
    {
        this.state = state;
    }

    public void doAction()
    {
        state.Move(this);
    }


    //设定构造方法，让它的状态初始设为元气耗尽状态
    public Ape()
    {
        state = new VitalityRunningOut();
        vitalityNum = 0;
    }
}


//测试代码
public class StatePattern
{
    public static void main(String[] args)
    {
        Ape ape = new Ape();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();
        ape.doAction();

    }
}



