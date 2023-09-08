/*装饰器模式
动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
相比起直接继承子类这种静态的改变，通过组合的方式可以动态的为子类添加新的功能
新建一个类，在内部传入原来那个类的对象，并在原来的方法的基础上增添新的方法
 */

//以星穹铁道中的模拟宇宙的错误怪为例
//在模拟宇宙中，错误怪都比原来正常模式下的怪物多出了一些新的特性（比如可可利亚召唤冰风）

//先定义一个敌方接口
interface Enemy
{
    void Attack();
}

//可可利亚的具体类，这个存放的是正常模式下的可可利亚的属性，可以进行普通攻击
class Kekeliya implements Enemy
{

    @Override
    public void Attack() {
        System.out.println("冰寒的审判！");
    }
}

//在模拟宇宙下，可可利亚除了原有的能力之外，又获得了新的召唤冰风的能力
//使用装饰类进行修饰
class KekeliyaDecorator
{
    //存放原来可可利亚的对象
    public Kekeliya kekeliya;
    public KekeliyaDecorator(Kekeliya kekeliya)
    {
        this.kekeliya = kekeliya;
    }

    //覆盖原有的方法（如果原有类是一个接口那么就重写）
    public void Attack()
    {
        kekeliya.Attack();
    }

    //增添新的方法，实现修饰器模式的功能
    public void SpecialBehaviour()
    {
        System.out.println("释放冰风！");
    }
}

//测试代码
public class DecoratorPattern
{
    public static void main(String[] args) {
        new KekeliyaDecorator(new Kekeliya()).Attack();
        new KekeliyaDecorator(new Kekeliya()).SpecialBehaviour();
    }
}