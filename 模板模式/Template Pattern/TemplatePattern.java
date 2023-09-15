/*模板模式
定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
一件事情可能分为多个步骤去实现，而这些步骤可能会有不同的实现方法，因此我们定义一个抽象的方法类，其中会有一个骨架，它决定了不同的方法要按什么顺序执行，并且这些具体的方法则延迟到子类中去实现
 */

//以把大象装进冰箱为例
//这个方法需要三个步骤，分别是把冰箱门打开，把大象装进去，把冰箱门关上
//这三个基本步骤是一样的，但是可以有不同的实现方法，例如你可以轻轻的关或者用力的关，可以装入不同的大象品种
//定义一个抽象的模板类
abstract class AbstractPuttingElephant
{
    public void elephantPutting()
    {
        fridgeOpening();
        puttingElephantIntoFridge();
        fridgeClosing();
    }

    //第一步
    public abstract void fridgeOpening();
    //第二步
    public abstract void puttingElephantIntoFridge();
    //第三步
    public abstract void fridgeClosing();
}

class PuttingElephant extends AbstractPuttingElephant
{
    @Override
    public void fridgeOpening() {
        System.out.println("轻轻打开冰箱门");
    }

    @Override
    public void puttingElephantIntoFridge() {
        System.out.println("把亚洲象装进去");
    }

    @Override
    public void fridgeClosing() {
        System.out.println("用力关上冰箱门");
    }
}


//测试代码
public class TemplatePattern
{
    public static void main(String[] args) {
        new PuttingElephant().elephantPutting();
    }
}