package com.gt.gestion_taches;

import com.gt.gestion_taches.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionTachesApplication implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(GestionTachesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        taskService.updateTaskState();
    }

}