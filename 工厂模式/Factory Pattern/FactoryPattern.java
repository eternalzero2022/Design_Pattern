//工厂模式实例
/*
 抽象工厂模式
  定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
 */


// 不同的产品类,所有的产品都继承自同一个产品抽象类
abstract class Product
{

}
class Product1 extends Product
{

}
class Product2 extends Product
{

}
enum ProductType
{
    PRODUCT1,
    PRODUCT2
}

//简单工厂模式
//定义一个工厂类，客户端只需调用工厂类中的创建方法来创建，省去创建的具体细节
class SimpleFactroy
{
    public static Product CreateProduct(ProductType type)
    {
        //根据不同的输入的类型来创建不同的对象
        if(type == ProductType.PRODUCT1)
        {
            //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
            return new Product1();
        }
        if(type == ProductType.PRODUCT2)
        {
            //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
            return new Product2();
        }
        return null;
    }
}


//工厂模式
//定义一个抽象工厂类，不同的实例工厂可以创建不同的产品
//客户调用不同的工厂实例中的创建代码来创建不太的产品对象
interface Factroy
{
    Product CreateProduct();
}
//具体的工厂类，可以分别生产不同种类的产品
class Factory1 implements Factroy
{

    @Override
    public Product CreateProduct() {

        //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
        return new Product1();
    }
}

class Factory2 implements Factroy
{

    @Override
    public Product CreateProduct() {
        //这里可以添加其他创建产品所需的代码，将创建代码封装在该方法内部，对外隐藏具体创建细节
        return new Product2();
    }
}


//具体操作演示
public class FactoryPattern
{
    public static void main(String[] args) {
        //使用简单工厂来创建对象
        Product a = SimpleFactroy.CreateProduct(ProductType.PRODUCT1);
        //使用工厂来创建对象
        Product b = new Factory2().CreateProduct();
    }
}