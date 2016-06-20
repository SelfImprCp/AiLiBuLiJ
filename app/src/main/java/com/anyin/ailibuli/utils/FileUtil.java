package com.anyin.ailibuli.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件工具类
 * 
 * @author zs 2015-8-8
 */
public class FileUtil {

	private Context context;
	
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/MoFox_User/";
	public static String JPG = ".jpg";

	public FileUtil(Context contex) {
		this.context = contex;
	}

	/**
	 * 以private方式保存文件到手机自带存储空间 只能被本应用访问 写入的内容会覆盖原文件的内容
	 * 
	 * @param name
	 *            文件名
	 * @param content
	 *            文件内容
	 */
	public void savePrivate(String name, String content) throws Exception {
		saveCommon(name, content, Context.MODE_PRIVATE);
	}

	/**
	 * 以append方式保存文件到手机自带存储空间 只能被本应用访问 会检查文件是否存在，存在就往文件追加内容，否则就创建新文件
	 * 
	 * @param name
	 *            文件名
	 * @param content
	 *            文件内容
	 */
	public void saveAppend(String name, String content) throws Exception {
		saveCommon(name, content, Context.MODE_APPEND);
	}

	/**
	 * 以 读写(WR) 方式保存文件到手机自带存储空间
	 * 
	 * @param name
	 *            文件名
	 * @param content
	 *            文件内容
	 */
	public void saveWR(String name, String content) throws Exception {
		saveCommon(name, content, Context.MODE_WORLD_WRITEABLE
				+ Context.MODE_WORLD_READABLE);
	}

	/**
	 * 公共的保存方法 文件会写到 data\data\包名\files\文件名
	 * 
	 * @param name
	 *            文件名
	 * @param content
	 *            文件内容
	 * @param mode
	 *            文件操作模式
	 */
	private void saveCommon(String name, String content, int mode)
			throws Exception {
		// 得到文件输出流 (输入还是输出, 是相对于我们应用而言)
		// 文件名不能包含任何路径和分隔符 (/ \)
		FileOutputStream out = context.openFileOutput(name, mode);
		// 将文件内容写出去
		out.write(content.getBytes());
		out.close(); // 关闭流
	}

 

