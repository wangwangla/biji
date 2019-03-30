# Open GL核心代码

## 项目基本框架

## 画图代码

**基本步骤为：shader分析，设计，顶点分析，创建，画图**

1. 一个三角形：

   分析：一个三角形有三个点，点的参数由程序传入。【shader中有接收顶点代码，将其传输给gl_position】

   片段着色器，定义全局精度，设置颜色。

   - 订单着色器

   ```c++
   attribute vec3 aPosition;  //顶点位置
   void main()     
   {                            		
      gl_Position = aPosition; //根据总变换矩阵计算此次绘制此顶点位置
   }       
   ```

   - 片段着色器

     ```c++
     precision mediump float;
     uniform  vec4 vColor;
     
     void main()                         
     {                       
        gl_FragColor = vColor;//给此片元颜色值
     }
     ```

     完成了着色器的操作，下来定义顶点，创建变量和位置。

     订单可以自己设置0~1之间。

     ```c++
     final float UNIT_SIZE=0.2f;
     float vertices[]=new float[]
     {
     	-4*UNIT_SIZE,0,
      	0,0,-4*UNIT_SIZE,
      	0,4*UNIT_SIZE,0,0
      };
     ```

     将其放入本地中

     ```
     ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
     vbb.order(ByteOrder.nativeOrder());
     mVertexBuffer = vbb.asFloatBuffer();
     mVertexBuffer.put(vertices);
     mVertexBuffer.position(0);
     ```

     获取着色器位置，设置参数

     ```
     maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
     //获取程序中顶点颜色属性引用id  
     maColorHandle= GLES20.glGetUniformLocation(program, name)
     ```

     画图

     ```
             GLES20.glVertexAttribPointer(maPositionHandle,3, 
              		GLES20.GL_FLOAT, 
              		false,
                     3*4,   
                     mVertexBuffer
              );
              //允许顶点位置数据数组
              GLES20.glEnableVertexAttribArray(maPositionHandle);  
              glUniform4i(location, x, y, z, w);
     ```

     画图

     ```
       GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount); 
     ```

     上面几乎是一个图像的最少思路。

   ## 变色

   

   上面仅仅完成了只有一个颜色构成的图像，下俩我们创建一个变色的图像。

   首先将着色器增加一个颜色的变量，varying vec3 v_color;传输给片段的变量varying vec3 a_color.

   顶点着色器

   ```
   attribute vec3 aPosition;  //顶点位置
   attribute vec4 aColor;    //顶点颜色
   varying  vec4 vColor;  //用于传递给片元着色器的变量
   
   void main()     
   {                            		
      gl_Position = aPosition; //根据总变换矩阵计算此次绘制此顶点位置
      vColor = aColor;//将接收的颜色传递给片元着色器 
   }             
   ```

   片段着色器

   ```
   precision mediump float;
   varying  vec4 vColor; //接收从顶点着色器过来的参数
   
   void main()                         
   {                       
      gl_FragColor = vColor;//给此片元颜色值
   }
   ```

   点的坐标不发生变化，但是需要增加一个颜色的数据

   ```
           float colors[]=new float[]
           {
           		1,1,1,0,
           		0,0,1,0,
           		0,1,0,0
           };
           
           ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
           cbb.order(ByteOrder.nativeOrder());
           mColorBuffer = cbb.asFloatBuffer();
           mColorBuffer.put(colors);
           mColorBuffer.position(0);
   ```

   下来获取到位置信息，设置参数

   ```c++
   maColorHandle= GLES20.glGetAttribLocation(mProgram, "aColor");
   
            GLES20.glVertexAttribPointer  
            (
           		maColorHandle,
            		4,
            		GLES20.GL_FLOAT,
            		false,
                   4*4,
                   mColorBuffer
            );
            //允许顶点位置数据数组
            GLES20.glEnableVertexAttribArray(maColorHandle);  
   ```

   基本需要改变的已经结束 ，三角形有了，颜色可以自己设置。

