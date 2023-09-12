/*组合模式
将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
组合模式依据树形结构来组合对象，用来表示部分以及整体层次。

实现这个类需要一种能够遍历所有树的方法
可以用于对不同的事物进行分类，并把它们放在决策树中的某个位置
 */

//以星穹铁道中为角色分类为例
//首先，我们需要先创建一个树的类型，包括树的节点类、整个树的数据类、管道类等等。
//最终这棵树的所有果实（属于树的节点之一）会存放所有属于这个类的角色。

//本次分类我们按照角色的命途以及属性来分类

import com.sun.source.tree.Tree;


import java.util.HashMap;
import java.util.Objects;
import java.util.ArrayList;

//先创建一个表示节点类型（树的子节点还是叶节点）
enum TreeType
{
    ROOT,
    CHILD,
    LEAF
}

//先创建一个表示树的节点和抽象类
abstract class TreeNodeBase
{
    //表示节点ID的字符串，用于精确获取每个节点
    private String treeID;
    //获取节点类型的方法
    public abstract TreeType getTreeType();
    //每个节点都会有一种对应于本层的过滤器，这里将过滤器的名字传入，以节省内存，需要时可以对照着表来确定实例化的过滤器
    private String filterNeedName;

    public String getFilterNeedName() {
        return filterNeedName;
    }

    public void setFilterNeedName(String filterNeedName) {
        this.filterNeedName = filterNeedName;
    }

    public String getTreeID() {
        return treeID;
    }

    public void setTreeID(String treeID) {
        this.treeID = treeID;
    }






}
class TreeNodeChild extends TreeNodeBase
{
    //重写表示获取节点类型的方法
    @Override
    public TreeType getTreeType()
    {
        return TreeType.CHILD;
    }


    //用于存放所有通向它的子节点的管道的列表，每个管道内都有一个判断条件，用于过滤符合条件的筛选对象。
    private ArrayList<TreeNodeLink> treeNodeLinkList;


    public ArrayList<TreeNodeLink> getTreeNodeLinkList()
    {
        return treeNodeLinkList;
    }

    public void setTreeNodeLinkList(ArrayList<TreeNodeLink> treeNodeLinkList)
    {
        this.treeNodeLinkList = treeNodeLinkList;
    }

}


//叶节点
class TreeNodeLeaf extends TreeNodeBase
{
    //重写表示获取节点类型的方法
    @Override
    public TreeType getTreeType()
    {
        return TreeType.LEAF;
    }

    //表示节点ID的字符串，用于精确获取每个节点
    private String treeID;
    //用于存放所有属于这一类的角色的列表。这里存放名字来代表这个角色类
    private ArrayList<String> gameCharacterNameList = new ArrayList<String>();
    //一些setter and getter
    public ArrayList<String> getCharacterNameList()
    {
        return gameCharacterNameList;
    }

    public void setTreeNodeLinkList(ArrayList<String > gameCharacterNameList)
    {
        this.gameCharacterNameList = gameCharacterNameList;
    }
}




//定义一个用于存放所有树的数据的类
class TreeRich
{
    private TreeNodeBase rootNode;

    public TreeNodeBase getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNodeBase rootNode) {
        this.rootNode = rootNode;
    }
}

//定义一个函数式接口，用于为他赋予lambda值
interface RuleFunction
{
    boolean check(String matterValue);
}

//定义一个管道类，这个类会被存放在所有的子节点和根节点中，用于对数据进行过滤，只有满足管道提出的条件的才可以
class TreeNodeLink
{
    //一个用于检验是否满足需求的函数对象，可以使用lambda表达式来声明
    RuleFunction ruleFunction;
    //表示该管道的来源节点与通向的节点
    TreeNodeBase nodeFrom;
    TreeNodeBase nodeTo;

    public RuleFunction getRuleFunction() {
        return ruleFunction;
    }

    public void setRuleFunction(RuleFunction ruleFunction) {
        this.ruleFunction = ruleFunction;
    }

