# OpenGLES程序详解

- openGLES 2.0开始支持Android和iso设备的，并且它具有向后兼容性。
- 它是一个桌面操作系统使用的跨平台的，广为接受的3D API.
- 在OpenGLES中除非加载了一个有效的顶点着色器和片段着色器才会正确的绘制出一个图像。顶点着色器和片段着色器是必须的。

## 创建着色器

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

## 视口和清除缓存

我们是EGL创建了渲染表面，并初始化和加载了着色器，glViewport(0,0,width,hight)指定创建的位置以及窗口的大小，视口就是现实图像的位置和大小，

```
glViewport(x,y,width,height);
```

清缓存

```
glClear(GL_COLOR_BUFFER_BIT);清掉缓存，使用glClearColor作为渲染颜色。
```



## 画图，清缓存

​	在执行的过程中，会有许多缓存，比如颜色、深度和摸版等，所以在画图之前，需要清除缓存。

## 加载几何图形

清除缓存以及将底部颜色设置之后，就可以加载图形，画出图元了。,顶点位置需要加载到GL中，并将其连接到属性的位置中，设置完毕之后，就可以设置图元了。

## 后台缓冲区

​	如何真正的显示出缓冲区中的内容，首先了解一下双缓存区，屏幕可见的缓冲区由一个二维数组组成的，显示的图像就是在绘制时简单的更新可见帧缓冲区的像素数据。但是这样会存在问题，就会会看到伪像。so，使用双缓存，一个是前台显示一个是后台渲染缓存，所有的渲染都在后台中执行的，当渲染完成的时候，缓存区被交换到前台上



## EGL

​	它是与平台无关的API，用于管理图像表面（窗体的一种类型），他提供了：与原生窗口通信、查询绘图表面的可用类型和配置，创建绘图表面，在OpenGLEs和其他的API之间同步渲染，管理贴图。

- 提供OpenGLES与计算机原生系统的之间的一个结合，在可以确定绘制的类型，他必须和窗口进行通信。
- 染赫使EGL的执行操作之前，都需要本地和EGL进行显示连接。



## 着色器和程序

​	创建对象，我们需要着色器和程序对象，着色器的加载编译、连接类似于c语言，编译程序，链接程序。着色器得到源代码，将其编译成一个目标文件--------->连接到一个对象，在连接阶段，就会生成目标机器语言。

- 创建着色器对象
- 加载代码到着色器
- 编译着色器
- 创建一个程序对象
- 着色器连接到程序



### 创建一个着色器API介绍

##### 创建着色器：

- GLuint glCreateShader(String type);

  - type：可以是片元，也可以是片段
  - 参数：GL_VERTEX_SHADER      GL_FRAGMENT_SHADER
  - 根据传参类型，创建出相应类型的着色器对象


##### 删除着色器

- void glDeleteShader（int shader）;

  - shader:着色器句柄，
  - 如果连接到一个程序对象，执行方法**不会理解执行**，等待不在连接任何程序对象的时候，才会删除。


##### 创建着色器代码

- void glShaderSource(shader,count,string,length)

  - 着色器
  - 字符串数量
  - 数组指针
  - 字符串长度

  在Android上也可以直接使用glShaderSource(shader,source);

##### 编译

- void glCompileShader(shader)

  - shader：着色器句柄
  - 将已经保存在着色器对象中的着色器源代码进行编译。

##### 查看着色器信息

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

着色器已准备好了，下来我们需要创建一个程序，将两个着色器对象连接起来。形成一个可执行的程序。

##### 创建一个程序

- GLuint glCreateProgram();
  - 仅仅返回一个执行新程序对象的句柄。


##### 删除程序

- void glDeleteProgram(proid);
  - 一个参数就是句柄


##### 将着色器连接，每一个程序对应一个顶点和片段。

- void glAttachShader(proid,shader);
  - 程序句柄
  - 着色器

  **这个在连接的过程中，着色器不一定要编译，不一定要有代码，唯一的要求，就是只能有一个片元和片段。，可以在任何时候进行连接**

##### 断开连接

- void glDetaShader(program,shader)

  - 指向句柄

  - 指向断开连接的着色器

    **到此准备工作做完了**

##### 最后的链接

- void glLinkProgram(proId)

  - 此时生成最终的可执行程序。
  - 连接程序检查各种对象数量，确保成功。
  - 确保输出变量和输入变量的值的类型相同。
  - 连接就是生成最终在硬件上执行的时候。

##### 使用程序

- void glUseProgram(projectId)

  - 使用程序



### 统一变量和属性

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

### 获取和设置属性值



### 着色器编译

- 着色器代码通常解析为某种中间的表现形式，将其转换为机器可以值性的机器指令，这个主要是CPU做的 

### 二进制代码：

- 他是完全编译和连接的程序的二进制表现形式，他可以避免在线进行编译。
- 二进制取决于供应商，程序可移植性差，

这个可以使用GL_LINK_STATUS进行检测，如果不可以用，那么就在编译一次



## 顶点属性、顶点数组和缓存区对象

指定顶点属性和数据的方法，讨论顶点属性的概念，如何指定它们支持的数据类型，如何绑定顶点属性以用于顶点着色器。可以使用数组进行指定。

