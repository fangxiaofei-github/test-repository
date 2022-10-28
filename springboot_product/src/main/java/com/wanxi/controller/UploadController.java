package com.wanxi.controller;


import cn.hutool.core.io.FileUtil;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 文件上传
 * @author: fxf
 * @date: 2022/8/29 9:27
 */
@Api(tags = "文件上传控制层模块")
@Controller
public class UploadController {


    /**
     * 这个接口只做文件的上传（对于普通组件的处理在另外一个接口中处理）
     * @param multipartFile  接收文件需要用@RequestParam(“file”)指定参数名称，layui默认文件上传的name是file
     * @return
     */
    @ApiOperation("图片上传接口")
    @RequestMapping("/upload")
    @ResponseBody
    @Async
    public ResultInfo upload(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile){

        ResultInfo info = new ResultInfo();

        if (multipartFile != null && !multipartFile.isEmpty()){
            // 获取原文件名称
            String filename = multipartFile.getOriginalFilename();

            // 新文件名, 一般要生成一个新的文件名，使用UUID等
            filename = UUID.randomUUID()+"_"+filename;

            // 表示只支持jpg/jpeg/png这3种类型的图片，其他不支持。也可以在前端layui的文件上传中设置
            if (filename.endsWith("jpg")|| filename.endsWith("jpeg") ||filename.endsWith("png")){

                try {
                    /**
                     * target目录下
                     */

                    /** 项目路径下
                     *    对于文件名称，一般我们要修改一下让它不会和其他文件名重复。（一般使用当前系统的毫秒数）
                     *       这样写的逻辑代码：若文件名相同则会被覆盖
                     */
                    // 指定上传文件的存储路径，这里静态资源下的images
                    String path = System.getProperty("user.dir");
                    String realPath = path + "/springboot_product/src/main/resources/static/images/";
                    //String out = path + "/springboot_product/target/classes/static/images/";
                    File realPathFile = new File(realPath,filename);
                    //File outFile = new File(out,filename);


                    // 判断文件是否存在
                    if (!realPathFile.exists()){
                        realPathFile.mkdirs();
                    }
                    ////if (!outFile.exists()){
                    //    outFile.mkdirs();
                    //}

                    //将图片写入到项目路径下
                    multipartFile.transferTo(realPathFile);
                    //multipartFile.transferTo(outFile);
                    //使用hutool工具包下的工具类
                    //FileUtil.writeBytes(multipartFile.getBytes(),realPathFile);
                    //FileUtil.writeBytes(multipartFile.getBytes(),outFile);




                    //把图片路径响应给前端"/images/"+filename，前端拿到这个值去存储到数据库中
                    Map<String,String> data = new HashMap<>();
                    data.put("src","http://127.0.0.1:8080/images/"+filename); //还有title属性可选，这种格式是layui返回值要求的
                    data.put("title",filename);
                    info.setData(data);
                    //System.out.println("图片的项目后端的访问路径：/images/"+filename);
                    info.setCode(0);
                    info.setMsg("上传成功");
                    return info;
                } catch (Exception e) {
                    e.printStackTrace();
                    info.setCode(1);
                    info.setMsg("文件上传异常");
                    return info;
                }
            }else {
                info.setCode(1);
                info.setMsg("不支持该上传类型");
                return info;
            }

        }

        info.setCode(0);
        info.setMsg("文件为空");
        return info;
    }










}
