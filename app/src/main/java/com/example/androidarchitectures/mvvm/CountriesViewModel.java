package com.example.androidarchitectures.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidarchitectures.model.CountriesService;
import com.example.androidarchitectures.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesViewModel extends ViewModel {

    private final MutableLiveData<List<String>> countries = new MutableLiveData<>();
    private final MutableLiveData<Boolean> error = new MutableLiveData<>();

    private CountriesService service;

    public CountriesViewModel() {
        service = new CountriesService();
        fetchCountries();
    }

    public LiveData<List<String>> getCountries() {
        return countries;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    private void fetchCountries() {
        service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> value) {
                        List<String> countryNames = new ArrayList<>();
                        for (Country country : value) {
                            countryNames.add(country.name);
                        }
                        countries.setValue(countryNames);
                        error.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(true);
                    }
                });
    }

    public void onRefresh() {
        fetchCountries();
    }

}
