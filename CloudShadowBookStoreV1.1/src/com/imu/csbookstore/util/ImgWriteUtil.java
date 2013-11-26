/**
 * д��ͼƬ
 */
package com.imu.csbookstore.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImgWriteUtil {

	public void writeImg(BufferedInputStream bis, BufferedOutputStream bos) {// д��ͼƬ
		int len = 0;
		try {
			byte[] buffer = new byte[2048];
			while ((len = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			throw new RuntimeException("�����ļ�ʱ����");
		} catch (IOException exception) {
			exception.printStackTrace();
			throw new RuntimeException("��д�ļ�ʱ����");
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
