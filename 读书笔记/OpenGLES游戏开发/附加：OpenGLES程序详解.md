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

- GLuint glCreateShader(String type);

  - type：可以是片元，也可以是片段

- void glDeleteShader（int shader）;

  - shader:着色器句柄，
  - 如果连接到一个程序对象，执行方法不会理解执行，等待不在连接任何程序对象的时候，才会删除。

- void glShaderSource(shader,count,string,length)

  - 着色器
  - 字符串数量
  - 数组指针
  - 字符串长度

  在Android上也可以直接使用glShaderSource(shader,source);

- void glCompileShader(shader)

  - shader：着色器句柄
  - 将已经保存在着色器对象中的着色器源代码进行编译。

- void glGetShderiv(shader,pname,*param)

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

- GLuint glCreateProgram();
  - 仅仅返回一个执行新程序对象的句柄。

- void glDeleteProgram(proid);
  - 一个参数就是句柄

- void glAttachShader(proid,shader);
  - 程序句柄
  - 着色器

  **这个在连接的过程中，着色器不一定要编译，不一定要有代码，唯一的要求，就是只能有一个片元和片段。**

- void glDetaShader(program,shader)

  - 指向句柄

  - 指向断开连接的着色器

    **到此准备工作做完了**

- void glLinkProgram(proId)

  - 此时生成最终的可执行程序。连接程序检查各种对象数量，连接就是生成最终在硬件上执行的时候。

- void glUseProgram(projectId)

  - 使用程序



### 统一变量

​	连接之后，就可以在对象上执行许多查询，

- 找出活动统一变量，存储应用程序通过OpenGLES传输给着色器只读的常数值变量。

- 统一变量分为两类：

  - 命名统一变量块：值有统一变量缓存区对象支持，【被分配一块统一变量块索引】
  - 默认统一变量块：没有名称或者统一块索引。

  **统一变量在顶点或者片段中均有，那么类型需要一致。值也需要相同，连接阶段，连接程序未程序中与默认统一变量块的相关活动指定位置。位置是加载的标识符 ，还为命名变量块相关的活动变量分配偏移和跨距。**

- 查询活动统一变量的列表，首先要用GL_ACTIVE_UNIFORMS参数调用glGetProgramiv,可以获取程序中活动同一变量的数量，这个数据主要包含了命名统一变量，默认统一变量以及着色器代码中内建的统一变量。如果被程序调用就认为是活动的，如果创建了一个变量但是一直没有使用，连接程序的似乎会将其去掉。

  使用API:gLGetActiveUniform(Unint program,GLuint index,GLsizei bufsize,GLsizer *length)可以获取所有统一变量的属性的名称和类

- 找出统一变量在程序的位置，这个位置用于后续加载统一变量值的后续调用。

  API:glGetUniformLocation(GLuint program,const GLchar *name); 这个必须是活动的。

- 位置获取之后，给他们设置统一变量的值。

  glUniform1f(Glint location,GLfloat x);

  glUniform1fv(Glint location,GLsizei count ,const GLFloat * value);

  glUniform1i(Glint location,GLfloat x);

  ……

- 统一变量缓存区

  - 统一变量数据，程序之间可以共享变量
  - 在调用的时候降低API开销、

- 着色器编译

  - 着色器代码通常解析为某种中间的表现形式，将其转换为机器可以值性的机器指令，这个主要是CPU做的 

- 二进制代码：

  - 他是完全编译和连接的程序的二进制表现形式，他可以避免在线进行编译。
  - 二进制取决于供应商，程序可移植性差，

  这个可以使用GL_LINK_STATUS进行检测，如果不可以用，那么就在编译一次





### 顶点属性、顶点数组和缓存区对象

#### 顶点着色器属性

​	顶点属性使用一个顶点数组将值进行指定，也可以将一个常量用于图元的所有顶点。所有的必须支持16个顶点的属性。应用程序可以查询特定实现支持顶点属性的准确值

​	API:glVertexAttrib1f(GLuint index,GLfloat x);

​	API:glVertexAttrib1f(GLuint index,GLfloat x,GLfloat y);

​	API:glVertexAttrib1f(GLuint index,GLfloat x,GLfloat y,GLfloat z);

​	API:glVertexAttrib1f(GLuint index,GLfloat x,GLfloat y,GLfloat z,GLfloat w);

#### 顶点数组

指定每个顶点的属性，保存应用程序地址空间







### 图元和光栅化

图元可以使用多种函数进行绘制，glDrawArraysInstanced和glDrawElementsInstanced等命令绘制。其他颜色定，与每个顶点相关联。



### 点精灵

​	它支持点精灵:GL_POINTS,点精灵指定绘制的，一般作为点并不是正方形绘制的，实现高效渲染，它需要指定位置和半径屏幕对其的正方形，位置是正中心，半径用于寻找4个点。

```
	半径设置  gl_Point = 10；
```

点精灵的位置？？？？？？？？

gl_PointCoord:只能在绘制点精灵的时候用于片段着色器内部内建变量，使用mediump精度限定一个vec2

```
#version 300 es
precision mediump float;
uniform samplers2D a_textSprite;
layout(location=0)out vec4 outColor;
void main()
{
    outColor = texture(s_textSprite,gl_PointCoord);
}
```



### 绘制图元

​	使用绘制图像的那几个API来完成这个操作，

### 图元重启

图元重启，可以一次绘制出多个不想连接的图元，可以降低API调用，，可以是一些所以列表来重启。



### 驱动顶点

如果没有限定符，那么在图元中使用的是线性插值，如果是平滑着色，那么没有插值，所以片段着色器中只有一个点的值可用。



图元装配包括：

​	裁剪------>透视分割----->视口变换

