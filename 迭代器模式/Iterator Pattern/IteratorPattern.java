import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*迭代器模式
提供一种方法顺序访问一个聚合对象中各个元素，而又无须暴露该对象的内部表示。
我们可以通过调用迭代器获取元素的方法来获取到所有的内容
例如每次调用迭代器的
 */


//以遍历链表中的每一个对象为例
//首先定义一个我们需要的具体的物品类
class Item
{
    public String name;
    public Item(String name) {
        this.name = name;
    }
}

//然后定义一个用来存放这种类的链表节点
class Node
{
    public Item item;
    public Node nextNode;

    public Node(Item item,Node nextNode)
    {
        this.item = item;
        this.nextNode = nextNode;
    }
}

//将整个链表类定义出来
class ItemLinkedList
{
    //定义一个链表的首节点
    Node firstNode;

    //定义一个添加节点的方法，在链表的尾部添加新的节点
    public void add(Item item)
    {
        if(firstNode == null)
        {
            firstNode = new Node(item,null);

        }
        Node nowNode = firstNode;
        Node pNode = null;
        while(nowNode!=null)
        {
            pNode = nowNode;
            nowNode = nowNode.nextNode;
        }
        nowNode = pNode;
        nowNode.nextNode = new Node(item,null);
    }
    //在类中定义一个迭代器子类
    class LinkedListIterator implements Iterator<Item>
    {
        //迭代器中有一个指向这个链表的某一个节点的变量
        Node cursor;
        //实现迭代器接口中的方法
        //表示是否存在下一个节点
        @Override
        public boolean hasNext() {
            return cursor!=null;
        }

        //用于返回当前节点的值并指向下一个节点
        @Override
        public Item next() {
            if(cursor == null)return null;
            Item value = cursor.item;
            cursor = cursor.nextNode;
            return value;
        }

        //创建迭代器对象时自动将迭代器指向的节点设为链表的首节点
        public LinkedListIterator()
        {
            cursor = firstNode;
        }
    }
    //定义一个获取该类的迭代器的方法，迭代器在被获取时会获取到当前链表的首元素，因此如果这个迭代器被创建时链表中没有元素，那么就会直接视为没有元素。
    public LinkedListIterator iterator()
    {
        return new LinkedListIterator();
    }
}


public class IteratorPattern
{

    public static void main(String[] args) {
        ItemLinkedList list = new ItemLinkedList();
        list.add(new Item("item1"));
        list.add(new Item("item2"));
        list.add(new Item("item3"));
        list.add(new Item("item4"));
        list.add(new Item("item5"));
        list.add(new Item("item6"));
        ItemLinkedList.LinkedListIterator itemListIterator = list.iterator();
        while(itemListIterator.hasNext())
        {
            Item item = itemListIterator.next();
            System.out.println("当前节点的item是"+item.name);
        }
        System.out.println("已经遍历完所有节点");
    }
}
