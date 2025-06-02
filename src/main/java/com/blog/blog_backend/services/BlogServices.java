package com.blog.blog_backend.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_backend.model.Blog;
import com.blog.blog_backend.repository.BlogRepository;

@Service
public class BlogServices {
	
	@Autowired
	private BlogRepository blogRepository;
	
	//get all blogs
	public List<Blog> getAllBlogs(){
		return blogRepository.findAll();
	}
	
	//get blog by ID
	public Optional<Blog> getBlogById(Long id){
		return blogRepository.findById(id);
	}
	
	//create new Blog
	public Blog createBlog(Blog blog) {
		return blogRepository.save(blog);
	}
	
	// Update blog
    public Blog updateBlog(Long id, Blog blogDetails) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));

        blog.setTitle(blogDetails.getTitle());
        blog.setContent(blogDetails.getContent());

        return blogRepository.save(blog);
    }

    // Delete blog
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
	
}
