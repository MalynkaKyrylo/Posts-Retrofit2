package org.example.app.model;

import org.example.app.entity.Post;
import org.example.app.network.ApiClient;
import org.example.app.network.ApiService;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Optional;

public class PostModel {
    public Optional<Response<List<Post>>> fetchPosts() {
        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<List<Post>> call = service.getPosts();

        try {
            Response<List<Post>> response = call.execute();
            return Optional.of(response);
        } catch (Exception ex) {

            return Optional.empty();
        }
    }

    public Optional<Response<Post>> fetchPostById(int id) {
        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<Post> call = service.getPostById(id);
        try {
            Response<Post> response = call.execute();
            return Optional.of(response);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
