package team_project.clat.domain.File;

import org.springframework.web.multipart.MultipartFile;
import team_project.clat.exception.FileSizeLimitException;

import java.util.List;

public class ValidationFileSize {

    public static void sizeCheck(List<MultipartFile> multipartFileList){
        if(multipartFileList.size() > 5){
            throw new FileSizeLimitException("5개를 초과해서는 안됩니다!");
        }
    }
}
