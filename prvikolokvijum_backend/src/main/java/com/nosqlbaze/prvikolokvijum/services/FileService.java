package com.nosqlbaze.prvikolokvijum.services;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nosqlbaze.prvikolokvijum.model.FileModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    public FileModel getFile(String id) throws IllegalStateException, IOException {
        GridFSFile gfile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        FileModel file = new FileModel();
        file.setTitle(gfile.getMetadata().get("title").toString());
        file.setStream(operations.getResource(gfile).getInputStream());
        return file;
    }

    public String addFile(String title, MultipartFile gfile) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "file");
        metaData.put("title", title);
        ObjectId id = gridFsTemplate.store(gfile.getInputStream(), gfile.getName(), gfile.getContentType(), metaData);
        return id.toString();
    }

}