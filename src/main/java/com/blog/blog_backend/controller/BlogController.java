package com.blog.blog_backend.controller;

import com.blog.blog_backend.model.Blog;
import com.blog.blog_backend.repository.BlogRepository;
import com.blog.blog_backend.services.BlogServices;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access (React default port)
public class BlogController {
	
	@Autowired
	
	private BlogRepository blogRepository;
	private BlogServices blogService;
	
	//Get all blogs
	@GetMapping
	public List<Blog> getAllBlogs(){
		return blogRepository.findAll();
	}
	
    // Get blog by ID
    @GetMapping("/{id}")
    public Optional<Blog> getBlogById(@PathVariable Long id) {
		return blogService.getBlogById(id);
    }
    
    // Update a blog
//    @PutMapping("/{id}")
//    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blogDetails) {
//        return blogService.updateBlog(id, blogDetails);
//    }
//    
    
    @PutMapping("/blogs/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog updatedBlog) {
        return blogRepository.findById(id)
                .map(blog -> {
                    blog.setTitle(updatedBlog.getTitle());
                    blog.setContent(updatedBlog.getContent());
                    blog.setAuthor(updatedBlog.getAuthor());
                    return blogRepository.save(blog);
                })
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    
//    @PutMapping("/blogs/{id}")
//    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog updatedBlog) {
//        return blogRepository.findById(id)
//            .map(blog -> {
//                blog.setTitle(updatedBlog.getTitle());
//                blog.setContent(updatedBlog.getContent());
//                blog.setAuthor(updatedBlog.getAuthor());
//                Blog saved = blogRepository.save(blog);
//                return ResponseEntity.ok(saved);
//            })
//            .orElse(ResponseEntity.notFound().build());
//    }

    
 // Delete a blog
//    @DeleteMapping("/{id}")
//    public void deleteBlog(@PathVariable Long id) {
//        blogService.deleteBlog(id);
//    }
    
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

	
	//POST a new blog
	@PostMapping
	public Blog createBlog(@RequestBody Blog blog) {
		blog.setAuthor("Ganesh");
		return blogRepository.save(blog);
	}
	
}
