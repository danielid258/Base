package com.base.designpattern;

import java.util.ArrayList;
import java.util.Date;

/**
 * @desc: Created by Fengyun on 4/25/2018.
 *
 *原型模式
 *
 * 通过复制现有的对象实例来创建新的对象实例
 *
 * 实现Cloneable接口
 * 重写Object类的clone方法
 *
 * 优点
 *  使用原型模型创建一个对象比直接new对象效率更高 因为它直接操作内存中的二进制流 特别是复制大对象时性能的差别非常明显
 *
 *  隐藏了创建新实例的复杂性 使创建对象就像我编辑文档时的复制粘贴一样简单
 *
 * 注意
 *  由于原型模式复制对象时不会调用类的构造方法 所以原型模式无法和单例模式组合使用 因为原型类需要将clone方法的作用域修改为public类型 而单例模式的条件就无法满足
 *
 *  使用原型模式时不能有final对象
 *
 *  Object类的clone方法只会拷贝对象中的基本数据类型 对于数组/引用对象等只能另行拷贝
 *
 *
 * 示例
 *  邮件发送邀请函 邮件中大部分内容都是一样的:邀请原由/相邀地点/时间等 只是被邀请者的名称不同 利用原型对象clone后重新set被邀请者的名称即可
 */
public class PrototypePattern {

    //实现Cloneable接口
    public class Mail implements Cloneable {
        private String receiver;
        private String subject;
        private String content;
        private Date date;
        private ArrayList<String> list;

        public Mail(String receiver, String subject, String content, Date date, ArrayList<String> list) {
            this.receiver = receiver;
            this.subject = subject;
            this.content = content;
            this.date = date;
            this.list = list;
        }

        //重写Object类的clone方法
        @Override
        public Mail clone() {
            Mail mail = null;
            try {
                //浅clone 拷贝基本数据类型及对应的包装类型和String
                mail = (Mail) super.clone();

                //深clone 拷贝非基本数据类型
                mail.list = (ArrayList<String>)this.list.clone();
                mail.date = (Date) this.date.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return mail;
        }

        public void test(){
            Mail mail = new Mail("xiaoma", "subject", "content", new Date(), new ArrayList<>());
            Mail mail1 = mail.clone();
            mail1.setReceiver("tom");

            Mail mail2 = mail.clone();
            mail2.setReceiver("jack");

        }
        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public ArrayList<String> getList() {
            return list;
        }

        public void setList(ArrayList<String> list) {
            this.list = list;
        }
    }
}
