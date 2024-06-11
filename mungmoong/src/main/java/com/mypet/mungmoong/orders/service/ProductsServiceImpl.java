package com.mypet.mungmoong.orders.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.main.model.Files;
import com.mypet.mungmoong.main.service.FilesService;
import com.mypet.mungmoong.orders.dto.Products;
import com.mypet.mungmoong.orders.mapper.ProductsMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private FilesService filesService;

    @Override
    public List<Products> list() throws Exception {
        List<Products> products = productsMapper.list();
        
        return products;
    }

    @Override
    public Products select(String id) throws Exception {
        Products products = productsMapper.select(id);
        // Files thumbnail = new Files();
        // thumbnail.setParentTable(Products.TABLE_NAME);
        // thumbnail.setParentId(id);
        // List<Files> files = filesService.listByParent(thumbnail);
        // for (Files file : files) {
        //     log.info("썸네일 : " + file);
        //     if( file.getIsMain() ) {
        //         thumbnail = file;
        //     }
        // }
        // String thumbnailId = thumbnail.getId();
        // products.setThumbnailId(thumbnailId);
        return products;
    }

    @Override
    public int insert(Products products) throws Exception {
        String id = UUID.randomUUID().toString();
        products.setId(id);
        int result = productsMapper.insert(products);
        if( result > 0 ) {
            // 썸네일 업로드
            updloadThumbnail(products);
        }
        return result;
    }

    @Override
    public int update(Products products) throws Exception {
        int result = productsMapper.update(products);
        if( result > 0 ) {
            // 썸네일 업로드
            updloadThumbnail(products);
        }
        return result;
    }

    @Override
    public int delete(String deleteIdList) throws Exception {
        int result = productsMapper.delete(deleteIdList);
        return result;
    }

    @Override
    public void updloadThumbnail(Products products) throws Exception {
        // 썸네일 업로드
        // - 부모테이블, 부모ID, 멀티파트파일, 대표 파일, 순서:1
        MultipartFile thumbnailFile = products.getThumbnail();
        // // 썸네일 파일 업로드한 경우만
        // if( thumbnailFile != null && !thumbnailFile.isEmpty() ) {
        //     log.info("썸네일 파일 : " + thumbnailFile.getOriginalFilename());

        //     // 기존 썸네일 삭제

        //     // 썸네일 등록
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(Products.TABLE_NAME);
            thumbnail.setParentId(products.getId());
            thumbnail.setIsMain(true);        // 대표 파일 (isMain=true)
            thumbnail.setSeq(1);               // 순서 : 1
            filesService.upload(thumbnail);        // 썸네일 파일 업로드
        }

    @Override
    public List<Products> adminList() throws Exception {

        List<Products> productsList = productsMapper.adminList();

        return productsList;

    }

    @Override
    public int adminInsert(Products products) throws Exception {
        int result = productsMapper.adminInsert(products);
        return result;
    }


    @Override
    public int adminUpdate(Products products) throws Exception {
        int result = productsMapper.adminUpdate(products);
        return result;
    }

    @Override
    public int adminDelete(String id) throws Exception {

        int result = productsMapper.adminDelete(id);

        return result;

    }

    

        

    }


    