#### 顶点着色器属性

​	顶点属性使用一个顶点数组对每个顶点进行指定，也可以将一个常量用于图元的所有顶点。所有的必须支持16个顶点的属性。应用程序可以查询特定实现支持顶点属性的准确值

​	通常使用它来加载指定的通用顶点属性

​	API:glVertexAttrib1f(GLuint index,GLfloat x);

```
(x,0.0,0.0,1.0)
```



​	API:glVertexAttrib2f(GLuint index,GLfloat x,GLfloat y);

```
(x,y,0.0,1.0)
```



​	API:glVertexAttrib3f(GLuint index,GLfloat x,GLfloat y,GLfloat z);

```
(x,y,z,1.0)
```



​	API:glVertexAttrib4f(GLuint index,GLfloat x,GLfloat y,GLfloat z,GLfloat w);

#### 顶点数组

指定每个顶点的属性，保存应用程序地址空间，

API:

```
glVertexAttribPointer(index,size,type,normalized,stride,*ptr)
index：顶点属性索引
size:为索引引用的ding单属性指定分量数量，
type:数据格式  GL_BYTE    GLUNSIGNED)BYTE   GL_SHORT    GL_UUNSIGNED_SHORT   GL_INT  GL_UNSIGNED_INT  
normalized:表示非浮点数据格式类型在浮点时是否需要格式化，偏移
ptr:准备的数据数组
```

- 一个缓冲区中存储顶点属性 的叫属性数组
- 单独缓存区保存单个属性的叫数组结结构



那种效率最高呢

一般是结果数组比较的高，每个顶点的属性可以顺序的读取，so高效

数组结构，每次将值进行偏移 。





​	在使用的过程中通过type指定顶点属性书的格式，不仅影响效率，也会影响它的整体性能，，数据量越小，所占带宽越小，

​	建议使用数据类型

```
尽可能是GL_HALF_FLOAT，一般用在纹理、法线、向量
颜色是由GL_UNSIGNED_BYTE，每个颜色4个分量
顶点位置使用GL_FLOAT
```



glVertexAttribPointer中的规范化标志如何工作

 	着色器之前，顶点存储为单精度，如果鄙视单精度会进行转换为单精度，规范化标志控制非浮点型向浮点型的转化。为假，就直接转化为浮点型，数据类型的范围都会转化为[-1.0,1.0] 或者是[0.0,1.0].





常量的顶点属性和顶点数组之间的选择

​	应用程序让openGL使用常量数据或者来自于顶点数组的数据。使用GLEnableVertexAttribArray和GlDisableVertexAttribArray使用或者禁用属性数组。

##### ----------------未结束-------------待续---------------------------------



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







## 顶点着色器

​	顶点着色器可以用于传统顶点的操作，通过矩阵变换，计算照明方式以方程是生成逐点颜色以及纹理

1.顶点着色器提供顶点操作的可编程方法，

2.输入输出：

- 属性
- 统一变量和统一变量缓存区
- 采样器
- 着色器程序

3.顶点着色器的输出称作为顶点着色器的输出变量，光栅化阶段为片段着色器计算变量，并作为传入。

- gl_VertexID:输入变量，用于保存顶点的整数索引
- gl_InstanceID：输入变量，用于保存绘制图元的编号
- gl_Position:输出订单位置的裁剪坐标，图元裁剪、位置变换。
- gl_PointSize()；点精灵尺寸

4.唯一的内建窗口深度范围

uniform gl_DepthRangeParameters

5.内建常量

顶点属性的最大数量

  const mediump int gl_MaxVertexAttribs=16;

  const mediump int gl_MaxVertexUniformVectors=256;  

  const mediump int gl_MaxVertexOutputVectors = 16;

  const mediump int gl_MaxVertexTextureImageUnits = 16;

  const mediump int gl_MaxCombinedTextureImageUnits = 16;

  6.精度符

   highp、out、mediump

  默认的精度符

 precision highp float;

 precision mediump float;



### 订单着色器的案例：

##### 矩阵变换

```
#version 300 es
uniform mat4 u_MvPMatrix;
in vec4 a_Position;
in vec4 a_Color;
out vec4 v_Color;
void main()
{
    v_Color=a_Color;计算插值之后的颜色
    gl_Position = u_MvPMatrix*a_Position;  使用变换后的顶点位置和图元类型
}
```

设置了光栅化阶段使用变换后顶点位置和图元类型将图元进行光栅化为片段，对每个片段，计算插值，最后作为输出传递给片段着色器

##### 模型矩阵

​	使用矩阵对图像进行平移等操作。但是并不存在函数，所以需要自己来构建，一般步骤为：

- 初始化单位矩阵
- 将单位矩阵结合一个平移，使得物体远离观察者
- 将其进行其他操作。



##### 投影矩阵

​	在固定功能gl中，使用的是glFrustum或者openGL工具函数指定。Frustum是将其进行此





#### 顶点着色器的照明

​	通过观察直射光、点光源、聚光灯的照明方式示例。直接光就是平行的光



点光源：光照位置   xyzw

​		光照距离  xyzw

​		衰减距离 光源颜色、光照强度

