package com.example.projetoa3.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrInfoUsuario;
import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Objetos.Usuarios;
import com.example.projetoa3.R;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class ActMenu extends ActApp {

    private DrawerLayout drDrawer;
    private NavigationView nvNavigationView;
    private NavController nvNavController;
    private AppBarConfiguration mAppBarConfiguration;
    private ImageView ivFotoPerfil;
    private TextView tvNomeUsuario;
    private final int FOTO_PERFIL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_menu);
        setSupportActionBar(findViewById(R.id.toolbar));
        configurarComponentes();
        preencherComponentes();
        configurarAcoes();

    }

    @Override
    protected void onResume(){
        super.onResume();
        preencherComponentes();
    }

    @Override
    public void configurarComponentes(){

        drDrawer = findViewById(R.id.drawer_layout);
        nvNavigationView = findViewById(R.id.nav_view);
        View headerView = nvNavigationView.getHeaderView(0);
        nvNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        //Constrói o appBar
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_produtos,
                R.id.nav_dados_usuario,
                R.id.nav_configuracoes
        ).setOpenableLayout(drDrawer).build();

        //Seta a navegação, o hamburguer
        NavigationUI.setupActionBarWithNavController(this, nvNavController, mAppBarConfiguration);

        ivFotoPerfil = headerView.findViewById(R.id.nav_menu_lateral_header_iv_foto_usuario);
        tvNomeUsuario = headerView.findViewById(R.id.nav_menu_lateral_header_tv_nome_usuario);
    }

    @Override
    public void preencherComponentes() {
        Usuarios usuario = CtrLogin.getUsuario();
        if(usuario.getFotoPerfil() != null)
            ivFotoPerfil.setImageBitmap(usuario.getFotoPerfil());

        tvNomeUsuario.setText(usuario.getNome());
    }

    @Override
    public void configurarAcoes() {

        nvNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Ação a ser executada quando um item do menu for clicado
                if(item.getItemId() == R.id.nav_sair){
                    CtrLogin.logoutUsuario();
                    ActLogin.iniciarActivity(ActMenu.this);
                    return true;
                }
                else {
                    NavigationUI.onNavDestinationSelected(item, nvNavController);
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imagem = null;

        switch(requestCode){
            case FOTO_PERFIL:
                if(data != null)
                    imagem = Biblioteca.getImagemBitMap(ActMenu.this, data);

                if(imagem == null){
                    Dialogo.informar(ActMenu.this, "Falha ao carregar imagem");
                    return;
                }
                imagem = Biblioteca.comprimirImagem(imagem, 64,64);

                Retorno retorno = CtrInfoUsuario.salvarDadosEdicaoFotoPerfil(Biblioteca.bitmapParaBase64(imagem));

                if(retorno.isHouveErro()){
                    Dialogo.informar(ActMenu.this, retorno.getMensagem());
                    return;
                }

                CtrLogin.updateFotoPerfil(Biblioteca.bitmapParaBase64(imagem));

                break;
        }
    }

    public void onBackPressed() {
        //03NavigationUI.onNavDestinationSelected(item, nvNavController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Aqui é a ação de clique no menu hambúrguer
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void iniciarActivity(Context contexto){
        Intent intent = new Intent(contexto, ActMenu.class);
        contexto.startActivity(intent);
    }
    
}