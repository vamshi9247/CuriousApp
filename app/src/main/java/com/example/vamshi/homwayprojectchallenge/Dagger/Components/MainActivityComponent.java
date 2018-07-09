package com.example.vamshi.homwayprojectchallenge.Dagger.Components;

import com.example.vamshi.homwayprojectchallenge.Dagger.MainActivityModule;
import com.example.vamshi.homwayprojectchallenge.Dagger.Scopes.MainActivityScope;
import com.example.vamshi.homwayprojectchallenge.Screens.MainScreen.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class ,dependencies = AppComponent.class)
@MainActivityScope
public interface MainActivityComponent {

     void Inject(MainActivity mainActivity);



}
