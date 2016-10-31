package com.example.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
      TextView tView[]=new TextView[4];
      
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
