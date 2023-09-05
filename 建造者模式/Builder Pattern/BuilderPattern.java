/*建造者模式
将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
 */

//以星穹铁道金人旧巷市廛喧中的摆放货物为例
//定义一个货物的接口，每个货物都有各自的属性，包括占用空间、收益等等，

import java.util.ArrayList;

interface Item
{
    String Name();
    int SmallSpaceOccupyNum();
    int BigSpaceOccupyNum();
    int SmallSpaceProvideNum();
    int BigSpaceProvideNum();
    int Reward();
}

//定义一些物品，它们各自有不同的属性，通过方法返回来获取
class Pot implements Item
{

    @Override
    public String Name() {
        return "罐子";
    }

    @Override
    public int SmallSpaceOccupyNum() {
        return 1;
    }

    @Override
    public int BigSpaceOccupyNum() {
        return 0;
    }

    @Override
    public int SmallSpaceProvideNum() {
        return 0;
    }

    @Override
    public int BigSpaceProvideNum() {
        return 0;
    }

    @Override
    public int Reward() {
        return 150;
    }
}

class PaperBox implements Item
{

    @Override
    public String Name() {
        return "纸盒";
    }

    @Override
    public int SmallSpaceOccupyNum() {
        return 2;
    }

    @Override
    public int BigSpaceOccupyNum() {
        return 0;
    }

    @Override
    public int SmallSpaceProvideNum() {
        return 1;
    }

    @Override
    public int BigSpaceProvideNum() {
        return 0;
    }

    @Override
    public int Reward() {
        return 250;
    }
}

class SmallBlanket implements Item
{

    @Override
    public String Name() {
        return "小地毯";
    }

    @Override
    public int SmallSpaceOccupyNum() {
        return 2;
    }

    @Override
    public int BigSpaceOccupyNum() {
        return 0;
    }

    @Override
    public int SmallSpaceProvideNum() {
        return 0;
    }

    @Override
    public int BigSpaceProvideNum() {
        return 1;
    }

    @Override
    public int Reward() {
        return 150;
    }
}

class Table implements Item
{

    @Override
    public String Name() {
        return "桌子";
    }

    @Override
    public int SmallSpaceOccupyNum() {
        return 0;
    }

    @Override
    public int BigSpaceOccupyNum() {
        return 4;
    }

    @Override
    public int SmallSpaceProvideNum() {
        return 0;
    }

    @Override
    public int BigSpaceProvideNum() {
        return 1;
    }

    @Override
    public int Reward() {
        return 1200;
    }
}

class BigBlanket implements Item
{

    @Override
    public String Name() {
        return "大地毯";
    }

    @Override
    public int SmallSpaceOccupyNum() {
        return 0;
    }

    @Override
    public int BigSpaceOccupyNum() {
        return 4;
    }

    @Override
    public int SmallSpaceProvideNum() {
        return 4;
    }

    @Override
    public int BigSpaceProvideNum() {
        return 0;
    }

    @Override
    public int Reward() {
        return 1100;
    }
}



//定义一个安装包类，用于存放不同物品的组合，并且有一些用于统计的成员变量,在这里就是仓库类
interface Package
{
    Package AppendItem(Item item);
    void GetDetails();
}
class Warehouse implements Package
{
    //存储所有存放在里面的货物的列表
    public ArrayList<Item> itemlist;
    //存放表示收益总和的变量
    public int rewardSum;
    //存放表示小空间占用总数的变量
    public int smallSpaceUse;
    //存放表示大空间占用总数的变量
    public int bigSpaceUse;
    public Warehouse()
    {
        itemlist = new ArrayList<Item>();
        rewardSum = 0;
        smallSpaceUse = 0;
        bigSpaceUse = 0;
    }
    public Package AppendItem(Item item)
    {
        itemlist.add(item);
        rewardSum += item.Reward();
        smallSpaceUse +=(item.SmallSpaceOccupyNum()-item.SmallSpaceProvideNum());
        bigSpaceUse += (item.BigSpaceOccupyNum()-item.BigSpaceProvideNum());
        return this;
    }

    //用于统计并输出统计信息的一个函数
    public void GetDetails()
    {
        System.out.println("仓库中总共存放了"+itemlist.size()+"个货物，它们分别是：");
        for(Item i:itemlist)
        {
            System.out.print(i.Name()+" ");
        }

        System.out.println("\n共占用"+smallSpaceUse+"个小形区域");
        System.out.println("共占用"+bigSpaceUse+"个大形区域");
        System.out.println("总收益为"+rewardSum);
    }
}


//定义一个建造类，这个建造类是建造者模式的核心，
//它用于将不同的物品进行组合，并提供了不同的组合方案，用于按照某种方案来建造自定义仓库
class Builder
{
    public Package TableWithBox()
    {
        return new Warehouse()
                .AppendItem(new Table())
                .AppendItem(new PaperBox())
                .AppendItem(new PaperBox());
    }

    public Package AllPot()
    {
        return new Warehouse()
                .AppendItem(new Pot())
                .AppendItem(new Pot())
                .AppendItem(new Pot())
                .AppendItem(new Pot())
                .AppendItem(new Pot());
    }

    public Package AllIn()
    {
        return new Warehouse()
                .AppendItem(new Pot())
                .AppendItem(new PaperBox())
                .AppendItem(new SmallBlanket())
                .AppendItem(new Table())
                .AppendItem(new BigBlanket());
    }
}


//测试代码
public class BuilderPattern
{
    public static void main(String[] args) {
        // 创建一个建造者对象
        new Builder().AllIn().GetDetails();
        System.out.println();
        new Builder().TableWithBox().GetDetails();
        System.out.println();
        new Builder().AllPot().GetDetails();
    }
}


