/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasenautiges.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mcg
 */
@RestController
public class HelloWorldRestController {

    @GetMapping({ "/hello", "/hw", "/hola" })
    public String helloWorld() {
        return "Hello,world!!!";
    }
}
