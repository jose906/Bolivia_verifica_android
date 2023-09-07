package com.bolivia.verifica.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bolivia.verifica.APIConnect;
import com.bolivia.verifica.R;
import com.bolivia.verifica.WpModel.Media;
import com.bolivia.verifica.WpModel.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RelativeLayout relativeLayout,relativeLayout1;
    TextView titulo;
    Button ant, sig;
    int mediaID;
    int status = 0;
    List<Posts> principal = new ArrayList<>();
    ImageSwitcher imageSwitcher;




    // TODO: Rename and change types of parameters


    public Home2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Home2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home2Fragment newInstance() {
        Home2Fragment fragment = new Home2Fragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        relativeLayout = (RelativeLayout) view.findViewById(R.id.corrusel);
        //relativeLayout1 = (RelativeLayout) view.findViewById(R.id.corrusel1);
        titulo = (TextView)view.findViewById(R.id.tituloHome);
        sig = (Button)view.findViewById(R.id.siguiente);
        ant = (Button)view.findViewById(R.id.anterior);
        imageSwitcher = (ImageSwitcher)view.findViewById(R.id.swicher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                ImageView imageView =  new ImageView(getContext());

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                return imageView;
            }
        });
        imageSwitcher.setInAnimation(getContext(), android.R.anim.fade_in);
        imageSwitcher.setInAnimation(getContext(), android.R.anim.fade_out);
        getallposts();

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(status==2){

                    titulo.setText(Html.fromHtml(principal.get(status).getTitle().getRendered()));
                   // Log.e("text",principal.get(status).getFeatured_media());
                    getImagePosts(principal.get(status).getFeatured_media());
                    status = 0;

                }else{

                    titulo.setText(Html.fromHtml(principal.get(status).getTitle().getRendered()));
                    getImagePosts(principal.get(status).getFeatured_media());
                    status = status +1;


                }

            }
        });
        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status==0){


                    titulo.setText(Html.fromHtml(principal.get(status).getTitle().getRendered()));
                    getImagePosts(principal.get(status).getFeatured_media());
                    status = 2;


                }else{

                    titulo.setText(Html.fromHtml(principal.get(status).getTitle().getRendered()));
                    getImagePosts(principal.get(status).getFeatured_media());
                    status = status -1;
                }


            }
        });


        if (getInternetConection()) {



        }else {

            AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
            alert.setMessage("Necesita iniciar sesion para acceder a esta funcion!").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                }
            });
            AlertDialog dialog=alert.create();
            dialog.setTitle("Usuario no encontrado!");
            dialog.show();
        }



        //relativeLayout.setBackground(animationDrawable);










    }
    public Boolean getInternetConection(){

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {
            connected = false;
        }

        return connected;
    }

    public void getallposts(){

        APIConnect.getRetrofit().getAperturas().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful() && response.body()!=null ){

                    principal.addAll(response.body());
                    titulo.setText(Html.fromHtml(principal.get(0).getTitle().getRendered()));
                    getImagePosts(response.body().get(0).getFeatured_media());
                    status = 1;



                }



            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });



    }

    public void getImagePosts(int id){

        APIConnect.getRetrofit().getImageUrl(id).enqueue(new Callback<Media>() {
            @Override
            public void onResponse(Call<Media> call, Response<Media> response) {
                if(response.isSuccessful() && response.body()!=null ){
                    Log.e("response",response.body().getMediaDetails().getSizes().getMedium().getSource_url());
                    Glide.with(relativeLayout.getContext()).load(response.body().getMediaDetails().getSizes().getFull().getSource_url()).into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {



                            imageSwitcher.setImageDrawable(resource);

                           // relativeLayout.setBackground(resource);


                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {


                        }
                    });

                }


            }

            @Override
            public void onFailure(Call<Media> call, Throwable t) {

                Log.e("resposnse","fallo");


            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }
}