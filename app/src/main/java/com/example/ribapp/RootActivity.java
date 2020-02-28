package com.example.ribapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.ribapp.root.RootBuilder;
import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;

public class RootActivity extends RibActivity {

    @Override
    protected ViewRouter<?, ?, ?> createRouter(ViewGroup parentViewGroup) {

        RootBuilder rootBuilder = new RootBuilder(new RootBuilder.ParentComponent(){

        });

        return rootBuilder.build(parentViewGroup);

    }

}
