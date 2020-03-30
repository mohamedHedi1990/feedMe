package com.biservices.wakalni.wakalni.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biservices.wakalni.wakalni.exception.FileStorageException;
import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.biservices.wakalni.wakalni.persistence.entities.Restaurant;
import com.biservices.wakalni.wakalni.services.DBFileStorageService;
import com.biservices.wakalni.wakalni.services.RestaurantService;

@RequestMapping("/api/file")
@RestController
public class FileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private DBFileStorageService DBFileStorageService;
	
	@Autowired
	private RestaurantService restoSevcie;

	@CrossOrigin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	// @PostMapping("/{restaurantId}/{menuId}")
	@PostMapping("/{restaurantId}")
	public Restaurant uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("restaurantId") Long restaurantId
			// , @PathVariable("menuId") Long menuId 
			) {
		String fileName;
		try {
			fileName = DBFileStorageService.storeFile(file);
			String fileDownloadUri = "";
			/*if(menuId == null) {
				fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/wakalni/"+restaurantId+"/"+menuId+"/")
						.path(fileName).toUriString();
				
			} else {
				fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/wakalni/"+restaurantId+"/")
						.path(fileName).toUriString();
			}*/
			
			fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
					.path(fileName).toUriString();
			
			// return restoSevcie.updateRestarantOrMenuPicture(fileDownloadUri,restaurantId, menuId);
			return restoSevcie.updateRestarantPicture(fileDownloadUri,restaurantId);

			// return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		} catch (FileStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	@CrossOrigin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	// @PostMapping("/{restaurantId}/{menuId}")
	@PostMapping("/menu/{menuId}")
	public Menu uploadFileForMenu(@RequestParam("file") MultipartFile file,  @PathVariable("menuId") Long menuId 
			) {
		String fileName;
		try {
			fileName = DBFileStorageService.storeFile(file);
			String fileDownloadUri = "";
			fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
					.path(fileName).toUriString();
			
			// return restoSevcie.updateRestarantOrMenuPicture(fileDownloadUri,restaurantId, menuId);
			return restoSevcie.updateMenuPicture(fileDownloadUri, menuId);

			// return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		} catch (FileStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	 @GetMapping("/downloadFile/{fileName:.+}")
	  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
	                                               HttpServletRequest request) throws FileStorageException {
	    // Load file as Resource
	    Resource resource = DBFileStorageService.loadFileAsResource(fileName);

	    // Try to determine file's content type
	    String contentType = null;
	    try {
	      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	    } catch (IOException ex) {
	    	LOGGER.info("Could not determine file type.");
	    }

	    // Fallback to the default content type if type could not be determined
	    if (contentType == null) {
	      contentType = "application/octet-stream";
	    }

	    return ResponseEntity.ok()
	                         .contentType(MediaType.parseMediaType(contentType))
	                         .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                         .body(resource);
	  }
}
