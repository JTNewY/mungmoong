package com.mypet.mungmoong.imgfile.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ImgFileDTO {
    
    private int no;
    private int parentNo;
    private String parentTable;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileCode;
    private Timestamp regDate;
    private Timestamp updDate;
    
 // Getters and Setters
 public int getNo() {
    return no;
}

public void setNo(int no) {
    this.no = no;
}

public int getParentNo() {
    return parentNo;
}

public void setParentNo(int parentNo) {
    this.parentNo = parentNo;
}

public String getParentTable() {
    return parentTable;
}

public void setParentTable(String parentTable) {
    this.parentTable = parentTable;
}

public String getFileName() {
    return fileName;
}

public void setFileName(String fileName) {
    this.fileName = fileName;
}

public String getFilePath() {
    return filePath;
}

public void setFilePath(String filePath) {
    this.filePath = filePath;
}

public long getFileSize() {
    return fileSize;
}

public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
}

public String getFileCode() {
    return fileCode;
}

public void setFileCode(String fileCode) {
    this.fileCode = fileCode;
}

public Timestamp getRegDate() {
    return regDate;
}

public void setRegDate(Timestamp regDate) {
    this.regDate = regDate;
}

public Timestamp getUpdDate() {
    return updDate;
}

public void setUpdDate(Timestamp updDate) {
    this.updDate = updDate;
}


}
