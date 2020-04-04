package com.effective.section3.subject;

import java.util.Random;
import java.util.TreeSet;

public class TesterMonitor {
/*	一个显示器生产工厂，其专门制造各种液晶显示器
	   
	   显示器类属性：
	     1. 屏幕尺寸  (14#,15#,17#,19#,23#)
	     2. 屏幕分辨率 (1024*768,1280*1024,1366*768,1920*1080)
	     3. 是否合格   (合格，不合格)

	   随机生成20台液晶电视(但不雷同）， 
	   
	   输出要求：

	   1. 按合格率排(合格的排前头，不合格排后头)
	   2. 屏幕尺寸 (从大到小)
	   3. 屏幕分辨率 (从低分辨率到高分辨率)

	   SAMPLE:
	   屏幕尺寸       屏幕分辨率      是否合格
	   15#           1920*1080       YES
	   .............................. 

	   合格率：35.45%


	   解答提示：
	   1.  使用Random类辅助生成显示器对象
	   2.  使用TreeSet进行排序（让每个显示器对象实现Comparable接口）*/
	public static void main(String[] args) {
		
		String[] size = {"14#","15#","17#","19#","23#"};
		String[] resolution = {"1024*768 ","1280*1024","1366*768 ","1920*1080"};
		String[] qualified  = {"YES","NO"};
		
		Monitor[] MntArray = new Monitor[40];//new出来的数组在内存空间中并没有创建40个对象，所以下面还要new对象否则出现空指针异常
		
		/**
		 * 初始化对象数组
		 * 
		 */
		int m = 0;
		for(int i = 0;i<size.length;i++){
			for(int j = 0; j < resolution.length; j++){
				for(int k = 0; k < qualified.length; k++){
					MntArray[m] = new Monitor(size[i],resolution[j],qualified[k]);
					m++;
				}
			}
		}
		
		
		TreeSet<Monitor> MntSet = new TreeSet<Monitor>();
		/**
		 * 将对象数组里的对象随机生成放入TreeSet中
		 */
		Random r= new Random();
		while(MntSet.size() != 20){//如果集合没有二十个元素继续加，TreeSet会判断相同的不加入集合
			int temp = Math.abs(r.nextInt()%40);
			MntSet.add(MntArray[temp]);
		}
		
		System.out.println("屏幕尺寸    屏幕分辨率     是否合格");
		int cnt = 0;
		for(Monitor mnt : MntSet){
			System.out.println(mnt);
			if(mnt.getQualified() == "YES"){
				cnt++;
			}
		}
		System.out.printf("合格率：%.2f%%  合格屏幕：%d" ,((double)cnt/20.0)*100,cnt);
		

	}
}