2. 加入动作，手动可以改变坐标，进行旋转。

   着色器需要使用mat矩阵，同坐标相乘得到最终的位置坐标。

   ```
   uniform mat4 uMVPMatrix; //总变换矩阵
   attribute vec3 aPosition;  //顶点位置
   attribute vec4 aColor;    //顶点颜色
   varying  vec4 vColor;  //用于传递给片元着色器的变量
   
   void main()     
   {                            		
      gl_Position = uMVPMatrix * vec4(aPosition,1); //根据总变换矩阵计算此次绘制此顶点位置
      vColor = aColor;//将接收的颜色传递给片元着色器 
   }                 
   ```

   ## 图形旋转、平移

   

   既然使用了矩阵，那么就先引入相机位置和投影

   ```
   //调用此方法计算产生透视投影矩阵
   Matrix.frustumM(Triangle.mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
   //调用此方法产生摄像机9参数位置矩阵
   Matrix.setLookAtM(Triangle.mVMatrix, 0, 0,0,3,0f,0f,0f,0f,1.0f,0.0f);
   ```

   使用了透视投影和相机位置，设置结束之后，我们需要将这个数组传递给着色器，让它确定最终点的位置，

   ```
   这里还要加一个获取位置信息。
   GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, Triangle.getFianlMatrix(mMMatrix), 0);
   ```

3. 旋转物体， 物体旋转无非就是通过矩阵变换罢了，所以也通过矩阵和他们进行相乘，之前是为了得到一个位置使用一个相机和投影矩阵最后形成一个总的矩阵，最后和坐标点相乘得到，如今可以将旋转矩阵和相机矩阵相乘，将最终的矩阵和点相乘得到最后的坐标位置。

   1. 物体平移

      平移无非就是x+XX，y+YY,z+ZZ,那么这个可以通过矩阵简单的实现。

      > ![1553674266662](E:\专题\笔记图片\1553674266662.png)
      >
      > 图片有个问题，一个矩阵的最后一个值变为1

   2. 现在创建一个数组

      ```
      初始化
      Matrix.setRotateM(mMMatrix,0,0,0,1,0);
      进行设置平移   参数说明数组，偏移，x,y,z
      Matrix.translateM(mMMatrix,0,0,0,1);
      ```

      数组创建结束，上面说了最终是和点相乘，那么我们两个坐标进行相乘既可。

      ```
      Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, spec, 0);
      Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);        
      ```

      解释一下，总结一下，现在有一个摄像机，有一个投影，有一个旋转，上面代码的目的就是将这几个都相乘，得到最后的矩阵。

      得到最的矩阵，将它传递给坐标位置。

      --------

      旋转

      ```
        Matrix.rotateM(mMMatrix,0,xAngle,1,0,0);
      ```

      实现原理：

      ![1553675508960](E:\专题\笔记图片\1553675508960.png)

      --------

      缩放

      ![1553676532073](E:\专题\笔记图片\1553676532073.png)

      ```
         public static void scale(float x, float y,float z) {
          	Matrix.scaleM(currMatrix,0, x, y, z);
          }
      ```

      

4. 正交投影

   现在无需改变着色器，矩阵，我有，顶点，我有，颜色吧，我也有

   - 正交变换，物体不会随着举例改变

     也仅仅是设置一个数组即可。

     ```
     Matrix.orthoM(mProjMatrix, 0, left, right, bottom, top, near, far);
     ```

5. 透视投影

   - 变换的结果，随着举例改变

     ```
     Matrix.frustumM(mProjMatrix, 0, left, right, bottom, top, near, far);
     ```

   在这个里面的far和near并没有具体的含义，主要是为了节约渲染而设定的 。 

   -------

   有时候为了可以将某一个时刻的物体保留下来，可以先将物体进行画出，然后保存状态（时间就是保存在画图时刻，矩阵的形态），将矩阵移动之后在次的画出图像。

   ```
       public static void pushMatrix()//保护变换矩阵
       {
       	stackTop++;
       	for(int i=0;i<16;i++)
       	{
       		mStack[stackTop][i]=currMatrix[i];
       	}
       }
       
       public static void popMatrix()//恢复变换矩阵
       {
       	for(int i=0;i<16;i++)
       	{
       		currMatrix[i]=mStack[stackTop][i];
       	}
       	stackTop--;
       }
   ```

   

6. #### 对于变换的解释   下

   #### 

7. 有时候画图的时候，为了节约顶点个数，可以使用索引。

8. 设置合理的视角

9. 卷面和背面裁剪

   在openGL默认逆时针是正，顺时针是否，到那时这个可以进行设置的，问题来了，什么背面裁剪

10. 



## 光照

