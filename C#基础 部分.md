# C#基础 部分

1. 程序结构

   ```
   命名空间我的理解就是包名，可以不使用
   using System;  //程序包含的命名空间
   using System.Collections.Generic;
   using System.Linq;
   using System.Text;
   using System.Threading.Tasks;
   //命名空间  类的住址
   namespace Demo02  //命名空间，它里面包含好多个的类
   {
   //定义类
   class Program   //声明一个类
   {
       //程序入口
       static void Main1(string[] args)
       {
           Console.Title = "第一个程序！";
   /**            Console.ReadLine();
           String str1 = Console.ReadLine();
           String str2 = "XXX";
           Console.WriteLine("输入字符串是："+str1);
           //占位符  控制显示顺序
           String str = string.Format("str2是：{1},st1字符串为：{0}", str1, str2);
           Console.WriteLine("str2是：{1},st1字符串为：{0}", str1, str2);
    */
           //金钱表示   加上c表示金钱
           Console.WriteLine("str2是：{0:c}",10);
           //位数  f1指定精度   p百分比   p0(100%) p1(100.0%)
           Console.WriteLine("str2是：{0:d2}", 1);
          //字符串前后加上双引号
           Console.WriteLine("我是\"康旺\"");
           //空字符
           char c1 = '\0';
           //两行显示   \r\n
            Console.ReadLine();
             }
       static void Main(string[] args) {
           //赋值
           int n1 = 3, n2 = 3;
           n1 = n2 = 2;
           //运算符
           int r1 = n1 / n2;
           int r2 = 5 / 2;//截断
            
           float r3 = 5/2; //2
   
           Console.WriteLine(r1+"   "+r2+"    "+r3);
           Console.ReadLine();
           //数据类型转换
           //int.Parse(string);
           
           Console.ReadKey();等待一个用户和那件按下。
       }
       }
   ```

**注意：**

- 大小写敏感
- 所有语句和表达式使用分号隔开
- 程序Main入口
- 文件名可以不同于类名称

2.//源文件  .cs文本  cls编译 exe中间语言 位于源代码和机器之前

3.运行在哪里，我们编译之后，就会保存硬盘，执行的时候放在内存中。

4.网速是按照字节计算的，不是按照M计算的。

 * 有符号和无符号，就是8个位置都放数据，有符号就是7个位置放数据。
 * 1个字节8位，2的8此方
 * 1 个字节sbyte -128 ~127  byte 255
 * 2个字节 short -32678~32678   ushort 0~65535
 * 4个字节 int uint
 * 8个字节 long ulong
 * 4个字节 float 1.3f   
 * 8 个字节 double 1.3d  
 * 16字节 decimal 1.3m  不加默认是double
 * 2个字节 char
 * String 字符串和文本
 * bool 1个字节
 * 声明就是开辟空间，遵循命名规则
 * 声明+赋值
 * 声明  赋值
 * 计算的时候需要注意 1.0f-0.1f 不为0.9f，因为精度损失 二进制表示十进制，有精度损失。

## 基本语法

```c#
using System;  //命名空间
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo03
{
    class Rectangle {
        double length;
        double width;
        public void Acceptdetails() {
            length = 4.5;
            width = 3.5;
        }
        public double GetArea() {
            return length * width;
        }
        public void Display()
        {
            Console.WriteLine("Length:{0}",length);
            Console.WriteLine("Width:{0}", width);
            Console.WriteLine("Area:{0}", GetArea());
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Rectangle r = new Rectangle();
            r.Acceptdetails();
            r.Display();
            Console.ReadKey();
        }
    }
}

```

## 数据类型

数据类型分类：值类型、引用类型、指针类型

- 值类型

  给变量分配一个值。

  - bool 、byte：8、char：16、decimal：128、double：64、float：32、int：32、long：64、sbyte：8、short：16、uint：32、ulong：64字节、ushort：16字节

  - 程序查看字节

    ```
    sizeof(int);
    ```

- 引用类型

  不存储实际内容，而是存储数据的地址。

  - 对象类型
  - 动态类型
  - 字符串

- 指针类型

  ```
  char *cptr；
  int *iptr；
  ```

  

## 类型转换

将一种类型向另一个类型的转换。转换分为隐式和显式。

## 变量

## 封装

​	将一个或者多个小牧封闭在一个项目中或者逻辑包中，面向对象中，封装是为了防止对实现细节的访问，C#会根据需要，设置不同的访问权限。

​	public  private  protected   internal