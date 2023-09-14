package org.example.app.controller;

import org.example.app.entity.Post;
import org.example.app.model.PostModel;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.PostByIdView;
import retrofit2.Response;

import java.util.Optional;


public class PostByIdController {
    PostModel model;
    PostByIdView view;

    public PostByIdController(PostModel model, PostByIdView view) {
        this.model = model;
        this.view = view;
    }

    public void getPostById() {
        view.getOutput(readPostById(
                Integer.parseInt(view.getData())
        ));
        AppStarter.startApp();
    }

    private String readPostById(int id) {
        Optional<Response<Post>> optional = model.fetchPostById(id);
        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            Response<Post>response = optional.get();
            Post post = response.body();

            if (post != null) {
                return "User: id " + post.getId() + ", " + post.getTitle() +
                        " " + post.getBody() + ", " + post.getUserId();
            } else {
                return "Response data is null or invalid.";
            }
        }
    }
}
