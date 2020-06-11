package com.forezp.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forezp.api.util.Notice;
import com.forezp.api.utils.FastDFSClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件操作")
@RestController
public class UploadController {

    @Autowired
    private FastDFSClientUtil dfsClient;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public Notice fdfsUpload(@RequestParam("file") MultipartFile file) {
        List<String> fileUrl = null;
        try {
            fileUrl = dfsClient.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Notice(HttpStatus.OK, fileUrl, "成功上传文件");
    }

    /**
     * @return
     */
    @GetMapping("/deleteFile")
    @ApiOperation("删除文件")
    public Notice delFile(String filePath) {
        try {
            dfsClient.delFile(filePath);
        } catch (Exception e) {
            // 文件不存在报异常 ： com.github.tobato.fastdfs.exception.FdfsServerException: 错误码：2，错误信息：找不到节点或文件
            // e.printStackTrace();
        }
        return new Notice(HttpStatus.OK, filePath, "成功删除");
    }
}