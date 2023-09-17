/*观察者模式
定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
当一个对象需要同时通知多个对象的时候，可以使用观察者模式，把所有需要通知的对象存放在一个列表当中，可以通过注册的方式往里面添加新的成员。
 */

import java.util.ArrayList;
import java.util.List;

//以星穹铁道中的战斗暂停系统为例
//战斗的时候，假如我们选择了暂停，那么所有的怪物都必须立刻暂停。为了能够通知到，我们必须使用观察者模式去通知那些怪物
//首先定义一个表示怪物的类
interface Enemy
{
    void Stop();
}

class bug implements Enemy
{
    @Override
    public void Stop()
    {
        System.out.println("虫群·真蛰虫暂停了");
    }
}

class deer implements Enemy
{
    @Override
    public void Stop()
    {
        System.out.println("丰饶玄鹿暂停了");
    }
}

//定义一个控制类
class GameController
{
    private List<Enemy> enemies = new ArrayList<Enemy>();
    public void AddEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }

    public void pause()
    {
        for(Enemy enemy:enemies)
        {
            enemy.Stop();
        }
    }
}


//测试代码
public class ObserverPattern
{
    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.AddEnemy(new bug());
        controller.AddEnemy(new bug());
        controller.AddEnemy(new deer());
        controller.AddEnemy(new deer());

        controller.pause();
    }
}
