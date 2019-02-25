package com.example.lession_01;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import static android.opengl.GLES10.glViewport;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES10.glClearColor;
import static android.opengl.GLES10.glClear;
import static android.opengl.GLES10.GL_COLOR_BUFFER_BIT;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {
	//ÿ��������������
	private static final int POSITION_COMPONENT_COUNT = 2;
	//ÿ�����ݵ��ֽ���
	private static final int BYTES_PER_FLOAT = 4;
	//���Ƶ����ش洢��λ��
	private final FloatBuffer vertexData;
	public MyRenderer() {
		float []tableVertices = {
				/*����һ���ı��Σ�����ͼ��ֻ��ʹ�õ��������Σ�����ʹ�õ����������������
				 * 0f,0f,
					0f,14f,
					9f,0f,
					9f,14f
					*/
				/*���´�������*/
				/*x   y */
				0f,0f,
				9f,14f,
				0f,14f,
				0f,0f,
				9f,0f,
				9f,14f,
				
				0f,7f,
				9f,7f,
				
				4.5f,2f,
				4.5f ,12f
		};

		vertexData = ByteBuffer.allocateDirect(BYTES_PER_FLOAT*tableVertices.length).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertexData.put(tableVertices);
	}
	@Override
	public void onDrawFrame(GL10 arg0) {
		// TODO Auto-generated method stub
		glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		// TODO Auto-generated method stub
		glViewport(0,0,width,height);
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		// TODO Auto-generated method stub
		glClearColor(1.0f,1.0f,1.0f,1.0f);
	}

}
