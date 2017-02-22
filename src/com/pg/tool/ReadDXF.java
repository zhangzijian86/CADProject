package com.pg.tool;
import java.io.*;
import java.util.ArrayList;
import com.pg.tool.ChangeCharset;;

public class ReadDXF {
	private String FileName;
	//private InputStream bReader=null;
	private BufferedReader bReader;
	private String temp1,temp2,temp3,temp4,temp5,temp6,temp7;
	private ArrayList linelist;
	private ArrayList ellipselist;
	private ArrayList circlelist;
	private ArrayList lwpolylinelist;
	private ArrayList arclist;
	private ArrayList alllist=new ArrayList();
	private int n=0,j=0; 
String readString(BufferedReader datain){
   String temp=null;
	 try {
		temp=new String(datain.readLine());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ChangeCharset test = new ChangeCharset(); 
	try {
		temp=test.toUTF_8(temp);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return temp;
	}

  public ReadDXF(String path) {
		FileName = path;
		File f = new File(FileName);
		if (!f.exists()) {
			System.out.println("File Not Exist");
			System.exit(0);
		}
		FileReader fr = null;
		try {
		  fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bReader = new BufferedReader(fr);
		//System.out.println("Begin read file" + FileName);
		temp1 = readString(bReader);// ��DXF�ļ��ж�ȡһ���ַ���
		while (!temp1.equals("EOF")) {// δ���ļ�������־
			temp1 = readString(bReader);
			if (temp1.equals("ENTITIES")) { // ʵ��ο�ʼ
				temp2 = readString(bReader);
				String lastOne = temp2;
				while (true) {
					temp2 = readString(bReader);					
					if (temp2.equals("CIRCLE")) {// �ж�CIRCLEʵ�忪ʼ
						circlelist=new ArrayList();
						 circlelist.add("1");
						 //System.out.println("==CIRCLE==");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								//System.out.println("=x="+temp4);
								circlelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								//System.out.println("=y="+temp4);
								circlelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								//System.out.println("=z="+temp4);
								circlelist.add(temp4);
							}
							if (temp3.equals(" 40")) {
								temp4 = readString(bReader);
								//System.out.println("=curr.code="+temp3+"=curr.value="+temp4);
								circlelist.add(temp4);
								break;
							}
						}
					alllist.add(circlelist);
					alllist.add("\n");
					}
					// �ж�CIRCLEʵ�����
					if (temp2.equals("ELLIPSE")) {// �ж�ellipseʵ�忪ʼ
						ellipselist=new ArrayList();
					    ellipselist.add("5");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								//System.out.println("=x="+temp4);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								//System.out.println("=y="+temp4);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								//System.out.println("=z="+temp4);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 40")) {
								temp4 = readString(bReader);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 41")) {
								temp4 = readString(bReader);
								ellipselist.add(temp4);
							}
							if (temp3.equals(" 42")) {
								temp4 = readString(bReader);
								ellipselist.add(temp4);
								break;
							}		
						}
					alllist.add(ellipselist);
					alllist.add("\n");
					}
					// �ж�ellipseʵ�����
					if (temp2.equals("ARC")) {// �ж�ARCʵ�忪ʼ
						arclist=new ArrayList();
					    arclist.add("4");
					    //System.out.println("==ARC==");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {                    
								temp4 = readString(bReader);
								//System.out.println("=x="+temp4);
								arclist.add(temp4);
							}
						
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								//System.out.println("=y="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								//System.out.println("=z="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 40")) {// �뾶
								temp4 = readString(bReader);
								//System.out.println("=curr.code="+temp3+"=curr.value="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 50")) {// ������ʼ�Ƕ�
								temp4 = readString(bReader);
								//System.out.println("=curr.code="+temp3+"=curr.value="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 51")) {// ������ֹ�Ƕ�
								temp4 = readString(bReader);
								//System.out.println("=curr.code="+temp3+"=curr.value="+temp4);
								arclist.add(temp4);
								break;
							}
						}
						alllist.add(arclist);
						alllist.add("\n");
					}
					// �ж�ARCʵ�����
					if (temp2.equals("LINE")) {// �ж�LINEʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("3");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
								break;
							}
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�LINEʵ�����
					if (temp2.equals("LWPOLYLINE")) {// �ж�LWPOLYLINEʵ�忪ʼ
						//System.out.println("=ENTITIES===LWPOLYLINE==");
						lwpolylinelist=new ArrayList();
						lwpolylinelist.add("2");
						int flag = 0;
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 90")){
								temp4= readString(bReader);	
								flag = Integer.parseInt(temp4.trim()); 								
								lwpolylinelist.add(temp4);
								//System.out.println("==90=="+temp4);
							}							
							if (temp3.equals(" 70")){
								temp4= readString(bReader);									
								lwpolylinelist.add(temp4);
								//System.out.println("=70=shape="+temp4);
							}
							if (temp3.equals(" 43")){
								temp4= readString(bReader);									
								lwpolylinelist.add(temp4);
								//System.out.println("=43=width="+temp4);
							}
							if (temp3.equals(" 10")) {								
								boolean vertexIsStarted = false;
								boolean vertexIsFinished = false;
								for(int i = 0; i < flag; i++) {
									while(true) {
										if(temp3.trim().equals("0")|| vertexIsFinished || temp3.trim().equals("1001")) {
											break;
										}
										if(temp3.trim().equals("10")){
											if(vertexIsStarted) {
												vertexIsFinished = true;
												continue;
											}
											temp4= readString(bReader);											
											lwpolylinelist.add(temp4);											
											vertexIsStarted = true;
											//System.out.println("=x="+temp4);
										}else if(temp3.trim().equals("20")){
											temp4= readString(bReader);											
											lwpolylinelist.add(temp4);	
											//System.out.println("=y="+temp4);
										}else if(temp3.trim().equals("30")){
											temp4= readString(bReader);											
											lwpolylinelist.add(temp4);
											//System.out.println("=z="+temp4);
										}else if(temp3.trim().equals("40")){
											temp4= "startWidth="+readString(bReader);	
											lwpolylinelist.add(temp4);
											//System.out.println("startWidth="+temp4);
										}else if(temp3.trim().equals("41")){
											temp4= "endWidth="+readString(bReader);	
											lwpolylinelist.add(temp4);
											//System.out.println("endWidth="+temp4);
										}else if(temp3.trim().equals("42")){
											temp4= readString(bReader);
											if(!temp4.trim().equals("0")) {												
												temp4= "bulge="+temp4;	
												lwpolylinelist.add(temp4);
												//System.out.println("bulge="+temp4);
											}
										}									
										temp3 = readString(bReader);
									}
									vertexIsStarted = false;
									vertexIsFinished = false;
								}
								flag = 0;
							}							
							if (temp3.equals(" 38")){
								temp4= "elevation="+readString(bReader);							
								lwpolylinelist.add(temp4);
								//System.out.println("elevation="+temp4);
							}
							
