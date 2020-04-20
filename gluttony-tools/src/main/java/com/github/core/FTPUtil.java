package com.github.core;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;


/**
 * User: hk
 * Date: 2017/8/10 下午4:31
 * version: 1.0
 */
public final class FTPUtil implements AutoCloseable {

    private FTPClient ftpClient;

    /**
     * <p>连接ftp服务器</p>
     *
     * @param serverIP 服务IP
     * @param port     端口
     * @param userName 用户名
     * @param password 密码
     * @throws IOException IOException
     */
    public void connectServer(String serverIP, int port, String userName, String password) throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(serverIP, port);
        ftpClient.login(userName, password);
        ftpClient.setBufferSize(1024);//设置上传缓存大小
        ftpClient.setControlEncoding("UTF-8");//设置编码
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件类型
        ftpClient.enterLocalPassiveMode();
    }

    /**
     * <p>下载ftp文件到本地</p>
     *
     * @param remoteFileName 远程文件名称
     * @param localFile      本地文件[绝对路径]
     * @return true/false
     * @throws IOException 异常
     */
    public boolean downloadFile(String remoteFileName, String localFile) throws IOException {
        boolean isSucc;
        File outFileName = new File(localFile);
        if (ftpClient == null)
            throw new IOException("ftp server not login");
        try (OutputStream outputStream = new FileOutputStream(outFileName)) {
            isSucc = ftpClient.retrieveFile(remoteFileName, outputStream);
        }
        return isSucc;
    }

    /**
     * <p>下载ftp文件到本地</p>
     *
     * @param path           文件路径
     * @param remoteFileName 远程文件
     * @param localFile      本地文件
     * @return true/false
     * @throws IOException
     */
    public boolean downloadFile(String path, String remoteFileName, String localFile) throws IOException {
        boolean isSucc;
        File outFileName = new File(localFile);
        if (ftpClient == null)
            throw new IOException("ftp server not login");
        try (OutputStream outputStream = new FileOutputStream(outFileName)) {
            ftpClient.changeWorkingDirectory(path);
            isSucc = ftpClient.retrieveFile(remoteFileName, outputStream);
        }
        return isSucc;
    }

    /**
     * <p>上传文件</p>
     * Example:<br>
     *
     * @param remoteFileName 远程文件名
     * @param localFile      本地文件[绝对路径]
     * @return true/false
     * @throws IOException 异常
     */
    public boolean uploadFile(String remoteFileName, String localFile) throws IOException {
        boolean isSucc;
        try (InputStream inputStream = new FileInputStream(localFile)) {
            if (ftpClient == null)
                throw new IOException("ftp server not login");
            isSucc = ftpClient.storeFile(remoteFileName, inputStream);
        }
        return isSucc;
    }

    /**
     * <p>上传文件指定目录下</p>
     *
     * @param remotePath     远程文件路径
     * @param remoteFileName 远程文件名称
     * @param localFile      本地文件[绝对路径]
     * @return true/false
     * @throws IOException 异常
     */
    public boolean uploadFile(String remotePath, String remoteFileName, String localFile) throws IOException {
        boolean isSucc;
        try (InputStream inputStream = new FileInputStream(localFile)) {
            if (ftpClient == null)
                throw new IOException("ftp server not login");
            //1.创建目录
            createDirectory(remotePath);
            //2.切换至指定目录
            changeDirectory(remotePath);
            //3.上传文件
            isSucc = ftpClient.storeFile(remoteFileName, inputStream);
        }
        return isSucc;
    }

    /**
     * <p>切换目录</p>
     *
     * @param path 创建目录
     * @return true/false
     * @throws IOException 异常
     */
    public boolean changeDirectory(String path) throws IOException {
        return ftpClient.changeWorkingDirectory(path);
    }

    /**
     * <p>创建目录</p>
     *
     * @param path 创建目录
     * @return true/false
     * @throws IOException 异常
     */
    public boolean createDirectory(String path) throws IOException {
        return ftpClient.makeDirectory(path);
    }


    /**
     * <p>自动关闭资源</p>
     */
    @Override
    public void close() throws Exception {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public static void main(String[] args) throws Exception {
        String remotePath = args[0];
        String fileName = args[1];
        FTPUtil ftpUtil = new FTPUtil();
        ftpUtil.connectServer("195.203.6.218",21,"ftptest","ftptest");
        ftpUtil.downloadFile(remotePath,fileName,"d://");
    }
}
