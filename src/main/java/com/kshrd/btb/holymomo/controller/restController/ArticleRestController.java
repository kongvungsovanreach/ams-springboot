package com.kshrd.btb.holymomo.controller.restController;

import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.service.ArticleService.ArticleService;
import com.kshrd.btb.holymomo.service.ArticleService.FileUploadService;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value = "CRUD Operation", tags = {"Article Rest"},description = "Crud")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/articlesPagination")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Ror ot khernh te brader")
    })

    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", paramType = "query"),
            @ApiImplicitParam(name = "cateId", paramType = "query"),
            @ApiImplicitParam(name = "page", paramType = "query",defaultValue = "1")
    })

    public ResponseEntity<Map<String,Object>> findPagination(@ApiIgnore Paging paging, @ApiIgnore Filter filter){
        Map map = new HashMap();
        List<Article> articles = articleService.viewPagination(paging,filter);
        if (articles.isEmpty()){
            map.put("message", "Article cannot be found!");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else {
            map.put("message", "Articles found!");
            map.put("status", true);
            map.put("data", articles);
            map.put("count", articles.size());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }


    @GetMapping("/articles")
    @ApiOperation(value = "Find All Articles")
    public ResponseEntity<Map<String,Object>> findAll(){
        Map map = new HashMap();
        List<Article> articles = articleService.findAll();
        if (articles.isEmpty()){
            map.put("status", false);
            map.put("message", "Article cannot be found!");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else {
            map.put("status", true);
            map.put("message", "Articles found!");
            map.put("data", articles);
            map.put("count", articles.size());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

    }

    @GetMapping("/article/{id}")

    public ResponseEntity<Map<String,Object>> findOne(@PathVariable int id){
        Map map = new HashMap();
        Article article = articleService.findOneById(id);
        if (article == null){
            map.put("status", false);
            map.put("message", "Article cannot be found!");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else {
            map.put("status", true);
            map.put("message", "Articles found!");
            map.put("data", article);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

    }

    @PostMapping("/article")
    public ResponseEntity<Map<String,Object>> insert(@RequestBody Article article){
        Map map = new HashMap();
        if (articleService.add(article)){
            map.put("status", false);
            map.put("message", "Article error add!");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else {
            map.put("status", true);
            map.put("message", "Articles addes!!");
            map.put("data", article);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @PostMapping("/fileupload")
    public ResponseEntity<Map<String,Object>> uploadFile(MultipartFile file){
        Map map = new HashMap();
        String url = fileUploadService.uploadFile(file);
        if (url.equals("")){
            map.put("status", false);
            map.put("message", "upload fail!");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else {
            map.put("status", true);
            map.put("message", "upload success!!");
            map.put("data", url);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}
