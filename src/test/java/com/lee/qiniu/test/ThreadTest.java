package com.lee.qiniu.test;

/**
 * 
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description 多线程
 * @author lijiaxi 
 * @date 2016-9-28
 */
public class ThreadTest{
	private boolean running = false;
	private Thread thread = null;
	private Queue<String> datas = new LinkedList<String>();
	
	private static final ExecutorService service = Executors.newCachedThreadPool();

	public boolean start() {
		
		
		if(this.thread != null)
			return false;
		
		this.thread = new Thread(new Runnable(){
			
			@Override
			public void run(){
				while(running){
					
					String data = null;
					
					synchronized(datas){
						while(datas.size() == 0){
							try{
								datas.wait();
							}catch(Exception ex){
							}
							// 当调用 stop 方法时，datas.wait 被打断，并且跳出循环等待
							if(running == false)
								break;
						}
						if(datas.size() > 0)
							data = datas.poll();//获取队列的第一个值
					}
					
					if(data != null){
						try{
						}catch(Exception ex){
						}finally{
						}
					}
				}
				
			}
		});
		this.thread.start();
		return true;
	}

	public void stop() {
		this.running = false;
		this.thread = null;
		synchronized(datas){
			datas.notifyAll();
		}
	}

	
	public void saveData(String data){
		synchronized(datas){
			datas.add(data);
			datas.notifyAll();
		}
	}
	

}
