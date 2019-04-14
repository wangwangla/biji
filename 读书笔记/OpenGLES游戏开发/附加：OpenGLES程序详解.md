## OpenGLES程序详解

- 在OpenGLES中除非加载了一个有效的顶点着色器和片段着色器才会正确的绘制出一个图像。顶点着色器和片段着色器是必须的。



### 创建着色器

### 编译和加载着色器

1.创建着色器：

```
glCreateShader(type);
```

2.加载源码

```
glShaderSource（shaderId,1,file,null）
```

3.编译

```
glCompileShader(shader);
```

4.检查错误：

如果发生错误，就将着色器删除掉。

### 创建程序链接

1、创建程序

```
glCreateProgram();
```

2.加载着色器

```
glAttachShader(programId,vtShader);
glAttachShader(programId,fgShader);
```

3.链接

```
glLinkProgram(programId)
```

4.使用程序

```
glUserProgram(programId);
```

### 视口和清除缓存

视口就是现实图像的位置和大小，

### 画图，清缓存

​	在执行的过程中，会有许多缓存，比如颜色、深度和摸版等，所以在画图之前，需要清除缓存。

### 加载几何图形

顶点位置需要加载到GL中，并将其连接到属性的位置中，设置完毕之后，就可以设置图元了。

### 后台缓冲区

​	如何真正的显示出缓冲区中的内容，首先了解一下双缓存区，屏幕可见的缓冲区由一个二维数组组成的，显示的图像就是在绘制时简单的更新可见帧缓冲区的像素数据。但是这样会存在问题，就会会看到伪像。so，使用双缓存，一个是前台显示一个是后台渲染缓存，所有的渲染都在后台中执行的，当渲染完成的时候，缓存区被交换到前台上







### 着色器和程序

​	创建对象，我们需要着色器和程序对象，着色器的加载编译、连接类似于c语言，编译程序，链接程序。着色器得到源代码，将其编译成一个目标文件--------->连接到一个对象，

- 创建着色器对象
- 加载代码到着色器
- 编译着色器
- 创建一个程序对象
- 着色器连接到程序



### 创建一个着色器API介绍

- glCreateShader(String type);

  - type：可以是片元，也可以是片段

- glDeleteShader（int shader）;

  - shader:着色器句柄，
  - 如果连接到一个程序对象，执行方法不会理解执行，等待不在连接任何程序对象的时候，才会删除。

- glShaderSource(shader,count,string,length)

  - 着色器
  - 字符串数量
  - 数组指针
  - 字符串长度

  在Android上也可以直接使用glShaderSource(shader,source);

- glCompileShader(shader)

  - shader：着色器句柄
  - 将已经保存在着色器对象中的着色器源代码进行编译。

- glGetShderiv(shader,pname,*param)

  - 着色器

  - 执行 的操作

    | GL_COMPILE_STATUS       | FL_TRUE/GL_FALSE         |
    | ----------------------- | ------------------------ |
    | GL_DELETE_STATUS        | 标记是否为删除           |
    | GL_INFO_LOG_LENGTH      | 检查日志长度             |
    | GL_SHADER_SOURCE_LENGTH | 返回着色器源码代码的长度 |
    | GL_SHADER_TYPE          | 返回着色器类型           |

  - 存储的位置

### 创建和链接程序

- glCreateProgram();
  - 仅仅返回一个执行新程序对象的句柄。
- glDeleteProgram(proid);
  - 一个参数就是句柄
- glAttachShader(proid,shader);
  - 程序句柄
  - 着色器
- 

