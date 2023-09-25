/*访问者模式
我们使用了一个访问者类，它改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。根据模式，元素对象已接受访问者对象，这样访问者对象就可以处理元素对象上的操作。
主要将数据结构与数据操作分离。
当我们想要调用某个类的方法时，我们可以通过访问者的方式来访问这个类，并且修改这个类中的方法
 */

//以对某个手机的硬件进行更新为例
//首先定义一个手机类
class MobilePhone
{
    //它包含一个CPU
    private CPU cpu = new CPU();
    public void Run()//运行程序
    {
        cpu.run();
    }
    public void Accept(Visitor visitor)
    {
        visitor.visit(cpu);
    }
}

//手机中应该会包含几个硬件，因此我们可以创建几个硬件类，并且提供一个对外的更新的接口
abstract class HardWare
{
    //每个硬件都有自己的参数，例如命令。
    protected String command;
    public HardWare(String command)
    {
        this.command = command;
    }
    //有一个方法可以修改命令
    public void SetCommand(String command)
    {
        this.command = command;
    }
    //每个硬件都有自己的一个运行方法
    void run()
    {
        System.out.println("执行命令:"+command);
    }

    //硬件也提供了自己的一个更新的方法,这个方法需要提供访问者自己
    void Accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}


class CPU extends HardWare
{
    public CPU() {
        super("进行CPU运算");
    }
}


//定义一个访问者类，这个访问者类可以调用硬件中的accept方法，并让accept方法给访问者自己提供自己的对象，进而进行修改
interface Visitor
{
    public void visit(HardWare hardWare);
}

class CPUVisitor implements Visitor
{
    @Override
    public void visit(HardWare hardWare) {
        hardWare.SetCommand("新CPU进行更强大的逻辑运算");
    }
}


//测试代码
public class VisitorPattern {
    public static void main(String[] args) {
        MobilePhone mobilePhone = new MobilePhone();
        mobilePhone.Run();
        mobilePhone.Accept(new CPUVisitor());
        mobilePhone.Run();
    }
}
