/**
 * 
 */
package com.effective.section3.demo8_12;

/**
 * @author Administrator
 *
 */
public class Apple implements Comparable<Apple>{
	
	String color;
	Integer weight;
	
	
	public Apple(String color, Integer weight) {
		super();
		this.color = color;
		this.weight=weight;
	}
	@Override
	public String toString(){
	     return "一个重量为"+this.weight+"克的"+this.color+"的苹果";
	}
	@Override
	public boolean equals(Object obj){
		
		System.out.println("apple equals method is invoked!");
		
		if(obj==null) return false;
		if(!(obj instanceof Apple)) return false;
		
		Apple otherApple=(Apple)obj; //更换引用变量，实现标配指向
		
		if(this.color.equals(otherApple.color) && Math.abs(this.weight-otherApple.weight)<=5)
			return true;
	 
	    return false;
		
	}
	
	
	@Override
	public int hashCode() {
		System.out.println("apple hashcode method is invoked!");
		return this.weight.hashCode();
		//hashCode简单比较，equals严格比较
		
		//比如Apple有十个属性，这时为了节约时间hashcode只返回两个比较重要的属性的比较（即返回地址，但其实也不算地址）
		//	然后比较重要的属性比较完之后，就可以知道哪些是没有非典的，加入列表（即不同的对象才可以进入）
		//如果比较相同（即有非典的可能性），这时候还要把这些有可能得非典的人在进行equal比较，不同则加入列表，相同则隔离列表（非典）
		//equal比较则比较更多的属性，比如十个属性全部比较，这样比较精确
	}
	
	/**TreeSet会调用这个方法进行天然排序（即从小到大）*/
	public int compareTo(Apple otherApple) {
		System.out.println("apple compareto method is invoked!"+otherApple);
//		if(this.weight>otherApple.weight) return -1;
//		if(this.weight<otherApple.weight) return 1;
//		
//		return 0;//返回0即对象相等，不存入TreeSet集合，还以为没有不调用hashcode就比较不了相等
	
		if(!this.color.equals(otherApple.color))
			return -1*(this.color.compareTo(otherApple.color));//颜色从大到小
		else if(!this.weight.equals(otherApple.weight))
			return this.weight.compareTo(otherApple.weight);//在颜色相同的情况下，重量从小到大
		
		return 0;
		
	}
	
	
	
}
