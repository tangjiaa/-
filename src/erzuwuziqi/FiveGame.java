package erzuwuziqi;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
public  class FiveGame extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	//add a line.
	
	// hhhhh
	//������Ϸ����
	//	��Ļ�ֱ�����:
	//		int w = f.getToolkit().getScreenSize().width;//���
	//		int h = f.getToolkit().getScreenSize().height;//�߶�
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	int x,y;  // ������������
	int[][] allChess = new int[15][15];   // ���������������ӣ�0��ʾ���ӣ�1��ʾ���ӣ�2��ʾ����
	boolean isblack = true;   //������ʾ���ӻ��ǰ��ӣ� true��ʾ����   false��ʾ����
	boolean canPlay = true;   // ������ʾ��ǰ��Ϸ�Ƿ����
	String message = "�ڷ�����";
	String blackMessage = "������";
	String whiteMessage = "������";
	
	//�������ף���¼˫��ÿһ�����ӵ�λ��
	int[] chessX = new int[255];
	int[] chessY = new int[255];
	int countX,countY;
	
	//Ĭ��������ʱ������
	int maxTime = 0;   //�������ʱ��
	int blackTime = 0;
	int whileTime = 0;   //����ڰ׷���ʣ���ʱ��
 
	public FiveGame(){
		this.setTitle("������1.0");
		this.setSize(500,500);
		this.setLocation((width - 500) / 2 , (height - 500) / 2 );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);  //���ô��ڲ��ɸı䣬�̶����ڴ�С
		this.setVisible(true);
		
		this.repaint();  //java��repaint()���ػ�component�ķ�����
		this.addMouseListener(this);
 
		
	}
	
	//�����̽���
	public void paint(Graphics g){
		//˫���弼��
		BufferedImage buf = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		Graphics g1 =  buf.createGraphics();  // ��������
		g1.setColor(new Color(0,169,158));
		g1.fill3DRect(43, 60, 375, 375, true);
		
			for (int i = 0; i <= 15; i++) {
				g1.setColor(Color.WHITE);
				g1.drawLine(43, 60+i*25, 375+43, 60+i*25);  //�����̺���
				g1.drawLine(43+i*25, 60, 43+i*25, 375+60);  //����������
			}
			
			g1.setFont(new Font("����",Font.BOLD,20));
			g1.drawString("��Ϸ��Ϣ:"+message,50,50);
			
			g1.drawRect(30, 440, 180, 40);
			g1.drawRect(250, 440, 180, 40);   //���ڷ�ʱ����׷�ʱ���ַ����ı߿�
			g1.setFont(new Font("����",0,12));
			
			    g1.drawString("�ڷ�ʱ��: "+blackMessage,40,465);
		        g1.drawString("�׷�ʱ��: "+whiteMessage,260,465);
		         
		        g1.drawRect(430,66,55,20);
		        g1.drawString("���¿�ʼ",432,80); //���¿�ʼ��ť
		         
		        g1.drawRect(430,106,55,20);
		        g1.drawString("��Ϸ����",432,120); //��Ϸ���ð�ť
		         
		        g1.drawRect(430,146, 55, 20);
		        g1.drawString("��Ϸ˵��", 432, 160); // ��Ϸ˵����ť
		  
		        g1.drawRect(430, 186, 55, 20);
		        g1.drawString("�˳���Ϸ", 432, 200);  // �˳���Ϸ��ť
		  
		        g1.drawRect(430, 246, 55, 20);
		        g1.drawString("����", 442, 260);  // ����
		  
		        g1.drawRect(430, 286, 55, 20);
		        g1.drawString("����", 442, 300);  // ����
		
		        
		     for(int i=0; i<15; i++){
				for (int j = 0; j < 15; j++) {
					//��ʵ�ĺ���
					if(allChess[i][j] == 1){    
						int tempX = i*25+47;
						int tempY = j*25+64;
						g1.setColor(Color.BLACK);
						g1.fillOval(tempX, tempY, 16, 16);
						g1.setColor(Color.BLACK);
						g1.drawOval(tempX, tempY, 16, 16);
					}
					
					//��ʵ�İ���
					if(allChess[i][j] == 2){
						int tempX = i*25+47;
						int tempY = j*25+64;
						g1.setColor(Color.WHITE);
						g1.fillOval(tempX, tempY, 16, 16);
						g1.setColor(Color.WHITE);
						g1.drawOval(tempX, tempY, 16, 16);
					}
				}
			}
		
			
			 g.drawImage(buf, 0, 0,this);	
	}
	
	
	
	public void mousePressed(MouseEvent e){
		if(canPlay){
			x=e.getX();
			y=e.getY();  // ������ȡ�������
			if(x>55 && x<= 405  && y>=72 && y<=420){
				//����������̷�Χ��
				if((x-55)%25>12){
					x=(x-55)/25 + 1;
				}else {
					x = (x-55)/25;
				}
				if((y-72)%25>12){
					y=(y-72)/25 + 1;
				}else {
					y=(y-72)/25;
				}
				
				//����
				if(allChess[x][y] == 0){
					chessX[countX++] = x;
					chessY[countY++] = y;
					if(isblack){
						allChess[x][y] = 1;
						isblack = false;
						message = "�׷�����";
					}else {
						allChess[x][y] = 2;
						isblack = true;
						message = "�ڷ�����";
					}
					this.repaint();
					
					if(this.isWin()){
						if(allChess[x][y] == 1){
							JOptionPane.showMessageDialog(this, "��Ϸ�������ڷ�ʤ��");
						}else {
							JOptionPane.showMessageDialog(this, "��Ϸ�������׷�ʤ��");
						}
						this.canPlay = false;  //��ʾ��Ϸ����
					}
					
					
				}
			}
		}
		
		//���¿�ʼ��Ϸ
		if(e.getX() >=430 && e.getY() <= (428+55)  && e.getY() >= 66
			&&	e.getY() <= (66+20) ){
			int result = JOptionPane.showConfirmDialog(this, "�Ƿ����¿�ʼ��Ϸ��"); 
			if(result == 0){
				restarGame();
			}
		}
		
		
		//��Ϸ˵��
		if(e.getX() >= 430 && e.getY() <= (430+55)  && e.getY() >=146
			&&	e.getY() <= (146+20) ){
			JOptionPane.showMessageDialog(this, "����:����б�����������߻�ʤ!");
		}
		
		//�˳���Ϸ
		if(e.getX() >=430 && e.getX() <= (430+55)  && e.getY() >=186 
				&&  e.getY() <= (186+20)){
			int result = JOptionPane.showConfirmDialog(this, "�Ƿ��˳���Ϸ��");
			if(result == 0){
				System.exit(0);
			}
		}
		
		//����
		if(e.getX() >= 430 && e.getX() <= (430+55) && e.getY() >= 246 
				&&  e.getY() <= (246+20)){
			int result = JOptionPane.showConfirmDialog(this, 
					(isblack == true ? "�׷�����,�ڷ��Ƿ�ͬ�⣿" :"�ڷ����壬�׷��Ƿ�ͬ�⣿"));
			// result = 0Ϊ����
			if(result == 0){
				allChess[chessX[--countX]][chessY[--countY]]=0;
				if(isblack == true ){
					isblack = false;
				}else {
					isblack = true;
				}
				
				this.repaint();  //�ػ�����
			}
		}
		
		 //����
        if(e.getX()>=430 && e.getX()<=(428+55) && e.getY()>=286 
        		&& e.getY()<=(286+20)){
            int result=JOptionPane.showConfirmDialog(this, "�Ƿ�����?");
            if(result==0){
                JOptionPane.showMessageDialog(this,
                	"��Ϸ����,"+(isblack==true ? "�ڷ����䣬�׷���ʤ!" : "�׷����䣬�ڷ���ʤ!"));
            }
        }
 
	}
	
		public void restarGame(){
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					allChess[i][j] = 0;  //������̵�����
				}
				
			}
			
			//���������������ļ�¼
			for (int i = 0; i < 15; i++) {
				chessX[i] = 0;
				chessY[i] = 0;
				
			}
			
			countX =0;
			countY =0;
			message = "�ڷ�����";
			blackMessage = "������";
			whiteMessage = "������";
			blackTime = maxTime;
			whileTime = maxTime;
			isblack = true;
			canPlay = true;
			this.repaint();
			
		}
	
	
 
	/**
	 * �ж���Ӯ����
	 * @return
	 */
	public boolean isWin(){
		boolean flag = false;
		int count = 1;  //�������湲����ͬ��ɫ����������������ʼֵΪ1
		int color = allChess[x][y];  //color = 1 (����) color = 2(����)
		
		//�жϺ����Ƿ���5�������������ص�:����������ͬ����allChess[x][y] ��yֵ����ͬ
		count = this.checkCount(1,0,color);
		if(count >= 5){
			flag = true;
		}else {
			//�ж�����
			count = this.checkCount(0,1,color);
			if(count >= 5){
				flag = true;
			}else {
				 //�ж�����,����
				count = this.checkCount(1,-1,color);
				if(count >= 5){
					flag = true;
				}else {
					//�ж�����,����
					count = this.checkCount(1,1,color);
					if(count >= 5){
						flag =  true;
					}
				}
			}
		}
		
		return flag;
	}
	/**
	 * ��������е��������Ƿ���������
	 * @param xChange
	 * @param yChenge
	 * @param color
	 * @return
	 */
	public int checkCount(int xChange , int yChenge ,int color){
		int count = 1;
		int tempX = xChange;
		int tempy = yChenge;  //�����ʼֵ
		
		//ȫ�ֱ���x,y���Ϊ����������꣬
		//�����巽���Ѿ���x,y�ķ�Χ���0-15(������������,Ѱ����ͬ��ɫ������)
		while(x + xChange >=0 && x+xChange <15  && y+yChenge >=0 && 
				y+yChenge < 15 && color == allChess[x+xChange][y+yChenge]){
	
			count++;
			if(xChange != 0)  xChange++;    
			if(yChenge != 0 ){      
				if(yChenge != 0){
					if(yChenge > 0) {   
						yChenge++;		
					}else {
						yChenge--;		
					}
				}
			}
			
		}
		
		
		xChange = tempX;
		yChenge = tempy;   // �ָ���ʼֵ
		
		
		while(x-xChange >=0 && x-xChange <15 && y-yChenge >=0 &&
				y-yChenge <15 && color == allChess[x-xChange][y-yChenge]){		
			count++;
			if(xChange != 0){
				xChange++;
			}
			if(yChenge != 0){
				if (yChenge > 0) {
					yChenge++;			
				}else {
					yChenge--;			
				}
			}
		}
		
		return count;
	}
	
	
 
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new FiveGame();
	}
 
	
}

