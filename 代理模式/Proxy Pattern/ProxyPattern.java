/*代理模式
为其他对象提供一种代理以控制对这个对象的访问。
一种基本的设计思路，也可以在其他设计模式中看到这个代理模式的影子，主要目的就是控制对该对象的访问
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//有一个普通类的接口，这个类可以实现一些功能
//以文件的快捷方式为例，一个文件可以被打开
//先定义一个文件的接口
interface File
{
    String Name();
    void open();
}

//一个具体的文件类
class File1 implements File
{

    @Override
    public void open()
    {
        System.out.println("文件被打开");
    }

    @Override
    public String Name()
    {
        return "文件1";
    }
}


//一个快捷方式，用于对文件的访问进行控制
class File1Proxy implements File
{
    private ArrayList<Date> dates;
    private File1 file1;
    //定义一个构造函数，用户不用为其传递一个File1的对象
    public File1Proxy()
    {
        file1 = new File1();
        dates = new ArrayList<Date>();
    }
    //对其提供一个代理开启和获取名字的接口
    @Override
    public void open() {
        file1.open();
        //同时它可以对其进行额外附加功能，例如日志记录
        dates.add(new Date(System.currentTimeMillis()));
    }

    @Override
    public String Name() {
        return file1.Name();
    }

    //访问日志记录
    public void Log()
    {
        for(Date date:dates)
            System.out.println((file1.Name()+" opened at ")+new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(date));
    }
}


//代码测试
public class ProxyPattern
{
    public static void main(String[] args) {
        File1Proxy proxy = new File1Proxy();
        proxy.open();
        proxy.Log();
    }
}
