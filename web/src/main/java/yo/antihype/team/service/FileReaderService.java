package yo.antihype.team.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class FileReaderService {

    @RequestMapping("/app/info")
    @ResponseBody
    public String toString(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        InputStream resourceInputStream = resource.getInputStream();
        String result = new BufferedReader(new InputStreamReader(resourceInputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}