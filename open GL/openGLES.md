# OPENGLES

​	OpenGLES在不同平台上实现，定义好的程序接口，不同平台按照接口实现，OpenGLES去掉了一些不是必须的数据类型和功能，比OpenGL更加轻量级，只有点,线，三角形。

​	**OpenGLES可以做什么?**

OpenGLES是手机游戏等嵌入式的图像api，在嵌入式上做图像处理的，可以做一下处理。图片处理，摄像图效果、视频处理、游戏。

![img](E:\专题\open GL\Open GL核心代码.md) 

	## openGLES的概念

**顶点着色器**

着色器是shader在GPU上运行的程序，可以处理顶点，他是处理像素的简单程序。对于每一个顶点，都会执行一次顶点着色器，其作用是将三维坐标点在屏幕上显示，并有深度信息，顶点着色器的属性：位置、颜色、纹理坐标。

![è¿éåå¾çæè¿°](https://img-blog.csdn.net/20161012113348032) 

**片元着色器**

​	他是计算出每个点的颜色和其他属性的，通过光照，阴影，镜面，半途明处理来计算出像素颜色和输出，，他仅仅是控制一个像素。

**投影**

正交和透视



# 画三角形

**画三角形**

​	我们知道，openGLES简化了openGL,无论多么复杂的图像，在这里都是使用点线三角形画出。

**步骤**

​	首先指定OpenGLES版本，我们可以有两种方法进行设置：

- 通过配置文件的方式进行设置

  ```
  <!-- Tell the system this app requires OpenGL ES 2.0. -->
  <uses-feature android:glEsVersion="0x00020000" android:required="true" />
  
  3.0的版本为0x00030000,3.1的版本为0x00030001。 
  ```

  

- 通过代码的方式进行设置

  **步骤一**

  ​	创建一个存放三角形的Activity，他通过创建GLSurfaceView，然后通知渲染器进行渲染。渲染器实现步骤：

   - 书写着色器
   - 加载着色器
   - 编译着色器
   - 连接程序
   - 确定顶点坐标和颜色数据
   - 社会视口
   - 将坐标和颜色进行设置。
   - 将其画出在桌面上。

- 着色器

  首先需要点，那么就定义属性点

  将点交给内部变量

  ```
   attribute vec4 vPosition;
   void main() {
       gl_Position = vPosition;
   }
   给三个点也是可以的，在这里在给第四个点，如果w点不用的话，也就是没有必要给了
  ```

  片段着色器

  ```
   precision mediump float;
   uniform vec4 vColor;
   void main() {
       gl_FragColor = vColor;
   }
  ```

  

- 我们准备顶点坐标和颜色数据

  ![è¿éåå¾çæè¿°](E:\专题\open GL\1.json) 

- 顶点数据，根据上图得到

  ```
  float triangleCoords[] = {
           0.5f,  0.5f, 0.0f, // top
           -0.5f, -0.5f, 0.0f, // bottom left
           0.5f, -0.5f, 0.0f  // bottom right
   };
  ```

  

- 颜色参数

  ```
  float color[] = { 1.0f, 1.0f, 1.0f, 1.0f }; //白色
  ```

  

- 实现渲染器的方法

  - 首先设置背景色

  - 加载顶点和颜色

  - 加载着色器，编译，连接

    ```
        //将背景设置为灰色
        GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);  
        //申请底层空间
        ByteBuffer bb = ByteBuffer.allocateDirect(
                    triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        //将坐标数据转换为FloatBuffer，用以传入给OpenGL ES程序
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);  
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                    vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                    fragmentShaderCode);
    
        //创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram();
        //将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader);
        //将片元着色器加入到程序中
        GLES20.glAttachShader(mProgram, fragmentShader);
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram);
    ```

  - 设置视口

    ```
    GLES20.glViewport(0,0,width,height);
    ```

    

  - 画图

    ```
    获取位置，设置参数
       //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram);
    
        //获取顶点着色器的vPosition成员句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    --------------------- 
    作者：湖广午王 
    来源：CSDN 
    原文：https://blog.csdn.net/junzia/article/details/52801772 
    版权声明：本文为博主原创文章，转载请附上博文链接！
    ```

--------------

彩色三角形

---------

投影

----

**深度**

​	深度其实就是该象素点在3d世界中距离**摄象机**的距离（绘制坐标），深度缓存中存储着**每个象素点（绘制在屏幕上的）的深度值！** 深度值（Z值）越大，则离摄像机越远。 

​	深度值是存贮在深度缓存里面的，我们用深度缓存的位数来衡量深度缓存的精度。深度缓存位数越高，则精确度越高，目前的显卡一般都可支持16位的Z Buffer，一些高级的显卡已经可以支持32位的Z Buffer，但一般用24位Z Buffer就已经足够了

**深度有什么作用呢？？**

​	不使用深度会怎样？？ 加入先绘制近处的物体，后绘制远处的，那么远处的将近处的覆盖掉。