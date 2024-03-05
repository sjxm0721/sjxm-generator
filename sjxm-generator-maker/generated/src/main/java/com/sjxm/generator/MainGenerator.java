package com.sjxm.generator;

import freemarker.template.TemplateException;

import com.sjxm.model.DataModel;

import java.io.File;
import java.io.IOException;




/**
 * @Author: 四季夏目
 * @Date: 2024/2/13
 * @Description:
 */
public class MainGenerator {

    public static void doGenerate(DataModel object) throws TemplateException, IOException {

        String inputRootPath = ".source/springboot-init";
        String outputRootPath = "generated";

        String inputPath;
        String outputPath;

            String password = object.mysqlConfig.password;
            String url = object.mysqlConfig.url;
            String username = object.mysqlConfig.username;
            String description = object.docsConfig.description;
            String title = object.docsConfig.title;
            String version = object.docsConfig.version;
        boolean needDocs = object.needDocs;
        boolean needPost = object.needPost;
        boolean needCors = object.needCors;
        boolean needEs = object.needEs;
        String basePackage = object.basePackage;
        boolean needRedis = object.needRedis;

        //groupKey = post
            if(needPost){
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/post/PostAddRequest.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/post/PostAddRequest.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/entity/Post.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/entity/Post.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/service/impl/PostServiceImpl.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/service/impl/PostServiceImpl.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/resources/mapper/PostMapper.xml.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/resources/mapper/PostMapper.xml").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/post/PostUpdateRequest.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/post/PostUpdateRequest.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/controller/PostController.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/controller/PostController.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/post/PostQueryRequest.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/post/PostQueryRequest.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/mapper/PostMapper.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/mapper/PostMapper.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
        inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/service/PostService.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/service/PostService.java").getAbsolutePath();
            DynamicGenerator.doGenerate(inputPath, outputPath,object);
            }



    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/common/ErrorCode.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/common/ErrorCode.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/exception/BusinessException.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/exception/BusinessException.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
    outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/common/PageRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/common/PageRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/config/MyBatisPlusConfig.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/config/MyBatisPlusConfig.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/enums/UserRoleEnum.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/enums/UserRoleEnum.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/exception/GlobalExceptionHandler.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/exception/GlobalExceptionHandler.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);




                if(needDocs){

    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/config/Knife4jConfig.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/config/Knife4jConfig.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);

                }




    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/service/impl/UserServiceImpl.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/service/impl/UserServiceImpl.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/user/UserRegisterRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/user/UserRegisterRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "pom.xml.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"pom.xml").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/resources/mapper/UserMapper.xml.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/resources/mapper/UserMapper.xml").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/test/.DS_Store").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/test/.DS_Store").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/common/DeleteRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/common/DeleteRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/constant/UserConstant.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/constant/UserConstant.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/user/UserQueryRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/user/UserQueryRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/user/UserAddRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/user/UserAddRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/MainApplication.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/MainApplication.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, ".DS_Store").getAbsolutePath();
    outputPath = new File(outputRootPath,".DS_Store").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/.DS_Store").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/.DS_Store").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/entity/User.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/entity/User.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/common/ResultUtils.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/common/ResultUtils.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/mapper/UserMapper.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/mapper/UserMapper.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);




                if(needCors){

    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/config/CorsConfig.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/config/CorsConfig.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);

                }




    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/exception/ThrowUtils.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/exception/ThrowUtils.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/service/.DS_Store").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/service/.DS_Store").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/controller/UserController.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/controller/UserController.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/common/BaseResponse.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/common/BaseResponse.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
    outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/config/JsonConfig.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/config/JsonConfig.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "Dockerfile").getAbsolutePath();
    outputPath = new File(outputRootPath,"Dockerfile").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/user/UserUpdateRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/user/UserUpdateRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/service/UserService.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/service/UserService.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/user/UserLoginRequest.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/user/UserLoginRequest.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);





    inputPath = new File(inputRootPath, "src/main/resources/application.yml.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/resources/application.yml").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);




                if(needPost && needEs){

    inputPath = new File(inputRootPath, "src/main/java/com/yupi/springbootinit/model/dto/post/PostEsDTO.java.ftl").getAbsolutePath();
    outputPath = new File(outputRootPath,"src/main/java/com/yupi/springbootinit/model/dto/post/PostEsDTO.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath,object);

                }


    }
}
