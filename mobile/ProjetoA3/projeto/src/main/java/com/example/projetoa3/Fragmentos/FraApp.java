package com.example.projetoa3.Fragmentos;

import android.view.View;
import androidx.fragment.app.Fragment;

public abstract class FraApp extends Fragment {

    public abstract void configurarComponentes(View view);

    public abstract void preencherComponentes();

    public abstract void configurarAcoes();

}
