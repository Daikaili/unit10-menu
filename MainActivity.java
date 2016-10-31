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
		//各文本框ID组成的数组
		for(int i=0;i<tView.length;i++){
			tView[i]=(TextView) this.findViewById(files[i]);
			//初始化数组，根据ID找到对应的文本框
			registerForContextMenu(tView[i]);
			//为文本框注册上下文菜单
			}
	}
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){
		//真正创建上下文菜单的代码 
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
		menu.setHeaderTitle("文件操作");
		menu.add(0,Menu.FIRST+num *10+1,0,"发送");
		SubMenu subMenu=menu.addSubMenu(0,Menu.FIRST+num*10+2,1,"设置文字的颜色");
		//添加子菜单
		subMenu.setHeaderTitle("The Second Level Menu");
		subMenu.add(0,Menu.FIRST+num*100+21,0,"红色");
		subMenu.add(0,Menu.FIRST+num*100+22,0,"黄色");
		subMenu.add(0,Menu.FIRST+num*100+23,0,"绿色");
		//子菜单添加子项
		menu.add(0,Menu.FIRST+num*10+3,2,"重命名");
		menu.add(0,Menu.FIRST+num*10+4,3,"删除");
		//添加菜单项
		super.onCreateContextMenu(menu, v, menuInfo);
		//调用父类的该方法
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
