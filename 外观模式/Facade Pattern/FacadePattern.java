/* 外观模式
为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
当一个业务涉及到多个底层的系统的一系列调用时，使用外观模式可以轻松简化业务的实现，客户无需了解底层设计，而只需要通过一个普通的接口实现全部功能
 */

import java.util.Date;

//以星穹铁道中的战斗系统为例
//战斗系统中玩家发动战斗指令时，可能会涉及到模型系统、音频系统、伤害计算系统等等各种系统，而一个外观模式的接口可以简化这一过程
//定义一些不同的系统
class SoundSystem
{
    public void Play()
    {
        System.out.println("播放音效");
    }
}
class MotionSystem
{
    public void Play()
    {
        System.out.println("执行动作");
    }
}

class DamageSystem
{
    public void DamageCal(int atk)
    {
        System.out.println("伤害计算:伤害为"+Math.max(atk-100,1));
    }
}

//定义外观类，当执行攻击时，外观类自动调用其他系统的方法
class Facade
{
    SoundSystem soundSystem = new SoundSystem();
    MotionSystem motionSystem = new MotionSystem();
    DamageSystem damageSystem = new DamageSystem();
    public void Attack(int atk)
    {
        soundSystem.Play();
        motionSystem.Play();
        damageSystem.DamageCal(atk);
    }
}


//测试代码
public class FacadePattern
{
    public static void main(String[] args) {
        new Facade().Attack(1000);
    }
}