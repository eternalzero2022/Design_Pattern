/*策略模式
定义一系列的算法，把它们一个个封装起来，并且使它们可相互替换。
在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的context对象。策略对象改变context对象的执行算法。
 */


//以星穹铁道中的自动战斗为例
//战斗分为两种方式：手动战斗和自动战斗。玩家可以指定某一种战斗模式并进行运行
//首先定义一个表示战斗模式的类
interface BattleStrategy
{
    void RunBattle();
}

//具体的作战选项类，以下为自动战斗
class AutoFight implements BattleStrategy
{
    @Override
    public void RunBattle()
    {
        System.out.println("使用自动战斗系统开始战斗");
    }
}

class PlayerFight implements BattleStrategy
{
    @Override
    public void RunBattle() {
        System.out.println("玩家开始操控战斗");
    }
}


//定义一个战斗系统类
class BattleSystem
{
    //将策略作为一种战斗方式的对象来放入战斗系统中
    private BattleStrategy strategy;
    public BattleSystem(BattleStrategy strategy)
    {
        this.strategy = strategy;
    }

    //提供一个对外开放的改变策略的接口

    public void changeStrategy(BattleStrategy strategy)
    {
        this.strategy = strategy;
    }

    //可以调用战斗函数，根据不同的战斗方式的类来进行具体的战斗方法
    public void startBattle()
    {
        strategy.RunBattle();
    }
}


//测试代码
public class StrategyPattern
{
    public static void main(String[] args) {
        new BattleSystem(new AutoFight()).startBattle();
    }
}


