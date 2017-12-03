package yo.antihype.team.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yo.antihype.team.service.FileReaderService;

import java.io.IOException;

/**
 * Created by Serhii_Vasylenko on 9/19/2017.
 */
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    private final FileReaderService fileReader;

    public Controller(FileReaderService fileReader) {
        this.fileReader = fileReader;
    }

    @GetMapping("/app/info")
    @ResponseBody
    public String toString(String path) throws IOException {
        return fileReader.toString("git.version");
    }

}
