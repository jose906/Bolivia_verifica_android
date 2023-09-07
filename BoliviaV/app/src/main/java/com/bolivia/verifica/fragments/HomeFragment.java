package com.bolivia.verifica.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bolivia.verifica.APIConnect;
import com.bolivia.verifica.Adapters.CategoriesAdapter;
import com.bolivia.verifica.Adapters.RecyclerAdapter;
import com.bolivia.verifica.Moduls.PaginationScrollListener;
import com.bolivia.verifica.R;
import com.bolivia.verifica.WpModel.Categories;
import com.bolivia.verifica.WpModel.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements CategoriesAdapter.MyCallBack{


    ArrayList<String> datos;
    RecyclerView recyclerView,categorieRecycler;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2;
    List<Posts> posts = new ArrayList<>();
    List<Categories> categories = new ArrayList<>();
    RecyclerAdapter adapter;
    CategoriesAdapter categoriesAdapter ;
    int page = 1;
    int cat=298;
    public  boolean isLoading = false;
    boolean lastpage;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.Home_recycler);
        //progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(posts);
        recyclerView.setAdapter(adapter);

        categorieRecycler = (RecyclerView) view.findViewById(R.id.categories);
        linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categorieRecycler.setLayoutManager(linearLayoutManager2);
        categoriesAdapter = new CategoriesAdapter(categories,this);
        categorieRecycler.setAdapter(categoriesAdapter);
        getposts(1);
        getCategories();
        onScroll();


    }
    private void onScroll(){
        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {

                int currentPage = ++page;
                Log.e("num",String.valueOf(page));

                isLoading = true;
                if(cat==298){
                    getposts(currentPage);
                }else {
                    getposts_categorie(cat, currentPage);
                }

            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


    }
    public void getCategories(){

        APIConnect.getRetrofit().getCaategories(50).enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if(response.isSuccessful()&&response.body()!=null){

                    categories.addAll(response.body());
                    categoriesAdapter.notifyDataSetChanged();
                    isLoading = false;

                }


            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

                isLoading=true;
            }
        });

    }

    public void getposts(int page){

      APIConnect.getRetrofit().getPosts(page).enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful() && response.body()!=null ){

                    Log.e("respuesta",String.valueOf(response.body().get(0).getFeatured_media()));
                    posts.addAll(response.body());
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });


    }

    public void getposts_categorie(int categorie, int pages){

        APIConnect.getRetrofit().PostByCategorie(categorie,pages).enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful() && response.body()!=null ){

                    Log.e("respuesta",String.valueOf(response.body().get(0).getFeatured_media()));
                    posts.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    isLoading= false;
                    page = page+1;
                    Log.e("pagi",String.valueOf(page));

                }else {

                    isLoading =true;
                }


            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void listenerMethod(int id) {
        posts.clear();

        if (id == 298) {

            cat = 298;
            getposts(1);


        }else {

            page = 1;
            cat = id;
            getposts_categorie(id,page);

        }
        Log.e("categorie",String.valueOf(id));

    }
}