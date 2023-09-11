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

//先创建一个表示节点类型（树的根节点还是子节点还是叶节点）
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
    private ArrayList<String> gameCharacterNameList;
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


//表示根节点
class TreeNodeRoot extends TreeNodeBase
{
    //重写表示获取节点类型的方法
    @Override
    public TreeType getTreeType()
    {
        return TreeType.ROOT;
    }

    //表示节点ID的字符串，用于精确获取每个节点
    private String treeID;
    //用于存放所有属于这一类的角色的列表。
    //用于存放所有通向它的子节点的管道的列表，每个管道内都有一个判断条件，用于过滤符合条件的筛选对象。
    private ArrayList<TreeNodeLink> treeNodeLinkList;

    //setter and getter
    public ArrayList<TreeNodeLink> getTreeNodeLinkList()
    {
        return treeNodeLinkList;
    }

    public void setTreeNodeLinkList(ArrayList<TreeNodeLink> treeNodeLinkList)
    {
        this.treeNodeLinkList = treeNodeLinkList;
    }
}

//定义一个函数式接口
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
    public static String name;
    //命途与属性
    private static Paths path;
    private static CombatTypes combatType;
}
//表示角色命途的枚举类
enum Paths
{
    THEHUNT,//巡猎
    ERUDITION//智识
}

enum CombatTypes
{
    QUANTUM,//量子
    IMAGINARY//虚数
}



//定义一个过滤器的接口，用来在程序中对每一层进行过滤操作
abstract class Filter
{
    //定义一个表示一次过滤的函数，这个函数会对传入的对象取得特定的内部属性，并遍历每个管道，调用每个管道内表示判断的函数，将这个属性传入，并判断管道是否通过属性，如果通过，则会返回管道通向的那个节点
    public TreeNodeBase filter(GameCharacter character,ArrayList<TreeNodeLink> treeNodeLinkList,Class<GameCharacter> characterClass)
    {
        String matterValue = getMatterValueFrom(characterClass);
        for(TreeNodeLink treeNodeLink:treeNodeLinkList)
        {
            //将对象值传入每个管道中用于判断的函数
            if(treeNodeLink.getRuleFunction().check(matterValue))
                return treeNodeLink.getNodeTo();
        }
        return null;
    }

    //定义一个用于获取不同的属性的函数，由于每个过滤器需要获取的参数都不相同，因此这个函数需要在不同的具体类中被不同实现，这里就写成抽象函数
    public abstract String getMatterValueFrom(Class<GameCharacter> characterClass);
}

//创建具体的过滤器类，不同的过滤器分别对应一种需要从角色身上取下来的物品
//一个用于获取角色命途的过滤类
class PathFilter extends Filter
{
    //使用Java反射机制获取类中的name变量
    @Override
    public String getMatterValueFrom(Class<GameCharacter> characterClass)
    {
        try
        {
            return ((Paths) (characterClass.getDeclaredField("path").get(null))).toString();
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
    public String getMatterValueFrom(Class<GameCharacter> characterClass)
    {
        try
        {
            return ((Paths) (characterClass.getDeclaredField("combatType").get(null))).toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}





