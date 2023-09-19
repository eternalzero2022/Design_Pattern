/*责任链模式
避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。
当需要处理一个请求时，我们让来链条中的第一个处理者去处理请求，如果第一个无法处理请求，就将请求传递给链条的下一个人去处理。
 */

//首先定义一个请求类
abstract class Request
{
    public int level;
    public Request(int level)
    {
        this.level = level;
    }
}

//定义一个处理者的抽象类
abstract class Handler
{
    public Handler nextHandler;
    public abstract void process(Request request);
    public Handler(Handler nextHandler)
    {
        this.nextHandler = nextHandler;
    }
}


//定义一些具体的处理者和请求
class SmallRequest extends Request
{
    public SmallRequest()
    {
        super(10);
    }
}

class BigRequest extends Request
{
    public BigRequest()
    {
        super(20);
    }
}

class SmallHandler extends Handler
{
    @Override
    public void process(Request request) {
        if(request.level<=15)
            System.out.println("SmallHandler Processing");
        else
        {
            System.out.println("SmallHandler Pass");
            nextHandler.process(request);
        }
    }

    public SmallHandler(Handler nextHandler)
    {
        super(nextHandler);
    }
}


class BigHandler extends Handler
{
    @Override
    public void process(Request request) {
        System.out.println("BigHandler Processing");
    }

    public BigHandler(Handler nextHandler)
    {
        super(nextHandler);
    }
}


//测试代码
public class ChainOfResponsibilityPattern
{
    public static void main(String[] args) {
        Handler big = new BigHandler(null);
        Handler small = new SmallHandler(big);
        small.process(new BigRequest());
    }
}