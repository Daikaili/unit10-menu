package com.example.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
      TextView tView[]=new TextView[4];
      int num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int[] files=new int[]{
				R.id.tvOne,R.id.tvTwo,R.id.tvThree,R.id.tvFouth};
		//���ı���ID��ɵ�����
		for(int i=0;i<tView.length;i++){
			tView[i]=(TextView) this.findViewById(files[i]);
			//��ʼ�����飬����ID�ҵ���Ӧ���ı���
			registerForContextMenu(tView[i]);
			//Ϊ�ı���ע�������Ĳ˵�
			}
	}
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){
		//�������������Ĳ˵��Ĵ��� 
		switch(v.getId()){
		case R.id.tvOne:
			num=1;
			break;
		case R.id.tvTwo:
			num=2;
			break;
		case R.id.tvThree:
			num=3;
			break;
		case R.id.tvFouth:
			num=4;
			break;
		default:
			break;
		}
		menu.setHeaderTitle("�ļ�����");
		menu.add(0,Menu.FIRST+num *10+1,0,"����");
		SubMenu subMenu=menu.addSubMenu(0,Menu.FIRST+num*10+2,1,"�������ֵ���ɫ");
		//����Ӳ˵�
		subMenu.setHeaderTitle("The Second Level Menu");
		subMenu.add(0,Menu.FIRST+num*100+21,0,"��ɫ");
		subMenu.add(0,Menu.FIRST+num*100+22,0,"��ɫ");
		subMenu.add(0,Menu.FIRST+num*100+23,0,"��ɫ");
		//�Ӳ˵��������
		menu.add(0,Menu.FIRST+num*10+3,2,"������");
		menu.add(0,Menu.FIRST+num*10+4,3,"ɾ��");
		//��Ӳ˵���
		super.onCreateContextMenu(menu, v, menuInfo);
		//���ø���ĸ÷���
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
