package com.example.demo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.websockets.WebSocketServer;


/**
 * Worlds controller which handles world creation requests from client side 
 * @author Brendan
 *
 */
@RestController
class worldsController {

    @Autowired
    worldRepository worldRepository;

    private final Logger logger = LoggerFactory.getLogger(worldsController.class);

    /**
     * Method to handle creating a new world through HTTP Post request
     * @param world
     * @return world
     * @throws IOException
     */
     @RequestMapping(method = RequestMethod.POST, path = "/world/new")
     public Worlds saveWorld(@RequestBody Worlds world) throws IOException {
    	 
    	
    	 String verify = "Success";
    	 
    	 if(world.getWname() == null) {
    		 world.setWname("Default");
    	 }
    	 
    	 if(world.getSpecies() == null) {
    		 world.setSpecies("Default");
    	 }
    	 
    	 if(world.getResources() == null) {
    		 world.setResources("Default");
    	 }
    	 
    	 if(world.getWowner() == null) {
    		 world.setWowner("Default");
    	 }
    	 
    	 List<Worlds> worldList = worldRepository.findAll();
    	 
    	 for(int i = 0; i < worldList.size();i++) {
    		if(worldList.get(i).getWname().equalsIgnoreCase(world.getWname())) {
    			verify = "Failed";
    		}
    	 }
    	 
    	 if (verify == "Success") {
    		 worldRepository.save(world);
    		 
    		 List<Worlds> worldList1 = worldRepository.findAll();
    	        String message = "";
    	        for(int i = 0; i < worldList1.size();i++) {
    	    		message = message + ", " + worldList1.get(i).getWname();
    	    	 }
    	        
    	        	WebSocketServer.broadcast(message);
    	        	
    	        	world.setWname("Success");
    	       	
    	 } else {
    		 world.setWname("Failed");
    	 }
    	 
        return world;
    }
     
     @RequestMapping(method = RequestMethod.POST, path = "/world/search")
     public Worlds searchWorld(@RequestBody Worlds world) throws IOException {
    	 
    	 String worldName = world.getWname();
     
    	 List<Worlds> worldList = worldRepository.findAll();
    	 Worlds requestedWorld = null;
    	 for(int i = 0; i < worldList.size();i++) {
    		 if (worldList.get(i).getWname().equals(worldName)) {
    			 requestedWorld = worldList.get(i);
    			 break;
    	 }
    		
     }
    	 
    	 return requestedWorld;
     }
     
     @RequestMapping(method = RequestMethod.POST, path = "/world/delete")
     public void deleteWorld(@RequestBody Worlds world) throws IOException {
    	 
    	 String worldName = world.getWname();
    	 List<Worlds> worldList = worldRepository.findAll();
    	 for(int i = 0; i < worldList.size();i++) {
    		 if (worldList.get(i).getWname().equals(worldName)) {
    			 worldRepository.delete(worldList.get(i));
    			 break;
    		 }
    	 }
    	 
     }
}