1. 光照的计算是很麻烦的，如果模型太难，就会造成计算过大，我们可以使用光照模型对其进行简化，光照包括：环境光、散射光、镜面光

   1. 环境光就是全方位的光，并且均匀，环境光的特点就是依赖于位置，没有方向性。

   2. 着色器修改

      ```
      uniform mat4 uMVPMatrix; //总变换矩阵
      attribute vec3 aPosition;  //顶点位置
      varying vec3 vPosition;//用于传递给片元着色器的顶点位置
      varying vec4 vAmbient;//用于传递给片元着色器的环境光分量
      void main()     
      {         
         //根据总变换矩阵计算此次绘制此顶点位置         		
         gl_Position = uMVPMatrix * vec4(aPosition,1); 
         //将顶点的位置传给片元着色器
         vPosition = aPosition;   
         //将的环境光分量传给片元着色器
         vAmbient = vec4(0.15,0.15,0.15,1.0);
      }      
      ```

      上面原本是一个球体，我们为了光照，这里将数据写入代码中传递给片段着色器。

      ```
      precision mediump float;
      uniform float uR;
      varying vec2 mcLongLat;//接收从顶点着色器过来的参数
      varying vec3 vPosition; //接收从顶点着色器过来的顶点位置
      varying vec4 vAmbient;//接收从顶点着色器过来的环境光分量
      void main()                         
      {
         vec3 color;
         float n = 8.0;//一个坐标分量分的总份数
         float span = 2.0*uR/n;//每一份的长度
         //每一维在立方体内的行列数
         int i = int((vPosition.x + uR)/span);
         int j = int((vPosition.y + uR)/span);
         int k = int((vPosition.z + uR)/span);
         //计算当点应位于白色块还是黑色块中
         int whichColor = int(mod(float(i+j+k),2.0));
         if(whichColor == 1) {//奇数时为红色
         		color = vec3(0.678,0.231,0.129);//红色
         }
         else {//偶数时为白色
         		color = vec3(1.0,1.0,1.0);//白色
         }
         //最终颜色
         vec4 finalColor=vec4(color,0);
         //给此片元颜色值
         gl_FragColor=finalColor*vAmbient;
      }     
      ```

      核心代码就一行，将传递的光照和颜色相乘。

      由上面可以得出，我们在绘制的时候只需要将环境的光和颜色相乘即可，那么也就是说，我们在使用的过程中，只需要表示出环境光、散射光、镜面光即可。当然了上面是直接将数据写入在shader中，我们也可以将数据从外界传入，传入数据又和前面的方式一样传入即可。

      

      为了方便理解，大可不必那么麻烦，不就是成一个数据吗，那就使用如果方式，直接修改片段着色器，

      ```
      gl_FragColor=vec4(color,0)*vec4(0.77,0.15,0.15,1.0);
      ```

      ------------

      - 环境光

        从四面八方射向物体，它仅仅是光射的一个案例，效果差，没有一个很好的层次。

      - 散射光

        物体从四面八方将数据传输反射的效果。代表的是显示升高中粗糙物体表面光照折射，并向四周均匀。散射虽说各个方法的光照均匀，但是散射光和入射强度以及入射的角度密切相关，当位置改变时，效果也会有很大的改变。

        散射光的结果是 = 材质的反射系数 x 散射光的强度 x max（cos(入射角)，0），在开发中分为两步

        散射光最终强度 = 散射光强度 X max(cos(入射角)，0);

        散射光照射结果 = 材质反射系数 X 散射光的最终强度。

        唯一的区别就是多了一个 max(cos(入射角)，0);，入射角越大散射越弱。

        ![1553681829035](笔记图片\1553681829035.png)

      - 实现，首先我们需要知道光源在哪里，我们将位置放在画图的地方，然后将他们放入到缓存区汇中

        ----

        可以将数据随时放入缓存区，并不是只有在创建的时候。

      - 改变光的位置

        ```
        public static void setLightLocation(float x,float y,float z)
        {
        llbbL.clear();
        
        lightLocation[0]=x;
        lightLocation[1]=y;
        lightLocation[2]=z;
        
        llbbL.order(ByteOrder.nativeOrder());//设置字节顺序
        lightPositionFB=llbbL.asFloatBuffer();
        lightPositionFB.put(lightLocation);
        lightPositionFB.position(0);
        }
        ```

        我们需要一个法向量，将数据存储，法向量通过坐标点进行计算得到。

        ```
        //创建绘制顶点法向量缓冲
        ByteBuffer nbb = ByteBuffer.allocateDirect(vertices.length*4);
        nbb.order(ByteOrder.nativeOrder());//设置字节顺序
        mNormalBuffer = nbb.asFloatBuffer();//转换为float型缓冲
        mNormalBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
        mNormalBuffer.position(0);//设置缓冲区起始位置     
        ```

      - 设置参数

        ```
        //将光源位置传入着色器程序   
        GLES20.glUniform3fv(maLightLocationHandle, 1, MatrixState.lightPositionFB);
        // 将顶点位置数据传入渲染管线
        GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT,
        false, 3 * 4, mVertexBuffer);
        //将顶点法向量数据传入渲染管线
        GLES20.glVertexAttribPointer(maNormalHandle, 3, GLES20.GL_FLOAT, false,
        3 * 4, mNormalBuffer);
        // 启用顶点位置数据
        GLES20.glEnableVertexAttribArray(maPositionHandle); 
        GLES20.glEnableVertexAttribArray(maNormalHandle);// 启用顶点法向量数据
        // 绘制球		
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);
        ```

        

      - 附送，改变位置

        ```
         GLES20.glUniform3fv(maLightLocationHandle, 1, MatrixState.lightPositionFB);
        ```

        

      - 计算向量

        ```
        void pointLight (								//散射光光照计算的方法
          in vec3 normal,								//法向量
          inout vec4 diffuse,								//散射光计算结果
          in vec3 lightLocation,							//光源位置
          in vec4 lightDiffuse							//散射光强度
        ){  
          vec3 normalTarget=aPosition+normal;					//计算变换后的法向量
          vec3 newNormal=(uMMatrix*vec4(normalTarget,1)).xyz-(uMMatrix*vec4(aPosition,1)).xyz;
          newNormal=normalize(newNormal);					//对法向量规格化
        //计算从表面点到光源位置的向量vp
          vec3 vp= normalize(lightLocation-(uMMatrix*vec4(aPosition,1)).xyz);
          vp=normalize(vp);									//规格化vp
          float nDotViewPosition=max(0.0,dot(newNormal,vp)); 	//求法向量与vp向量的点积与0的最大值
          diffuse=lightDiffuse*nDotViewPosition;			//计算散射光的最终强度
        }
        ```

        上面可以看做是一个公式吧。

        -----------

        镜面光

        当光滑的物体被照射之后会有很强的反射，











