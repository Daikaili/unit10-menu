package com.example.menu;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 * 真正创建上下文菜单的代码 
	 */
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 * 补充完整 onCreateOptionsMenu方法
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public boolean onOptionsItemSeiected(MenuItem item){
		switch(item.getItemId()){
		case R.id.start:
		case R.id.stop:
		  invalidateOptionsMenu();
		  break;
		case R.id.exit:
			finish();
			break;
     	default:
			break;
		}
		Toast.makeText(MainActivity.this, item.getTitle()+"被单击了",1000).show();
		return true;
		
	}
	/**
	 * @param menu
	 * 调用和完整onPrepareOptionMenu方法
	 * @return
	 */
	public boolean onPrepareOptionMenu(Menu menu){
		super.onPrepareOptionsMenu(menu);
		MenuItem start=menu.findItem(R.id.start);
		MenuItem stop=menu.findItem(R.id.stop);
		boolean flag = false;
		start.setEnabled(flag);
		stop.setEnabled(!flag);
		return true;
		
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 * 重写完整activity中onCreateContextItemSelectd方法
	 */
	public boolean onContextItemSelected(MenuItem item){
		String mesString="你选择的是：";
		int count=item.getItemId()-Menu.FIRST;
		num=count/10;
		if(num>10){
			num=num/10;
		}
		//计算num的值并确保与上面的值一致
		if(item.getItemId()==(Menu.FIRST+num*10+1)){
			 mesString+="发送";                             
		}
		else if(item.getItemId()==(Menu.FIRST+num*10+2)){
			mesString+="进入颜色设置界面";   
		}else if(item.getItemId()==(Menu.FIRST+num*100+21)){
			tView[num-1].setTextColor(Color.RED);
			//是否选择红色，且设置文本框颜色
		}else if(item.getItemId()==(Menu.FIRST+num*100+22)){
			tView[num-1].setTextColor(Color.BLUE);
			//是否选择蓝色，且设置文本框颜色
		}else if(item.getItemId()==(Menu.FIRST+num*100+23)){
			tView[num-1].setTextColor(Color.GREEN);
			//是否选择绿色，且设置文本框颜色
		}else if(item.getItemId()==(Menu.FIRST+num*10+3)){
			final EditText inputname=new EditText(this);
			//创建一个文本编辑框
			AlertDialog bDialog=new AlertDialog.Builder(MainActivity.this)
			.setIcon(android.R.drawable.btn_star)
			.setTitle("请输入新名字")
			.setView(inputname)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					tView[num-1].setText(inputname.getText().toString());
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			}).create();
			//设置对话框图标，标题，对话框显示控件
		bDialog.show();
		mesString+="重命名成功";
	}else if(item.getItemId()==(Menu.FIRST+num*10+4)){
		mesString+="删除";
	}
		Toast.makeText(this, mesString,Toast.LENGTH_LONG).show();
		
		return true;
		
	}
}
