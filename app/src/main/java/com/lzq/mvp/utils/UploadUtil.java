package com.lzq.mvp.utils;

/**
 * Created by LZQ on 2017/11/28 15:20.
 * Describe :
 */

import android.os.AsyncTask;

import com.lzq.mvp.http.RetrofitFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadUtil {

    /**
     * 上传单张图片
     *
     * @param filePath 图片路径
     * @param observer 观察者
     * @throws IOException
     */
    public static void uploadImage(final String filePath, final Observer observer) {
        new AsyncTask<Integer, Integer, File>() {
            @Override
            protected File doInBackground(Integer... params) {
                //压缩图片
                File file = new File(BitmapUtil.compressImage(filePath));
                return file;
            }

            @Override
            protected void onPostExecute(File file) {
                super.onPostExecute(file);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                RetrofitFactory.getInstance()
                        .imageUpload( part)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        }.execute();
    }

    /**
     * 上传多张照片
     *
     * @param mFilesPath
     * @param observer
     */
    public static void uploadImages(final ArrayList<String> mFilesPath, final Observer observer) {
        new AsyncTask<Integer, Integer, List<File>>() {
            @Override
            protected List<File> doInBackground(Integer... params) {
                //压缩图片
                final List<File> files = new ArrayList<>();
                for (String path : mFilesPath) {
                    File file = new File(BitmapUtil.compressImage(path));
                    files.add(file);

                }
                return files;
            }

            @Override
            protected void onPostExecute(List<File> files) {
                super.onPostExecute(files);
                List<MultipartBody.Part> xx = filesToMultipartBodyParts(files);
                RetrofitFactory.getInstance()
                        .imagesUpload( xx)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        }.execute();


    }

    /**
     * @param files 多图片文件转表单
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
