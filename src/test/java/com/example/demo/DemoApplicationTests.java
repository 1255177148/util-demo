package com.example.demo;

import com.example.demo.entity.CloneDepth;
import com.example.demo.entity.CloneShallow;
import com.example.demo.entity.Prototype;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    /**
     * 测试原型设计模式（浅克隆与深克隆的用法和区别）
     * 若要克隆的对象内部有引用对象（即内部嵌套了其他类的对象），则浅克隆只是克隆了该对象内部引用对象的引用，
     * 并不会独立出来，修改该对象内部应用对象的属性会对原型对象造成影响；
     * 而深克隆则会完全复制对象的所有数据，额外开辟空间来存储复制出来的数据，完全独立于原型对象，修改克隆对象
     * 的属性值不会对原型对象造成影响
     */
    @Test
    public void testPrototype() {
        /**
         * 这里的测试思路为：
         * 1、首先，cloneDepth对象为深度克隆，cloneShallow对象为浅克隆;
         * 2、先为原型对象prototype以及其内部的引用对象cloneDepth和cloneShallow赋值;
         * 3、然后克隆prototype对象（其中内部引用对象cloneDepth为深克隆，cloneShallow为浅克隆）;
         * 4、并为克隆对象prototypeClone以及内部引用对象重新赋值;
         * 5、分别查看原型对象与克隆对象的属性值;
         * 6、预计结果：由于cloneShallow对象为浅克隆，所以克隆且为克隆对象中的应用对象cloneShallow赋值后，
         *    原型对象中的引用对象cloneShallow的属性值也会被改变。相应的，cloneDepth对象为深克隆，所以克隆后
         *    赋的值对原型中的引用对象cloneDepth无影响。
         */

        /**
         * 先创建对象
         */
        Prototype prototype = new Prototype();
        CloneDepth cloneDepth = new CloneDepth();
        CloneShallow cloneShallow = new CloneShallow();
        cloneDepth.setName("小猫咪");
        cloneDepth.setAge(1);
        cloneShallow.setName("小狗狗");
        cloneShallow.setAge(2);
        prototype.setAge(3);
        prototype.setName("宠物");
        prototype.setCloneDepth(cloneDepth);
        prototype.setCloneShallow(cloneShallow);
        System.out.println("克隆前每个原型对象属性的值为：");
        System.out.println("prototype对象的属性为:name=" + prototype.getName() + ",age=" + prototype.getAge());
        System.out.println("cloneDepth对象的属性为:name=" + cloneDepth.getName() + ",age=" + cloneDepth.getAge());
        System.out.println("cloneShallow对象的属性为:name=" + cloneShallow.getName() + ",age=" + cloneShallow.getAge());
        /**
         * 克隆对象，深度克隆和浅度克隆同时进行，校验结果
         */
        Prototype prototypeClone = prototype.clone();
        prototypeClone.getCloneDepth().setName("大猫咪");
        prototypeClone.getCloneDepth().setAge(3);
        prototypeClone.getCloneShallow().setName("大狗狗");
        prototypeClone.getCloneShallow().setAge(3);
        prototypeClone.setName("大宠物");
        prototypeClone.setAge(6);
        System.out.println("克隆后每个原型对象属性的值为：");
        System.out.println("prototype对象的属性为:name=" + prototype.getName() + ",age=" + prototype.getAge());
        System.out.println("cloneDepth对象的属性为:name=" + cloneDepth.getName() + ",age=" + cloneDepth.getAge());
        System.out.println("cloneShallow对象的属性为:name=" + cloneShallow.getName() + ",age=" + cloneShallow.getAge());
        System.out.println("克隆后每个克隆对象属性的值为：");
        System.out.println("prototypeClone:name=" + prototypeClone.getName() + ",age=" + prototypeClone.getAge());
        System.out.println("cloneDepth对象的属性为:name=" + prototypeClone.getCloneDepth().getName() + ",age=" + prototypeClone.getCloneDepth().getAge());
        System.out.println("cloneShallow对象的属性为:name=" + prototypeClone.getCloneShallow().getName() + ",age=" + prototypeClone.getCloneShallow().getAge());
    }

}
