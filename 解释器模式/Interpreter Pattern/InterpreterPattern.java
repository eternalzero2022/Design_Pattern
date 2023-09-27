/*解释器模式
给定一个语言，定义它的文法表示，并定义一个解释器，这个解释器使用该标识来解释语言中的句子。
例如解析一个加减法运算，我们可以根据里面的加减法的
 */

//以加减法解释器为例
//首先定义一个表示内容的类，里面可以存储整个命令以及中间变量
class Context
{
    String command;
    int value;
    int offset;
    public Context(String command) {
        this.command = command;
        offset = 0;
    }
}

//定义一个interpreter的抽象类，不同的interpreter可以负责解析不同的内容
abstract class Interpreter
{
    //定义一个interpret的抽象方法
    public abstract int interpret(Context context);

    //定义一些内置的词法分析器
    public int findNextNumber(Context context)
    {
        String result = "";
        while(context.offset < context.command.length())
        {
            if(Character.isDigit(context.command.charAt(context.offset)))//是数字
            {
                result += context.command.charAt(context.offset)+"";
                context.offset++;
            }
            else {
                break;
            }
        }
        if(result.equals(""))
            return 0;
        return Integer.parseInt(result);
    }

    public String findNextOperation(Context context)
    {
        if(context.offset < context.command.length())
        {
            return context.command.charAt(context.offset++)+"";
        }
        return "=";
    }
}


//定义一些具体的解释器类，主要实现interpret方法，会根据不同的运算符去进行运算
class AddInterpreter extends Interpreter
{
    @Override
    public int interpret(Context context) {
        //先找下一个数，然后再找下一个运算符，判断是哪一种运算符，再转到对应的解释器去进行接下来的操作
        context.value += findNextNumber(context);
        switch(findNextOperation(context))
        {
            case "+":
                return new AddInterpreter().interpret(context);
            case "-":
                return new SubInterpreter().interpret(context);
            case "=":
                return new EqualsInterpreter().interpret(context);
            default:
                throw new RuntimeException("Invalid operation");
        }
    }
}


class SubInterpreter extends Interpreter
{
    @Override
    public int interpret(Context context) {
        //先找下一个数，然后再找下一个运算符，判断是哪一种运算符，再转到对应的解释器去进行接下来的操作
        context.value -= findNextNumber(context);
        switch(findNextOperation(context))
        {
            case "+":
                return new AddInterpreter().interpret(context);
            case "-":
                return new SubInterpreter().interpret(context);
            case "=":
                return new EqualsInterpreter().interpret(context);
            default:
                throw new RuntimeException("Invalid operation");

        }
    }
}

class EqualsInterpreter extends Interpreter
{
    @Override
    public int interpret(Context context) {
        //由于是最后一级解释器，直接输出结果
        return context.value;
    }
}


//测试代码
public class InterpreterPattern
{
    public static void main(String[] args) {
        System.out.println(new AddInterpreter().interpret(new Context("3+5+8-4-6-9-8")));
    }
}