	/**
	 * 保存文件 到SD卡
	 * 
	 * @param bm
	 * @param fileName
	 * @param path
	 *            :/mnt/sdcard/kelong/notice/head/
	 * @throws IOException
	 */
	public static File saveFileSdCard(Bitmap bm, String path, String fileName)
			throws IOException {
		File dirFile = new File(path);
		if (!dirFile.exists()) {// 文件夹不存在
			dirFile.mkdirs();
			File nullFile = new File(path + ".nomedia"); // 避免在系统相册看到
			BufferedOutputStream bos1 = new BufferedOutputStream(
					new FileOutputStream(nullFile));
			bos1.flush();
			bos1.close();
		}
		try {// 先删除
			delelteFile(path, fileName + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		File myCaptureFile = new File(path + fileName + ".png");
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.PNG, 80, bos); // 背景透明
		// bm.compress(Bitmap.CompressFormat.JPEG, 80, bos); //背景黑色
		bos.flush();
		bos.close();
		return myCaptureFile;
	}

	/**
	 * 同时保存大小头像 到SD卡
	 */
	public static boolean saveTwoIconToSD(Bitmap bigBitmap, Bitmap smallBitmap,
			String path, String account) throws IOException {
		File dirFile = new File(path);
		if (!dirFile.exists()) {// 文件夹不存在
			dirFile.mkdirs();
			File nullFile = new File(path + ".nomedia"); // 避免在系统相册看到
			BufferedOutputStream bos1 = new BufferedOutputStream(
					new FileOutputStream(nullFile));
			bos1.flush();
			bos1.close();
		}
		try {// 先删除
			delelteFile(path, account + "-big.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		File bigFile = new File(path + account + "-big.png");
		BufferedOutputStream bigBos = new BufferedOutputStream(
				new FileOutputStream(bigFile));
		bigBitmap.compress(Bitmap.CompressFormat.PNG, 80, bigBos); // 背景透明
		bigBos.flush();
		bigBos.close();
		try {// 先删除
			delelteFile(path, account + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		File smallFile = new File(path + account + ".png");
		BufferedOutputStream smallBos = new BufferedOutputStream(
				new FileOutputStream(smallFile));
		smallBitmap.compress(Bitmap.CompressFormat.PNG, 80, smallBos); // 背景透明
		smallBos.flush();
		smallBos.close();
		return true;
	}

	/**
	 * 读取文件内容 读取路径: data\data\包名\files\文件名
	 * 
	 * @param name
	 *            文件名
	 * @return 文件内容
	 */
	public String read(String name) throws Exception {
		File file = new File(context.getFilesDir(), name);
		// FileInputStream in = context.openFileInput(name);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = -1; // 存储读取的字节数
		// 输入流会把读取的内容放进byte[] buffer中
		while ((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len); // 把读取到的数据写到内存中去
		}
		byte[] data = baos.toByteArray(); // 拿到内存中的数据
		baos.close();
		in.close();
		return new String(data);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param name
	 *            文件名
	 * @return 文件内容
	 */
	public static void delelteFile(String path, String name) throws Exception {
		File file = new File(path, name);
		if (file.exists()) {
			file.delete();
		}
	}


	public static void saveBitmap(Bitmap bm, String picName) {
		Log.e("", "保存图片");
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.e("", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName) {
		File file = new File(SDPATH + fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	
	 /**
		 * 写文本文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
		 * 
		 * @param context
		 * @param msg
		 */
		public static void write(Context context, String fileName, String content) {
			if (content == null)
				content = "";

			try {
				FileOutputStream fos = context.openFileOutput(fileName,
						Context.MODE_PRIVATE);
				fos.write(content.getBytes());

				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 读取文本文件
		 * 
		 * @param context
		 * @param fileName
		 * @return
		 */
		public static String read(Context context, String fileName) {
			try {
				FileInputStream in = context.openFileInput(fileName);
				return readInStream(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		public static String readInStream(InputStream inStream) {
			try {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[512];
				int length = -1;
				while ((length = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, length);
				}

				outStream.close();
				inStream.close();
				return outStream.toString();
			} catch (IOException e) {
				Log.i("FileTest", e.getMessage());
			}
			return null;
		}

		public static File createFile(String folderPath, String fileName) {
			File destDir = new File(folderPath);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			return new File(folderPath, fileName + fileName);
		}

		/**
		 * 向手机写图片
		 * 
		 * @param buffer
		 * @param folder
		 * @param fileName
		 * @return
		 */
		public static boolean writeFile(byte[] buffer, String folder,
				String fileName) {
			boolean writeSucc = false;

			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED);

			String folderPath = "";
			if (sdCardExist) {
				folderPath = Environment.getExternalStorageDirectory()
						+ File.separator + folder + File.separator;
			} else {
				writeSucc = false;
			}

			File fileDir = new File(folderPath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			File file = new File(folderPath + fileName);
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(file);
				out.write(buffer);
				writeSucc = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return writeSucc;
		}

		/**
		 * 根据文件绝对路径获取文件名
		 * 
		 * @param filePath
		 * @return
		 */
		public static String getFileName(String filePath) {
			if (StringUtils.isEmpty(filePath))
				return "";
			return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
		}

		/**
		 * 根据文件的绝对路径获取文件名但不包含扩展名
		 * 
		 * @param filePath
		 * @return
		 */
		public static String getFileNameNoFormat(String filePath) {
			if (StringUtils.isEmpty(filePath)) {
				return "";
			}
			int point = filePath.lastIndexOf('.');
			return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
					point);
		}

		/**
		 * 获取文件扩展名
		 * 
		 * @param fileName
		 * @return
		 */
		public static String getFileFormat(String fileName) {
			if (StringUtils.isEmpty(fileName))
				return "";

			int point = fileName.lastIndexOf('.');
			return fileName.substring(point + 1);
		}

		/**
		 * 获取文件大小
		 * 
		 * @param filePath
		 * @return
		 */
		public static long getFileSize(String filePath) {
			long size = 0;

			File file = new File(filePath);
			if (file != null && file.exists()) {
				size = file.length();
			}
			return size;
		}

		/**
		 * 获取文件大小
		 * 
		 * @param size
		 *            字节
		 * @return
		 */
		public static String getFileSize(long size) {
			if (size <= 0)
				return "0";
			java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
			float temp = (float) size / 1024;
			if (temp >= 1024) {
				return df.format(temp / 1024) + "M";
			} else {
				return df.format(temp) + "K";
			}
		}

		/**
		 * 转换文件大小
		 * 
		 * @param fileS
		 * @return B/KB/MB/GB
		 */
		public static String formatFileSize(long fileS) {
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			String fileSizeString = "";
			if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df.format((double) fileS / 1048576) + "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1073741824) + "G";
			}
			return fileSizeString;
		}

		/**
		 * 获取目录文件大小
		 * 
		 * @param dir
		 * @return
		 */
		public static long getDirSize(File dir) {
			if (dir == null) {
				return 0;
			}
			if (!dir.isDirectory()) {
				return 0;
			}
			long dirSize = 0;
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					dirSize += file.length();
				} else if (file.isDirectory()) {
					dirSize += file.length();
					dirSize += getDirSize(file); // 递归调用继续统计
				}
			}
			return dirSize;
		}

		/**
		 * 获取目录文件个数
		 * 
		 * @param emojiFragment
		 * @return
		 */
		public long getFileList(File dir) {
			long count = 0;
			File[] files = dir.listFiles();
			count = files.length;
			for (File file : files) {
				if (file.isDirectory()) {
					count = count + getFileList(file);// 递归
					count--;
				}
			}
			return count;
		}

		public static byte[] toBytes(InputStream in) throws IOException {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				out.write(ch);
			}
			byte buffer[] = out.toByteArray();
			out.close();
			return buffer;
		}

		/**
		 * 检查文件是否存在
		 * 
		 * @param name
		 * @return
		 */
		public static boolean checkFileExists(String name) {
			boolean status;
			if (!name.equals("")) {
				File path = Environment.getExternalStorageDirectory();
				File newPath = new File(path.toString() + name);
				status = newPath.exists();
			} else {
				status = false;
			}
			return status;
		}

		/**
		 * 检查路径是否存在
		 * 
		 * @param path
		 * @return
		 */
		public static boolean checkFilePathExists(String path) {
			return new File(path).exists();
		}

		/**
		 * 计算SD卡的剩余空间
		 * 
		 * @return 返回-1，说明没有安装sd卡
		 */
		public static long getFreeDiskSpace() {
			String status = Environment.getExternalStorageState();
			long freeSpace = 0;
			if (status.equals(Environment.MEDIA_MOUNTED)) {
				try {
					File path = Environment.getExternalStorageDirectory();
					StatFs stat = new StatFs(path.getPath());
					long blockSize = stat.getBlockSize();
					long availableBlocks = stat.getAvailableBlocks();
					freeSpace = availableBlocks * blockSize / 1024;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return -1;
			}
			return (freeSpace);
		}

		/**
		 * 新建目录
		 * 
		 * @param directoryName
		 * @return
		 */
		public static boolean createDirectory(String directoryName) {
			boolean status;
			if (!directoryName.equals("")) {
				File path = Environment.getExternalStorageDirectory();
				File newPath = new File(path.toString() + directoryName);
				status = newPath.mkdir();
				status = true;
			} else
				status = false;
			return status;
		}

		/**
		 * 检查是否安装SD卡
		 * 
		 * @return
		 */
		public static boolean checkSaveLocationExists() {
			String sDCardStatus = Environment.getExternalStorageState();
			boolean status;
			if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
				status = true;
			} else
				status = false;
			return status;
		}
		
		/**
		 * 检查是否安装外置的SD卡
		 * 
		 * @return
		 */
		public static boolean checkExternalSDExists() {
			
			Map<String, String> evn = System.getenv();
			return evn.containsKey("SECONDARY_STORAGE");
		}

		/**
		 * 删除目录(包括：目录里的所有文件)
		 * 
		 * @param fileName
		 * @return
		 */
		public static boolean deleteDirectory(String fileName) {
			boolean status;
			SecurityManager checker = new SecurityManager();
			
			if (!fileName.equals("")) {

				File path = Environment.getExternalStorageDirectory();
				File newPath = new File(path.toString() + fileName);
				checker.checkDelete(newPath.toString());
				if (newPath.isDirectory()) {
					String[] listfile = newPath.list();
					try {
						for (int i = 0; i < listfile.length; i++) {
							File deletedFile = new File(newPath.toString() + "/"
									+ listfile[i].toString());
							deletedFile.delete();
						}
						newPath.delete();
						Log.i("DirectoryManager deleteDirectory", fileName);
						status = true;
					} catch (Exception e) {
						e.printStackTrace();
						status = false;
					}

				} else
					status = false;
			} else
				status = false;
			return status;
		}

		/**
		 * 删除文件
		 * 
		 * @param fileName
		 * @return
		 */
		public static boolean deleteFile(String fileName) {
			boolean status;
			SecurityManager checker = new SecurityManager();

			if (!fileName.equals("")) {

				File path = Environment.getExternalStorageDirectory();
				File newPath = new File(path.toString() + fileName);
				checker.checkDelete(newPath.toString());
				if (newPath.isFile()) {
					try {
						Log.i("DirectoryManager deleteFile", fileName);
						newPath.delete();
						status = true;
					} catch (SecurityException se) {
						se.printStackTrace();
						status = false;
					}
				} else
					status = false;
			} else
				status = false;
			return status;
		}

		/**
		 * 删除空目录
		 * 
		 * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
		 * 
		 * @return
		 */
		public static int deleteBlankPath(String path) {
			File f = new File(path);
			if (!f.canWrite()) {
				return 1;
			}
			if (f.list() != null && f.list().length > 0) {
				return 2;
			}
			if (f.delete()) {
				return 0;
			}
			return 3;
		}

		/**
		 * 重命名
		 * 
		 * @param oldName
		 * @param newName
		 * @return
		 */
		public static boolean reNamePath(String oldName, String newName) {
			File f = new File(oldName);
			return f.renameTo(new File(newName));
		}

		/**
		 * 删除文件
		 * 
		 * @param filePath
		 */
		public static boolean deleteFileWithPath(String filePath) {
			SecurityManager checker = new SecurityManager();
			File f = new File(filePath);
			checker.checkDelete(filePath);
			if (f.isFile()) {
				Log.i("DirectoryManager deleteFile", filePath);
				f.delete();
				return true;
			}
			return false;
		}
		
		/**
		 * 清空一个文件夹
		 * @param files
		 */
		public static void clearFileWithPath(String filePath) {
			List<File> files = FileUtil.listPathFiles(filePath);
			if (files.isEmpty()) {
				return;
			}
			for (File f : files) {
				if (f.isDirectory()) {
					clearFileWithPath(f.getAbsolutePath());
				} else {
					f.delete();
				}
			}
		}

		/**
		 * 获取SD卡的根目录
		 * 
		 * @return
		 */
		public static String getSDRoot() {
			
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		
		/**
		 * 获取手机外置SD卡的根目录
		 * 
		 * @return
		 */
		public static String getExternalSDRoot() {
			
			Map<String, String> evn = System.getenv();
			
			return evn.get("SECONDARY_STORAGE");
		}

		/**
		 * 列出root目录下所有子目录
		 * 
		 * @param path
		 * @return 绝对路径
		 */
		public static List<String> listPath(String root) {
			List<String> allDir = new ArrayList<String>();
			SecurityManager checker = new SecurityManager();
			File path = new File(root);
			checker.checkRead(root);
			// 过滤掉以.开始的文件夹
			if (path.isDirectory()) {
				for (File f : path.listFiles()) {
					if (f.isDirectory() && !f.getName().startsWith(".")) {
						allDir.add(f.getAbsolutePath());
					}
				}
			}
			return allDir;
		}
		
		/**
		 * 获取一个文件夹下的所有文件
		 * @param root
		 * @return
		 */
		public static List<File> listPathFiles(String root) {
			List<File> allDir = new ArrayList<File>();
			SecurityManager checker = new SecurityManager();
			File path = new File(root);
			checker.checkRead(root);
			File[] files = path.listFiles();
			for (File f : files) {
				if (f.isFile())
					allDir.add(f);
				else 
					listPath(f.getAbsolutePath());
			}
			return allDir;
		}

		public enum PathStatus {
			SUCCESS, EXITS, ERROR
		}

		/**
		 * 创建目录
		 * 
		 * @param path
		 */
		public static PathStatus createPath(String newPath) {
			File path = new File(newPath);
			if (path.exists()) {
				return PathStatus.EXITS;
			}
			if (path.mkdir()) {
				return PathStatus.SUCCESS;
			} else {
				return PathStatus.ERROR;
			}
		}

		/**
		 * 截取路径名
		 * 
		 * @return
		 */
		public static String getPathName(String absolutePath) {
			int start = absolutePath.lastIndexOf(File.separator) + 1;
			int end = absolutePath.length();
			return absolutePath.substring(start, end);
		}
		
		/**
		 * 获取应用程序缓存文件夹下的指定目录
		 * @param context
		 * @param dir
		 * @return
		 */
		public static String getAppCache(Context context, String dir) {
			String savePath = context.getCacheDir().getAbsolutePath() + "/" + dir + "/";
			File savedir = new File(savePath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			savedir = null;
			return savePath;
		}
	
	
	
}
