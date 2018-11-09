package com.jsxk.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.Catalogue;
import com.jsxk.ws.service.CatalogueService;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/catalogue")
public class CatalogueController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private CatalogueService catalogueService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCatalogue(@RequestBody Catalogue catalogue, BindingResult bindingResult) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "添加错误");

        resultJson.put("state", false);

        if (bindingResult.hasErrors()) {

            resultJson.put("message", bindingResult.getFieldError().getDefaultMessage());
            resultJson.put("state", false);
            return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

        }
        try {

            boolean result = catalogueService.addCatalogue(catalogue);
            if (result) {

                resultJson.put("message", "添加成功");

                resultJson.put("state", true);
            }

        } catch (Exception ex) {

            log.error("新增错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping(value = "/getcatalogList")
    public String getCatalogueList() {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        try {

            List<Catalogue> catalogues = catalogueService.getCatalogueList();
            resultJson.putPOJO("data", catalogues);

        } catch (Exception ex) {

            log.error("查询分类列表错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping(value = "/getcatalogSecond")
    public String getCataloguByparentId(@RequestParam("parentId") int ParentId, @RequestParam("pageNum") int pagenum, @RequestParam("limt") int limt) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        try {

            Page page = PageHelper.startPage(pagenum, limt);
            List<Catalogue> catalogues = catalogueService.getCatalogueByParentId(ParentId);
            resultJson.putPOJO("data", catalogues);
            resultJson.put("pageNum", page.getPageNum());
            resultJson.put("pageSize", page.getPageSize());

        } catch (Exception ex) {

            log.error("查询分类列表错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    @RequestMapping("/dele")
    public String deletCatalogu(@RequestParam("id") int id) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("state", false);
        resultJson.put("message", "删除错误");

        try {

            List<Catalogue> catalogues = catalogueService.getCatalogueByParentId(id);
            if (catalogues != null && catalogues.size() > 0) {
                resultJson.put("state", false);
                resultJson.put("message", "有子分类，先删除子分类才能继续！");

                return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);
            }

            boolean result = catalogueService.deleCatalogue(id);

            if (result) {

                resultJson.put("message", "删除成功");

                resultJson.put("state", true);
            }

        } catch (Exception ex) {

            log.error("查询分类列表错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCatalogu(@RequestBody Catalogue catalogue) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        resultJson.put("state", false);
        resultJson.put("message", "编辑错误");


        try {

            boolean result = catalogueService.editCatalogue(catalogue);

            if (result) {

                resultJson.put("message", "编辑成功");

                resultJson.put("state", true);

                return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

            }


        } catch (Exception ex)

        {

            log.error("编辑错误{}", ex);
        }


        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }

    //文件上传类


    @RequestMapping(value = "/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        resultJson.put("state", false);
        resultJson.put("message", "上传错误！");
        resultJson.put("url", "");


        try {
            if (file.isEmpty()) {
                resultJson.put("message", "文件为空");
                return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

            }
            // 获取文件名
            String fileName = file.getOriginalFilename();

            String filestemp="";
            Random rand = new Random();

            filestemp+=rand.nextInt(10000)+new Date().toString();

            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);

            fileName=filestemp+suffixName;
            // 设置文件存储路径
            String filePath = "/home/OTA/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            resultJson.put("state", true);
            resultJson.put("message", "上传成功！");
            resultJson.put("url", "47.150.50.221/OTA/" + fileName);
            return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);
    }


    //文件下载相关代码
    @RequestMapping(value = "/downloadImage",method = RequestMethod.GET)
    public String downloadImage(String imageName, HttpServletRequest request, HttpServletResponse response) {
        //String fileName = "123.JPG";
        log.debug("the imageName is : "+imageName);
        String fileUrl = "/home/OTA/"+imageName;
        if (fileUrl != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
           /* String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");*/
            /*File file = new File(realPath, fileName);*/
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }






}
