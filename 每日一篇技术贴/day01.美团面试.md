# 第一天
```

```
## 1.System.out.println(3|9)；输出什么？
```

  逻辑运算
  联想知识点有
  &和&&   
    共同点：
        他们都是表示运算符两边都是true时，结果为true
    不同点：
        &在运算中两边都会执行，但是&&具有短路的作用

  |和||
    共同点：
        他们表示运算符两边任意一边为true，结果为true，两边都不为true的时候为false，但是如|的一边为true的时候，后面会计算运算
    不同点：

  进行运算的时候，也是安找上面的标准来计算
```
## 2.转发和重定向的区别
```

  （1）转发是一次请求，是服务端的行为，重定向是客户端的行为。
  （2）转发：通过RequestDispatcher对象的forward方法实现的
       重定向是利用服务端返回的状态码来实现的，客户端浏览器请求请求会有一个状态码，服务器是通过HTTPServletRequestResponse的setStatus方法设置状态码的，
       从地址栏说：forward地址栏不会发生变化，服务器直接访问资源将数据拿取过来，返回给客户端，客户端并不知道数据是从哪里来的，重定向将请求重新去请求，地址栏就是新的
       共享数据来说：forward可以共享request域中的数据
                    redirect：不可以共享数据
       运用地方来说：forward一般用于登录的时候，根据角色转发响应的模块
                    redirect：一般是用户注销登录是返回主页面和跳转其他网站等
       效率：forward高  redirect：的效率低
       
```
## 3.一次url请求经历了哪些协议
``` 

  罗列一下：
    DNS:域名解析 域名解析成IP
    ARP：路由器在服务器之间通信时，需要将ip地址转换为MAC,需要使用这个协议
    OPSF：IP数据包在路由器之间，路由选择协议
    HTTP：通过Http协议请求网页数据 
    TCP[建立客户端与服务器的连接，因为现在的连接是长连接，首先进行连接，然后在进行通信]

