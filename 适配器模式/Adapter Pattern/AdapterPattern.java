/*适配器模式
将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
当一个类需要另一个类，但是这两个类之间的接口不兼容时，就可以创建一个适配器的类，修改其中被需要的那个类的接口，让它将这个类的接口与另一个类所兼容。
类似于手机的苹果接头转成Type-C的接头一样
 */

import java.util.ArrayList;
import java.util.Dictionary;

//以星穹铁道中的角色升级系统为例
//一个角色类，里面有一个经验属性和一个增加经验的方法
//角色增加经验的方法使用的参数是整型
abstract class Character
{
    protected int EXP;
    public abstract void AddEXP(int EXP);
    public void showEXP()
    {
        System.out.println("当前的经验值为"+EXP);
    }
}

class DanHeng extends Character
{
    public DanHeng()
    {
        EXP = 0;
    }

    @Override
    public void AddEXP(int EXP) {
        this.EXP += EXP;
    }
}

//不同的经验书，里面包含了不同的经验值
abstract class EXPBook
{
    public int EXP;
}

class SmallEXPBook extends EXPBook
{
    public SmallEXPBook()
    {
        EXP = 1000;
    }
}
class BigEXPBook extends EXPBook
{
    public BigEXPBook()
    {
        EXP = 5000;
    }
}

//有一个EXPBook的管理类，有一个使用所有经验书的方法，会返回经验书列表
class EXPBookManager
{
    public ArrayList<EXPBook> expBooks;
    public EXPBookManager()
    {
        expBooks = new ArrayList<EXPBook>();
    }
    public ArrayList<EXPBook> UseAllEXPBooks()
    {
        return new ArrayList<EXPBook>(expBooks);
    }
}

//AddEXP方法需要的参数是int，但是我们使用UseAllEXPBooks方法时返回的却是经验书列表，无法直接作为AddEXP方法的参数，因此需要一个Adapter适配器类去改变它的接口
class Adapter
{
    public EXPBookManager manager;
    public Adapter(EXPBookManager manager)
    {
        this.manager = manager;
    }
    public int translate()
    {
        ArrayList<EXPBook> books = manager.UseAllEXPBooks();
        int sum = 0;
        for(EXPBook book: books)
            sum+=book.EXP;
        return sum;
    }
}

//通过客户端类来升级
class Client
{
    public EXPBookManager manager;
    public Client(EXPBookManager manager)
    {
        this.manager = manager;
    }
    public void GiveEXP(Character character)
    {
        character.AddEXP(new Adapter(manager).translate());
    }
}


//实际示范
public class AdapterPattern
{
    public static void main(String[] args) {
        //定义一个经验书管理类，并添加一些经验书
        EXPBookManager manager = new EXPBookManager();
        manager.expBooks.add(new SmallEXPBook());
        manager.expBooks.add(new BigEXPBook());
        Character danheng = new DanHeng();
        //调用Client中的GiveEXP函数为丹恒增加经验值
        new Client(manager).GiveEXP(danheng);
        danheng.showEXP();
    }
}