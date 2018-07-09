package com.example.vamshi.homwayprojectchallenge.Screens.MainScreen;

public interface ContractPresenterView {

    interface PresenterMainWork {

        void getPlaces(String s);

        void addFavourite(String key);

        void removeFavourite(String key);

        Boolean isFavourite(String key);

        void maStart();

        void maStop();

    }

}
