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
		//���ı���ID��ɵ�����
		for(int i=0;i<tView.length;i++){
			tView[i]=(TextView) this.findViewById(files[i]);
			//��ʼ�����飬����ID�ҵ���Ӧ���ı���
			registerForContextMenu(tView[i]);
			//Ϊ�ı���ע�������Ĳ˵�
			}
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 * �������������Ĳ˵��Ĵ��� 
	 */
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 * �������� onCreateOptionsMenu����
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
		Toast.makeText(MainActivity.this, item.getTitle()+"��������",1000).show();
		return true;
		
	}
	/**
	 * @param menu
	 * ���ú�����onPrepareOptionMenu����
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
	 * ��д����activity��onCreateContextItemSelectd����
	 */
	public boolean onContextItemSelected(MenuItem item){
		String mesString="��ѡ����ǣ�";
		int count=item.getItemId()-Menu.FIRST;
		num=count/10;
		if(num>10){
			num=num/10;
		}
		//����num��ֵ��ȷ���������ֵһ��
		if(item.getItemId()==(Menu.FIRST+num*10+1)){
			 mesString+="����";                             
		}
		else if(item.getItemId()==(Menu.FIRST+num*10+2)){
			mesString+="������ɫ���ý���";   
		}else if(item.getItemId()==(Menu.FIRST+num*100+21)){
			tView[num-1].setTextColor(Color.RED);
			//�Ƿ�ѡ���ɫ���������ı�����ɫ
		}else if(item.getItemId()==(Menu.FIRST+num*100+22)){
			tView[num-1].setTextColor(Color.BLUE);
			//�Ƿ�ѡ����ɫ���������ı�����ɫ
		}else if(item.getItemId()==(Menu.FIRST+num*100+23)){
			tView[num-1].setTextColor(Color.GREEN);
			//�Ƿ�ѡ����ɫ���������ı�����ɫ
		}else if(item.getItemId()==(Menu.FIRST+num*10+3)){
			final EditText inputname=new EditText(this);
			//����һ���ı��༭��
			AlertDialog bDialog=new AlertDialog.Builder(MainActivity.this)
			.setIcon(android.R.drawable.btn_star)
			.setTitle("������������")
			.setView(inputname)
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					tView[num-1].setText(inputname.getText().toString());
				}
			}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			}).create();
			//���öԻ���ͼ�꣬���⣬�Ի�����ʾ�ؼ�
		bDialog.show();
		mesString+="�������ɹ�";
	}else if(item.getItemId()==(Menu.FIRST+num*10+4)){
		mesString+="ɾ��";
	}
		Toast.makeText(this, mesString,Toast.LENGTH_LONG).show();
		
		return true;
		
	}
}
