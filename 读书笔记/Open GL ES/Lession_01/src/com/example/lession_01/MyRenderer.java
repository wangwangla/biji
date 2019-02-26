package com.example.lession_01;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.util.ShaderHelper;
import com.example.util.TextResourceReader;

import static android.opengl.GLES10.glViewport;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES10.glClearColor;
import static android.opengl.GLES10.glClear;
import static android.opengl.GLES10.GL_COLOR_BUFFER_BIT;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {
	//每个顶点的坐标个数
	private static final int POSITION_COMPONENT_COUNT = 2;
	//每个数据的字节数
	private static final int BYTES_PER_FLOAT = 4;
	//复制到本地存储的位置
	private final FloatBuffer vertexData;
	//定义Context
	private Context context;
	//得到连接程序ID
	private int progarm ;
	//创建一个名字来容纳位置颜色信息。
	private static final String U_COLOR = "u_Color";
	private int uColorLocation ;
	//获取属性位置
	private static final String A_POSITION="a_Position";
	private int aPosition ;
	public MyRenderer(Context context) {
		this.context = context;
		float []tableVertices = {
				/*定义一个四边形，但是图像只能使用的是三角形，所以使用的是两个三角形组成
				 * 0f,0f,
					0f,14f,
					9f,0f,
					9f,14f
					*/
				/*更新代码如下*/
				/*x   y */
				-0.5f,-0.5f,
				0.5f,0.5f,
				-0.5f,0.5f,
				
				-0.5f,-0.5f,
				0.5f,-0.5f,
				0.5f,0.5f,
				
				-0.5f,0f,
				0.5f,0f,
				
				0f,-0.25f,
				0f ,0.25f
		};

		vertexData = ByteBuffer.allocateDirect(BYTES_PER_FLOAT*tableVertices.length).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertexData.put(tableVertices);
	}
	@Override
	public void onDrawFrame(GL10 arg0) {
		// TODO Auto-generated method stub
		glClear(GL_COLOR_BUFFER_BIT);
		glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glUniform4f(uColorLocation, 0.0f, 0.0f, 0.0f, 1.0f);
		glDrawArrays(GL_LINES, 6, 2);
		glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
		glDrawArrays(GL_POINTS, 8, 1);
		glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
		glDrawArrays(GL_POINTS, 9, 1);
		
		
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		// TODO Auto-generated method stub
		glViewport(0,0,width,height);
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		// TODO Auto-generated method stub
		glClearColor(1.0f,0.0f,0.0f,0.0f);
		//加载顶点着色器
		String vertextShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_vertext_shaper);
		//加载片段着色器
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_fragment_shader);
	
		int vertextShader = ShaderHelper.compileVertexShader(vertextShaderSource);
		int fragmentShader = ShaderHelper.comileFileFragmentShader(fragmentShaderSource);
		
		/*上面创建了顶点，着色器，以及将二者绑定在一起了，下载就是将着色器放入到OpenGLES*/
		progarm = ShaderHelper.linkProgram(vertextShader, fragmentShader);
		ShaderHelper.validateProgram(progarm);
		glUseProgram(progarm);
		//获取uniform位置
		uColorLocation = glGetUniformLocation(progarm,U_COLOR);
		//获取属性位置
		aPosition = glGetAttribLocation(progarm, A_POSITION);
		vertexData.position(0);
		glVertexAttribPointer(aPosition, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData);
		glEnableVertexAttribArray(aPosition);
	}

}
