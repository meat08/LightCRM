package ru.lightcrm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "file_infos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Long size;

    @Column(name = "key_name")
    private String keyName;

    @CreationTimestamp
    @Column(name = "upload_date")
    private OffsetDateTime uploadDate;

    public static FileInfo createNewFileInfo(String fileName, String KeyName, String fileType, long fileSize) {
        FileInfo newFileInfo = new FileInfo();
        newFileInfo.setName(fileName);
        newFileInfo.setType(fileType);
        newFileInfo.setKeyName(KeyName);
        newFileInfo.setSize(fileSize);
        newFileInfo.setUploadDate(OffsetDateTime.now());

        return newFileInfo;
    }
    public static FileInfo createNewFileInfo(MultipartFile resource, String keyName) {
        return createNewFileInfo(resource.getOriginalFilename(), keyName, resource.getContentType(), resource.getSize());
    }
}
