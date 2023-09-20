/*命令模式
将一个请求封装成一个对象，从而使您可以用不同的请求对客户进行参数化。
web开发的MVC模型中，图形界面层和业务逻辑层需要分开来，而它们之间的连接便是通过命令
 */

//以网页中的按钮为例
//首先定义一个表示命令的接口和具体类
interface Command
{
    void Execute();
}

//具体的打印命令

class PrintCommand implements Command
{
    //打印命令中需要一个字符串，作为打印的内容
    private String stringToPrint;
    //将命令与业务逻辑绑定
    private PrintService service = new PrintService();
    public PrintCommand(String stringToPrint)
    {
        this.stringToPrint=stringToPrint;
    }

    @Override
    public void Execute() {
        service.print(stringToPrint);
    }
}

//定义一个业务逻辑类
class PrintService
{
    public void print(String a)
    {
        System.out.println(a);
    }
}


//定义一个按钮类
class Button
{
    protected Command command;
    public void bindCommand(Command command)
    {
        this.command=command;
    }
    public void onPress()
    {
        command.Execute();
    }
}


public class CommandPattern
{
    public static void main(String[] args) {
        Button a = new Button();
        a.bindCommand(new PrintCommand("aaavvvbbb"));
        a.onPress();
    }
}
