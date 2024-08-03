package com.example.springdoc.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.springdoc.entity.Category;
import com.example.springdoc.entity.Post;
import com.example.springdoc.entity.User;
import com.example.springdoc.exception.ResourceNotFoundException;
import com.example.springdoc.payload.PostDto;
import com.example.springdoc.payload.PostResponse;
import com.example.springdoc.repository.CategoryRepository;
import com.example.springdoc.repository.PostRepository;
import com.example.springdoc.repository.UserRepository;
import com.example.springdoc.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " Id ", userId));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "id ", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostImage("default.png");
		post.setPostCreatedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepository.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", "post id ", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage(postDto.getPostImage());
		Post updatedPost = this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id ", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		 Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "post Id ", postId));
		 
		 return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		Page<Post> pageOfPost = this.postRepository.findAll(pageable);
		List<Post> findAllPost = pageOfPost.getContent();
		List<PostDto> postDto = findAllPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pageOfPost.getNumber());
		postResponse.setPageSize(pageOfPost.getSize());
		postResponse.setTotalElemets(pageOfPost.getTotalElements());
		postResponse.setTotalPages(pageOfPost.getTotalPages());
		postResponse.setLastPage(pageOfPost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "category id ", categoryId));
		List<Post> listOfPost = this.postRepository.findByCategory(cat);
		List<PostDto> postDto = listOfPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "user Id", userId));
		List<Post> listOfPostByUser = this.postRepository.findByUser(user);
		List<PostDto> postDto = listOfPostByUser.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPostByKeyword(String keyword) {
		System.out.println(keyword+" this is keyword...");
		List<Post> posts = this.postRepository.findByPostTitle(keyword);
		System.out.println(posts.toString());
		List<PostDto> postDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		System.out.println("Here ");
		return postDto;
	}

	@Override
	public List<PostDto> searchPostsTitleWithKewordContains(String keyword) {
		List<Post> posts = this.postRepository.findByPostTitleContaining(keyword);	
		List<PostDto> postDto = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}