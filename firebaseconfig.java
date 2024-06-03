package com.assignment.rest_demo.config;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class firebaseconfig {
    @PostConstruct

    public void configurefirebaseConnection() throws IOException
    {
        File file = ResourceUtils.getFile("classpath:config/springboot-firebase-1001-firebase-adminsdk-dhd0w-3dd6a1c43e.json")
        FileInputStream serviceAccount =
            new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        FirebaseApp.initializeApp(options);


    }

}
