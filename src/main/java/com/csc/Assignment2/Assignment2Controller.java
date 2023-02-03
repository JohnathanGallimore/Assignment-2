/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csc.Assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author johna
 */
@RestController
public class Assignment2Controller {
     @GetMapping("/fact")
    public Object getFact() {
        try {
            String url = "https://catfact.ninja/fact";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            //Print the whole response to console.
            System.out.println(root);

            //Parse out the most important info from the response.
            String quoteFact = root.get("fact").asText();
            System.out.println("fact: " + quoteFact);
            

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Assignment2Controller.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /fact";
        }
    }
}
