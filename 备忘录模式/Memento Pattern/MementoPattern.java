/*备忘录模式
在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
可以创建一个快照类，里面只有需要存储的对象的内部状态相同的变量，并且提供了创建快照的接口。
创建一个快照是通过需要被保存的对象本身进行创建的。
 */

import java.util.Stack;

//以一个存储了字符串的对象为例
class Document
{
    private String s;
    //提供创建一个快照的方法
    public BackUp save()
    {
        return new BackUp(s);
    }

    //提供一个撤销操作
    public boolean Resume(BackUp backUp)
    {
        if(backUp == null)
            return false;
        this.s = backUp.getString();
        return true;
    }

    //提供一个修改本类的方法
    public void Change(String s)
    {
        this.s = s;
    }

    //提供一个获取字符串的方法
    public String getString()
    {
        return s;
    }
}

//快照类的接口
interface Memento
{

}

class BackUp implements Memento{
    //表示快照中存储了一个string类型的数据
    private String s;
    //构造方法
    public BackUp(String s)
    {
        this.s = s;
    }

    public String getString()
    {
        return s;
    }
}


//定义一个表示快照栈的类
class History
{
    private Stack<BackUp> history = new Stack<BackUp>();
    public void saveVersion(BackUp backUp)
    {
        history.push(backUp);
    }
    public BackUp getLatestVersion()
    {
        return history.pop();
    }
}


//测试代码
public class MementoPattern
{
    public static void main(String[] args) {
        History history = new History();
        Document document = new Document();
        document.Change("aaaaa");
        history.saveVersion(document.save());
        System.out.println(document.getString());

        document.Change("bbbbb");
        System.out.println(document.getString());
        document.Resume(history.getLatestVersion());
        System.out.println(document.getString());
    }
}