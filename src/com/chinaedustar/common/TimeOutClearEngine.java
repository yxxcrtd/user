package com.chinaedustar.common;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chinaedustar.map.TicketInfo;

public final class TimeOutClearEngine {
	private static long TIMEOUT = 0;
	private static threadClass thread = null;
	
	/** 日志 */
	private static final Log log = LogFactory.getLog(TimeOutClearEngine.class);

	@SuppressWarnings("deprecation")
	public static void InitEngine(long iOutSec) {
		TIMEOUT = iOutSec * 1000;

		thread = new threadClass(143);
		thread.start();

		log.info(new java.util.Date().toLocaleString() + "：成功启动过期用户清理线程：" + thread + " 设定的超时时间为：" + iOutSec + " 秒");
	}

	@SuppressWarnings("deprecation")
	public static void CloseEngine() {
		try {
			thread.stop();
			log.info(new java.util.Date().toLocaleString() + "：成功关闭过期用户清理线程：" + thread);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 线程类
	 */
	private static class threadClass extends Thread {
		@SuppressWarnings("unused")
		long minPrime;

		threadClass(long minPrime) {
			this.minPrime = minPrime;
		}

		@SuppressWarnings("static-access")
		public void run() {
			while (true) {
				int i = 0;
				try {
					// 检查项目的队列
					Date dCurDate = new Date();
					String sUserKey = "";

					Iterator<String> oUserinfoList = AppOnLoad.USERMAP.ACCESSSESSIONMAP.keySet().iterator();

					while (oUserinfoList.hasNext()) {
						sUserKey = oUserinfoList.next();
						TicketInfo oTicketInfo = AppOnLoad.USERMAP.ACCESSSESSIONMAP.get(sUserKey);
						if ((dCurDate.getTime() - oTicketInfo.getCreate().getTime()) > TIMEOUT) {
							AppOnLoad.USERMAP.ACCESSSESSIONMAP.remove(sUserKey);
							i++;
						}
					}
				} catch (Exception e) {
					log.info("在清理过期用户时出现错误：" + e.getMessage());
				}
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