    public TreeNodeBase getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(TreeNodeBase nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public TreeNodeBase getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(TreeNodeBase nodeTo) {
        this.nodeTo = nodeTo;
    }
}


//定义一个角色类，不同的角色有不同的属性
abstract class GameCharacter
{
    public String name;
    //命途与属性
    public Paths path;
    public CombatTypes combatType;
    public GameCharacter(String name,Paths path,CombatTypes combatType)
    {
        this.name = name;
        this.path = path;
        this.combatType = combatType;
    }
}
//表示角色命途的枚举类
enum Paths
{
    THEHUNT,//巡猎
    DESTRUCTION//毁灭
}

enum CombatTypes
{
    QUANTUM,//量子
    IMAGINARY//虚数
}

//创建一个存储名字与类一一对应的表



//定义一个过滤器的接口，用来在程序中对每一层进行过滤操作
abstract class Filter
{
    //定义一个表示一次过滤的函数，这个函数会对传入的对象取得特定的内部属性，并遍历每个管道，调用每个管道内表示判断的函数，将这个属性传入，并判断管道是否通过属性，如果通过，则会返回管道通向的那个节点
    public TreeNodeBase filter(ArrayList<TreeNodeLink> treeNodeLinkList,String characterName)
    {
        String matterValue = getMatterValueFrom(characterName);
        for(TreeNodeLink treeNodeLink:treeNodeLinkList)
        {
            //将对象值传入每个管道中用于判断的函数
            if(treeNodeLink.getRuleFunction().check(matterValue))
                return treeNodeLink.getNodeTo();
        }
        return null;
    }

