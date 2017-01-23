package br.fabMenuManual;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Boolean isFabOpen = false;

    private LinearLayout viewBackground;

    private FloatingActionButton fabMenu;

    private FloatingActionButton fabShare;
    private FloatingActionButton fabSettings;
    private FloatingActionButton fabSearch;

    private Animation viewBackground_open;

    private Animation fabShare_open;
    private Animation fabSettings_open;
    private Animation fabSearch_open;

    private Animation viewBackground_close;

    private Animation fabShare_close;
    private Animation fabSettings_close;
    private Animation fabSearch_close;

    private Animation rotate_forward;
    private Animation rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabMenu = (FloatingActionButton) findViewById(R.id.fabMenu);

        fabShare = (FloatingActionButton) findViewById(R.id.fabShare);
        fabSettings = (FloatingActionButton) findViewById(R.id.fabSettings);
        fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);

        viewBackground = (LinearLayout) findViewById(R.id.viewBackground);

        //animação para abrir a view
        viewBackground_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);

        //animação para abrir os fabs
        fabShare_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabSettings_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabSearch_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);

        //animação para fechar a view
        viewBackground_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        //animação para fechar os fabs
        fabShare_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabSettings_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabSearch_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        //animação para rotacionar os fabMenu
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fabMenu.setOnClickListener(this);

        fabShare.setOnClickListener(this);
        fabSettings.setOnClickListener(this);
        fabSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabMenu:
                animateFAB();
                break;
            case R.id.fabShare:
                Log.d("FAB", "Fab 1");
                break;
            case R.id.fabSettings:
                Log.d("FAB", "Fab 2");
                break;
            case R.id.fabSearch:
                Log.d("FAB", "Fab 3");
                break;
        }

    }

    //função que cria a animção ao clicar no fabMenu
    public void animateFAB() {

        if (isFabOpen) {

            //Rotaciona o fabMenu
            fabMenu.startAnimation(rotate_backward);

            //inicia a animação de fechamento do primeiro fab
            fabSearch.startAnimation(fabSearch_close);
            fabSearch_close.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //inicia a animação de fechamento do próximo fab após encerramento do fab anterior
                    fabSettings.startAnimation(fabSettings_close);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            fabSettings_close.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //inicia a animação de fechamento do fab e da view após encerramento do fab anterior
                    fabShare.startAnimation(fabShare_close);
                    viewBackground.startAnimation(viewBackground_close);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            isFabOpen = false;
            Log.d("FAB", "close");

        } else {

            //Rotaciona fabMenu
            fabMenu.startAnimation(rotate_forward);

            //inicia a animação de abertura do primeiro fab e da view
            viewBackground.startAnimation(viewBackground_open);
            fabShare.startAnimation(fabShare_open);

            fabShare_open.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //inicia a animação de abertura do próximo fab após encerramento do fab anterior
                    fabSettings.startAnimation(fabSettings_open);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            fabSettings_open.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //inicia a animação de abertura do próximo fab após encerramento do fab anterior
                    fabSearch.startAnimation(fabSearch_open);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            isFabOpen = true;
            Log.d("FAB", "open");

        }
    }
}