## 纹理

​       纹理简单理解就是画出一个物体，我们在物体的表面上用图片覆盖在它的上面。加入我画出一个三角形，但是三角形是没有任何其他东西的，仅仅是个三角形，我们可以在它的上面加上纹理，变为一个砖块的纹理。

​	纹理它的左上角为0，最大的数值为1，

​	纹理使用步骤，创建纹理，将图片加载。

使用步骤：

- 创建纹理

- 写shader，获取纹理（因为纹理是一段一段进行的，所以有一个段进行作为每次的渲染）

  1.顶点着色器

  ```
  uniform mat4 uMVPMatrix; //总变换矩阵
  attribute vec3 aPosition;  //顶点位置
  attribute vec2 aTexCoor;    //顶点纹理坐标
  varying vec2 vTextureCoord;  //用于传递给片元着色器的变量
  void main()     
  {                            		
     gl_Position = uMVPMatrix * vec4(aPosition,1); //根据总变换矩阵计算此次绘制此顶点位置
     vTextureCoord = aTexCoor;//将接收的纹理坐标传递给片元着色器
  }        
  ```

  2.片段着色器

  ```
  precision mediump float;
  varying vec2 vTextureCoord; //接收从顶点着色器过来的参数
  uniform sampler2D sTexture;//纹理内容数据
  void main()                         
  {           
     //给此片元从纹理中采样出颜色值            
     gl_FragColor = texture2D(sTexture, vTextureCoord); 
  }       
  ```

  3.创建一个顶点和纹理位置

  ```
  float vertices[]=new float[]
  {
  0*UNIT_SIZE,11*UNIT_SIZE,0,
  -11*UNIT_SIZE,-11*UNIT_SIZE,0,
  11*UNIT_SIZE,-11*UNIT_SIZE,0,
  };
  
  //创建顶点坐标数据缓冲
  //vertices.length*4是因为一个整数四个字节
  ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
  vbb.order(ByteOrder.nativeOrder());//设置字节顺序
  mVertexBuffer = vbb.asFloatBuffer();//转换为Float型缓冲
  mVertexBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
  mVertexBuffer.position(0);//设置缓冲区起始位置
  //特别提示：由于不同平台字节顺序不同数据单元不是字节的一定要经过ByteBuffer
  //转换，关键是要通过ByteOrder设置nativeOrder()，否则有可能会出问题
  //顶点坐标数据的初始化================end============================
  
  //顶点纹理坐标数据的初始化================begin============================
  float texCoor[]=new float[]//顶点颜色值数组，每个顶点4个色彩值RGBA
  {
  0.5f,0, 
  0,1, 
  1,1        		
  };        
  //创建顶点纹理坐标数据缓冲
  ByteBuffer cbb = ByteBuffer.allocateDirect(texCoor.length*4);
  cbb.order(ByteOrder.nativeOrder());//设置字节顺序
  mTexCoorBuffer = cbb.asFloatBuffer();//转换为Float型缓冲
  mTexCoorBuffer.put(texCoor);//向缓冲区中放入顶点着色数据
  mTexCoorBuffer.position(0);//设置缓冲区起始位置
  ```

  4.获取位置

  ```
  maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
  //获取程序中顶点纹理坐标属性引用id  
  maTexCoorHandle= GLES20.glGetAttribLocation(mProgram, "aTexCoor");
  //获取程序中总变换矩阵引用id
  muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
  ```

  5.绑定数据

  ```
           GLES20.glVertexAttribPointer  
           (
           		maPositionHandle,   
           		3, 
           		GLES20.GL_FLOAT, 
           		false,
                  3*4,   
                  mVertexBuffer
           );       
           //为画笔指定顶点纹理坐标数据
           GLES20.glVertexAttribPointer  
           (
          		maTexCoorHandle, 
           		2, 
           		GLES20.GL_FLOAT, 
           		false,
                  2*4,   
                  mTexCoorBuffer
           );   
  ```

  6.绑定纹理

  ```
          //绑定纹理
           GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
           GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texId);
           
           //绘制纹理矩形
           GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount); 
  ```

  

  ------------

  **纹理的其他知识**

  上面仅仅是对纹理的一个简单使用，下来是纹理的其他内容。

  - 拉伸方式

    - 重复拉伸

      设置重复拉伸，当顶点数据大于1的时候。就会进行纹理的重复显示，比如

      ![1553736150254](笔记图片\1553736150254.png)

      ![1553736367722](笔记图片\1553736367722.png)

      纹理参数设置

      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GLES20.GL_REPEAT);

      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GLES20.GL_REPEAT);

      应用场景，比如地图中草坪的显示。

    - 截取拉伸

      当纹理值大于1的时候就会对边缘进行拉伸

      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GLES20.GL_CLAMP_TO_EDGE);

      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GLES20.GL_CLAMP_TO_EDGE);

      原图

      ![1553736746311](笔记图片\1553736746311.png)

      

      ![1553736883156](笔记图片\1553736883156.png)

  我们在上面用到了

  	GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);	GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
  如果说纹理采样就是将坐标文职的和纹理中的位置进行提取对应的一个过程，但是有时候纹理并不是一一对应的，小的纹理到大的图元，那么就需要放大，反之。

  

  - 采样：最近点采样
    - 特点：速度最快，计算量小
    - 缺点：有锯齿。
    - 应用场景，缩小的时候可以使用。不适合在放大的环境下。
  - 线性纹理采样
    - 它不仅仅是使用一个像素，而是使用附近的多个像素，采样时对附近多个像素进行了加权平均，因此不会有锯齿，但是有模糊。

