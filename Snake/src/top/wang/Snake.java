package top.wang;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		JFrame frame = new JFrame();
		frame.setBounds(300, 300, 900, 720);//设定窗口坐标与大小
		frame.setResizable(false);		//是否可以改变大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭时退出程序
		

		SnakePanel panel = new SnakePanel();		
		frame.add(panel);					//加载画布
		
		frame.setVisible(true); 			//显示出来
		
		
	}

}
