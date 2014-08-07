package com.thehp.sudoku;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	int i,j,I,J,put,track[][],q,c1,c2,c3;char ret;
	int given[][];
	boolean solved=false;
	Handler mhandler = new Handler();
	//Button b= (Button) findViewById(R.id.btn_solve);
	
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button b= (Button) findViewById(R.id.btn_solve);
        
        given = new int[9][9];
  	  track= new int[9][9];
        for(int p=0;p<9;p++)
        {
        	for(int q=0;q<9;q++)
        	{
        		given[p][q]=0;
        	track[p][q]=0;
        	}
        	}
        b.setOnTouchListener(new View.OnTouchListener() {        
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b.setBackgroundResource(R.drawable.solve);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                    	 b.setBackgroundResource(R.drawable.solve3);
                    	 popup();
                    	 
                         
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });
         
         }
 
    
   
public void popup()
{
	Button b= (Button) findViewById(R.id.btn_solve);

	LayoutInflater layoutInflater 
    = (LayoutInflater)getBaseContext()
     .getSystemService(LAYOUT_INFLATER_SERVICE);  
   View popupView = layoutInflater.inflate(R.layout.popup, null);  
            final PopupWindow popupWindow = new PopupWindow(
              popupView, 
              LayoutParams.WRAP_CONTENT,  
                    LayoutParams.WRAP_CONTENT);  
            
            Button btnback = (Button)popupView.findViewById(R.id.btn_back);
            Button btnsure = (Button)popupView.findViewById(R.id.btn_sure);
            btnback.setOnClickListener(new Button.OnClickListener(){

    @Override
    public void onClick(View v) {
     // TODO Auto-generated method stub
     popupWindow.dismiss();
    }});
            btnsure.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                 // TODO Auto-generated method stub
                	Toast.makeText(MainActivity.this, "Done.",Toast.LENGTH_SHORT).show();
                	solved=true;
               	 Log.e("solve","Entered");
            		for(int p=0;p<9;p++)
                    {
                    	for(int q=0;q<9;q++)
                    	{
                    		if(given[p][q]==0)
                    	track[p][q]=0;
                    		else track[p][q]=1;
                    		
                    	}
                    }                		
            		
            		Thread th= new Thread(){
                   	 public void run()
                   	 {
                   		 
                   		solve();
                   	 }
                    };th.start();
                 popupWindow.dismiss();
                }});
              
            popupWindow.showAsDropDown(b,130,-140);
        
  }

		
    
    public void btn_click(View view)
    {
    	Button btn;
    	String disp;
    	if(solved==false)
    	{
    	switch(view.getId())
    	{ 
    	case R.id.button1:
    		
    		given[0][0]++;
    		btn=(Button)findViewById(R.id.button1);
    		if(given[0][0]>9)
    			given[0][0]=0;
    		if(given[0][0]!=0)disp=String.valueOf(given[0][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		
    		//player++;
    		break;
    	case R.id.button2:
    		given[0][1]++;btn=(Button)findViewById(R.id.button2);
    		if(given[0][1]>9)
    			given[0][1]=0;
    		if(given[0][1]!=0)disp=String.valueOf(given[0][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.button3:
    		given[0][2]++;btn=(Button)findViewById(R.id.button3);
    		if(given[0][2]>9)
    			given[0][2]=0;
    		if(given[0][2]!=0)disp=String.valueOf(given[0][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button09:
    		given[1][0]++;btn=(Button)findViewById(R.id.Button09);
    		if(given[1][0]>9)
    			given[1][0]=0;
    		if(given[1][0]!=0)disp=String.valueOf(given[1][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button08:
    		given[1][1]++;btn=(Button)findViewById(R.id.Button08);
    		if(given[1][1]>9)
    			given[1][1]=0;
    		if(given[1][1]!=0)disp=String.valueOf(given[1][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button18:
    		given[1][2]++;btn=(Button)findViewById(R.id.Button18);
    		if(given[1][2]>9)
    			given[1][2]=0;
    		if(given[1][2]!=0)disp=String.valueOf(given[1][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button01:
    		given[2][0]++;btn=(Button)findViewById(R.id.Button01);
    		if(given[2][0]>9)
    			given[2][0]=0;
    		if(given[2][0]!=0)disp=String.valueOf(given[2][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button14:
    		given[2][1]++;btn=(Button)findViewById(R.id.Button14);
    		if(given[2][1]>9)
    			given[2][1]=0;
    		if(given[2][1]!=0)disp=String.valueOf(given[2][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button22:
    		given[2][2]++;btn=(Button)findViewById(R.id.Button22);
    		if(given[2][2]>9)
    			given[2][2]=0;
    		if(given[2][2]!=0)disp=String.valueOf(given[2][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	//grid2	
    	case R.id.button4:
    		given[0][3]++;btn=(Button)findViewById(R.id.button4);
    		if(given[0][3]>9)
    			given[0][3]=0;
    		if(given[0][3]!=0)disp=String.valueOf(given[0][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.button5:
    		given[0][4]++;btn=(Button)findViewById(R.id.button5);
    		if(given[0][4]>9)
    			given[0][4]=0;
    		if(given[0][4]!=0)disp=String.valueOf(given[0][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.button6:
    		given[0][5]++;btn=(Button)findViewById(R.id.button6);
    		if(given[0][5]>9)
    			given[0][5]=0;
    		if(given[0][5]!=0)disp=String.valueOf(given[0][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button26:
    		given[1][3]++;btn=(Button)findViewById(R.id.Button26);
    		if(given[1][3]>9)
    			given[1][3]=0;
    		if(given[1][3]!=0)disp=String.valueOf(given[1][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button34:
    		given[1][4]++;btn=(Button)findViewById(R.id.Button34);
    		if(given[1][4]>9)
    			given[1][4]=0;
    		if(given[1][4]!=0)disp=String.valueOf(given[1][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button42:
    		given[1][5]++;btn=(Button)findViewById(R.id.Button42);
    		if(given[1][5]>9)
    			given[1][5]=0;
    		if(given[1][5]!=0)disp=String.valueOf(given[1][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button30:
    		given[2][3]++;btn=(Button)findViewById(R.id.Button30);
    		if(given[2][3]>9)
    			given[2][3]=0;
    		if(given[2][3]!=0)disp=String.valueOf(given[2][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button38:
    		given[2][4]++;btn=(Button)findViewById(R.id.Button38);
    		if(given[2][4]>9)
    			given[2][4]=0;
    		if(given[2][4]!=0)disp=String.valueOf(given[2][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button46:
    		given[2][5]++;btn=(Button)findViewById(R.id.Button46);
    		if(given[2][5]>9)
    			given[2][5]=0;
    		if(given[2][5]!=0)disp=String.valueOf(given[2][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid3
    		
    	case R.id.button7:
    		given[0][6]++;btn=(Button)findViewById(R.id.button7);
    		if(given[0][6]>9)
    			given[0][6]=0;
    		if(given[0][6]!=0)disp=String.valueOf(given[0][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.button8:
    		given[0][7]++;btn=(Button)findViewById(R.id.button8);
    		if(given[0][7]>9)
    			given[0][7]=0;
    		if(given[0][7]!=0)disp=String.valueOf(given[0][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.button9:
    		given[0][8]++;btn=(Button)findViewById(R.id.button9);
    		if(given[0][8]>9)
    			given[0][8]=0;
    		if(given[0][8]!=0)disp=String.valueOf(given[0][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button50:
    		given[1][6]++;btn=(Button)findViewById(R.id.Button50);
    		if(given[1][6]>9)
    			given[1][6]=0;
    		if(given[1][6]!=0)disp=String.valueOf(given[1][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button58:
    		given[1][7]++;btn=(Button)findViewById(R.id.Button58);
    		if(given[1][7]>9)
    			given[1][7]=0;
    		if(given[1][7]!=0)disp=String.valueOf(given[1][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button66:
    		given[1][8]++;btn=(Button)findViewById(R.id.Button66);
    		if(given[1][8]>9)
    			given[1][8]=0;
    		if(given[1][8]!=0)disp=String.valueOf(given[1][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button54:
    		given[2][6]++;btn=(Button)findViewById(R.id.Button54);
    		if(given[2][6]>9)
    			given[2][6]=0;
    		if(given[2][6]!=0)disp=String.valueOf(given[2][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button62:
    		given[2][7]++;btn=(Button)findViewById(R.id.Button62);
    		if(given[2][7]>9)
    			given[2][7]=0;
    		if(given[2][7]!=0)disp=String.valueOf(given[2][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button70:
    		given[2][8]++;btn=(Button)findViewById(R.id.Button70);
    		if(given[2][8]>9)
    			given[2][8]=0;
    		if(given[2][8]!=0)disp=String.valueOf(given[2][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid4
    	case R.id.Button02:
    		given[3][0]++;btn=(Button)findViewById(R.id.Button02);
    		if(given[3][0]>9)
    			given[3][0]=0;
    		if(given[3][0]!=0)disp=String.valueOf(given[3][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button13:
    		given[3][1]++;btn=(Button)findViewById(R.id.Button13);
    		if(given[3][1]>9)
    			given[3][1]=0;
    		if(given[3][1]!=0)disp=String.valueOf(given[3][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button19:
    		given[3][2]++;btn=(Button)findViewById(R.id.Button19);
    		if(given[3][2]>9)
    			given[3][2]=0;
    		if(given[3][2]!=0)disp=String.valueOf(given[3][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button04:
    		given[4][0]++;btn=(Button)findViewById(R.id.Button04);
    		if(given[4][0]>9)
    			given[4][0]=0;
    		if(given[4][0]!=0)disp=String.valueOf(given[4][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button15:
    		given[4][1]++;btn=(Button)findViewById(R.id.Button15);
    		if(given[4][1]>9)
    			given[4][1]=0;
    		if(given[4][1]!=0)disp=String.valueOf(given[4][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button21:
    		given[4][2]++;btn=(Button)findViewById(R.id.Button21);
    		if(given[4][2]>9)
    			given[4][2]=0;
    		if(given[4][2]!=0)disp=String.valueOf(given[4][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button03:
    		given[5][0]++;btn=(Button)findViewById(R.id.Button03);
    		if(given[5][0]>9)
    			given[5][0]=0;
    		if(given[5][0]!=0)disp=String.valueOf(given[5][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button16:
    		given[5][1]++;btn=(Button)findViewById(R.id.Button16);
    		if(given[5][1]>9)
    			given[5][1]=0;
    		if(given[5][1]!=0)disp=String.valueOf(given[5][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button17:
    		given[5][2]++;btn=(Button)findViewById(R.id.Button17);
    		if(given[5][2]>9)
    			given[5][2]=0;
    		if(given[5][2]!=0)disp=String.valueOf(given[5][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid6
    	case R.id.Button51:
    		given[3][6]++;btn=(Button)findViewById(R.id.Button51);
    		if(given[3][6]>9)
    			given[3][6]=0;
    		if(given[3][6]!=0)disp=String.valueOf(given[3][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button59:
    		given[3][7]++;btn=(Button)findViewById(R.id.Button59);
    		if(given[3][7]>9)
    			given[3][7]=0;
    		if(given[3][7]!=0)disp=String.valueOf(given[3][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button67:
    		given[3][8]++;btn=(Button)findViewById(R.id.Button67);
    		if(given[3][8]>9)
    			given[3][8]=0;
    		if(given[3][8]!=0)disp=String.valueOf(given[3][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button53:
    		given[4][6]++;btn=(Button)findViewById(R.id.Button53);
    		if(given[4][6]>9)
    			given[4][6]=0;
    		if(given[4][6]!=0)disp=String.valueOf(given[4][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button61:
    		given[4][7]++;btn=(Button)findViewById(R.id.Button61);
    		if(given[4][7]>9)
    			given[4][7]=0;
    		if(given[4][7]!=0)disp=String.valueOf(given[4][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button69:
    		given[4][8]++;btn=(Button)findViewById(R.id.Button69);
    		if(given[4][8]>9)
    			given[4][8]=0;
    		if(given[4][8]!=0)disp=String.valueOf(given[4][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button49:
    		given[5][6]++;btn=(Button)findViewById(R.id.Button49);
    		if(given[5][6]>9)
    			given[5][6]=0;
    		if(given[5][6]!=0)disp=String.valueOf(given[5][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button57:
    		given[5][7]++;btn=(Button)findViewById(R.id.Button57);
    		if(given[5][7]>9)
    			given[5][7]=0;
    		if(given[5][7]!=0)disp=String.valueOf(given[5][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button65:
    		given[5][8]++;btn=(Button)findViewById(R.id.Button65);
    		if(given[5][8]>9)
    			given[5][8]=0;
    		if(given[5][8]!=0)disp=String.valueOf(given[5][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid5
    	case R.id.Button27:
    		given[3][3]++;btn=(Button)findViewById(R.id.Button27);
    		if(given[3][3]>9)
    			given[3][3]=0;
    		if(given[3][3]!=0)disp=String.valueOf(given[3][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button35:
    		given[3][4]++;btn=(Button)findViewById(R.id.Button35);
    		if(given[3][4]>9)
    			given[3][4]=0;
    		if(given[3][4]!=0)disp=String.valueOf(given[3][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button43:
    		given[3][5]++;btn=(Button)findViewById(R.id.Button43);
    		if(given[3][5]>9)
    			given[3][5]=0;
    		if(given[3][5]!=0)disp=String.valueOf(given[3][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button29:
    		given[4][3]++;btn=(Button)findViewById(R.id.Button29);
    		if(given[4][3]>9)
    			given[4][3]=0;
    		if(given[4][3]!=0)disp=String.valueOf(given[4][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button37:
    		given[4][4]++;btn=(Button)findViewById(R.id.Button37);
    		if(given[4][4]>9)
    			given[4][4]=0;
    		if(given[4][4]!=0)disp=String.valueOf(given[4][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button45:
    		given[4][5]++;btn=(Button)findViewById(R.id.Button45);
    		if(given[4][5]>9)
    			given[4][5]=0;
    		if(given[4][5]!=0)disp=String.valueOf(given[4][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button25:
    		given[5][3]++;btn=(Button)findViewById(R.id.Button25);
    		if(given[5][3]>9)
    			given[5][3]=0;
    		if(given[5][3]!=0)disp=String.valueOf(given[5][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button33:
    		given[5][4]++;btn=(Button)findViewById(R.id.Button33);
    		if(given[5][4]>9)
    			given[5][4]=0;
    		if(given[5][4]!=0)disp=String.valueOf(given[5][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button41:
    		given[5][5]++;btn=(Button)findViewById(R.id.Button41);
    		if(given[5][5]>9)
    			given[5][5]=0;
    		if(given[5][5]!=0)disp=String.valueOf(given[5][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid7
    	case R.id.Button07:
    		given[6][0]++;btn=(Button)findViewById(R.id.Button07);
    		if(given[6][0]>9)
    			given[6][0]=0;
    		if(given[6][0]!=0)disp=String.valueOf(given[6][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button12:
    		given[6][1]++;btn=(Button)findViewById(R.id.Button12);
    		if(given[6][1]>9)
    			given[6][1]=0;
    		if(given[6][1]!=0)disp=String.valueOf(given[6][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button20:
    		given[6][2]++;btn=(Button)findViewById(R.id.Button20);
    		if(given[6][2]>9)
    			given[6][2]=0;
    		if(given[6][2]!=0)disp=String.valueOf(given[6][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button05:
    		given[7][0]++;btn=(Button)findViewById(R.id.Button05);
    		if(given[7][0]>9)
    			given[7][0]=0;
    		if(given[7][0]!=0)disp=String.valueOf(given[7][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button11:
    		given[7][1]++;btn=(Button)findViewById(R.id.Button11);
    		if(given[7][1]>9)
    			given[7][1]=0;
    		if(given[7][1]!=0)disp=String.valueOf(given[7][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button23:
    		given[7][2]++;btn=(Button)findViewById(R.id.Button23);
    		if(given[7][2]>9)
    			given[7][2]=0;
    		if(given[7][2]!=0)disp=String.valueOf(given[7][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button06:
    		given[8][0]++;btn=(Button)findViewById(R.id.Button06);
    		if(given[8][0]>9)
    			given[8][0]=0;
    		if(given[8][0]!=0)disp=String.valueOf(given[8][0]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button10:
    		given[8][1]++;btn=(Button)findViewById(R.id.Button10);
    		if(given[8][1]>9)
    			given[8][1]=0;
    		if(given[8][1]!=0)disp=String.valueOf(given[8][1]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button24:
    		given[8][2]++;btn=(Button)findViewById(R.id.Button24);
    		if(given[8][2]>9)
    			given[8][2]=0;
    		if(given[8][2]!=0)disp=String.valueOf(given[8][2]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid8
    	case R.id.Button28:
    		given[6][3]++;btn=(Button)findViewById(R.id.Button28);
    		if(given[6][3]>9)
    			given[6][3]=0;
    		if(given[6][3]!=0)disp=String.valueOf(given[6][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button36:
    		given[6][4]++;btn=(Button)findViewById(R.id.Button36);
    		if(given[6][4]>9)
    			given[6][4]=0;
    		if(given[6][4]!=0)disp=String.valueOf(given[6][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button44:
    		given[6][5]++;btn=(Button)findViewById(R.id.Button44);
    		if(given[6][5]>9)
    			given[6][5]=0;
    		if(given[6][5]!=0)disp=String.valueOf(given[6][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button31:
    		given[7][3]++;btn=(Button)findViewById(R.id.Button31);
    		if(given[7][3]>9)
    			given[7][3]=0;
    		if(given[7][3]!=0)disp=String.valueOf(given[7][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button39:
    		given[7][4]++;btn=(Button)findViewById(R.id.Button39);
    		if(given[7][4]>9)
    			given[7][4]=0;
    		if(given[7][4]!=0)disp=String.valueOf(given[7][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button47:
    		given[7][5]++;btn=(Button)findViewById(R.id.Button47);
    		if(given[7][5]>9)
    			given[7][5]=0;
    		if(given[7][5]!=0)disp=String.valueOf(given[7][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button32:
    		given[8][3]++;btn=(Button)findViewById(R.id.Button32);
    		if(given[8][3]>9)
    			given[8][3]=0;
    		if(given[8][3]!=0)disp=String.valueOf(given[8][3]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button40:
    		given[8][4]++;btn=(Button)findViewById(R.id.Button40);
    		if(given[8][4]>9)
    			given[8][4]=0;
    		if(given[8][4]!=0)disp=String.valueOf(given[8][4]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button48:
    		given[8][5]++;btn=(Button)findViewById(R.id.Button48);
    		if(given[8][5]>9)
    			given[8][5]=0;
    		if(given[8][5]!=0)disp=String.valueOf(given[8][5]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    		//grid9
    	case R.id.Button52:
    		given[6][6]++;btn=(Button)findViewById(R.id.Button52);
    		if(given[6][6]>9)
    			given[6][6]=0;
    		if(given[6][6]!=0)disp=String.valueOf(given[6][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button60:
    		given[6][7]++;btn=(Button)findViewById(R.id.Button60);
    		if(given[6][7]>9)
    			given[6][7]=0;
    		if(given[6][7]!=0)disp=String.valueOf(given[6][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button68:
    		given[6][8]++;btn=(Button)findViewById(R.id.Button68);
    		if(given[6][8]>9)
    			given[6][8]=0;
    		if(given[6][8]!=0)disp=String.valueOf(given[6][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button55:
    		given[7][6]++;btn=(Button)findViewById(R.id.Button55);
    		if(given[7][6]>9)
    			given[7][6]=0;
    		if(given[7][6]!=0)disp=String.valueOf(given[7][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button63:
    		given[7][7]++;btn=(Button)findViewById(R.id.Button63);
    		if(given[7][7]>9)
    			given[7][7]=0;
    		if(given[7][7]!=0)disp=String.valueOf(given[7][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button71:
    		given[7][8]++;btn=(Button)findViewById(R.id.Button71);
    		if(given[7][8]>9)
    			given[7][8]=0;
    		if(given[7][8]!=0)disp=String.valueOf(given[7][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button56:
    		given[8][6]++;btn=(Button)findViewById(R.id.Button56);
    		if(given[8][6]>9)
    			given[8][6]=0;
    		if(given[8][6]!=0)disp=String.valueOf(given[8][6]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    	case R.id.Button64:
    		given[8][7]++;btn=(Button)findViewById(R.id.Button64);
    		if(given[8][7]>9)
    			given[8][7]=0;
    		if(given[8][7]!=0)disp=String.valueOf(given[8][7]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;	
    	case R.id.Button72:
    		given[8][8]++;btn=(Button)findViewById(R.id.Button72);
    		if(given[8][8]>9)
    			given[8][8]=0;
    		if(given[8][8]!=0)disp=String.valueOf(given[8][8]);
    		else disp=" "; 		
    		
    		btn.setText(disp);
    		//player++;
    		break;
    		
    	case R.id.btn_solve:
    		Log.e("solve","Entered");
    		for(int p=0;p<9;p++)
            {
            	for(int q=0;q<9;q++)
            	{
            		Log.e("solve","given[][] = "+String.valueOf(given[p][q]));
            	}
            	}
    		for(int p=0;p<9;p++)
            {
            	for(int q=0;q<9;q++)
            	{
            		if(given[p][q]==0)
            	track[p][q]=0;
            		else track[p][q]=1;
            		
            	}
            	}
    		for(int p=0;p<9;p++)
            {
            	for(int q=0;q<9;q++)
            	{
            		Log.e("solve","track[][] = "+String.valueOf(track[p][q]));
            	}
            	}
    		
    		
    		Thread th= new Thread(){
           	 public void run()
           	 {
           		 
           		solve();
           	 }
            };th.start();
    		
    	
    	default:
    		
			break;       		
    				
    	}
    	}
    	
    	
    }  
    
    
    public void display()
    {
    	Log.e("solve","Enterde display");
    	Button btn;
    	btn=(Button) findViewById(R.id.button1);
    	btn.setText(String.valueOf(given[0][0]));
    	btn=(Button) findViewById(R.id.button2);
    	btn.setText(String.valueOf(given[0][1]));
    	btn=(Button) findViewById(R.id.button3);
    	btn.setText(String.valueOf(given[0][2]));
    	btn=(Button) findViewById(R.id.button4);
    	btn.setText(String.valueOf(given[0][3]));
    	btn=(Button) findViewById(R.id.button5);
    	btn.setText(String.valueOf(given[0][4]));
    	btn=(Button) findViewById(R.id.button6);
    	btn.setText(String.valueOf(given[0][5]));
    	btn=(Button) findViewById(R.id.button7);
    	btn.setText(String.valueOf(given[0][6]));
    	btn=(Button) findViewById(R.id.button8);
    	btn.setText(String.valueOf(given[0][7]));
    	btn=(Button) findViewById(R.id.button9);
    	btn.setText(String.valueOf(given[0][8]));
    	btn=(Button) findViewById(R.id.Button09);
    	btn.setText(String.valueOf(given[1][0]));
    	btn=(Button) findViewById(R.id.Button08);
    	btn.setText(String.valueOf(given[1][1]));
    	btn=(Button) findViewById(R.id.Button18);
    	btn.setText(String.valueOf(given[1][2]));
    	btn=(Button) findViewById(R.id.Button26);
    	btn.setText(String.valueOf(given[1][3]));
    	btn=(Button) findViewById(R.id.Button34);
    	btn.setText(String.valueOf(given[1][4]));
    	btn=(Button) findViewById(R.id.Button42);
    	btn.setText(String.valueOf(given[1][5]));
    	btn=(Button) findViewById(R.id.Button50);
    	btn.setText(String.valueOf(given[1][6]));
    	btn=(Button) findViewById(R.id.Button58);
    	btn.setText(String.valueOf(given[1][7]));
    	btn=(Button) findViewById(R.id.Button66);
    	btn.setText(String.valueOf(given[1][8]));
    	btn=(Button) findViewById(R.id.Button01);
    	btn.setText(String.valueOf(given[2][0]));
    	btn=(Button) findViewById(R.id.Button14);
    	btn.setText(String.valueOf(given[2][1]));
    	btn=(Button) findViewById(R.id.Button22);
    	btn.setText(String.valueOf(given[2][2]));
    	btn=(Button) findViewById(R.id.Button30);
    	btn.setText(String.valueOf(given[2][3]));
    	btn=(Button) findViewById(R.id.Button38);
    	btn.setText(String.valueOf(given[2][4]));
    	btn=(Button) findViewById(R.id.Button46);
    	btn.setText(String.valueOf(given[2][5]));
    	btn=(Button) findViewById(R.id.Button54);
    	btn.setText(String.valueOf(given[2][6]));
    	btn=(Button) findViewById(R.id.Button62);
    	btn.setText(String.valueOf(given[2][7]));
    	btn=(Button) findViewById(R.id.Button70);
    	btn.setText(String.valueOf(given[2][8]));
    	btn=(Button) findViewById(R.id.Button02);
    	btn.setText(String.valueOf(given[3][0]));
    	btn=(Button) findViewById(R.id.Button13);
    	btn.setText(String.valueOf(given[3][1]));
    	btn=(Button) findViewById(R.id.Button19);
    	btn.setText(String.valueOf(given[3][2]));
    	btn=(Button) findViewById(R.id.Button27);
    	btn.setText(String.valueOf(given[3][3]));
    	btn=(Button) findViewById(R.id.Button35);
    	btn.setText(String.valueOf(given[3][4]));
    	btn=(Button) findViewById(R.id.Button43);
    	btn.setText(String.valueOf(given[3][5]));
    	btn=(Button) findViewById(R.id.Button51);
    	btn.setText(String.valueOf(given[3][6]));
    	btn=(Button) findViewById(R.id.Button59);
    	btn.setText(String.valueOf(given[3][7]));
    	btn=(Button) findViewById(R.id.Button67);
    	btn.setText(String.valueOf(given[3][8]));
    	btn=(Button) findViewById(R.id.Button04);
    	btn.setText(String.valueOf(given[4][0]));
    	btn=(Button) findViewById(R.id.Button15);
    	btn.setText(String.valueOf(given[4][1]));
    	btn=(Button) findViewById(R.id.Button21);
    	btn.setText(String.valueOf(given[4][2]));
    	btn=(Button) findViewById(R.id.Button29);
    	btn.setText(String.valueOf(given[4][3]));
    	btn=(Button) findViewById(R.id.Button37);
    	btn.setText(String.valueOf(given[4][4]));
    	btn=(Button) findViewById(R.id.Button45);
    	btn.setText(String.valueOf(given[4][5]));
    	btn=(Button) findViewById(R.id.Button53);
    	btn.setText(String.valueOf(given[4][6]));
    	btn=(Button) findViewById(R.id.Button61);
    	btn.setText(String.valueOf(given[4][7]));
    	btn=(Button) findViewById(R.id.Button69);
    	btn.setText(String.valueOf(given[4][8]));
    	btn=(Button) findViewById(R.id.Button03);
    	btn.setText(String.valueOf(given[5][0]));
    	btn=(Button) findViewById(R.id.Button16);
    	btn.setText(String.valueOf(given[5][1]));
    	btn=(Button) findViewById(R.id.Button17);
    	btn.setText(String.valueOf(given[5][2]));
    	btn=(Button) findViewById(R.id.Button25);
    	btn.setText(String.valueOf(given[5][3]));
    	btn=(Button) findViewById(R.id.Button33);
    	btn.setText(String.valueOf(given[5][4]));
    	btn=(Button) findViewById(R.id.Button41);
    	btn.setText(String.valueOf(given[5][5]));
    	btn=(Button) findViewById(R.id.Button49);
    	btn.setText(String.valueOf(given[5][6]));
    	btn=(Button) findViewById(R.id.Button57);
    	btn.setText(String.valueOf(given[5][7]));
    	btn=(Button) findViewById(R.id.Button65);
    	btn.setText(String.valueOf(given[5][8]));
    	btn=(Button) findViewById(R.id.Button07);
    	btn.setText(String.valueOf(given[6][0]));
    	btn=(Button) findViewById(R.id.Button12);
    	btn.setText(String.valueOf(given[6][1]));
    	btn=(Button) findViewById(R.id.Button20);
    	btn.setText(String.valueOf(given[6][2]));
    	btn=(Button) findViewById(R.id.Button28);
    	btn.setText(String.valueOf(given[6][3]));
    	btn=(Button) findViewById(R.id.Button36);
    	btn.setText(String.valueOf(given[6][4]));
    	btn=(Button) findViewById(R.id.Button44);
    	btn.setText(String.valueOf(given[6][5]));
    	btn=(Button) findViewById(R.id.Button52);
    	btn.setText(String.valueOf(given[6][6]));
    	btn=(Button) findViewById(R.id.Button60);
    	btn.setText(String.valueOf(given[6][7]));
    	btn=(Button) findViewById(R.id.Button68);
    	btn.setText(String.valueOf(given[6][8]));
    	btn=(Button) findViewById(R.id.Button05);
    	btn.setText(String.valueOf(given[7][0]));
    	btn=(Button) findViewById(R.id.Button11);
    	btn.setText(String.valueOf(given[7][1]));
    	btn=(Button) findViewById(R.id.Button23);
    	btn.setText(String.valueOf(given[7][2]));
    	btn=(Button) findViewById(R.id.Button31);
    	btn.setText(String.valueOf(given[7][3]));
    	btn=(Button) findViewById(R.id.Button39);
    	btn.setText(String.valueOf(given[7][4]));
    	btn=(Button) findViewById(R.id.Button47);
    	btn.setText(String.valueOf(given[7][5]));
    	btn=(Button) findViewById(R.id.Button55);
    	btn.setText(String.valueOf(given[7][6]));
    	btn=(Button) findViewById(R.id.Button63);
    	btn.setText(String.valueOf(given[7][7]));
    	btn=(Button) findViewById(R.id.Button71);
    	btn.setText(String.valueOf(given[7][8]));
    	btn=(Button) findViewById(R.id.Button06);
    	btn.setText(String.valueOf(given[8][0]));
    	btn=(Button) findViewById(R.id.Button10);
    	btn.setText(String.valueOf(given[8][1]));
    	btn=(Button) findViewById(R.id.Button24);
    	btn.setText(String.valueOf(given[8][2]));
    	btn=(Button) findViewById(R.id.Button32);
    	btn.setText(String.valueOf(given[8][3]));
    	btn=(Button) findViewById(R.id.Button40);
    	btn.setText(String.valueOf(given[8][4]));
    	btn=(Button) findViewById(R.id.Button48);
    	btn.setText(String.valueOf(given[8][5]));
    	btn=(Button) findViewById(R.id.Button56);
    	btn.setText(String.valueOf(given[8][6]));
    	btn=(Button) findViewById(R.id.Button64);
    	btn.setText(String.valueOf(given[8][7]));
    	btn=(Button) findViewById(R.id.Button72);
    	btn.setText(String.valueOf(given[8][8]));
    	
    	
    }
    
    
    
    	
  public void solve()
  {
 
	 
	  
	  for(i=0;i<9;i++) 
		{
		for(j=0;j<9;j++)
		{ 
		
		
			if(given[i][j]==0)
		{ put=check(i,j,given[i][j]+1);
		if(put!=10){given[i][j]=put;}
		else{I=i;
		J=j-1;if(J==-1){J=8;I-=1;};while(track[I][J]!=0){J-=1;if(J==-1){J=8;I-=1;}};
			while((J!=j)||(I!=i))
			{put=check(I,J,given[I][J]+1);
			if(put==10){given[I][J]=0;J-=1;if(J==-1){J=8;I-=1;};while(track[I][J]!=0){J-=1;if(J==-1){J=8;I-=1;}}}
			else{given[I][J]=put;J+=1;if(J==9){J=0;I+=1;};while(track[I][J]!=0){J+=1;if(J==9){J=0;I+=1;}}
			}
			
			}
			if((I==i)&&(J==j))
			{j-=1;}
		}
		}
			
			
		}
		
		}
	  Log.e("solve","Solved  /"+String.valueOf(given[1][1]));
	  Runnable runnable = new Runnable() {
	      @Override
	      public void run() {                
	          {                    
	              display();
	              //mhandler.postDelayed(this, 1000);
	          }
	      }
	  };        
	  mhandler.post(runnable);
	  
	 
  }

  public void callAsynchronousTask() {
	    final Handler handler = new Handler();
	    Timer timer = new Timer();
	    TimerTask doAsynchronousTask = new TimerTask() {       
	        @Override
	        public void run() {
	            handler.post(new Runnable() {
	                public void run() {       
	                    try { display();
	                        
	                    } catch (Exception e) {
	                        // TODO Auto-generated catch block
	                    }
	                }
	            });
	        }
	    };
	    timer.schedule(doAsynchronousTask, 0, 5); //execute in every 50000 ms
	}

  public int check(int a,int b,int c)
  {	
  	
  	if((a<3)&&(b<3))
  {
  	while((c<10))
  {c1=0;
  	for(int m=0;m<3;m++)
  	{for(int n=0;n<3;n++)
  	{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  	{if((given[a][o]==c)||(given[o][b]==c))c1++;}
  	if(c1==0){break;}
  	c++;


  	}return c;}

  	if((a<3)&&(b>2)&&(b<6))
  {while((c<10))
  {c1=0;for(int m=0;m<3;m++)
  	{for(int n=3;n<6;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}
  	if((a<3)&&(b>5))
  {while((c<10))
  {c1=0;for(int m=0;m<3;m++)
  	{for(int n=6;n<9;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}
  	if((a>2)&&(b<3)&&(a<6))
  {while((c<10))
  {c1=0;for(int m=3;m<6;m++)
  	{for(int n=0;n<3;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}
  	if((a>2)&&(b>2)&&(a<6)&&(b<6))
  {while((c<10))
  {c1=0;for(int m=3;m<6;m++)
  	{for(int n=3;n<6;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}

  	if((a>2)&&(a<6)&&(b>5))
  {while((c<10))
  {c1=0;for(int m=3;m<6;m++)
  	{for(int n=6;n<9;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}

  if((a>5)&&(b<3))
  {while((q==0)&&(c<10))
  {c1=0;for(int m=6;m<9;m++)
  	{for(int n=0;n<3;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  }return c;}

  	if((a>5)&&(b<6)&&(b>2))
  {while((c<10))
  {c1=0;for(int m=6;m<9;m++)
  	{for(int n=3;n<6;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}

  	if((a>5)&&(b>5))
  {while((c<10))
  {c1=0;for(int m=6;m<9;m++)
  	{for(int n=6;n<9;n++)
  		{
  	if(given[m][n]==c)c1++;}
  	}
  	for(int o=0;o<9;o++)
  		if((given[a][o]==c)||(given[o][b]==c))c1++;
  	if(c1==0){break;}
  	c++;

  	}return c;}
	return c;
	
  	}







  	
  
    
}