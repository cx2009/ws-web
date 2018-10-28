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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/catalogue")
public class CatalogueController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private CatalogueService catalogueService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCatalogue(@RequestBody Catalogue catalogue) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "添加错误");

        resultJson.put("state", false);
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

}
