package org.example.app.controller;

import org.example.app.entity.Post;
import org.example.app.model.PostModel;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.PostsView;
import retrofit2.Response;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PostsController {
    private final PostModel model;
    private final PostsView view;

    public PostsController(PostModel model, PostsView view) {
        this.model = model;
        this.view = view;
    }

    public void getPosts() {
        view.getOutput(readPosts());
        AppStarter.startApp();
    }

    private String readPosts() {
        Optional<Response<List<Post>>> optional = model.fetchPosts();

        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            Response<List<Post>> response = optional.get();

            if (response.isSuccessful()) {
                List<Post> posts = response.body();

                if (posts != null && !posts.isEmpty()) {

                    StringBuilder stringBuilder = new StringBuilder();
                    AtomicInteger cnt = new AtomicInteger(0);
                    String str;

                    for (Post post : posts) {
                        str = cnt.incrementAndGet() + ") User: id " + post.getId() + ", " +
                                post.getTitle() + " " + post.getBody() + ", " +
                                post.getUserId() + "\n";
                        stringBuilder.append(str);
                    }
                    return stringBuilder.toString();
                } else {
                    return "No posts available.";
                }
            } else {
                return "Error occurred while fetching posts.";
            }
        }
    }
}