    //定义一个用于获取不同的属性的函数，由于每个过滤器需要获取的参数都不相同，因此这个函数需要在不同的具体类中被不同实现，这里就写成抽象函数
    public abstract String getMatterValueFrom(String characterName);
}

//创建具体的过滤器类，不同的过滤器分别对应一种需要从角色身上取下来的物品
//一个用于获取角色命途的过滤类
class PathFilter extends Filter
{
    //使用Java反射机制获取类中的name变量
    @Override
    public String getMatterValueFrom(String characterName)
    {
        try
        {
            return (EngineConfig.CharacterHashMap.get(characterName).path.toString());
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

//用于获取角色属性的过滤类
class CombatTypeFilter extends Filter
{
    //使用Java反射机制获取类中的name变量
    @Override
    public String getMatterValueFrom(String characterName)
    {
        try
        {
            return ((EngineConfig.CharacterHashMap.get(characterName).combatType).toString());
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

//是时候开始编写最终引擎了，定义一个可以遍历整个树并最终达到效果的运行引擎类
interface IEngine
{
    public boolean Process(GameCharacter character,TreeRich treeRich);
}

//定义一个引擎类的配置类，用于存放一些引擎类会用到的成员变量，引擎类只需继承此类
class EngineConfig
{
    //用于通过一个字符串寻找到指定的过滤器的map表
    static HashMap<String,Filter> filterHashMap;
    //用于通过一个字符串寻找到指定的角色对象的map表
    static HashMap<String,GameCharacter> CharacterHashMap;
    //在静态代码块中初始化hashmap
    static
    {
        filterHashMap = new HashMap<String,Filter>();
        CharacterHashMap = new HashMap<String,GameCharacter>();
        filterHashMap.put("PathFilter",new PathFilter());
        filterHashMap.put("CombatTypeFilter",new CombatTypeFilter());
        CharacterHashMap.put("希儿",new Seele());
        CharacterHashMap.put("丹恒·饮月",new DanHeng_ImbibitorLunae());
    }
}

class Engine extends EngineConfig implements IEngine
{
    //对一个角色类进行归类的总方法
    @Override
    public boolean Process(GameCharacter character,TreeRich treeRich)
    {
        //获取角色名称
        String characterName = character.name;
        TreeNodeBase rootNode = treeRich.getRootNode();
        //定义一个表示当前节点的变量
        TreeNodeBase nowNode;
        nowNode = rootNode;
        //如果不是叶节点就继续循环
        while(nowNode.getTreeType()!=TreeType.LEAF)
        {
            Filter filter = filterHashMap.get(nowNode.getFilterNeedName());
            nowNode = filter.filter(((TreeNodeChild)nowNode).getTreeNodeLinkList(),characterName);
        }
        //尝试为其进行归类
        try
        {
            ((TreeNodeLeaf)nowNode).getCharacterNameList().add(characterName);
            System.out.println(characterName + "已被成功归类");
            return true;
        }
        catch (Exception e)
        {
            System.out.println("失败");
            return false;
        }
    }
}



//定义两个具体的角色类
class Seele extends GameCharacter
{
    public Seele()
    {
        super("希儿",Paths.THEHUNT,CombatTypes.QUANTUM);
    }
}

class DanHeng_ImbibitorLunae extends GameCharacter
{
    public DanHeng_ImbibitorLunae()
    {
        super("丹恒·饮月",Paths.DESTRUCTION,CombatTypes.IMAGINARY);
    }

}


//开始测试代码
public class CompositePattern
{
    public static void main(String[] args) {
        //初始化整棵树
        TreeRich treeRich = new TreeRich();
        TreeNodeBase node0 = new TreeNodeChild();
        ((TreeNodeChild) node0).setFilterNeedName("PathFilter");
        treeRich.setRootNode(node0);

        TreeNodeLink link1 = new TreeNodeLink();
        link1.setNodeFrom(node0);
        link1.setRuleFunction((String a)->{
            if(a.equals("THEHUNT"))
                System.out.println("命途：巡猎");
            return a.equals("THEHUNT");
        });

        TreeNodeLink link2 = new TreeNodeLink();
        link2.setNodeFrom(node0);
        link2.setRuleFunction((String a)->{
            if(a.equals("DESTRUCTION"))
                System.out.println("命途：毁灭");
            return a.equals("DESTRUCTION");
        });

        ArrayList<TreeNodeLink> linkList0 = new ArrayList<TreeNodeLink>();
        linkList0.add(link1);
        linkList0.add(link2);
        ((TreeNodeChild)node0).setTreeNodeLinkList(linkList0);

        TreeNodeBase node1 = new TreeNodeChild();
        TreeNodeBase node2 = new TreeNodeChild();
        ((TreeNodeChild) node1).setFilterNeedName("CombatTypeFilter");
        ((TreeNodeChild) node2).setFilterNeedName("CombatTypeFilter");
        link1.setNodeTo(node1);
        link2.setNodeTo(node2);

        TreeNodeLink link11 = new TreeNodeLink();
        TreeNodeLink link12 = new TreeNodeLink();
        TreeNodeLink link21 = new TreeNodeLink();
        TreeNodeLink link22 = new TreeNodeLink();
        link11.setNodeFrom(node1);
        link12.setNodeFrom(node1);
        link21.setNodeFrom(node2);
        link22.setNodeFrom(node2);
        link11.setRuleFunction((String a)->{
            if(a.equals("QUANTUM"))
                System.out.println("属性：量子");
            return a.equals("QUANTUM");
        });
        link12.setRuleFunction((String a)->{
            if(a.equals("IMAGINARY"))
                System.out.println("属性：虚数");
            return a.equals("IMAGINARY");
        });
        link21.setRuleFunction((String a)->{
            if(a.equals("QUANTUM"))
                System.out.println("属性：量子");
            return a.equals("QUANTUM");
        });
        link22.setRuleFunction((String a)->{
            if(a.equals("IMAGINARY"))
                System.out.println("属性：虚数");
            return a.equals("IMAGINARY");
        });
        ArrayList<TreeNodeLink> linkList1 = new ArrayList<TreeNodeLink>();
        linkList1.add(link11);
        linkList1.add(link12);
        ((TreeNodeChild)node1).setTreeNodeLinkList(linkList1);
        ArrayList<TreeNodeLink> linkList2 = new ArrayList<TreeNodeLink>();
        linkList2.add(link21);
        linkList2.add(link22);
        ((TreeNodeChild)node2).setTreeNodeLinkList(linkList2);

        TreeNodeBase node11 = new TreeNodeLeaf();
        TreeNodeBase node12 = new TreeNodeLeaf();
        TreeNodeBase node21 = new TreeNodeLeaf();
        TreeNodeBase node22 = new TreeNodeLeaf();
        link11.setNodeTo(node11);
        link12.setNodeTo(node12);
        link21.setNodeTo(node21);
        link22.setNodeTo(node22);


        new Engine().Process(new Seele(),treeRich);
        new Engine().Process(new DanHeng_ImbibitorLunae(),treeRich);
    }
}





