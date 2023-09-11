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

//先创建一个表示节点类型（树的根节点还是子节点还是叶节点）
enum TreeType
{
    ROOT,
    CHILD,
    LEAF
}

//先创建一个表示树的节点和抽象类和接口
interface ITreeNode
{
    //获取节点类型的方法
    public TreeType getTreeType();
}
abstract class TreeNodeChildBase implements ITreeNode
{
    //重写表示获取节点类型的方法
    @Override
    public TreeType getTreeType()
    {
        return TreeType.CHILD;
    }

    //表示节点ID的字符串，用于精确获取每个节点
    private String treeID;
    //用于存放所有通向它的子节点的管道的列表，每个管道内都有一个判断条件，用于过滤符合条件的筛选对象。
    private ArrayList<TreeNodeLink> treeNodeLinkList;

    //一些setter and getter

    public String getTreeID() {
        return treeID;
    }

    public void setTreeID(String treeID) {
        this.treeID = treeID;
    }

    public ArrayList<TreeNodeLink> getTreeNodeLinkList()
    {
        return treeNodeLinkList;
    }

    public void setTreeNodeLinkList(TreeNodeLink treeNodeLinkList)
    {
        this.treeNodeLinkList = treeNodeLinkList;
    }
}

