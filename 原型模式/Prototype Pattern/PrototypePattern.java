/*原型模式
用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
当想要克隆某一个对象时，不可以在对象的外部去克隆，因为有些私有成员是无法被访问到的
因此需要让这个对象所在的类自己实现克隆接口
 */

//以星穹铁道中的丰饶灵兽·奎木为例
//定义一个原型接口，内部包含一个克隆方法，允许该对象被克隆
interface Prototype
{
    //克隆方法返回一个对象
    Object clone();
}

//定义一个表示丰饶灵兽·奎木的类，其中包含该单位的各项数值,该单位可以克隆自身（召唤另一只和它属性相同的狼）

class Wolf implements Prototype
{
    //类中所有的属性都是私有属性，故无法在外部对该对象进行克隆
    private String name;
    private int maxHealth;
    private int health;
    private int damage;
    //定义一个无参数构造方法
    public Wolf()
    {
        this.name = "丰饶灵兽·奎木";
        this.maxHealth = 20000;
        this.health = 20000;
        this.damage = 1000;
    }

    //为了实现克隆的效果，需要再定义一个克隆的构造方法，传递一个Wolf对象并让新的对象继承该对象的全部或大部分属性
    public Wolf(Wolf wolf)
    {
        //保留原有大部分属性，只对名称进行修改
        this.name = "狈影";
        this.maxHealth = wolf.maxHealth;
        this.health = wolf.health;
        this.damage = wolf.damage;
    }

    //实现克隆接口
    @Override
    public Object clone() {
        //通过克隆的构造方法来新建一个新的对象并返回，以达到克隆的效果
        return new Wolf(this);
    }

}