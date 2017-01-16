package com.hxp.leschool.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hxp on 17-1-16.
 */

public class MyFileHelper {

    // 判断外部存储是否被挂载
    public static boolean isExternalStorageMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    // 获取外部存储根目录
    public static String getExternalStorageRootDir() {
        if (isExternalStorageMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    // 获取外部存储的完整空间大小，返回MB
    public static long getExternalStorageSize() {
        if (isExternalStorageMounted()) {
            StatFs fs = new StatFs(getExternalStorageRootDir());
            long count = fs.getBlockCountLong();
            long size = fs.getBlockSizeLong();
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    // 获取外部存储的剩余空间大小
    public static long getExternalStorageFreeSize() {
        if (isExternalStorageMounted()) {
            StatFs fs = new StatFs(getExternalStorageRootDir());
            long count = fs.getFreeBlocksLong();
            long size = fs.getBlockSizeLong();
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    // 获取S外部存储的可用空间大小
    public static long getExternalStorageAvailableSize() {
        if (isExternalStorageMounted()) {
            StatFs fs = new StatFs(getExternalStorageRootDir());
            long count = fs.getAvailableBlocksLong();
            long size = fs.getBlockSizeLong();
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    // 往外部存储的公有目录下保存文件
    public static boolean saveFileToExternalStoragePublicDir(byte[] data, String type, String fileName) {
        BufferedOutputStream bos = null;
        if (isExternalStorageMounted()) {
            File file = Environment.getExternalStoragePublicDirectory(type);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // 往外部存储的自定义目录下保存文件
    public static boolean saveFileToExternalStorageCustomDir(byte[] data, String dir, String fileName) {
        BufferedOutputStream bos = null;
        if (isExternalStorageMounted()) {
            File file = new File(getExternalStorageRootDir() + File.separator + dir);
            if (!file.exists()) {
                file.mkdirs();// 递归创建自定义目录
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // 往外部存储的私有File目录下保存文件
    public static boolean saveFileToExternalStoragePrivateFileDir(byte[] data, String type, String fileName, Context context) {
        BufferedOutputStream bos = null;
        if (isExternalStorageMounted()) {
            File file = context.getExternalFilesDir(type);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // 往外部存储的私有Cache目录下保存文件
    public static boolean saveFileToExternalStoragePrivateCacheDir(byte[] data, String fileName, Context context) {
        BufferedOutputStream bos = null;
        if (isExternalStorageMounted()) {
            File file = context.getExternalCacheDir();
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // 保存bitmap图片到外部存储的私有Cache目录
    public static boolean saveBitmapToExternalStoragePrivateCacheDir(Bitmap bitmap, String fileName, Context context) {
        if (isExternalStorageMounted()) {
            BufferedOutputStream bos = null;
            // 获取私有的Cache缓存目录
            File file = context.getExternalCacheDir();
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                if (fileName != null && (fileName.contains(".png") || fileName.contains(".PNG"))) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                }
                bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // 从外部存储获取文件
    public static byte[] loadFileFromExternalStorage(String fileDir) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(fileDir)));
            byte[] buffer = new byte[8 * 1024];
            int c = 0;
            while ((c = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, c);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 从外部存储中寻找指定目录下的文件，返回Bitmap
    public Bitmap loadBitmapFromExternalStorage(String filePath) {
        byte[] data = loadFileFromExternalStorage(filePath);
        if (data != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bm != null) {
                return bm;
            }
        }
        return null;
    }

    // 获取外部存储公有目录的路径
    public static String getExternalStoragePublicDir(String type) {
        return Environment.getExternalStoragePublicDirectory(type).toString();
    }

    // 获取外部存储私有Cache目录的路径
    public static String getExternalStoragePrivateCacheDir(Context context) {
        return context.getExternalCacheDir().getAbsolutePath();
    }

    // 获取外部存储私有Files目录的路径
    public static String getExternalStoragePrivateFilesDir(Context context, String type) {
        return context.getExternalFilesDir(type).getAbsolutePath();
    }

    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    // 从外部存储中删除文件
    public static boolean removeFileFromExternalStorage(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