```
GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_LINEAR);	GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);

```

纹理的采样一般都是MAX和MIN两种方式，纹理比图元大的时候就需要使用MIN对应的纹理，当纹理比图元小的时候就需要使用MAX的纹理。

- mipmap纹理技术

  有时候会有一种现象，远处的比近处的景物清晰，因为纹理的大小对应到图元上的效果，造成的。

  可以使用在远处的物体使用尺寸不同的纹理就可以解决。在openGL中，他会自己的进行处理，仅仅需要一个简单的处理即可。他会自己生成每次是自己1/2的图，直到最后为1的时候结束。

- 多重纹理和过程纹理{随后加}

- 压缩  {随后加}



## 丰富的立体形状

​	此部分学习图形，已经知道，在openGl中物体都是由三角形组成的，那么我们仅仅是需要使用三角形组成各物体。

### 三角形



![1553739717948](笔记图片\1553739717948.png)

指定是三个顶点坐标。

- 六角形

  ​	![1553741220410](笔记图片\1553741220410.png)

这图形，可以看做是两个圆，一个是大圆，一个是小圆，一个旋转30du，一个旋转60

所以顶点写法就是图中坐标的位置，将他们放入数组中，然后在写入顶点缓存。

0 0 Z    |    rcos(x)  rsin(x)  Z |  Rcos(x)  Rsin(x)  Z 

