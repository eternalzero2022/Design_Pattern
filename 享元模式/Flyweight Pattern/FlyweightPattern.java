/*享元模式
运用共享技术有效地支持大量细粒度的对象。
当创建了与之前创建过的对象相同的对象时，会先寻找是否有这个创建过的相似的对象，如果有的话就直接将这个对象返回
许多池技术也使用了享元模式，例如字符串常量池等
 */


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

//以图书馆借阅书籍为例
//首先定义一个书籍的抽象类
abstract class BookFlyWeight
{
    //定义一些状态的变量
    //表示书的ID
    protected String ID;
    //表示是否被借阅
    protected String bookName;
    protected boolean borrowState;
    //表示被借阅时的日期
    protected Date borrowDate;
    //表示借阅者的姓名
    protected String borrowerName;

    public BookFlyWeight(String bookName,String ID)
    {
        this.bookName = bookName;
        borrowState = false;
        borrowDate = null;
        borrowerName = null;
        this.ID = ID;
    }

    //定义一个借书的函数

    abstract public boolean onBeingBorrowed(String borrowerName);


    //定义一个还书的函数
    abstract public boolean onReturned();

    //定义一个获取借阅状态的函数
    public boolean isBorrowed()
    {
        return borrowState;
    }

    public String getBookName()
    {
        return bookName;
    }
    public String getID()
    {
        return ID;
    }
}


//定义一些具体的书籍类
class Calculus extends BookFlyWeight
{
    public Calculus(String ID)
    {
        super("微积分",ID);
    }
    @Override
    public boolean onBeingBorrowed(String borrowerName)
    {
        if(borrowState)
        {
            System.out.println("借阅失败");
            return false;
        }
        borrowState = true;
        borrowDate = new Date(System.currentTimeMillis());
        this.borrowerName = borrowerName;
        System.out.println("书籍 "+bookName+" (id:"+ID+") 被 "+ borrowerName +" 借出 于 "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(borrowDate));
        return true;
    }

    @Override
    public boolean onReturned()
    {
        if(!borrowState)
        {
            System.out.println("归还失败");
            return false;
        }
        borrowState = false;
        borrowDate = null;
        borrowerName = null;
        System.out.println("书籍 "+bookName+" (id:"+ID+") 被归还");
        return true;
    }
}

class LinearAlgebra extends BookFlyWeight
{
    public LinearAlgebra(String ID)
    {
        super("线性代数",ID);
    }
    @Override
    public boolean onBeingBorrowed(String borrowerName)
    {
        //如果已经被借走则返回false
        if(borrowState)
        {
            System.out.println("借阅失败");
            return false;
        }
        borrowState = true;
        borrowDate = new Date(System.currentTimeMillis());
        this.borrowerName = borrowerName;
        System.out.println("书籍 "+bookName+" (id:"+ID+") 被 "+ borrowerName +" 借出 于 "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(borrowDate));
        return true;
    }

    @Override
    public boolean onReturned()
    {
        //如果本来就没有被借出去就返回false
        if(!borrowState)
        {
            System.out.println("归还失败");
            return false;
        }
        borrowState = false;
        borrowDate = null;
        borrowerName = null;
        System.out.println("书籍 "+bookName+" (id:"+ID+") 被归还");
        return true;
    }
}

//定义一个享元类
class BookFlyWeightFactory
{
    //将这个工厂设为单例模式
    private static BookFlyWeightFactory instance = new BookFlyWeightFactory();

    //定义一个哈希表用于存放所有的自行车
    private Set<BookFlyWeight> bookPool;
    private BookFlyWeightFactory()
    {
        bookPool = new HashSet<BookFlyWeight>();
        //放四本微积分书和三本线代书
        bookPool.add(new Calculus("a001"));
        bookPool.add(new Calculus("a002"));
        bookPool.add(new Calculus("a003"));
        bookPool.add(new Calculus("a004"));
        bookPool.add(new LinearAlgebra("a005"));
        bookPool.add(new LinearAlgebra("a006"));
        bookPool.add(new LinearAlgebra("a007"));
    }

    public static BookFlyWeightFactory getInstance()
    {
        return instance;
    }

    //定义一个表示借一本书的函数
    public BookFlyWeight getBook(String name)
    {
        List<BookFlyWeight> books = bookPool.stream().filter(book->book.getBookName().equals(name) && !book.isBorrowed()).toList();
        if(books.size()==0)
            return null;
        BookFlyWeight bookToBorrow = books.get(0);
        if(bookToBorrow.onBeingBorrowed(name))
            return bookToBorrow;
        return null;
    }
}


//测试代码
public class FlyweightPattern
{
    public static void main(String[] args) {
        BookFlyWeightFactory factory = BookFlyWeightFactory.getInstance();
        BookFlyWeight book1 = factory.getBook("微积分");
        BookFlyWeight book2 = factory.getBook("微积分");
        book2.onReturned();
        book2 = factory.getBook("微积分");
        BookFlyWeight book3 = factory.getBook("线性代数");
        BookFlyWeight book4 = factory.getBook("线性代数");
        book3.onReturned();
        book4.onReturned();
    }
}
