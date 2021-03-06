package com.anyin.ailibuli.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *  以文件形式保存对像的一个具类
 * @author Administrator
 *
 */
public class ObjectSaveUtil {

   /**
    * 保存对象
    *
    * @param ser
    * @param file
    * @throws IOException
    */
   public static boolean saveObject(Context context, Serializable ser,
       String file) {
       LogCp.i(LogCp.CP, ObjectSaveUtil.class + "保存数据 的file :" + file);
   FileOutputStream fos = null;
   ObjectOutputStream oos = null;
   try {
       fos = context.openFileOutput(file, Context.MODE_PRIVATE);
       oos = new ObjectOutputStream(fos);
       oos.writeObject(ser);
       oos.flush();
       return true;
   } catch (Exception e) {
       e.printStackTrace();
       return false;
   } finally {
       try {
       oos.close();
       } catch (Exception e) {
       }
       try {
       fos.close();
       } catch (Exception e) {
       }
   }
   }

   /**
    * 读取对象
    *
    * @param file
    * @return
    * @throws IOException
    */
   public static Serializable readObject(Context context, String file) {
   if (!isExistDataCache(context, file))
       return null;
   FileInputStream fis = null;
   ObjectInputStream ois = null;
   try {
       fis = context.openFileInput(file);
       ois = new ObjectInputStream(fis);
       return (Serializable) ois.readObject();
   } catch (FileNotFoundException e) {
   } catch (Exception e) {
       e.printStackTrace();
       // 反序列化失败 - 删除缓存文件
       if (e instanceof InvalidClassException) {
       File data = context.getFileStreamPath(file);
       data.delete();
       }
   } finally {
       try {
       ois.close();
       } catch (Exception e) {
       }
       try {
       fis.close();
       } catch (Exception e) {
       }
   }
   return null;
   }


   /**
    * 判断数据 是否存在
    *
    * @param file
    * @return
    */
   public static boolean isExistDataCache(Context context, String file) {
   if (context == null)
       return false;
   boolean exist = false;
   File data = context.getFileStreamPath(file);
   if (data.exists())
       exist = true;
   return exist;
   }





}