0 0 Z    |    Rcos(x) Rsin(x) Z |  rcos(x+angle) rsin(x+angle) Z  

然后循环一周即可。

- 正方体

  ![1553742845631](E:\专题\笔记图片\1553742845631.png)

  一个面一个面处理，使用三角形扇或者三角形都可以{下来试，感觉不可以，因为三角扇是一个顶点，向四周去画出三角形}表示，上下作用，每个点按照坐标点写即可，写的时候注意别写错了就可以。颜色表示，也别客气，给每个点设置参数好了。

  - 三角形条形带绘制扇形

    ![1553754191002](笔记图片\1553754191002.png)

    其实也是圆上点，旋转角度，然后按照顺序得出即可。

    ![1553754141112](笔记图片\1553754141112.png)

  R*cos(x)  Rsin(x) Z  |   r*cos(x)  rsin(x) Z   使用一个循环，两个点循环。

  - 球

    计算过程，先获取到一个球的半径，半径cos(x)求出每一个○的半径，○cos

    ![1553755568159](笔记图片\1553755568159.png)

  

  ![1553755607133](笔记图片\1553755607133.png)

  ![1553755661295](笔记图片\1553755661295.png)

  

  ![1553756016284](笔记图片\1553756016284.png)

  

  - 圆柱体

    圆柱体有两个圆和一个长方形组成，

    ![1553756390847](笔记图片\1553756390847.png)

    如果使用纹理，纹理坐标通过圆上的坐标进行转换。

    ![1553757708068](笔记图片\1553757708068.png)

  

  圆柱侧面画法：	

  ​	圆柱侧面是一个四边形，他可以看做是一个一个的三角形组成。

  ![1553820094284](笔记图片\1553820094284.png)

  

  点的坐标R*cos(x)  y  Rsin(x)将数据的结果表示xyz

  圆柱体滑动时候，先画出上面的○，将其下移，在画出底部，在画出侧面

  - 圆锥体

    顶点坐标计算

    ![1553820838132](笔记图片\1553820838132.png)

  ![1553820941936](笔记图片\1553820941936.png)

  顶点坐标为Rcos(x)   0  Rsin(x)   |||  0 y 0 ||| Rsin(x+angle) 0 Rcos(x+angle)

  

  ## 图像绘制方式

  

  open GL ES不仅仅可以使用三角形，也可以使用其他的点、线画图。

  - 点：GL_POINT
  - 线段：GL_LINES
  - 顶点顺序连接，但不封口：GL_LINES_STRIP
  - 顶点顺序连接，但封口:GL_LINES_LOOP
  - 三角形：GL_TRIANGLES
  - 将点顺次连接：GL_TRIANGLES_STRIP
  - 三角形扇：GL_TRIANGLES_FAN

  

  ## 加载3D模型

  ​	obj模型文件的概述

  ​	介绍如何用程序obj之前，了解一下文件的导入导出，obj本质就是一个文本文件，只是具有固定的格式，其格式为基本单元的格式定义，只不过在每个的开头一个特殊的字符。

  '#'表示注释

  ‘v’存放顶点的位置

  'vt'存放纹理坐标的位置

  'vn'存放法向量

  'g'一组的开始

  'f'表示一个面

  

  

  

  ## 混合和雾

  ​	物体都不是透明的，但是一般都是透明的，所以需要在通过各项测试之后，进入帧缓存区中的偏片源和帧缓存中原有的片源按照设定的比例加权计算出最终的颜色，可以理解为新片源将不再直接覆盖缓存区中的源片元。

  ​	混合前设定加权比例，OpenGLES设定混合因子，一般为两个：

  - 第一个源因子，用于确定将进入帧缓存最终片源比例

  - 确定员帧片源在最终片源的比例、 

    

  

