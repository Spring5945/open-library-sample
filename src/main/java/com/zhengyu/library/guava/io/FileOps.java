package com.zhengyu.library.guava.io;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhengyu.nie on 2018/7/31.
 */
public class FileOps {

    public static void main(String[] args) throws IOException {
        File file = new File(System.getProperty("test.txt"));
        readFile(file);
        writeFile("test", file);
        copyFile(file, new File(System.getProperty("copy.txt")));
    }

    /**
     * 写文件
     *
     * @param content
     * @param file
     * @throws IOException
     */
    public static void writeFile(String content, File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        Files.write(content.getBytes(Charsets.UTF_8), file);
    }

    /**
     * 读文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> readFile(File file) throws IOException {
        if (!file.exists()) {
            return ImmutableList.of(); //避免返回null
        }
        return Files.readLines(file, Charsets.UTF_8);
    }

    /**
     * 文件复制
     *
     * @param from
     * @param to
     * @throws IOException
     */
    public static void copyFile(File from, File to) throws IOException {
        if (!from.exists()) {
            return;
        }
        if (!to.exists()) {
            to.createNewFile();
        }
        Files.copy(from, to);
    }

}

