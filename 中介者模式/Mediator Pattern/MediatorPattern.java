/*中介者模式
用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
当每两个对象之间需要进行交互时，整体的关系呈现一种网状结构，使得耦合度和复杂度大大提高，还会导致一系列由于协调不能而产生的问题。
因此，可以使用一个中介者，让各个对象只与中介者联系。
 */

//以分配空教室为例
//首先，我们会有一些空教室被注册，而当学生想要借用空教室时，我们不必向每一个教室都尝试沟通，看看是不是空教室。而我们只需要与中介者进行交互，它会自动为我们分配一个存在的空教室

import java.util.ArrayList;
import java.util.List;

//定义一个教室类
class Classroom
{
    String id;
    boolean isEmpty = true;
    public Classroom(String id)
    {
        this.id = id;
    }
}

//接着定义一个中介者类
interface Mediator
{
    //定义一个注册教室的方法
    void register(Classroom classroom);

    Classroom getEmptyClassroom();
    void returnClassroom(Classroom classroom);
}

//定义具体实现类
class EmptyClassroomManager implements Mediator
{
    private ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
    @Override
    public void register(Classroom classroom) {
        classrooms.add(classroom);
    }

    @Override
    public Classroom getEmptyClassroom() {
        for(Classroom classroom:classrooms)
        {
            if(classroom.isEmpty)
            {
                classroom.isEmpty = false;
                System.out.println("教室"+classroom.id+"被借出");
                return classroom;
            }
        }
        return null;
    }

    @Override
    public void returnClassroom(Classroom classroom) {
        classroom.isEmpty = true;
        System.out.println("教室"+classroom.id+"被归还");
    }
}


//测试代码
public class MediatorPattern
{
    public static void main(String[] args) {
        EmptyClassroomManager manager = new EmptyClassroomManager();
        manager.register(new Classroom("101"));
        manager.register(new Classroom("102"));
        manager.register(new Classroom("103"));
        Classroom classroomToUse = manager.getEmptyClassroom();
        Classroom classroomToUse2 = manager.getEmptyClassroom();
        manager.returnClassroom(classroomToUse2);
        manager.returnClassroom(classroomToUse);    }
}


