package com.example.mvvmdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmdemo.databinding.ActivityMvvmLayoutBinding;
import com.example.mvvmdemo.model.TicViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_layout);


//        DataBindingUtil.setContentView(this, R.layout.activity_mvvm_layout).setVariable(BR.viewModel, new TicViewModel());

        ActivityMvvmLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_layout);
        binding.setViewModel(new TicViewModel());


    }


}