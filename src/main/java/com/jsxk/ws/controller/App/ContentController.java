package com.jsxk.ws.controller.App;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsxk.ws.common.AuthManager;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.*;
import com.jsxk.ws.model.Po.VoidesContent;
import com.jsxk.ws.service.AppManagerService;
import com.jsxk.ws.service.CatalogueService;
import com.jsxk.ws.service.UserContentService;
import com.jsxk.ws.service.VoidesService;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/content")
public class ContentController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    CatalogueService catalogueService;

    @Autowired
    UserContentService userContentService;

    @Autowired
    AuthManager autowired;

    @Autowired
    VoidesService voidesService;

    @Autowired
    AppManagerService appManagerService;

    /**
     * 获取轮播图
     *
     * @return
     */
    @RequestMapping("/getBlue")
    public String getBlue() {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            List<Blues> blues = catalogueService.getBlues();
            resultJson.putPOJO("data", blues);

        } catch (Exception ex) {

            log.error("获取轮播图错误！");
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    /***
     *
     * 获取标签列表
     * @return
     */

    @RequestMapping("/getCatatList")
    public String getCataList() {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        List<Catalogue> catalogues = catalogueService.getCatalogueByParentId(0);
        resultJson.putPOJO("data", catalogues);

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    /***
     *
     * 获取内容列表页   //先写死
     * @return
     */

    @RequestMapping("/getContentList")
    public String getContentList(HttpServletRequest request, @RequestParam("pageNum") int pagenum, @RequestParam("limt") int limt, @RequestParam("catalogId") String catalogId) {


        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        UserInfor userInfor = autowired.getUserInfoByToken(request);

        try {

            Page page = PageHelper.startPage(pagenum, limt);
            List<VoidesContent> voideslist = userContentService.getContetnList(catalogId, userInfor.getState());
            resultJson.putPOJO("data", voideslist);
            resultJson.put("pageNum", page.getPageNum());
            resultJson.put("pageSize", page.getPageSize());

        } catch (Exception ex) {

            log.error("查询列表错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    /***
     *
     *
     * 获取分类列表
     * @return
     */

    @RequestMapping("/getLableList")
    public String getLableList(@RequestParam("parentId") int parentId) {


        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();


        List<Catalogue> catalogues = catalogueService.getCatalogueListSecond(parentId);

        resultJson.putPOJO("data", catalogues);

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/modifystore")
    public String addStore(@RequestParam("voideId") int voideId, HttpServletRequest request) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();


        resultJson.put("message", "操作错误");

        resultJson.put("state", false);

        int userId = autowired.getUserInfoByToken(request).getId();

        try {

            Boolean result = userContentService.modifyStore(voideId, userId);
            if (result) {

                resultJson.put("message", "操作成功");
                resultJson.put("state", true);
            }

        } catch (Exception ex) {

            log.error("新增收藏错误{}", ex);

        }
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/getVoideById")
    public String getVoidesById(@RequestParam("id") int id, @RequestParam("limit") int limit) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        try {

            Voides voides = voidesService.getVoidesById(id);

            resultJson.putPOJO("video", voides);

            if (voides != null) {

                Page page = PageHelper.startPage(0, limit);

                List<Voides> video1 = voidesService.getVoidesByCatalogId(voides.getCatalogid());

                resultJson.putPOJO("data", video1);

            }


        } catch (Exception ex) {

            log.error("查询错误{}", ex);
        }
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/searchVoides")
    public String searchVoide() {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();


        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }

    //观看次数
    @RequestMapping("/addvideoNum")

    public String addvoideoNum(@RequestParam("voideId") int voideId) {


        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            boolean result = voidesService.addVoidesNum(voideId);
            if (result) {

                resultJson.put("message", "操作成功");
                resultJson.put("state", true);
            }
        } catch (Exception ex) {

            log.error("更新观看次数错误{}", ex);

        }
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    @RequestMapping("/getNovel")
    public String getNovel(@RequestParam("pageNum") int pageNum, @RequestParam("catalogId") int catalogId, @RequestParam("limit") int limit) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Page page = PageHelper.startPage(pageNum, limit);
            List<Novel> novels = appManagerService.getNovelList(catalogId);

            resultJson.put("pageNum", page.getPageNum());

            resultJson.put("pagesize", page.getPageSize());

            resultJson.putPOJO("date", novels);


        } catch (Exception ex) {

            log.error("查询小说错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/getNovelById")
    public String getNovelById(@RequestParam("NovelId") int NovelId) {


        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Novel novel = appManagerService.getNovelById(NovelId);

            resultJson.putPOJO("data", novel);


        } catch (Exception ex) {

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


}
