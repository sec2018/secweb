package com.sec.demo.api;

import com.sec.demo.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequestMapping("api/test")
@Controller
public class TestApi {


    protected static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "runpy",method = RequestMethod.GET)
    @ResponseBody
    public String testRunPy(){
        String res = "default";
        try {
            res = runPy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    //运行py文件
    public String runPy() throws IOException {
        String path = "D:\\PycharmProjects\\MachineLearning\\Test\\TestRunPy.py";
        String name = "测试方法";
        Runtime runtime = Runtime.getRuntime();
        //执行命令
        Process process = runtime.exec("python "+path);
        //接收命令执行的输出信息
        BufferedReader infoReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
        String info = "";
        StringBuilder sb = new StringBuilder();
        while((info = infoReader.readLine()) != null){
            sb.append(info+"\n");
        }
        //接收命令执行的错误信息
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(),"UTF-8"));
        String error = "";
        StringBuilder sbErr = new StringBuilder();
        while((error = errorReader.readLine()) != null){
            sbErr.append(error+"\n");
        }
        String result = sb.toString();
        error = sbErr.toString();
        String pathName = path.substring(path.lastIndexOf("\\")+1);
        if("".equals(error)){

        }else{
            logger.error("名称："+name+"\n运行："+pathName+":"+error,"system","系统异常");
        }
        System.out.println(pathName + " " +result);
        return pathName + " 运行结果： " +result;
    }


}
