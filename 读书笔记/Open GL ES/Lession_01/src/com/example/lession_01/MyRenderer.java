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
	//ÿ��������������
	private static final int POSITION_COMPONENT_COUNT = 2;
	//ÿ�����ݵ��ֽ���
	private static final int BYTES_PER_FLOAT = 4;
	//���Ƶ����ش洢��λ��
	private final FloatBuffer vertexData;
	//����Context
	private Context context;
	//�õ����ӳ���ID
	private int progarm ;
	//����һ������������λ����ɫ��Ϣ��
	private static final String U_COLOR = "u_Color";
	private int uColorLocation ;
	//��ȡ����λ��
	private static final String A_POSITION="a_Position";
	private int aPosition ;
	public MyRenderer(Context context) {
		this.context = context;
		float []tableVertices = {
				/*����һ���ı��Σ�����ͼ��ֻ��ʹ�õ��������Σ�����ʹ�õ����������������
				 * 0f,0f,
					0f,14f,
					9f,0f,
					9f,14f
					*/
				/*���´�������*/
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
		//���ض�����ɫ��
		String vertextShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_vertext_shaper);
		//����Ƭ����ɫ��
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.first_fragment_shader);
	
		int vertextShader = ShaderHelper.compileVertexShader(vertextShaderSource);
		int fragmentShader = ShaderHelper.comileFileFragmentShader(fragmentShaderSource);
		
		/*���洴���˶��㣬��ɫ�����Լ������߰���һ���ˣ����ؾ��ǽ���ɫ�����뵽OpenGLES*/
		progarm = ShaderHelper.linkProgram(vertextShader, fragmentShader);
		ShaderHelper.validateProgram(progarm);
		glUseProgram(progarm);
		//��ȡuniformλ��
		uColorLocation = glGetUniformLocation(progarm,U_COLOR);
		//��ȡ����λ��
		aPosition = glGetAttribLocation(progarm, A_POSITION);
		vertexData.position(0);
		glVertexAttribPointer(aPosition, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData);
		glEnableVertexAttribArray(aPosition);
	}

}
