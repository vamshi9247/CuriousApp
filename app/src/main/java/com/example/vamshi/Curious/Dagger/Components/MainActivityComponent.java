package com.example.vamshi.Curious.Dagger.Components;

import com.example.vamshi.Curious.Dagger.MainActivityModule;
import com.example.vamshi.Curious.Dagger.Scopes.MainActivityScope;
import com.example.vamshi.Curious.Screens.MainScreen.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class ,dependencies = AppComponent.class)
@MainActivityScope
public interface MainActivityComponent {

     void Inject(MainActivity mainActivity);



}