							if (temp3.equals(" 39")){
								temp4= "depth="+readString(bReader);								
								lwpolylinelist.add(temp4);
								//System.out.println("depth="+temp4);
							}						
							if(temp3.equals("  0"))break;	
						}	
						alllist.add(lwpolylinelist);
						alllist.add("\n");
					}
					// �ж�LWPOLYLINEʵ�����
					if (temp2.equals("TEXT")) {// �ж�TEXTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("6");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals("  1")) {//text
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 40")) {// Text height
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 41")) {//Scale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 72")) {//Horizontal alignment
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 73")) {//Vertical alignment
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�TEXTʵ�����
					if (temp2.equals("DIMENSION")) {// �ж�DIMENSIONʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("7");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals("  1")) {//Text entered by user explicitly
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals("  2")) {//Referenced block name
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 42")) {//Actual measurement  //entity.actualMeasurement 
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 50")) {//Angle of rotated, horizontal, or vertical dimensions   //entity.angle
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 71")) {//5 = Middle center   //entity.attachmentPoint
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�DIMENSIONʵ�����
					// �ж�SOLIDʵ�����
					if (temp2.equals("SOLID")&&lastOne.equals("  0")) {// �ж�SOLIDʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("8");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 12")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 22")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 32")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 13")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 23")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 33")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�DIMENSIONʵ�����		
					if (temp2.equals("POINT")) {// �ж�POINTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("9");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}	
							if (temp3.equals(" 39")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�POINTʵ�����	
					if (temp2.equals("MTEXT")) {// �ж�MTEXTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("10");
					    String str = "";
						while (true) {							
							temp3 = readString(bReader);
							if (temp3.equals("  1")) {
								str = str + readString(bReader);	
								//System.out.println("=11="+str);
							}
							if (temp3.equals("  3")) {
								//System.out.println("=22="+str);
								str = str + readString(bReader);
								//System.out.println("=33="+str);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}	
							if (temp3.equals(" 40")) {//height
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 41")) {//width
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 71")) {//attachmentPoint
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 72")) {//drawingDirection
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
						linelist.add(str);
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�MTEXTʵ�����	
					if (temp2.equals("INSERT")) {// �ж�INSERTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("111");
					    String str = "";
						while (true) {							
							temp3 = readString(bReader);
							if (temp3.equals("  2")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}	
							if (temp3.equals(" 41")) {//xScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 42")) {//yScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 43")) {//zScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 44")) {//zScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 45")) {//zScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 50")) {//rotation
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 70")) {//columnCount
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 71")) {//rowCount
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 72")) {//drawingDirection
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
						linelist.add(str);
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�INSERTʵ�����	
					if (temp2.equals("ENDSEC"))
					{
						break;
					}
				} // ʵ��ν�����ѭ������  			
			}	// ʵ��ν���
			if(temp1.equals("BLOCKS")){
				temp2 = readString(bReader);
				String lastOne = temp2;
				while (true) {
					temp2 = readString(bReader);
					if (temp2.equals("CIRCLE")) {// �ж�CIRCLEʵ�忪ʼ
						circlelist=new ArrayList();
						 circlelist.add("1");
						 //System.out.println("==CIRCLE==");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								//System.out.println("=x="+temp4);
								circlelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								//System.out.println("=y="+temp4);
								circlelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								//System.out.println("=z="+temp4);
								circlelist.add(temp4);
							}
							if (temp3.equals(" 40")) {
								temp4 = readString(bReader);
								//System.out.println("=curr.code="+temp3+"=curr.value="+temp4);
								circlelist.add(temp4);
								break;
							}
						}
					alllist.add(circlelist);
					alllist.add("\n");
					}
					// �ж�CIRCLEʵ�����
					if (temp2.equals("ARC")) {// �ж�ARCʵ�忪ʼ
						arclist=new ArrayList();
					    arclist.add("4");
					    //System.out.println("=BLOCKS=ARC==");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {                    
								temp4 = readString(bReader);
								//System.out.println("=BLOCKS=x="+temp4);
								arclist.add(temp4);
							}
						
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								//System.out.println("=BLOCKS=y="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								//System.out.println("=BLOCKS=z="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 40")) {// �뾶
								temp4 = readString(bReader);
								//System.out.println("=BLOCKS=curr.code="+temp3+"=curr.value="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 50")) {// ������ʼ�Ƕ�
								temp4 = readString(bReader);
								//System.out.println("=BLOCKS=curr.code="+temp3+"=curr.value="+temp4);
								arclist.add(temp4);
							}
							if (temp3.equals(" 51")) {// ������ֹ�Ƕ�
								temp4 = readString(bReader);
								//System.out.println("=BLOCKS=curr.code="+temp3+"=curr.value="+temp4);
								arclist.add(temp4);
								break;
							}
						}
						alllist.add(arclist);
						alllist.add("\n");
					}
					// �ж�ARCʵ�����
					if (temp2.equals("LWPOLYLINE")) {// �ж�LWPOLYLINEʵ�忪ʼ
						//System.out.println("=BLOCKS===LWPOLYLINE==");
						lwpolylinelist=new ArrayList();
						lwpolylinelist.add("2");
						int flag = 0;
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 90")){
								temp4= readString(bReader);	
								flag = Integer.parseInt(temp4.trim()); 								
								lwpolylinelist.add(temp4);
								//System.out.println("==90=="+temp4);
							}							
							if (temp3.equals(" 70")){
								temp4= readString(bReader);									
								lwpolylinelist.add(temp4);
								//System.out.println("=70=shape="+temp4);
							}
							if (temp3.equals(" 43")){
								temp4= readString(bReader);									
								lwpolylinelist.add(temp4);
								//System.out.println("=43=width="+temp4);
							}
							if (temp3.equals(" 10")) {								
								boolean vertexIsStarted = false;
								boolean vertexIsFinished = false;
								for(int i = 0; i < flag; i++) {
									while(true) {
										if(temp3.trim().equals("0")|| vertexIsFinished || temp3.trim().equals("1001")) {
											break;
										}
										if(temp3.trim().equals("10")){
											if(vertexIsStarted) {
												vertexIsFinished = true;
												continue;
											}
											temp4= readString(bReader);											
											lwpolylinelist.add(temp4);	
											vertexIsStarted = true;
											//System.out.println("=x="+temp4);
										}else if(temp3.trim().equals("20")){
											temp4= readString(bReader);											
											lwpolylinelist.add(temp4);
											//System.out.println("=y="+temp4);
										}else if(temp3.trim().equals("30")){
											temp4= readString(bReader);											
											lwpolylinelist.add(temp4);
											//System.out.println("=z="+temp4);
										}else if(temp3.trim().equals("40")){
											temp4= "startWidth="+readString(bReader);	
											lwpolylinelist.add(temp4);
											//System.out.println("startWidth="+temp4);
										}else if(temp3.trim().equals("41")){
											temp4= "endWidth="+readString(bReader);	
											lwpolylinelist.add(temp4);
											//System.out.println("endWidth="+temp4);
										}else if(temp3.trim().equals("42")){
											temp4= readString(bReader);
											if(!temp4.trim().equals("0")) {												
												temp4= "bulge="+temp4;	
												lwpolylinelist.add(temp4);
												//System.out.println("bulge="+temp4);
											}
										}									
										temp3 = readString(bReader);
									}
									vertexIsStarted = false;
									vertexIsFinished = false;
								}
								flag = 0;
							}							
							if (temp3.equals(" 38")){
								temp4= "elevation="+readString(bReader);							
								lwpolylinelist.add(temp4);
								//System.out.println("elevation="+temp4);
							}
							
							if (temp3.equals(" 39")){
								temp4= "depth="+readString(bReader);									
								lwpolylinelist.add(temp4);
								//System.out.println("depth="+temp4);
							}						
							if(temp3.equals("  0"))break;	
						}	
						alllist.add(lwpolylinelist);
						alllist.add("\n");
					}
					// �ж�LWPOLYLINEʵ�����
					if (temp2.equals("LINE")) {// �ж�LINEʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("3");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
								break;
							}
						}
					alllist.add(linelist);
					alllist.add("\n");
					}					
					// �ж�LINEʵ�����
					if (temp2.equals("TEXT")) {// �ж�TEXTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("6");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals("  1")) {//text
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 40")) {// Text height
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 41")) {//Scale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 72")) {//Horizontal alignment
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 73")) {//Vertical alignment
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�TEXTʵ�����
					if (temp2.equals("DIMENSION")) {// �ж�DIMENSIONʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("7");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals("  1")) {//Text entered by user explicitly
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals("  2")) {//Referenced block name
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 42")) {//Actual measurement  //entity.actualMeasurement 
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 50")) {//Angle of rotated, horizontal, or vertical dimensions   //entity.angle
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 71")) {//5 = Middle center   //entity.attachmentPoint
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�SOLIDʵ�����
					if (temp2.equals("SOLID")&&lastOne.equals("  0")) {// �ж�SOLIDʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("8");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 11")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 21")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 31")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 12")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 22")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 32")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 13")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 23")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 33")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�DIMENSIONʵ�����		
					if (temp2.equals("POINT")) {// �ж�POINTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("9");
						while (true) {
							temp3 = readString(bReader);
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}	
							if (temp3.equals(" 39")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�POINTʵ�����	
					if (temp2.equals("MTEXT")) {// �ж�MTEXTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("10");
					    String str = "";
						while (true) {							
							temp3 = readString(bReader);
							if (temp3.equals("  1")) {
								str = str + readString(bReader);	
								//System.out.println("=11="+str);
							}
							if (temp3.equals("  3")) {
								//System.out.println("=22="+str);
								str = str + readString(bReader);
								//System.out.println("=33="+str);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}	
							if (temp3.equals(" 40")) {//height
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 41")) {//width
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 71")) {//attachmentPoint
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 72")) {//drawingDirection
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
						linelist.add(str);
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�MTEXTʵ�����	
					if (temp2.equals("INSERT")) {// �ж�INSERTʵ�忪ʼ
						linelist=new ArrayList();
					    linelist.add("11");
					    String str = "";
						while (true) {							
							temp3 = readString(bReader);
							if (temp3.equals("  2")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 10")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 20")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 30")) {
								temp4 = readString(bReader);
								linelist.add(temp4);
							}	
							if (temp3.equals(" 41")) {//xScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 42")) {//yScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 43")) {//zScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 44")) {//zScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 45")) {//zScale
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 50")) {//rotation
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 70")) {//columnCount
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 71")) {//rowCount
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if (temp3.equals(" 72")) {//drawingDirection
								temp4 = readString(bReader);
								linelist.add(temp4);
							}
							if(temp3.equals("  0"))break;	
						}
						linelist.add(str);
					alllist.add(linelist);
					alllist.add("\n");
					}
					// �ж�INSERTʵ�����	
					if (temp2.equals("ENDSEC"))
					{
						//System.out.println("===rrr==="+temp3);
						//System.out.println("===rrr==="+temp4);
						break;
					}
					lastOne = temp2;
				}
			}
		} // �ļ�ѭ��������
	} // jiexi��������
  
	public ArrayList getResult(){
		return alllist;
	}
//    void displayall(){
//	  for(int i=0;i<alllist.size();i++)
//	  {
//		 if(alllist.get(i).toString().startsWith("[111,")){
//			 System.out.println((alllist.get(i)));
//		 }
//		 //System.out.print((alllist.get(i)));
//	  } 
//    }
//	public static void main(String[] args) {
//	    ReadDXF object1=new ReadDXF("E:\\webgl\\works\\api-cw750-details.dxf");
//	    object1.displayall();
//	}
}

