package com.sport.sportsmailserver.controller;

import com.sport.sportsmailserver.dto.LoginUser;
import com.sport.sportsmailserver.dto.RestModel;
import com.sport.sportsmailserver.security.MustUserLogin;
import com.sport.sportsmailserver.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author itning
 * @date 2020/2/12 12:46
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    /**
     * 根据商品类别获取商品
     *
     * @param typeId   类别ID
     * @param pageable 分页
     * @return ResponseEntity
     */
    @GetMapping("/type")
    public ResponseEntity<?> findByType(@MustUserLogin LoginUser loginUser,
                                        @RequestParam String typeId,
                                        @PageableDefault(
                                                size = 20, sort = {"gmtModified"},
                                                direction = Sort.Direction.DESC
                                        )
                                                Pageable pageable) {
        return RestModel.ok(commodityService.findByType(typeId, pageable));
    }

    /**
     * 获取所有推荐商品
     *
     * @return ResponseEntity
     */
    @GetMapping("/recommends")
    public ResponseEntity<?> allRecommend() {
        return RestModel.ok(commodityService.findByRecommend());
    }

    /**
     * 获取一个商品
     *
     * @param id 商品ID
     * @return ResponseEntity
     */
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getById(@MustUserLogin LoginUser loginUser,
                                     @PathVariable String id) {
        return RestModel.ok(commodityService.findById(id));
    }
}