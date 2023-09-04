/*
抽象工厂模式
定义一个超级工厂的接口，该超级工厂可以创建不同的产品的组合
调用时，可以直接通过不同的超级工厂的生产来进行产品的组合
 */

//定义两种不同的产品和装饰
interface Product
{

}
class Product1 implements Product
{

}
class Product2 implements Product
{

}

interface Decoration
{

}
class Decoration1 implements Decoration
{

}
class Decoration2 implements Decoration
{

}
//定义一个工厂接口，其中包含了创建产品和装饰的抽象方法
interface AbstractFactory
{
    Product CreateProduct();
    Decoration CreateDecoration();
}

//一个具体的超级工厂类，实现了工厂的接口，分别可以创建不同的一种产品和装饰
class SuperFactory1 implements AbstractFactory
{

    @Override
    public Product CreateProduct() {
        System.out.println("Product1 is created");
        return new Product1();
    }

    @Override
    public Decoration CreateDecoration() {
        System.out.println("Decoration1 is created");
        return new Decoration1();
    }
}

class SuperFactory2 implements AbstractFactory
{

    @Override
    public Product CreateProduct() {
        System.out.println("Product2 is created");
        return new Product2();
    }

    @Override
    public Decoration CreateDecoration() {
        System.out.println("Decoration2 is created");
        return new Decoration2();
    }
}

//实际演示
public class AbstractFactoryPattern
{
    public static void main(String[] args) {
        AbstractFactory factory1 = new SuperFactory1();
        AbstractFactory factory2 = new SuperFactory2();
        Product a = factory1.CreateProduct();
        Decoration c = factory1.CreateDecoration();
        Product b = factory2.CreateProduct();
        Decoration d = factory2.CreateDecoration();
    }
}