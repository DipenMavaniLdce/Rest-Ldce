package com.ldce.Dao;

import com.ldce.Main.LdceApplication;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;

@Component
public class FileHandalingDao {


    public HashMap<String, String> createStorage(MultipartFile file, String id, String type, String domain) {
        // types === photo/sign/feereceipt/marksheet

        // domain student,admin,student/request etc...
        HashMap<String, String> fileData = new HashMap<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String file_name = type + "_" + id + "_" + timestamp.getTime() + "_" + file.getOriginalFilename();
        String file_path = Paths.get(LdceApplication.uploadDirectory, domain, type).toString();
        String file_url = Paths.get(domain, type, file_name).toString();
        String file_type = file.getContentType();
        long file_Size = file.getSize();
        String file_size = Long.toString(file_Size);
        fileData.put("file_name", file_name);
        fileData.put("file_path", file_path);
        fileData.put("file_type", file_type);
        fileData.put("file_size", file_size);
        fileData.put("file_url", file_url);
        return fileData;
    }

    public boolean storeFile(MultipartFile file, String file_path, String file_name) throws IOException {
        Logger logger = LoggerFactory.getLogger(FileHandalingDao.class);
        logger.info("request come for store file at path:  " + file_path);
        logger.info("Name of the file is  " + file_name);
        boolean fileExists = true;
        File f = new File(file_path);
        if (!f.exists()) {
            logger.info("file path does not exist" + file_path);
            fileExists = f.mkdirs();
            if (fileExists)
                logger.info("path created successfully " + file_path);
            else
                logger.info("unable to create filepath successfully " + file_name);
        }
        if (fileExists) {
            try {

                BufferedOutputStream stream1 = new BufferedOutputStream(
                        new FileOutputStream(Paths.get(file_path, file_name).toString()));
                logger.info("stream created at " + file_path);
                stream1.write(file.getBytes());
                stream1.close();
                logger.info("stream closed at " + file_path);

            } catch (Exception e) {
                logger.error("Exception occured during storing a file: ", file_name);
                logger.error(e.toString());
                return false;
            }
            return true;
        }
        return false;
    }

    public void deleteOldFile(String filename) {
        Logger logger = LoggerFactory.getLogger(FileHandalingDao.class);
        logger.info("request come for delete old file " + filename);
        try {
            boolean delete = new File(filename).delete();
            if (delete) {
                logger.info(filename + " deleted success fully");

            } else {
                logger.info(filename + " is not deleted");
            }
        } catch (Exception e) {
            logger.error("exception occure for deleting old file " + filename);
            logger.error(e.toString());

        }

    }

}



